package mk.ukim.finki.wp.wp_lab;


import mk.ukim.finki.wp.wp_lab.model.*;
import mk.ukim.finki.wp.wp_lab.repository.CourseRepository;
import mk.ukim.finki.wp.wp_lab.repository.StudentRepository;
import mk.ukim.finki.wp.wp_lab.repository.TeacherRepository;
import mk.ukim.finki.wp.wp_lab.service.impl.CourseServiceImpl;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EditCourseTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock

    private TeacherRepository teacherRepository;


    private CourseServiceImpl courseService;


    private Course course;
    private Teacher teacher;


    @Before
    public void setUp() {
        course = new Course();
        course.setCourseId(1L);
        course.setName("Test Course");
        course.setDescription("This is a test course description.");
        course.setType(Type.MANDATORY);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setTeacherFullName(new TeacherFullName("John", "Doe"));
        teacher.setUsername("john.doe");
        teacher.setDateOfEmployment(LocalDate.now());

        courseService = spy(new CourseServiceImpl(courseRepository, studentRepository, teacherRepository));

    }


    @Test
    public void testEdit()
    {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));


        courseService.edit("Updated Test Course", "This is an updated test course description.", 1L, Type.WINTER, 1L);

        assertEquals("Updated Test Course", course.getName());
        assertEquals("This is an updated test course description.", course.getDescription());
        assertEquals(teacher, course.getTeacher());
        assertEquals(Type.WINTER, course.getType());
    }
    @Test
    public void testEditCourseNotFound()
    {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        courseService.edit("Updated Test Course", "This is an updated test course description.", 1L, Type.WINTER, 1L);

        assertEquals("Test Course", course.getName());
        assertEquals("This is a test course description.", course.getDescription());
        assertEquals(Type.MANDATORY, course.getType());
    }

    @Test
    public void testEditTeacherNotFound()
    {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        courseService.edit("Updated Test Course", "This is an updated test course.", 1L, Type.WINTER, 1L);

        assertEquals("Test Course", course.getName());
        assertEquals("This is a test course description.", course.getDescription());
        assertNull(course.getTeacher());
        assertEquals(Type.MANDATORY, course.getType());
    }


}
