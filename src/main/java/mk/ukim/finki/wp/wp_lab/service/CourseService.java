package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Optional<Course> addStudentInCourse(String username, Long courseId);

    List<Course> listAll();

    Optional<Course> findCourseById(Long id);

    void deleteCourseById(Long id);

    Optional<Course> save(String courseName, String courseDescription, Long teacherId);

    void edit(String courseName, String courseDescription, Long teacherId, Long courseId);

    List<Long> validIdsOfCourses();



}