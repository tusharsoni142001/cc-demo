package com.cc_demo.controller;

import com.cc_demo.entity.Employee;
import com.cc_demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
