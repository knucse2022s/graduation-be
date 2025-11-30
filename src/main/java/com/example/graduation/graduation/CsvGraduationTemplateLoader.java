package com.example.graduation.graduation;

import com.example.graduation.exception.BusinessException;
import com.example.graduation.exception.ErrorCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class CsvGraduationTemplateLoader {

    private static final String CSV_BASE_PATH = "classpath:";

    private final ResourceLoader resourceLoader;

    public List<CourseTemplate> loadTemplates(MajorTrack majorTrack) {
        Resource resource = resourceLoader.getResource(CSV_BASE_PATH + majorTrack.getCsvFileName());
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            List<CourseTemplate> templates = new ArrayList<>();
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                if (!StringUtils.hasText(line)) {
                    continue;
                }
                templates.add(parseLine(line));
            }
            return templates;
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.TEMPLATE_NOT_FOUND);
        }
    }

    private CourseTemplate parseLine(String line) {
        String[] tokens = line.split(",", -1);
        if (tokens.length < 7) {
            throw new BusinessException(ErrorCode.TEMPLATE_NOT_FOUND);
        }
        String category = tokens[0].trim();
        String term = tokens[1].trim();
        String courseName = tokens[2].trim();
        int credit = parseInteger(tokens[3].trim());
        String majorType = tokens[4].trim();
        String status = tokens[5].trim();
        String checker = tokens[6].trim();
        return new CourseTemplate(category, term, courseName, credit, majorType, status, checker);
    }

    private int parseInteger(String value) {
        if (!StringUtils.hasText(value)) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new BusinessException(ErrorCode.TEMPLATE_NOT_FOUND);
        }
    }

    public record CourseTemplate(
            String category,
            String term,
            String courseName,
            int credit,
            String majorType,
            String status,
            String checker
    ) {
    }
}
