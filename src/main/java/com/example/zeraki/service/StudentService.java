package com.example.zeraki.service;

import com.example.zeraki.models.Courses;
import com.example.zeraki.models.Institution;
import com.example.zeraki.models.Student;
import com.example.zeraki.web.dto.StudentRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface StudentService extends UserDetailsService {

    /**
     *
     * Students
     */
    Student save(StudentRegistrationDto registrationDto);

    Student Update(Student user);

    Student findUserById(Long id);

    Student findUser (String email);

    List<Student> getUsers();

    void deleteStudentById(Long id);










    /**
     *
     * COURSES
     *
     */

    Courses saveCourses(Courses courses);

    void  addCourseToStudent (String email,String roleName);

    List<Courses> getCourses();


    /**
     * Institution
     *
     */


    Institution saveInstitution(Institution courses);

    void  addInstitutionToStudent (String email,String institutionName);

    List<Institution> getInstitution();

    void  addCourseToInstitution(String name,String courseName);




    Page<Institution> findFilterByName(String name, int page, int size);






}



