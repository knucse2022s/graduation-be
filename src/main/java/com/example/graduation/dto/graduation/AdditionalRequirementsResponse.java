package com.example.graduation.dto.graduation;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AdditionalRequirementsResponse(
        @JsonProperty("English") AdditionalRequirementItemResponse english,
        @JsonProperty("SDGs") AdditionalRequirementItemResponse sdgs,
        @JsonProperty("GraduationThesisAndCapstone") AdditionalRequirementItemResponse graduationThesisAndCapstone
) {
}
