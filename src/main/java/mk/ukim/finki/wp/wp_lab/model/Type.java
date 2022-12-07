package mk.ukim.finki.wp.wp_lab.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Type {
    WINTER,
    SUMMER,
    MANDATORY,
    ELECTIVE;

    public static List<String> toListOfStrings()
    {
       return Arrays.stream(Type.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }
}
