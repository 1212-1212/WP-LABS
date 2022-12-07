package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.model.Type;
import org.checkerframework.checker.nullness.Opt;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Optional<Course> addStudentInCourse(Student student, Course course);

    List<Course> findAll();

    Optional<Course> findCourseById(Long id);

    void deleteCourseById(Long id);

    Optional<Course> save(String courseName, String courseDescription, Long teacherId, Type type);

    Optional<Course> findCourseByName(String courseName);


    void edit(String courseName, String courseDescription, Long teacherId, Type type , Long courseId);

    List<Long> validIdsOfCourses();

    Optional<Course> save(Course course);



}