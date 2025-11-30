package com.example.graduation.security;

import com.example.graduation.domain.student.Student;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class StudentPrincipal implements UserDetails {

    private final Long id;
    private final String studentId;
    private final String password;

    private StudentPrincipal(Long id, String studentId, String password) {
        this.id = id;
        this.studentId = studentId;
        this.password = password;
    }

    public static StudentPrincipal from(Student student) {
        return new StudentPrincipal(student.getId(), student.getStudentId(), student.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return studentId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
