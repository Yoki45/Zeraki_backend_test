package com.example.zeraki.repository;

import com.example.zeraki.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long> {

    Courses findByName(String name);

    void deleteCoursesById(Long id);


}
