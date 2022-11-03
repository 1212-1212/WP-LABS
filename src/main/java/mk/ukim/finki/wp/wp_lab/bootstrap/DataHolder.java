package mk.ukim.finki.wp.wp_lab.bootstrap;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component

public class DataHolder {
    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("petar.petrov", "123", "Petar", "Petrov"));
        students.add(new Student("mile.milev", "123", "Mile", "Milev"));
        students.add(new Student("gjorgji.gjorgjiev", "123", "Gjorgji", "Gjorgjiev"));
        students.add(new Student("stefan.stefanovski", "123", "Stefan", "Stefanovski"));
        students.add(new Student("filip.filipovski", "123", "Filip", "Filipovski"));
        courses.add(new Course(1L, "Web programming", "Web programming", createListOfStudents()));
        courses.add(new Course(2L, "Operating Systems", "Operating Systems", createListOfStudents()));
        courses.add(new Course(3L, "Electronic and Mobile Commerce", "Electronic and Mobile Commerce", createListOfStudents()));
        courses.add(new Course(4L, "Computer Networks", "Computer Networks", createListOfStudents()));
        courses.add(new Course(5L, "Algorithms and Data structures", "Algorithms and Data structures", createListOfStudents()));
    }

    public List<Student> createListOfStudents() {
        Random random = new Random();
        int n = random.nextInt(5) + 1;
        return IntStream.range(0, n)
                .map(index -> random.nextInt(n))
                .mapToObj(index -> students.get(index))
                .distinct()
                .collect(Collectors.toList());
    }

}
