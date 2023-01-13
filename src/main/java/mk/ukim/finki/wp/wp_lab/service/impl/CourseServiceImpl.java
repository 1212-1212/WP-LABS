package mk.ukim.finki.wp.wp_lab.service.impl;

import mk.ukim.finki.wp.wp_lab.model.*;
import mk.ukim.finki.wp.wp_lab.repository.CourseRepository;
import mk.ukim.finki.wp.wp_lab.repository.StudentRepository;
import mk.ukim.finki.wp.wp_lab.repository.TeacherRepository;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {


    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;



    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;

    }



    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow().getStudents();
    }

    @Override
    @Transactional
    public Optional<Course> addStudentInCourse(Student student, Course course) {
        if(course.getStudents().contains(student))
            return Optional.empty();
        course.getStudents().add(student);
        courseRepository.save(course);
        return Optional.of(course);

    }

    @Override

    public List<Course> findAll() {
        return courseRepository.findAll();
    }


    @Override
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findCourseByName(String courseName) {
        return courseRepository.findCourseByName(courseName);
    }

    @Override
    @Transactional

    public Course save(String courseName, String courseDescription, Long teacherId, Type type) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        Course course = new Course(courseName, courseDescription, type, teacher);
        return courseRepository.save(course);

    }


    @Override
    public List<Long> validIdsOfCourses() {
        return findAll().stream().mapToLong(Course::getCourseId).boxed().toList();
    }

    @Override
    public void deleteCourseById(Long id) {

        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void edit(String courseName, String courseDescription, Long teacherId, Type type, Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (course.isPresent() && teacher.isPresent()) {

            course.get().setName(courseName);
            course.get().setDescription(courseDescription);
            course.get().setTeacher(teacher.get());
            course.get().setType(type);
            courseRepository.save(course.get());
        }

    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }


}
