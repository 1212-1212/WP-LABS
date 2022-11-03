package mk.ukim.finki.wp.wp_lab.exceptions;

public class NoStudentSelectedException extends Exception{
    public NoStudentSelectedException() {
        super("A student must be selected!");
    }
}
