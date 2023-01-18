package com.example.zeraki.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name =  "institution", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
public class Institution {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "institution_courses",
            joinColumns = @JoinColumn(
                    name = "institution_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "courses_id", referencedColumnName = "id"))

    private Collection<Courses> courses = new ArrayList<>();

    public Institution() {

    }



    public Institution(String name, Collection<Courses> courses
    ) {
        super();
        this.name = name;
        this.courses = courses;


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Courses> courses) {
        this.courses = courses;
    }

}
