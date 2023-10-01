package com.example.springbootrestcrud.service;

import com.example.springbootrestcrud.model.StudentModel;
import com.example.springbootrestcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void createStudent(StudentModel studentModel) {
        studentRepository.save(studentModel); // save() method (CRUD operation) lies in JPA repository
    }

    public void addStudents(List<StudentModel> studentList) {
        studentRepository.saveAll(studentList);
    }

    public List<StudentModel> getStudents() {
        return studentRepository.findAll();
    }

    public StudentModel getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public boolean updateStudent(int id, StudentModel studentModel) {
        boolean idFound = false;
        StudentModel oldData = null;
        Optional<StudentModel> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            idFound = true;
            oldData = optional.get();
            if (studentModel.getFirstName() != null) {
                oldData.setFirstName(studentModel.getFirstName());
            }
            if (studentModel.getFirstName() != null) {
                oldData.setFirstName(studentModel.getFirstName());
            }
            if (studentModel.getFirstName() != null) {
                oldData.setFirstName(studentModel.getFirstName());
            }
            if (studentModel.getFirstName() != null) {
                oldData.setFirstName(studentModel.getFirstName());
            }
            if (studentModel.getFirstName() != null) {
                oldData.setFirstName(studentModel.getFirstName());
            }

            studentRepository.save(oldData);
        }

        return idFound;
    }
}
