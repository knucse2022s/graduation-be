package com.example.graduation.controller;

import com.example.graduation.dto.graduation.GraduationResponse;
import com.example.graduation.service.GraduationRequirementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graduation")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class GraduationController {

    private final GraduationRequirementService graduationRequirementService;

    @GetMapping("/my")
    public ResponseEntity<GraduationResponse> getMyGraduationRequirements() {
        GraduationResponse response = graduationRequirementService.getMyGraduationRequirements();
        return ResponseEntity.ok(response);
    }
}
