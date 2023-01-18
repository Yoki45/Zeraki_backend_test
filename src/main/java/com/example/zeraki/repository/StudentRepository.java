package com.example.zeraki.repository;

import com.example.zeraki.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByEmail(String email);


    void deleteStudentById(Long id);




}
