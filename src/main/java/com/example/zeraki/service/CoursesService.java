package com.example.zeraki.service;

import com.example.zeraki.exception.CourseAlreadyAssignedException;
import com.example.zeraki.models.Courses;
import com.example.zeraki.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;


/*    public void deleteCourse(Long id) {
        this.coursesRepository.deleteCoursesById(id);


    }*/


    public void deleteCourse(Long id) {
        try {
            this.coursesRepository.deleteCoursesById(id); // call Spring's Data JPA repository method deleteById
            coursesRepository.flush();
       } catch (DataIntegrityViolationException exception) {
            throw new CourseAlreadyAssignedException("Cannot Delete course: [" + id + "] as it is already assigned to an Institution and a Student");
        }
    }



    public Courses updateCourse(Courses courses) {
        return (Courses) this.coursesRepository.save(courses);
    }













}
