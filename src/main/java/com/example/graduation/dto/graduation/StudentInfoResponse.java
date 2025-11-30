package com.example.graduation.dto.graduation;

import com.example.graduation.domain.student.Student;

public record StudentInfoResponse(String studentId, String name, String major) {

    public static StudentInfoResponse from(Student student) {
        return new StudentInfoResponse(student.getStudentId(), student.getName(), student.getMajor());
    }
}
