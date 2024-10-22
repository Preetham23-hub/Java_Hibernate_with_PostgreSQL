package com.hibernate.dbtest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PhoneNumber")
@Setter
@Getter
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int phoneNumber;
    private int countryCode;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
