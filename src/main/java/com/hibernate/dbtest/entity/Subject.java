package com.hibernate.dbtest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Subject")
@Setter
@Getter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String author;

    @ManyToMany(mappedBy = "subjects",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
}
