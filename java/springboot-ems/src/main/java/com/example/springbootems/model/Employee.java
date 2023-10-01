package main.java.com.example.springbootems.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    public String empName;
    public String empEmail;
    public String empPhone;
    public String empDept;
    public Double empSalary;

    public Employee(String empName, String empEmail, String empPhone, String empDept, Double empSalary) {
        this.empName = empName;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empDept = empDept;
        this.empSalary = empSalary;
    }

    public Employee() {

    }
}
