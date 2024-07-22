package com.uni.university.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "professor")
@ToString
@Builder
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "firstname",nullable = false)
    private String firstName;

    @Column(name = "lastname",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "phone",nullable = false,unique = true)
    private String phone;

    @Column(name = "birthday",nullable = false)
    private LocalDate birthday;

    @Column(name = "gender",nullable = false)
    private Gender gender;


    @OneToMany(mappedBy = "professor",fetch = FetchType.EAGER)
    private List<Course> courses;

    public Professor(String firstName, String lastName, String email, String phone, LocalDate birthday, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
    }
}
