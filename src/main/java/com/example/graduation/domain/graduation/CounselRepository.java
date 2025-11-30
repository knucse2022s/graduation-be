package com.example.graduation.domain.graduation;

import com.example.graduation.domain.student.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselRepository extends JpaRepository<Counsel, Long> {

    Optional<Counsel> findByStudent(Student student);
}
