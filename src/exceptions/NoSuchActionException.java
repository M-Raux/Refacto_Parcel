package exceptions;

public class NoSuchActionException extends Exception{
    String action;

    public NoSuchActionException(String action) {
        super();
        this.action = action;
    }

    @Override
    public String getMessage() {
        return "'" + action + "' is not a valid action name";
    }
}
