package com.student.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "regno")
    private int regno;

    @Column(name = "name")
    private String name;

    @Column(name = "cgpa")
    private double cgpa;

    public Student() {
    }

    public Student(int regno, String name, double cgpa) {
        this.regno = regno;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getRegno() {
        return regno;
    }

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}