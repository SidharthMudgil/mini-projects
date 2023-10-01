package main.java.com.example.springbootems.service;

import com.example.springbootems.model.Employee;
import com.example.springbootems.repository.EMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EMSService {

    List<Employee> getAllEmployees();

    void saveStudent(Employee employee);
}

