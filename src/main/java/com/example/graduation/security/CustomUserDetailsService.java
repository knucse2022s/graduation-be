package com.example.graduation.security;

import com.example.graduation.domain.student.Student;
import com.example.graduation.domain.student.StudentRepository;
import com.example.graduation.exception.BusinessException;
import com.example.graduation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentId(username)
                .orElseThrow(() -> new UsernameNotFoundException("학생 정보를 찾을 수 없습니다."));
        return StudentPrincipal.from(student);
    }
}
