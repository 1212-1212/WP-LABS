package mk.ukim.finki.wp.wp_lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course implements Comparable<Course>{
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;

    private Teacher teacher;

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;

    }

    public Course(String name, String description, Teacher teacher) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }


    @Override
    public int compareTo(Course o) {
        return this.name.compareTo(o.name);
    }
}

