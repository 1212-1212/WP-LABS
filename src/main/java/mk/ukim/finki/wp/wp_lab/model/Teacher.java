package mk.ukim.finki.wp.wp_lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Convert(converter = TeacherFullNameConverter.class)
    private TeacherFullName teacherFullName;


    private String username;

    public Teacher() {
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;


    public Teacher(TeacherFullName teacherFullName, String username, LocalDate dateOfEmployment) {
        this.teacherFullName=teacherFullName;
        this.username = username;
        this.dateOfEmployment = dateOfEmployment;

    }

//    public Teacher(String name, String surname, String username, LocalDate dateOfEmployment) {
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.dateOfEmployment = dateOfEmployment;
//    }
}
