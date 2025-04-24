package com.cc_demo.controller;

import com.cc_demo.entity.Employee;
import com.cc_demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cc-demo")
public class Controller {

    EmployeeService employeeService;

    Controller(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployee()
    {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long id)
    {
        Employee employee = employeeService.getEmployee(id);
        if(employee!= null)
        {
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<String>("No employee found with id: "+id,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        Employee createdEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("department") String department) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        if (!employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No employees found in that department
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<String>("Employee with id: " + id + " has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("No employee found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
