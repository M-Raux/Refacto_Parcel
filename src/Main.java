import exceptions.NoSuchActionException;

public class Main {
    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        try {
            int pointsGained = parcel.playNextTurn("PLANTA");
            System.out.printf("TURN DONE SUCCESSFULLY AND EARNED %d POINTS\n%s%n", pointsGained, parcel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
