package com.alianza.exercise.opentrivia.service;

import com.alianza.exercise.opentrivia.client.OpenTriviaClient;
import com.alianza.exercise.opentrivia.client.resources.TriviaCategoryResource;
import com.alianza.exercise.opentrivia.client.resources.TriviaCategoryResponse;
import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResource;
import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OpenTriviaServiceTest {

    @Mock
    private OpenTriviaClient client;

    @Autowired
    @InjectMocks
    private OpenTriviaService service;


    @Test
    void loadCategories_Success() {
        TriviaCategoryResponse responseMock = new TriviaCategoryResponse();
        responseMock.setTrivia_categories(new ArrayList<>());
        responseMock.getTrivia_categories().add(new TriviaCategoryResource(1, "Test 01"));
        responseMock.getTrivia_categories().add(new TriviaCategoryResource(2, "Test 02"));
        responseMock.getTrivia_categories().add(new TriviaCategoryResource(3, "Test 03"));
        responseMock.getTrivia_categories().add(new TriviaCategoryResource(4, "Test 04"));

        when(client.loadTriviaCategories())
                .thenReturn(new ResponseEntity<>(responseMock, HttpStatus.OK));

        Map<String, Integer> categoriesMap = this.service.loadTriviaCategories();

        assertEquals(responseMock.getTrivia_categories().size(), categoriesMap.size());
        for (TriviaCategoryResource category : responseMock.getTrivia_categories()) {
            assertEquals(category.getId(), categoriesMap.get(category.getName()));
        }
    }

    @Test
    void loadCategories_Status500() {
        when(client.loadTriviaCategories())
                .thenReturn(new ResponseEntity<>(new TriviaCategoryResponse(), HttpStatus.INTERNAL_SERVER_ERROR));

        Map<String, Integer> categories = this.service.loadTriviaCategories();

        assertNotNull(categories);
        assertTrue(categories.isEmpty());
    }

    @Test
    void loadCategories_NullResponseBody() {
        when(client.loadTriviaCategories())
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        Map<String, Integer> categories = this.service.loadTriviaCategories();

        assertNotNull(categories);
        assertTrue(categories.isEmpty());
    }

    @Test
    void loadCategories_EmptyCategoryList() {
        TriviaCategoryResponse responseMock = new TriviaCategoryResponse();
        responseMock.setTrivia_categories(new ArrayList<>());

        when(client.loadTriviaCategories())
                .thenReturn(new ResponseEntity<>(responseMock, HttpStatus.OK));

        Map<String, Integer> categories = this.service.loadTriviaCategories();

        assertNotNull(categories);
        assertTrue(categories.isEmpty());
    }

    @Test
    void getOpenTriviaQuestions_Success() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 10);

        when(client.getQuestions(any()))
                .thenReturn(new ResponseEntity<>(buildMockResponse(), HttpStatus.OK));

        TriviaQuestionResponse response = service.getOpenTriviaQuestions(params);

        assertNotNull(response);
        assertNotNull(response.getResults());
        assertEquals(0, response.getResponse_code());
        assertEquals(5, response.getResults().size());
    }

    @Test
    void getOpenTriviaQuestions_Status500() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 10);

        when(client.getQuestions(any()))
                .thenReturn(new ResponseEntity<>(new TriviaQuestionResponse(), HttpStatus.INTERNAL_SERVER_ERROR));

        TriviaQuestionResponse response = service.getOpenTriviaQuestions(params);

        assertNull(response);
    }

    @Test
    void getOpenTriviaQuestions_NullResponseBody() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 10);

        when(client.getQuestions(any()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        TriviaQuestionResponse response = service.getOpenTriviaQuestions(params);

        assertNull(response);
    }

    @Test
    void getOpenTriviaQuestions_EmptyQuestionList() {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 10);

        TriviaQuestionResponse responseMock = new TriviaQuestionResponse();
        responseMock.setResponse_code(0);
        responseMock.setResults(new ArrayList<>());

        when(client.getQuestions(any()))
                .thenReturn(new ResponseEntity<>(responseMock, HttpStatus.OK));

        TriviaQuestionResponse response = service.getOpenTriviaQuestions(params);

        assertNotNull(response);
        assertNotNull(response.getResults());
        assertEquals(0, response.getResponse_code());
        assertTrue(response.getResults().isEmpty());
    }

    private TriviaQuestionResponse buildMockResponse() {
        List<TriviaQuestionResource> questionsMock = new ArrayList<>();

        TriviaQuestionResource question01 = new TriviaQuestionResource();
        question01.setCategory("Testing Category 01");
        question01.setQuestion("Testing Question 01");
        question01.setType("Testing Type 01");
        question01.setDifficulty("Testing Difficulty 01");
        question01.setCorrect_answer("Testing CorrectAnswer 01");
        question01.setIncorrect_answers(Arrays.asList("Testing WrongAnswer 011", "Testing WrongAnswer 012"));

        TriviaQuestionResource question02 = new TriviaQuestionResource();
        question02.setCategory("Testing Category 02");
        question02.setQuestion("Testing Question 02");
        question02.setType("Testing Type 02");
        question02.setDifficulty("Testing Difficulty 02");
        question02.setCorrect_answer("Testing CorrectAnswer 02");
        question02.setIncorrect_answers(Arrays.asList("Testing WrongAnswer 021", "Testing WrongAnswer 022"));

        TriviaQuestionResource question03 = new TriviaQuestionResource();
        question03.setCategory("Testing Category 03");
        question03.setQuestion("Testing Question 03");
        question03.setType("Testing Type 03");
        question03.setDifficulty("Testing Difficulty 03");
        question03.setCorrect_answer("Testing CorrectAnswer 03");
        question03.setIncorrect_answers(Arrays.asList("Testing WrongAnswer 031", "Testing WrongAnswer 032"));

        TriviaQuestionResource question04 = new TriviaQuestionResource();
        question04.setCategory("Testing Category 04");
        question04.setQuestion("Testing Question 04");
        question04.setType("Testing Type 04");
        question04.setDifficulty("Testing Difficulty 04");
        question04.setCorrect_answer("Testing CorrectAnswer 04");
        question04.setIncorrect_answers(Arrays.asList("Testing WrongAnswer 041", "Testing WrongAnswer 042"));

        TriviaQuestionResource question05 = new TriviaQuestionResource();
        question05.setCategory("Testing Category 05");
        question05.setQuestion("Testing Question 05");
        question05.setType("Testing Type 05");
        question05.setDifficulty("Testing Difficulty 05");
        question05.setCorrect_answer("Testing CorrectAnswer 05");
        question05.setIncorrect_answers(Arrays.asList("Testing WrongAnswer 051", "Testing WrongAnswer 052"));

        questionsMock.add(question01);
        questionsMock.add(question02);
        questionsMock.add(question03);
        questionsMock.add(question04);
        questionsMock.add(question05);

        TriviaQuestionResponse response = new TriviaQuestionResponse();
        response.setResponse_code(0);
        response.setResults(questionsMock);

        return response;
    }


}
