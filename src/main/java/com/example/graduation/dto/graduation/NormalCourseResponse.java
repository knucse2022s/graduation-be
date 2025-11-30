package com.example.graduation.dto.graduation;

import com.example.graduation.domain.graduation.NormalCourse;

public record NormalCourseResponse(
        Long id,
        int credit,
        String status,
        String term,
        String major
) {

    public static NormalCourseResponse from(NormalCourse course) {
        return new NormalCourseResponse(
                course.getId(),
                course.getCredit(),
                course.getStatus(),
                course.getTerm(),
                course.getMajor()
        );
    }
}
