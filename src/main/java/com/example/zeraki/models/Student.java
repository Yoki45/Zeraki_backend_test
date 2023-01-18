package com.example.zeraki.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name =  "students", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@NoArgsConstructor


public class Student {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    @Column(nullable = true)

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_institution",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "institution_id", referencedColumnName = "id"))

    private Collection<Institution> institutions = new ArrayList<>();



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "courses_id", referencedColumnName = "id"))
    private Collection<Courses> courses = new ArrayList<>();






    public Student(String firstName, String lastName, String email, String password,
                   Collection<Institution> institutions
            ,Collection<Courses> courses   ) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.institutions= institutions;
        this.courses =courses;
        //this.roles = roles;


    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Collection<Institution> institutions) {
        this.institutions = institutions;
    }

    public Collection<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Courses> courses) {
        this.courses = courses;
    }

 /* public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }*/
}
