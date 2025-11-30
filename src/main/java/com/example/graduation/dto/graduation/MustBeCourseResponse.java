package com.example.graduation.dto.graduation;

import com.example.graduation.domain.graduation.MustBeCourse;

public record MustBeCourseResponse(
        Long id,
        String majorName,
        int credit,
        String status,
        String major
) {

    public static MustBeCourseResponse from(MustBeCourse course) {
        return new MustBeCourseResponse(
                course.getId(),
                course.getMajorName(),
                course.getCredit(),
                course.getStatus(),
                course.getMajor()
        );
    }
}
