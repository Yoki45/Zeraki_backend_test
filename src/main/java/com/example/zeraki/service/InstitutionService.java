package com.example.zeraki.service;


import com.example.zeraki.exception.InstituteAlreadyAssignedtoStudentException;
import com.example.zeraki.models.Institution;
import com.example.zeraki.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;




    public void deleteInstitution(Long id) {
        try {
            this.institutionRepository.deleteInstitutionById(id);
            institutionRepository.flush();
        }catch (DataIntegrityViolationException exception){

            throw new InstituteAlreadyAssignedtoStudentException("Cannot Delete Institution: [" + id + "] as it is already assigned to a Student");



        }
    }

    public List<Institution> findAlInstitution() {
        return this.institutionRepository.findAll(Sort.by("id"));
    }

    public Institution updateInstitution(Institution institution) {
        return (Institution) this.institutionRepository.save(institution);
    }




}
