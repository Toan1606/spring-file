package com.eric.file_management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", length = 255)
    private String studentName;

    @Column(name = "email", length = 255, unique = true)
    @Email
    private String email;

    @Column(name = "first_name", length = 255)
    @NotEmpty
    private String firstName;

    @Column(name = "last_name", length = 255)
    @NotEmpty
    private String lastName;

    @Column(name = "middle_name", length = 255)
    private String middleName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class classes;

    private byte[] images;
}
