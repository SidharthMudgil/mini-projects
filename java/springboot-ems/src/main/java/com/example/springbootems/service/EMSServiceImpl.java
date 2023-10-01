package main.java.com.example.springbootems.service;

import com.example.springbootems.model.Employee;
import com.example.springbootems.repository.EMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMSServiceImpl implements EMSService {

    @Autowired
    private EMSRepository emsRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return emsRepository.findAll();
    }

    @Override
    public void saveStudent(Employee employee) {
        emsRepository.save(employee);
    }
}

