package com.alianza.exercise.opentrivia.client.resources;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TriviaQuestionResource {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public String toStringValuesOnly(String fieldDelimiter, String listDelimiter) {
        return category + fieldDelimiter
                + type + fieldDelimiter
                + difficulty + fieldDelimiter
                + question + fieldDelimiter
                + correct_answer + fieldDelimiter
                + String.join(listDelimiter, incorrect_answers);
    }

    @Override
    public String toString() {
        return "TriviaQuestionResource{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", incorrect_answers=" + incorrect_answers +
                '}';
    }
}
