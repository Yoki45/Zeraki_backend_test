package com.example.zeraki.repository;

import com.example.zeraki.models.Courses;
import com.example.zeraki.models.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository  extends JpaRepository<Institution,Long> {

    Institution findByName(String name);

    void deleteInstitutionById(Long id);

    @Query
    Page<Institution> findFilterByname(String name, Pageable pageable);




}
