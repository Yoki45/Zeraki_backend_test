package com.example.zeraki.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="courses")
@Data
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length=45)
    private String name;

    public Courses() {

    }

    public Courses(String name) {
        super();
        this.name = name;
        this.id=id;
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

}
