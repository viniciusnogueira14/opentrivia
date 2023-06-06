package com.alianza.exercise.opentrivia.service;

import com.alianza.exercise.opentrivia.client.resources.TriviaQuestionResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExportService {

    @Value("${export.file.field-delimiter}")
    private String fieldDelimiter;

    @Value("${export.file.list-delimiter}")
    private String listDelimiter;

    @Value("${export.file.path}")
    private String filePath;

    @Value("${export.file.name}")
    private String defaultFileName;

    public void exportToCsvFile(List<TriviaQuestionResource> questions, String[] fileName) {
        List<String> content = new ArrayList<>();
        content.add(this.getHeader(new TriviaQuestionResource()));
        content.addAll(this.getContent(questions));

        String checkedFileName = this.defaultFileName;
        if (Optional.ofNullable(fileName).isPresent() && Objects.nonNull(fileName[0])) {
            checkedFileName = fileName[0];
        }

        writeToFile(content, checkedFileName);
    }

    public void writeToFile(List<String> content, String fileName) {
        Path path = Paths.get(filePath + fileName);

        try {
            Files.write(path, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getContent(List<TriviaQuestionResource> questions) {
        if (Objects.nonNull(questions)) {
            return questions.stream()
                    .map(question -> question.toStringValuesOnly(fieldDelimiter, listDelimiter))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public String getHeader(TriviaQuestionResource question) {
        if (Objects.nonNull(question)) {
            Field[] header = question.getClass().getDeclaredFields();
            return Stream.of(header).map(Field::getName)
                    .collect(Collectors.joining(fieldDelimiter));
        } else {
            return "";
        }
    }
}
