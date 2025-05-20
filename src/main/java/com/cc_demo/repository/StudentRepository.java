package com.cc_demo.repository;

import com.cc_demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMajorIgnoreCase(String major);
    List<Student> findByYear(int year);
    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);
}