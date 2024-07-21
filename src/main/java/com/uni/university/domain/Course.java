package com.uni.university.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
@ToString
@Builder
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(name = "course_student",
                joinColumns = @JoinColumn (name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
