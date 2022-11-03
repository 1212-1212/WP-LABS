package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.exceptions.NoStudentSelectedException;
import mk.ukim.finki.wp.wp_lab.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);

    Student findStudentByUsername(String username) throws NoStudentSelectedException;
}
