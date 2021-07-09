package exceptions;

public class NoSuchActionException extends Exception{
    @Override
    public String getMessage() {
        return "INVALID ACTION";
    }
}
