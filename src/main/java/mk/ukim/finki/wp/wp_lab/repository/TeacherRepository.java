package mk.ukim.finki.wp.wp_lab.repository;

import mk.ukim.finki.wp.wp_lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.wp_lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll()
    {
        return DataHolder.teachers;
    }

    public Optional<Teacher> findById(Long id)
    {
        return DataHolder.teachers
                .stream()
                .filter(teacher -> teacher.getId().equals(id))
                .findFirst();
    }


}
