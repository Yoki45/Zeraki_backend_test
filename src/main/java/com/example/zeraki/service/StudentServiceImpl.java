package com.example.zeraki.service;

import com.example.zeraki.exception.InstitutionAlreadyExistException;
import com.example.zeraki.models.Courses;
import com.example.zeraki.models.Institution;
import com.example.zeraki.models.Student;
import com.example.zeraki.repository.CoursesRepository;
import com.example.zeraki.repository.InstitutionRepository;
import com.example.zeraki.repository.StudentRepository;
import com.example.zeraki.web.dto.StudentRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j

public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired

    private CoursesRepository coursesRepository;

    @Autowired
    private InstitutionRepository institutionRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Student save(StudentRegistrationDto registrationDto) {
        Student student = new Student(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList()
                ,Arrays.asList());

        return studentRepository.save(student);



    }

    @Override
    public Student Update(Student user) {
        return studentRepository.save(user);
    }

    @Override
    public Student findUserById(Long id) {
        return null;
    }

    public Optional<Student> find(Long id){
        return studentRepository.findById(id);
    }


    @Override
    public Student findUser(String email) {
        return studentRepository.findByEmail(email);

    }

    @Override
    public List<Student> getUsers() {
        return studentRepository.findAll(Sort.by("institutions"));

    }

    @Override
    public void deleteStudentById(Long id) {
        this.studentRepository.deleteStudentById(id);

    }


    @Override
    public Courses saveCourses(Courses courses) {
        return coursesRepository.save(courses);
    }

    @Override
    public void addCourseToStudent(String email, String roleName) {
        Student student = studentRepository.findByEmail(email);
        Courses courses = coursesRepository.findByName(roleName);
        student.getCourses().add(courses);


    }

    @Override
    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Institution saveInstitution(Institution courses) {
        try {
            return institutionRepository.save(courses);
        } catch(DataIntegrityViolationException exception){

            throw new InstitutionAlreadyExistException("This Institution already exists");





        }
    }

    @Override
    public void addInstitutionToStudent(String email, String institutionName) {
        Student student = studentRepository.findByEmail(email);
        Institution institution = institutionRepository.findByName(institutionName);

        student.getInstitutions().add(institution);


    }

    @Override
    public List<Institution> getInstitution() {
        return institutionRepository.findAll();
    }

    @Override
    public void addCourseToInstitution(String name, String courseName) {
        Institution institution = institutionRepository.findByName(name);
        Courses courses = coursesRepository.findByName(courseName);


        institution.getCourses().add(courses);

    }

    @Override
    public Page<Institution> findFilterByName(String name, int page, int size) {
        Pageable page1 = PageRequest.of(page, size);
        return institutionRepository.findFilterByname(name,page1);




    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }



}
