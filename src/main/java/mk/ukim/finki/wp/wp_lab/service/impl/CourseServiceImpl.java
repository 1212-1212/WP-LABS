package mk.ukim.finki.wp.wp_lab.service.impl;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.repository.CourseRepository;
import mk.ukim.finki.wp.wp_lab.repository.StudentRepository;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    public final CourseRepository courseRepository;
    public final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository
                .findById(courseId)
                .getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Course course = courseRepository.findById(courseId);
        Student student = studentRepository.findStudentByUsername(username);
        course.getStudents().add(student);
        return course;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }



    @Override
    public Course findCourseById(Long id) {
       return courseRepository.findById(id);
    }
}
