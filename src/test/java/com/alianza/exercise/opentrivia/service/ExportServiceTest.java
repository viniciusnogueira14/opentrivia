package com.alianza.exercise.opentrivia.service;

import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExportServiceTest {

    @Autowired
    private ExportService service;

    @Test
    void getHeader_Success() {
        String expectedHeader = "category,type,difficulty,question,correct_answer,incorrect_answers";
        String header = service.getHeader(new TriviaQuestionResource());

        assertNotNull(header);
        assertEquals(expectedHeader, header);
    }

    @Test
    void getHeader_NullParam() {
        String header = service.getHeader(null);

        assertNotNull(header);
        assertTrue(header.isEmpty());
    }

    @Test
    void getContent_Success() {
        List<String> expectedContent = new ArrayList<>();
        expectedContent.add("Testing Category 01,Testing Type 01,Testing Difficulty 01,Testing Question 01,Testing CorrectAnswer 01,Testing WrongAnswer 011-Testing WrongAnswer 012");
        expectedContent.add("Testing Category 02,Testing Type 02,Testing Difficulty 02,Testing Question 02,Testing CorrectAnswer 02,Testing WrongAnswer 021-Testing WrongAnswer 022");
        expectedContent.add("Testing Category 03,Testing Type 03,Testing Difficulty 03,Testing Question 03,Testing CorrectAnswer 03,Testing WrongAnswer 031-Testing WrongAnswer 032");
        expectedContent.add("Testing Category 04,Testing Type 04,Testing Difficulty 04,Testing Question 04,Testing CorrectAnswer 04,Testing WrongAnswer 041-Testing WrongAnswer 042");
        expectedContent.add("Testing Category 05,Testing Type 05,Testing Difficulty 05,Testing Question 05,Testing CorrectAnswer 05,Testing WrongAnswer 051-Testing WrongAnswer 052");

        List<String> content = service.getContent(buildQuestionsMock());

        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertEquals(expectedContent, content);
    }

    @Test
    void getContent_EmptyList() {
        List<String> content = service.getContent(new ArrayList<>());

        assertNotNull(content);
        assertTrue(content.isEmpty());
    }

    @Test
    void getContent_NullQuestionList() {
        List<String> content = service.getContent(null);

        assertNotNull(content);
        assertTrue(content.isEmpty());
    }

    private List<TriviaQuestionResource> buildQuestionsMock() {
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

        return questionsMock;
    }

}
