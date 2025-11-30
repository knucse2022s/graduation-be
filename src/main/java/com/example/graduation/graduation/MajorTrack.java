package com.example.graduation.graduation;

import com.example.graduation.exception.BusinessException;
import com.example.graduation.exception.ErrorCode;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MajorTrack {
    ARTIFICIAL_INTELLIGENCE("인공지능", "ArtificialIntelligence.csv"),
    SIMHWA_COMPUTER("심화컴퓨터", "SimhwaComputer.csv"),
    GLOBAL_SOFTWARE("글로벌소프트웨어", "GlobalSoftware.csv");

    private final String displayName;
    private final String csvFileName;

    MajorTrack(String displayName, String csvFileName) {
        this.displayName = displayName;
        this.csvFileName = csvFileName;
    }

    public static MajorTrack from(String major) {
        return Arrays.stream(values())
                .filter(track -> track.displayName.equalsIgnoreCase(major)
                        || track.name().equalsIgnoreCase(major)
                        || track.csvFileName.equalsIgnoreCase(major))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_MAJOR));
    }
}
