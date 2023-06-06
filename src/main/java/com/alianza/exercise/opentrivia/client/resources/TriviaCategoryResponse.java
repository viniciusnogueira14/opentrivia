package com.alianza.exercise.opentrivia.client.resources;

import lombok.Data;

import java.util.List;

@Data
public class TriviaCategoryResponse {

    private List<TriviaCategoryResource> trivia_categories;
}
