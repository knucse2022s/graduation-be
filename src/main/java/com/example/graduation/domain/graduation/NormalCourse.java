package com.example.graduation.domain.graduation;

import com.example.graduation.domain.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "normal_courses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NormalCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int credit;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private NormalCourse(int credit, String status, String term, String major) {
        this.credit = credit;
        this.status = status;
        this.term = term;
        this.major = major;
    }

    public static NormalCourse create(int credit, String status, String term, String major) {
        return new NormalCourse(credit, status, term, major);
    }

    public void assignStudent(Student student) {
        this.student = student;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
