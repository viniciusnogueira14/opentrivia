package com.alianza.exercise.opentrivia.client.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TriviaQuestionResponse {

    private Integer response_code;
    private List<TriviaQuestionResource> results;
}
