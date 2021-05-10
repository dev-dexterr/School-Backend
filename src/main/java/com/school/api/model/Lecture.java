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
@Table(name = "lecture")
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @JsonBackReference
    @OneToMany(mappedBy = "lecture",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;
}
