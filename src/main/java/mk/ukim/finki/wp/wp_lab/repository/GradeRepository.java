package mk.ukim.finki.wp.wp_lab.repository;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findGradeByStudentAndCourse(Student student, Course course);
    List<Grade> findAllByCourse(Course course);

    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);

    void deleteAllByCourse(Course course);



    Optional<Grade> findGradeByGradeId(Long id);

}
