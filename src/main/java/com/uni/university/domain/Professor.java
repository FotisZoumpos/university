package com.uni.university.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professor")
public class Professor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "firstname", nullable = false)
  private String firstName;

  @Column(name = "lastname", nullable = false)
  private String lastName;

  // TODO some users may not have an email or phone, this is a nullable field
  @Column(name = "email", nullable = false)
  private String email;

  // TODO some users may not have an email or phone, this is a nullable field
  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "birthday", nullable = false)
  private LocalDate birthday;

  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Builder.Default
  @OneToMany(mappedBy = "professor")
  private Set<Course> courses = new HashSet<>();

}
