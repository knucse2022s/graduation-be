package com.example.graduation.service;

import com.example.graduation.domain.student.Student;
import com.example.graduation.domain.student.StudentRepository;
import com.example.graduation.domain.graduation.AdditionalRequirement;
import com.example.graduation.domain.graduation.RequirementType;
import com.example.graduation.dto.graduation.AdditionalRequirementItemResponse;
import com.example.graduation.dto.graduation.AdditionalRequirementsResponse;
import com.example.graduation.dto.graduation.CounselResponse;
import com.example.graduation.dto.graduation.GraduationResponse;
import com.example.graduation.dto.graduation.MustBeCourseResponse;
import com.example.graduation.dto.graduation.NormalCourseResponse;
import com.example.graduation.dto.graduation.StudentInfoResponse;
import com.example.graduation.exception.BusinessException;
import com.example.graduation.exception.ErrorCode;
import com.example.graduation.security.SecurityUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GraduationRequirementService {

    private final StudentRepository studentRepository;

    public GraduationResponse getMyGraduationRequirements() {
        String studentId = SecurityUtils.getCurrentStudentId()
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHENTICATED));

        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        List<MustBeCourseResponse> mustBeCourses = student.getMustBeCourses()
                .stream()
                .map(MustBeCourseResponse::from)
                .collect(Collectors.toList());

        List<NormalCourseResponse> normalCourses = student.getNormalCourses()
                .stream()
                .map(NormalCourseResponse::from)
                .collect(Collectors.toList());

        CounselResponse counsel = CounselResponse.from(student.getCounsel());

        AdditionalRequirementsResponse additionalRequirements = mapAdditionalRequirements(student.getAdditionalRequirements());

        StudentInfoResponse studentInfo = StudentInfoResponse.from(student);

        return new GraduationResponse(studentInfo, mustBeCourses, normalCourses, counsel, additionalRequirements);
    }

    private AdditionalRequirementsResponse mapAdditionalRequirements(List<AdditionalRequirement> requirements) {
        AdditionalRequirement english = findRequirement(requirements, RequirementType.ENGLISH);
        AdditionalRequirement sdgs = findRequirement(requirements, RequirementType.SDGS);
        AdditionalRequirement thesis = findRequirement(requirements, RequirementType.GRADUATION_THESIS_AND_CAPSTONE);

        return new AdditionalRequirementsResponse(
                AdditionalRequirementItemResponse.from(english),
                AdditionalRequirementItemResponse.from(sdgs),
                AdditionalRequirementItemResponse.from(thesis)
        );
    }

    private AdditionalRequirement findRequirement(List<AdditionalRequirement> requirements, RequirementType type) {
        return requirements.stream()
                .filter(req -> req.getType() == type)
                .findFirst()
                .orElse(null);
    }
}
