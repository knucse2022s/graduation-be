package com.example.graduation.dto.graduation;

import java.util.List;

public record GraduationResponse(
        StudentInfoResponse student,
        List<MustBeCourseResponse> mustBeCourses,
        List<NormalCourseResponse> normalCourses,
        CounselResponse counsel,
        AdditionalRequirementsResponse additionalRequirements
) {
}
