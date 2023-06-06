package com.alianza.exercise.opentrivia.business;

import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResponse;
import com.alianza.exercise.opentrivia.service.ExportService;
import com.alianza.exercise.opentrivia.service.OpenTriviaService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class ExerciseBusiness {

    private final OpenTriviaService openTriviaService;
    private final ExportService exportService;

    public ExerciseBusiness(OpenTriviaService openTriviaService, ExportService exportService) {
        this.openTriviaService = openTriviaService;
        this.exportService = exportService;
    }

    public void getQuestionsAndExportToCsv(Map<String, Object> questionsParams, String... fileName) {
        TriviaQuestionResponse questions = openTriviaService.getOpenTriviaQuestions(questionsParams);
        if (validateOpenTriviaQuestionsResponse(questions)) {
            this.exportService.exportToCsvFile(questions.getResults(), fileName);
        }
    }

    private boolean validateOpenTriviaQuestionsResponse(TriviaQuestionResponse response) {
        return response != null && response.getResponse_code() == 0
                && !response.getResults().isEmpty();
    }
}
