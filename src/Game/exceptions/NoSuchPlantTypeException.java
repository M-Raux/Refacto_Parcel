package Game.exceptions;

public class NoSuchPlantTypeException extends Exception{
    @Override
    public String getMessage() {
        return "INVALID OR MISSING TYPE OF PLANT";
    }
}
