//package mk.ukim.finki.wp.wp_lab.bootstrap;
//
//import mk.ukim.finki.wp.wp_lab.model.Course;
//import mk.ukim.finki.wp.wp_lab.model.Student;
//import mk.ukim.finki.wp.wp_lab.model.Teacher;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//
//public class DataHolder {
//    public static List<Student> students = new ArrayList<>();
//    public static List<Course> courses = new ArrayList<>();
//
//    public static List<Teacher> teachers = new ArrayList<>();
//
//    @PostConstruct
//    public void init() {
//        students.add(new Student("petar.petrov", "123", "Petar", "Petrov"));
//        students.add(new Student("mile.milev", "123", "Mile", "Milev"));
//        students.add(new Student("gjorgji.gjorgjiev", "123", "Gjorgji", "Gjorgjiev"));
//        students.add(new Student("stefan.stefanovski", "123", "Stefan", "Stefanovski"));
//        students.add(new Student("filip.filipovski", "123", "Filip", "Filipovski"));
//
//
//
//        Teacher teacher = new Teacher("Sasho", "sasho.gramatikov");
//        Course course = new Course("Web programming", "WP", createListOfStudents(), teacher);
//        teachers.add(teacher);
//        courses.add(course);
//
//        teacher = new Teacher("Igor", "igor.mishkovski");
//        course = new Course("Operating Systems", "OS", createListOfStudents(), teacher);
//        teachers.add(teacher);
//        courses.add(course);
//
//        teacher = new Teacher("Goran", "goran.velinov");
//        course = new Course("Database Management Systems", "DBMS", createListOfStudents(), teacher);
//        teachers.add(teacher);
//        courses.add(course);
//
//        teacher = new Teacher("Sonja", "sonja.filipovska");
//        course = new Course("Computer Networks and Security", "CNS", createListOfStudents(), teacher);
//        teachers.add(teacher);
//        courses.add(course);
//
//        teacher = new Teacher("Marija", "marija.mihova");
//        course = new Course("Algorithms and Data structures", "ADS", createListOfStudents(), teacher);
//        teachers.add(teacher);
//        courses.add(course);
//
//    }
//
//    public List<Student> createListOfStudents() {
//        Random random = new Random();
//        int n = random.nextInt(5) + 1;
//        return IntStream.range(0, n)
//                .map(index -> random.nextInt(n))
//                .mapToObj(index -> students.get(index))
//                .distinct()
//                .collect(Collectors.toList());
//    }
//
//}
