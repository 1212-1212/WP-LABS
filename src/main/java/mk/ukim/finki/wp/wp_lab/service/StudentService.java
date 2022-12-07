package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.exceptions.NoStudentSelectedException;
import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);

    Optional<Student> findStudentByUsername(String username) throws NoStudentSelectedException;


}
