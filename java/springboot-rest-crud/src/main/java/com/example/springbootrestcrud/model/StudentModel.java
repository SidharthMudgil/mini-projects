package com.example.springbootrestcrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // By adding this annotator getters and setters need not be created manually
@Entity // represents the class as Model Class and maps the class to database
@Table(name = "Students")
public class StudentModel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String firstName;
    private String lastName;
    private String email;
    public String address;
    private int age;
}
