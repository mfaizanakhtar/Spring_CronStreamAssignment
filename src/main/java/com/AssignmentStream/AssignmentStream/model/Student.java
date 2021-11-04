package com.AssignmentStream.AssignmentStream.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="STUDENT")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private int id;

    @Column(name="ROLL_NUMBER")
    private String rollNumber;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="ACTIVE_IND")
    private boolean activeStatus;

    @OneToMany(mappedBy="student")
    private List<BookIssued> bookIssued;
}
