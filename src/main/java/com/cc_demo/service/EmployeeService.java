package com.cc_demo.service;

import com.cc_demo.entity.Employee;
import com.cc_demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department); // Assuming this method is implemented in the repository
    }
        public void deleteEmployee (Long id){
            employeeRepository.deleteById(id);

    }
}
