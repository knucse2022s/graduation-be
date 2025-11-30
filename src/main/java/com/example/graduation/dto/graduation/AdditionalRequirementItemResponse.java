package com.example.graduation.dto.graduation;

import com.example.graduation.domain.graduation.AdditionalRequirement;

public record AdditionalRequirementItemResponse(Long id, boolean check) {

    public static AdditionalRequirementItemResponse from(AdditionalRequirement requirement) {
        if (requirement == null) {
            return new AdditionalRequirementItemResponse(null, false);
        }
        return new AdditionalRequirementItemResponse(requirement.getId(), requirement.isChecked());
    }
}
