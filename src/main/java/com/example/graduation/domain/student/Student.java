package com.example.graduation.domain.student;

import com.example.graduation.domain.graduation.AdditionalRequirement;
import com.example.graduation.domain.graduation.Counsel;
import com.example.graduation.domain.graduation.MustBeCourse;
import com.example.graduation.domain.graduation.NormalCourse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "students")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MustBeCourse> mustBeCourses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<NormalCourse> normalCourses = new ArrayList<>();

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Counsel counsel;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AdditionalRequirement> additionalRequirements = new ArrayList<>();

    private Student(String studentId, String name, String major, String password) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.password = password;
    }

    public static Student create(String studentId, String name, String major, String password) {
        return new Student(studentId, name, major, password);
    }

    public void addMustBeCourse(MustBeCourse course) {
        mustBeCourses.add(course);
        course.assignStudent(this);
    }

    public void addNormalCourse(NormalCourse course) {
        normalCourses.add(course);
        course.assignStudent(this);
    }

    public void addCounsel(Counsel counsel) {
        this.counsel = counsel;
        counsel.assignStudent(this);
    }

    public void addAdditionalRequirement(AdditionalRequirement requirement) {
        additionalRequirements.add(requirement);
        requirement.assignStudent(this);
    }
}
