package com.student.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student.entity.Student;

@Repository
@Transactional
public class StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Add student
    public void save(Student student) {
        entityManager.persist(student);
    }

    // Display all students
    public List<Student> getAllStudents() {
        return entityManager
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    // Search student
    public Student getStudent(int regno) {
        return entityManager.find(Student.class, regno);
    }

    // Update student
    public void update(Student student) {
        entityManager.merge(student);
    }

    // Delete student
    public void delete(int regno) {
        Student student = entityManager.find(Student.class, regno);

        if (student != null) {
            entityManager.remove(student);
        }
    }
}