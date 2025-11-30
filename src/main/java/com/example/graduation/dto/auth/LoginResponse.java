package com.example.graduation.dto.auth;

public record LoginResponse(
        String accessToken,
        String message,
        String studentId,
        String name,
        String major
) {

    public static LoginResponse success(String accessToken, String studentId, String name, String major) {
        return new LoginResponse(accessToken, "로그인 성공", studentId, name, major);
    }
}
