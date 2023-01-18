package com.example.zeraki.web;

import com.example.zeraki.models.Courses;
import com.example.zeraki.models.Institution;
import com.example.zeraki.models.Student;
import com.example.zeraki.service.CoursesService;
import com.example.zeraki.service.InstitutionService;
import com.example.zeraki.service.StudentService;
import com.example.zeraki.web.dto.StudentRegistrationDto;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/")
public class MainController {

    private StudentService userService;
    private InstitutionService institutionService;


    private CoursesService coursesService;




    private Student user;

    public MainController(StudentService userService,InstitutionService institutionService,CoursesService coursesService) {
        super();
        this.userService = userService;
        this.institutionService = institutionService;
        this.coursesService = coursesService;

    }


    /**
     *
     *
     * Student registration API,allows one to perform all CRUD functionalities, Create,read,update and delete
     */


    @PostMapping("auth/studentregister")
    public Student register(@RequestBody StudentRegistrationDto userRegistrationDto) {
        return userService.save(userRegistrationDto);
    }

    @PutMapping({"/updateStudentDetails"})
    public Student updateProfile(@RequestBody Student user) {
        return userService.Update(user);

    }


    @GetMapping("/allStudents")
    public ResponseEntity<List<Student>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping({"/student/{id}"})
    public ResponseEntity<Student> getUserById(@PathVariable("id") Long id) {
        Student newUser = this.userService.findUserById(id);
        return new ResponseEntity(newUser, HttpStatus.OK);
    }


    @DeleteMapping({"/deleteStudent/{id}"})
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        this.userService.deleteStudentById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * COURSE API,allows one to add a course and assign a course to a registered student.
     */

    @PostMapping("/course/save")
    public ResponseEntity<Courses> saveCourse(@RequestBody Courses courses) {
        return ResponseEntity.ok().body(userService.saveCourses(courses));
    }

    @PostMapping("/assignCourse")
    public ResponseEntity<Courses> AssignRole(@RequestBody addCourseToStudentForm form) {
        userService.addCourseToStudent(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/courses")
    public ResponseEntity<List<Courses>> getRoles() {
        return ResponseEntity.ok().body(userService.getCourses());
    }


    @DeleteMapping({"/deleteCourse/{id}"})
    public ResponseEntity<?> deleteCourses(@PathVariable("id") Long id) {
        this.coursesService.deleteCourse(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping({"/updateCourse"})
    public ResponseEntity<Courses> updateCourses(@RequestBody Courses courses) {
        Courses updateCourse = this.coursesService.updateCourse(courses);
        return new ResponseEntity(updateCourse, HttpStatus.OK);
    }





    /**
     * Institution
     */

    @PostMapping("/institute/save")
    public ResponseEntity<Institution> saveInstitution(@RequestBody Institution institute) {
        return ResponseEntity.ok().body(userService.saveInstitution(institute));
    }

    @PostMapping("/assignInstitute")
    public ResponseEntity<Institution> AssignInstitute(@RequestBody addInstituteToStudentForm form) {
        userService.addInstitutionToStudent(form.getUserName(), form.getInstituteName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/institute")
    public ResponseEntity<List<Institution>> getInstitute() {
        return ResponseEntity.ok().body(userService.getInstitution());
    }



    @DeleteMapping({"/deleteInstitute/{id}"})
    public ResponseEntity<?> deleteInstitute(@PathVariable("id") Long id) {
        this.institutionService.deleteInstitution(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping({"/updateInstitution"})
    public ResponseEntity<Institution> updateInstitution(@RequestBody Institution institution) {
        Institution updateInstitution = this.institutionService.updateInstitution(institution);
        return new ResponseEntity(updateInstitution, HttpStatus.OK);
    }

    @GetMapping({"/allInstitutions"})
    public ResponseEntity<Object> getAlLInstitution() {
        List<Institution> cbo = this.institutionService.findAlInstitution();
        return new ResponseEntity(cbo, HttpStatus.OK);
    }


    @GetMapping("/filterInstitution")
    public Page<Institution> fetchCustomersWithPageInterface(@RequestParam(defaultValue = "KU") String name,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return userService.findFilterByName(name, page, size);


    }


    @PostMapping("/assignCourseToInstitute")
    public ResponseEntity<Institution> AssignCourseToInstitute(@RequestBody addCourseToInstitutionForm form) {
        userService.addCourseToInstitution(form.getName(), form.getCourseName());
        return ResponseEntity.ok().build();
    }

}

@Data
class addCourseToStudentForm {
    String userName;
    String roleName;

}

@Data
class addInstituteToStudentForm {
    String userName;
    String instituteName;

}

@Data
class addCourseToInstitutionForm {
    String name;
    String courseName;

}








