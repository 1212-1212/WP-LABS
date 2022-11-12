package mk.ukim.finki.wp.wp_lab.service.impl;

import mk.ukim.finki.wp.wp_lab.exceptions.NoStudentSelectedException;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.repository.StudentRepository;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        Student student = new Student(username, password, name, surname);
        studentRepository.save(username, password, name, surname);
        return student;
    }

    @Override
    public Optional<Student> findStudentByUsername(String username) throws NoStudentSelectedException {
        if(username == null)
            throw new NoStudentSelectedException();
        return studentRepository.findStudentByUsername(username);
    }
}
