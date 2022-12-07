package mk.ukim.finki.wp.wp_lab.service.impl;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.repository.CourseRepository;
import mk.ukim.finki.wp.wp_lab.repository.GradeRepository;
import mk.ukim.finki.wp.wp_lab.repository.StudentRepository;
import mk.ukim.finki.wp.wp_lab.service.GradeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Grade> findGradeByStudentAndCourse(Student student, Course course) {
        return gradeRepository.findGradeByStudentAndCourse(student, course);
    }

    @Override
    public List<Grade> findAllByCourse(Course course) {
        return gradeRepository.findAllByCourse(course);
    }

    @Override
    public Optional<Grade> save(Grade grade) {
        return Optional.of(gradeRepository.save(grade));
    }

    @Override
    public void deleteAllByCourse(Course course) {
        gradeRepository.deleteAllByCourse(course);
    }



    @Override
    public void edit(Grade grade, Character mark, Student student, Course course, LocalDateTime dateTime) {
        grade.setGrade(mark);
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setTimestamp(dateTime);
        gradeRepository.save(grade);
    }

    @Override
    public Optional<Grade> findGradeByGradeId(Long id) {
        return gradeRepository.findGradeByGradeId(id);
    }

    @Override
    @Transactional
    public Map<Student, Grade> getGradeMapForCourse(Course course) {
        return findAllByCourse(course)
                .stream()
                .collect(Collectors.toMap(
                        Grade::getStudent,
                        Function.identity()
                ));
    }

    @Override
    public List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to) {
        return gradeRepository.findAllByTimestampBetween(from, to);
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }
}
