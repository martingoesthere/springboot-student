package com.example.demo.student;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student not found with id " + id);
    }
}
