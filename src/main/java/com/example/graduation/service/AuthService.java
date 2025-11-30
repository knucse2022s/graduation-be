package com.example.graduation.service;

import com.example.graduation.domain.student.Student;
import com.example.graduation.domain.student.StudentRepository;
import com.example.graduation.dto.auth.LoginRequest;
import com.example.graduation.dto.auth.LoginResponse;
import com.example.graduation.dto.auth.SignupRequest;
import com.example.graduation.exception.BusinessException;
import com.example.graduation.exception.ErrorCode;
import com.example.graduation.graduation.GraduationRequirementInitializer;
import com.example.graduation.graduation.MajorTrack;
import com.example.graduation.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final GraduationRequirementInitializer graduationRequirementInitializer;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signup(SignupRequest request) {
        if (studentRepository.existsByStudentId(request.studentId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_STUDENT);
        }

        String majorName = request.major().trim();

        Student student = Student.create(
                request.studentId(),
                request.name(),
                majorName,
                passwordEncoder.encode(request.password())
        );

        MajorTrack majorTrack = MajorTrack.from(majorName);
        graduationRequirementInitializer.initializeRequirements(student, majorTrack);
        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Student student = studentRepository.findByStudentId(request.studentId())
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), student.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String token = tokenProvider.createAccessToken(student.getStudentId());
        return LoginResponse.success(token, student.getStudentId(), student.getName(), student.getMajor());
    }
}
