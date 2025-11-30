package com.example.graduation.domain.graduation;

import com.example.graduation.domain.student.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalCourseRepository extends JpaRepository<NormalCourse, Long> {

    List<NormalCourse> findByStudent(Student student);
}
