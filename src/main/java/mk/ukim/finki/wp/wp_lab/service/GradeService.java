package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;
import org.checkerframework.checker.nullness.Opt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GradeService {
    Optional<Grade> findGradeByStudentAndCourse(Student student, Course course);
    List<Grade> findAllByCourse(Course course);

    Optional<Grade> save(Grade grade);

    Map<Student, Grade> getGradeMapForCourse(Course course);

    void deleteAllByCourse(Course course);


    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);

    List<Grade> findAll();

    Optional<Grade> findGradeByGradeId(Long id);


    void edit(Grade grade, Character grade1, Student student, Course course, LocalDateTime dateTime);
}