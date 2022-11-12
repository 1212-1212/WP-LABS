package mk.ukim.finki.wp.wp_lab.repository;

import mk.ukim.finki.wp.wp_lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.wp_lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {



    public List<Student> findAllStudents()
    {
        return DataHolder.students;
    }
    public List<Student> findAllByNameOrSurname(String text)
    {
        if (text == null || text.isEmpty())
            throw new IllegalArgumentException();
        return DataHolder.students
                .stream()
                .filter(student -> student.getName().contains(text) || student.getSurname().contains(text))
                .collect(Collectors.toList());
    }
    public Optional<Student> findStudentByUsername(String username)
    {
        if(username == null || username.isEmpty())
            throw new IllegalArgumentException();

        return DataHolder.students
                .stream()
                .filter(student -> student.getUsername().equals(username))
                .findFirst();
    }

    public Student save(String username, String password, String name, String surname)
    {
        if(username == null || username.isEmpty())
            throw new IllegalArgumentException();
        if(password == null || password.isEmpty())
            throw new IllegalArgumentException();
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException();
        if(surname == null || surname.isEmpty())
            throw new IllegalArgumentException();
        Student student = new Student(username, password, name, surname);
        DataHolder.students.add(student);
        return student;
    }

}
