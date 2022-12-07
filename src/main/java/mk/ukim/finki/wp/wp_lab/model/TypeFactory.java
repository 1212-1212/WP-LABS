package mk.ukim.finki.wp.wp_lab.model;

public class TypeFactory {

    public  static Type toType(String type)
    {
        if(type.equalsIgnoreCase("WINTER"))
            return Type.WINTER;
        else if(type.equalsIgnoreCase("SUMMER"))
            return Type.SUMMER;
        else if(type.equalsIgnoreCase("MANDATORY"))
            return Type.MANDATORY;
        else
            return Type.ELECTIVE;

    }
}
