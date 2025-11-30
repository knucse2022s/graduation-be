package com.example.graduation.graduation;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraduationRequirementTemplateProvider {

    private static final String MUST_CATEGORY = "MUST";

    private final CsvGraduationTemplateLoader csvLoader;

    public CourseTemplates courseTemplates(MajorTrack majorTrack) {
        List<CsvGraduationTemplateLoader.CourseTemplate> csvTemplates = csvLoader.loadTemplates(majorTrack);
        List<MustBeCourseTemplate> mustBeCourses = new ArrayList<>();
        List<NormalCourseTemplate> normalCourses = new ArrayList<>();

        for (CsvGraduationTemplateLoader.CourseTemplate template : csvTemplates) {
            if (MUST_CATEGORY.equalsIgnoreCase(template.category())) {
                mustBeCourses.add(new MustBeCourseTemplate(
                        template.courseName(),
                        template.credit(),
                        template.status(),
                        template.majorType()
                ));
            } else {
                normalCourses.add(new NormalCourseTemplate(
                        template.credit(),
                        template.status(),
                        template.term(),
                        template.majorType()
                ));
            }
        }

        return new CourseTemplates(mustBeCourses, normalCourses);
    }

    public CounselTemplate counsel() {
        return new CounselTemplate(1, 1);
    }

    public List<AdditionalRequirementTemplate> additionalRequirements() {
        return List.of(
                new AdditionalRequirementTemplate("English", false),
                new AdditionalRequirementTemplate("SDGs", false),
                new AdditionalRequirementTemplate("GraduationThesisAndCapstone", false)
        );
    }

    public record CourseTemplates(
            List<MustBeCourseTemplate> mustBeCourses,
            List<NormalCourseTemplate> normalCourses
    ) {
    }

    public record MustBeCourseTemplate(String majorName, int credit, String status, String major) {
    }

    public record NormalCourseTemplate(int credit, String status, String term, String major) {
    }

    public record CounselTemplate(int templateId, int times) {
    }

    public record AdditionalRequirementTemplate(String type, boolean checked) {
    }
}
