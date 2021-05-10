package com.school.api.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long age;
    private String mobile;
    private String email;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @JsonBackReference
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fee> fees;
}
