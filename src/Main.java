import exceptions.NoSuchActionException;

public class Main {
    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        String actionReceived = receiveAction();
        try {
            int pointsGained = parcel.playNextTurn(actionReceived);
            System.out.printf("TURN DONE SUCCESSFULLY AND EARNED %d POINTS\n%s%n", pointsGained, parcel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String receiveAction() {
        return "PLANTA";
    }
}
