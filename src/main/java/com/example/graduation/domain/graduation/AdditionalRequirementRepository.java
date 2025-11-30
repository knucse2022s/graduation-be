package com.example.graduation.domain.graduation;

import com.example.graduation.domain.student.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalRequirementRepository extends JpaRepository<AdditionalRequirement, Long> {

    List<AdditionalRequirement> findByStudent(Student student);
}
