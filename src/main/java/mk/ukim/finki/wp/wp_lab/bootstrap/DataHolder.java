package mk.ukim.finki.wp.wp_lab.bootstrap;

import mk.ukim.finki.wp.wp_lab.model.*;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.GradeService;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import mk.ukim.finki.wp.wp_lab.service.TeacherService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component

public class DataHolder {

    private final CourseService courseService;
    private final StudentService studentService;

    private final TeacherService teacherService;
    private final GradeService gradeService;

    public DataHolder(CourseService courseService, StudentService studentService, TeacherService teacherService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }

    @PostConstruct
    public void init() {

        studentService.save("petar.petrov", "123", "Petar", "Petrov");
        studentService.save("mile.milev", "123", "Mile", "Milev");
        studentService.save("gjorgji.gjorgjiev", "123", "Gjorgji", "Gjorgjiev");
        studentService.save("stefan.stefanovski", "123", "Stefan", "Stefanovski");
        studentService.save("filip.filipovski", "123", "Filip", "Filipovski");



        Teacher teacher = new Teacher(new TeacherFullName("Sasho", "Gramatikov"), "sasho.gramatikov", LocalDate.now());
        Course course = new Course("Web programming", "WP", createListOfStudents(),Type.WINTER, teacher);
        teacherService.save(teacher);
        courseService.save(course);

        teacher = new Teacher(new TeacherFullName("Igor", "Mishkovski"), "igor.mishkovski", LocalDate.now());
        course = new Course("Operating Systems", "OS", createListOfStudents(), Type.SUMMER,teacher);
        teacherService.save(teacher);
        courseService.save(course);

        teacher = new Teacher(new TeacherFullName("Goran", "Velinov"), "goran.velinov", LocalDate.now());
        course = new Course("Database Management Systems", "DBMS", createListOfStudents(), Type.WINTER,teacher);
        teacherService.save(teacher);
        courseService.save(course);

        teacher = new Teacher(new TeacherFullName("Sonja", "Filipovska"), "sonja.filipovska", LocalDate.now());
        course = new Course("Computer Networks and Security", "CNS", createListOfStudents(), Type.WINTER,teacher);
        teacherService.save(teacher);
        courseService.save(course);

        teacher = new Teacher(new TeacherFullName("Marija", "Mihova"), "marija.mihova", LocalDate.now());
        course = new Course("Algorithms and Data structures", "ADS", createListOfStudents(), Type.WINTER,teacher);
        teacherService.save(teacher);
        courseService.save(course);

        courseService.findAll().stream()
                .forEach(course1 -> {
                    List<Student> studentsList = course1.getStudents();
                    studentsList.stream().forEach(
                            student -> gradeService.save(new Grade(generateRandomGrade(), student, course1, LocalDateTime.now()))
                    );
                });


    }

    public List<Student> createListOfStudents() {
        Random random = new Random();
        int n = random.nextInt(5) + 1;
        return IntStream.range(0, n)
                .map(index -> random.nextInt(n))
                .mapToObj(index -> studentService.findAll().get(index))
                .distinct()
                .collect(Collectors.toList());
    }
    public Character generateRandomGrade()
    {
        List<Character> grade =  List.of('A', 'B', 'C', 'D', 'E', 'F');
        Random random = new Random();
        int n = random.nextInt(6);
        return grade.get(n);
    }

}
