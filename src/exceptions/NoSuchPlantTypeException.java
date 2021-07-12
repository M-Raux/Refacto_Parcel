package exceptions;

public class NoSuchPlantTypeException extends Exception{
    String plantType;

    public NoSuchPlantTypeException(String plantType) {
        this.plantType = plantType;
    }

    @Override
    public String getMessage() {
        return "'" + plantType + "' is not a valid plant type";
    }
}
