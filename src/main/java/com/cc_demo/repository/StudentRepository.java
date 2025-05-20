package com.cc_demo.repository;

import com.cc_demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMajor(String major);
    List<Student> findByYear(int year);
}