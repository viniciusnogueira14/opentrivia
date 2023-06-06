package com.alianza.exercise.opentrivia.challenge;

import com.alianza.exercise.opentrivia.business.ExerciseBusiness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ChallengeExecutionTest {

    @Autowired
    private ExerciseBusiness business;

    @Test
    void firstChallenge() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 10);
        params.put("difficulty", "medium");
        params.put("type", "multiple");
        params.put("category", "Science: Computers");

        String fileName = "TenMediumQuestions.csv";

        business.getQuestionsAndExportToCsv(params, fileName);
    }

    @Test
    void secondChallenge() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 12);
        params.put("difficulty", "easy");
        params.put("type", "multiple");
        params.put("category", "Sports");
        params.put("encode", "url3986");

        String fileName = "TwelveEasyQuestions.csv";

        business.getQuestionsAndExportToCsv(params, fileName);
    }
}
