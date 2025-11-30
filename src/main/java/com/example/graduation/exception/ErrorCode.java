package com.example.graduation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    DUPLICATE_STUDENT(HttpStatus.CONFLICT, "이미 가입된 학번입니다."),
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "학생 정보를 찾을 수 없습니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "학번 또는 비밀번호가 올바르지 않습니다."),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    INVALID_MAJOR(HttpStatus.BAD_REQUEST, "지원하지 않는 전공입니다."),
    TEMPLATE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "전공별 졸업 요건 템플릿을 불러오지 못했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
