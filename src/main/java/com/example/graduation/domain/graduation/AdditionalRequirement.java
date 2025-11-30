package com.example.graduation.domain.graduation;

import com.example.graduation.domain.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "additional_requirements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdditionalRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequirementType type;

    @Column(nullable = false)
    private boolean checked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private AdditionalRequirement(RequirementType type, boolean checked) {
        this.type = type;
        this.checked = checked;
    }

    public static AdditionalRequirement create(RequirementType type, boolean checked) {
        return new AdditionalRequirement(type, checked);
    }

    public void assignStudent(Student student) {
        this.student = student;
    }

    public void updateChecked(boolean checked) {
        this.checked = checked;
    }
}
