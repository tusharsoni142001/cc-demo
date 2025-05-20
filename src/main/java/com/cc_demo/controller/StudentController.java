package com.cc_demo.controller;

import com.cc_demo.entity.Student;
import com.cc_demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cc-demo/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No student found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Add new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return new ResponseEntity<Student>(createdStudent, HttpStatus.CREATED);
    }

    // Get students by major
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable("major") String major) {
        List<Student> students = studentService.getStudentsByMajor(major);
        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get students by year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getStudentsByYear(@PathVariable("year") int year) {
        List<Student> students = studentService.getStudentsByYear(year);
        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>("Student with id: " + id + " has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No student found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}