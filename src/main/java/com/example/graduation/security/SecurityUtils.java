package com.example.graduation.security;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<String> getCurrentStudentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof StudentPrincipal studentPrincipal) {
            return Optional.of(studentPrincipal.getStudentId());
        }

        if (principal instanceof String principalName) {
            return Optional.of(principalName);
        }

        return Optional.empty();
    }
}
