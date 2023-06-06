package com.alianza.exercise.opentrivia.service;

import com.alianza.exercise.opentrivia.client.OpenTriviaClient;
import com.alianza.exercise.opentrivia.client.resources.TriviaCategoryResource;
import com.alianza.exercise.opentrivia.client.resources.TriviaCategoryResponse;
import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OpenTriviaService {

    private OpenTriviaClient openTriviaClient;

    public OpenTriviaService(OpenTriviaClient openTriviaClient) {
        this.openTriviaClient = openTriviaClient;
    }

    public Map<String, Integer> loadTriviaCategories() {
        ResponseEntity<TriviaCategoryResponse> categoriesResponse =
                this.openTriviaClient.loadTriviaCategories();

        if (isValidResponse(categoriesResponse)) {
            return categoriesResponse.getBody().getTrivia_categories()
                    .stream().collect(Collectors.toUnmodifiableMap(
                            TriviaCategoryResource::getName, TriviaCategoryResource::getId));
        } else {
            // TODO Implement a Custom Exception for this scenario.
            return new HashMap<>();
        }
    }

    public TriviaQuestionResponse getOpenTriviaQuestions(Map<String, Object> params) {
        if (params.get("category") instanceof String) {
            this.convertCategoryValueIfNeeded(params);
        }

        ResponseEntity<TriviaQuestionResponse> response = openTriviaClient.getQuestions(params);

        if (isValidResponse(response)) {
            return response.getBody();
        } else {
            // TODO Implement a Custom Exception for this scenario.
            return null;
        }
    }

    public void convertCategoryValueIfNeeded(Map<String, Object> params) {
        String category = String.valueOf(params.get("category"));
        Map<String, Integer> categories = this.loadTriviaCategories();

        if (Objects.nonNull(categories.get(category))) {
            params.put("category", categories.get(category));
        }
    }

    private boolean isValidResponse(ResponseEntity<?> responseEntity) {
        return responseEntity.getStatusCode().equals(HttpStatus.OK)
                && Objects.nonNull(responseEntity.getBody());
    }

}
