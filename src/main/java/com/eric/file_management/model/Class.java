package com.eric.file_management.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", length = 255, nullable = false)
    private String className;

    @Column(name = "semester", length = 255, nullable = false)
    private String semester;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Set<Student> students;
}
