package com.alianza.exercise.opentrivia.integrated;

import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResponse;
import com.alianza.exercise.opentrivia.service.OpenTriviaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ExerciseIntegratedTest {

    @Autowired
    private OpenTriviaService openTriviaService;


    @Test
    public void testIntegrated() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 10);
        params.put("category", "Science: Computers");
        params.put("difficulty", "medium");
        params.put("type", "multiple");

        TriviaQuestionResponse response = this.openTriviaService.getOpenTriviaQuestions(params);

        assertNotNull(response);

    }

}
