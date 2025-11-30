package com.example.graduation.dto.graduation;

import com.example.graduation.domain.graduation.Counsel;

public record CounselResponse(Long id, int times) {

    public static CounselResponse from(Counsel counsel) {
        if (counsel == null) {
            return new CounselResponse(null, 0);
        }
        return new CounselResponse(counsel.getId(), counsel.getTimes());
    }
}
