package mk.ukim.finki.wp.wp_lab.service.impl;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.model.Teacher;
import mk.ukim.finki.wp.wp_lab.repository.CourseRepository;
import mk.ukim.finki.wp.wp_lab.repository.StudentRepository;
import mk.ukim.finki.wp.wp_lab.repository.TeacherRepository;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {


    public final CourseRepository courseRepository;
    public final StudentRepository studentRepository;

    public final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Optional<Course> addStudentInCourse(String username, Long courseId) {
        Optional<Student> student = studentRepository.findStudentByUsername(username);
        Optional<Course> course = courseRepository.findById(courseId);
        if(student.isPresent() && course.isPresent())
        {
            course.get().getStudents().add(student.get());
        }
        return course;

    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }



    @Override
    public Optional<Course> findCourseById(Long id) {
       return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> save(String courseName, String courseDescription, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        courseRepository.save(courseName, courseDescription, teacherId);
        return Optional.of(new Course(courseName, courseDescription, teacher));
    }

    @Override
    public List<Long> validIdsOfCourses() {
        return  listAll().stream().mapToLong(Course::getCourseId).boxed().toList();
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.delete(id);

    }
    @Override
    public void edit(String courseName, String courseDescription, Long teacherId, Long courseId) {
        courseRepository.edit(courseName, courseDescription, teacherId, courseId);
    }

}
