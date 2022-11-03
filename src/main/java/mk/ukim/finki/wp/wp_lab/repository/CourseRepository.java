package mk.ukim.finki.wp.wp_lab.repository;

import mk.ukim.finki.wp.wp_lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {



    public List<Course> findAllCourses()
    {
        return DataHolder.courses;
    }

    public Course findById(Long courseId)
    {
        if(courseId == null)
            throw new IllegalArgumentException();
        return DataHolder.courses
                .stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst().orElseThrow(NullPointerException::new);
    }
    public List<Student> findAllStudentsByCourse(Long courseId)
    {
        return findById(courseId).getStudents();
    }
    public Course addStudentToCourse(Student student, Course course)
    {
        findById(course.getCourseId()).getStudents().removeIf(student1 -> student1.equals(student));
        findById(course.getCourseId()).getStudents().add(student);
        return course;
    }


}
