package mk.ukim.finki.wp.wp_lab.model;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class TeacherFullNameConverter implements AttributeConverter<TeacherFullName, String> {

    private static final String DELIMITER = ", ";

    @Override
    public String convertToDatabaseColumn(TeacherFullName teacherFullName) {
        if (Objects.isNull(teacherFullName)) return null;
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(teacherFullName.getName()) && !teacherFullName.getName().isBlank() && !teacherFullName.getName().isEmpty()) {
            sb.append(teacherFullName.getName()).append(DELIMITER);
        }
        if (Objects.nonNull(teacherFullName.getSurname()) && !teacherFullName.getSurname().isBlank() && !teacherFullName.getSurname().isEmpty()) {
            sb.append(teacherFullName.getSurname());
        }

        return sb.toString();

    }

    @Override
    public TeacherFullName convertToEntityAttribute(String s) {
        if (Objects.isNull(s) || s.isEmpty() || s.isBlank()) return null;

        String[] parts = s.split(DELIMITER);
        if (parts.length == 0)
            return null;
        TeacherFullName teacherFullName = new TeacherFullName();
        String name = !parts[0].isEmpty() && !parts[0].isBlank() ? parts[0] : null;
        if (parts.length >= 2) {
            String surname = !parts[1].isBlank() && !parts[1].isEmpty() ? parts[1] : null;
            if (Objects.nonNull(name) && Objects.nonNull(surname)) {
                teacherFullName.setName(name);
                teacherFullName.setSurname(surname);
            }
        }
        return teacherFullName;
    }
}
