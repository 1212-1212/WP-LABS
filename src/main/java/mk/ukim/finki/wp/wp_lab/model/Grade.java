package mk.ukim.finki.wp.wp_lab.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Grade implements Comparable<Grade> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    private Character grade;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    @Override
    public int compareTo(Grade o) {
        return this.course.compareTo(o.course);
    }

    public Grade(Character grade, Student student, Course course, LocalDateTime timestamp) {
        this.grade = grade;
        this.student = student;
        this.course = course;
        this.timestamp = timestamp;
    }

    public static List<Character> getDomain()
    {
        return List.of('A', 'B', 'C', 'D', 'E', 'F');
    }

    public Grade() {
    }
}
