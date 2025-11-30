package com.example.graduation.graduation;

import com.example.graduation.domain.graduation.AdditionalRequirement;
import com.example.graduation.domain.graduation.Counsel;
import com.example.graduation.domain.graduation.MustBeCourse;
import com.example.graduation.domain.graduation.NormalCourse;
import com.example.graduation.domain.graduation.RequirementType;
import com.example.graduation.domain.student.Student;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraduationRequirementInitializer {

    private static final String COMPLETED_STATUS = "기이수";
    private static final String PLANNED_STATUS = "이수예정";

    private final GraduationRequirementTemplateProvider templateProvider;

    public void initializeRequirements(Student student, MajorTrack majorTrack) {
        GraduationRequirementTemplateProvider.CourseTemplates templates =
                templateProvider.courseTemplates(majorTrack);
        createMustBeCourses(student, templates.mustBeCourses());
        createNormalCourses(student, templates.normalCourses());
        createCounsel(student);
        createAdditionalRequirements(student);
    }

    private void createMustBeCourses(Student student,
                                     List<GraduationRequirementTemplateProvider.MustBeCourseTemplate> templates) {
        for (GraduationRequirementTemplateProvider.MustBeCourseTemplate template : templates) {
            String status = transformStatus(template.status());
            MustBeCourse course = MustBeCourse.create(
                    template.majorName(),
                    template.credit(),
                    status,
                    template.major()
            );
            student.addMustBeCourse(course);
        }
    }

    private void createNormalCourses(Student student,
                                     List<GraduationRequirementTemplateProvider.NormalCourseTemplate> templates) {
        for (GraduationRequirementTemplateProvider.NormalCourseTemplate template : templates) {
            String status = transformStatus(template.status());
            NormalCourse course = NormalCourse.create(
                    template.credit(),
                    status,
                    template.term(),
                    template.major()
            );
            student.addNormalCourse(course);
        }
    }

    private void createCounsel(Student student) {
        GraduationRequirementTemplateProvider.CounselTemplate counselTemplate = templateProvider.counsel();
        Counsel counsel = Counsel.create(counselTemplate.times());
        student.addCounsel(counsel);
    }

    private void createAdditionalRequirements(Student student) {
        List<GraduationRequirementTemplateProvider.AdditionalRequirementTemplate> templates =
                templateProvider.additionalRequirements();
        for (GraduationRequirementTemplateProvider.AdditionalRequirementTemplate template : templates) {
            RequirementType type = mapType(template.type());
            AdditionalRequirement requirement = AdditionalRequirement.create(type, template.checked());
            student.addAdditionalRequirement(requirement);
        }
    }

    private String transformStatus(String status) {
        return COMPLETED_STATUS.equals(status) ? PLANNED_STATUS : status;
    }

    private RequirementType mapType(String typeName) {
        return switch (typeName) {
            case "English" -> RequirementType.ENGLISH;
            case "SDGs" -> RequirementType.SDGS;
            case "GraduationThesisAndCapstone" -> RequirementType.GRADUATION_THESIS_AND_CAPSTONE;
            default -> throw new IllegalArgumentException("지원하지 않는 졸업요건 타입: " + typeName);
        };
    }
}
