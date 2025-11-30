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
@Table(name = "must_be_courses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MustBeCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String majorName;

    private int credit;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private MustBeCourse(String majorName, int credit, String status, String major) {
        this.majorName = majorName;
        this.credit = credit;
        this.status = status;
        this.major = major;
    }

    public static MustBeCourse create(String majorName, int credit, String status, String major) {
        return new MustBeCourse(majorName, credit, status, major);
    }

    public void assignStudent(Student student) {
        this.student = student;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
