package mk.ukim.finki.wp.wp_lab.repository;

import mk.ukim.finki.wp.wp_lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {


    private final TeacherRepository teacherRepository;

    public CourseRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }

    public Optional<Course> findById(Long courseId) {
        return DataHolder.courses
                .stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return findById(courseId).orElseThrow(IllegalArgumentException::new).getStudents();
    }

    public Optional<Course> addStudentToCourse(Student student, Course course) {

        checkAndRemoveIfDuplicateStudent(course, student);
        course.getStudents().add(student);
        return Optional.of(course);
    }

    public void checkAndRemoveIfDuplicateStudent(Course course, Student student) {
        course.getStudents().removeIf(student1 -> student1.equals(student));
    }

    public Optional<Course> save(String courseName, String courseDescription, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        Course course = new Course(courseName, courseDescription, teacher);
        DataHolder.courses.add(course);
        return Optional.of(course);
    }

    public void delete(Long id)
    {
        DataHolder.courses.removeIf(course -> course.getCourseId().equals(id));
        System.out.println(DataHolder.courses.toString());
    }

    public void edit(String courseName, String courseDescription, Long teacherId, Long courseId)
    {
        Course course = DataHolder.courses
                .stream()
                .filter(course1 -> course1.getCourseId().equals(courseId))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        course.setName(courseName);
        course.setDescription(courseDescription);
        course.setTeacher(teacherRepository.findById(teacherId).get());
    }
}
