package com.example.springbootrestcrud.repository;
import com.example.springbootrestcrud.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

}

