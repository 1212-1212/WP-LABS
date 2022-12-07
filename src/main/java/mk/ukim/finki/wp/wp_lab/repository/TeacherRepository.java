package mk.ukim.finki.wp.wp_lab.repository;

import mk.ukim.finki.wp.wp_lab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
