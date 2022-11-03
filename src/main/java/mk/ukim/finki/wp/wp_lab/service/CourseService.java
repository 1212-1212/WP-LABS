package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;

import java.util.List;
public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);

    List<Course> listAll();

    Course findCourseById(Long id);



}