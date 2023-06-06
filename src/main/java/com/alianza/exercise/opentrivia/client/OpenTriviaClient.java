package com.alianza.exercise.opentrivia.client;

import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResponse;
import com.alianza.exercise.opentrivia.client.resources.TriviaCategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class OpenTriviaClient {

    @Value(value = "${open-trivia.protocol}")
    private String protocol;

    @Value(value = "${open-trivia.host}")
    private String host;

    @Value(value = "${open-trivia.path.api}")
    private String apiPath;

    @Value(value = "${open-trivia.path.category}")
    private String categoryPath;

    private final RestTemplate restTemplate;

    public OpenTriviaClient() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<TriviaCategoryResponse> loadTriviaCategories() {
        String url = this.protocol + "://" + this.host + "/" + this.categoryPath;
        return this.restTemplate.getForEntity(url, TriviaCategoryResponse.class);
    }

    public ResponseEntity<TriviaQuestionResponse> getQuestions(Map<String, Object> params) {
        String url = this.protocol + "://" + this.host + "/" + this.apiPath + buildUriVariables(params);
        return this.restTemplate.getForEntity(url, TriviaQuestionResponse.class);
    }

    private String buildUriVariables(Map<String, Object> params) {
        if (params.isEmpty()) {
            return "";
        } else {
            StringBuilder uriVariables = new StringBuilder().append("?");
            for (Map.Entry<String, Object> param : params.entrySet()) {
                uriVariables.append(param.getKey())
                        .append("=")
                        .append(param.getValue())
                        .append("&");
            }

            uriVariables.deleteCharAt(uriVariables.length() - 1);
            return uriVariables.toString();
        }
    }
}
