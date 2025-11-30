package com.example.graduation.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank(message = "학번은 필수입니다.") String studentId,
        @NotBlank(message = "이름은 필수입니다.") String name,
        @NotBlank(message = "전공은 필수입니다.") String major,
        @NotBlank(message = "비밀번호는 필수입니다.") String password
) {
}
