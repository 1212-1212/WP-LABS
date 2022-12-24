package mk.ukim.finki.wp.wp_lab.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table()
public class Course implements Comparable<Course>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long courseId;

    private String name;
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;
    @Enumerated(EnumType.STRING)
    private Type type;



    @ManyToOne
    private Teacher teacher;

    public Course(String name, String description, List<Student> students, Type type, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.type = type;
        this.teacher = teacher;
    }

    public Course(Long courseId, String name, String description, Type type, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.students = new ArrayList<>();
        this.description = description;
        this.type = type;
        this.teacher = teacher;
    }

    public Course(Long courseId, String name, String description, List<Student> students, Type type, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = students;
        this.type = type;
        this.teacher = teacher;
    }

    public Course(String name, String description, Type type, Teacher teacher) {
        this.name = name;
        this.students = new ArrayList<>();
        this.description = description;
        this.type = type;
        this.teacher = teacher;
    }

    public Course() {

    }

    @Override
    public int compareTo(Course o) {
        return this.name.compareTo(o.name);
    }
}

