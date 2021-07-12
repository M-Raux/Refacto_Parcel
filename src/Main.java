public class Main {
    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        String act = receiveAction();
        int pointsGained = parcel.playNextTurn(act);
        String turnInformation;
        switch (pointsGained) {
            case Parcel.INVALID_PLANT_ERROR:{
                turnInformation = "INVALID OR MISSING TYPE OF PLANT";
                break;
            }
            case Parcel.INVALID_ACTION_ERROR: {
                turnInformation = "INVALID ACTION";
                break;
            }
            default:
                turnInformation = String.format("TURN DONE SUCCESSFULLY AND EARNED %d POINTS\n%s", pointsGained, parcel);
        }
        System.out.println(turnInformation);
    }

    private static String receiveAction() {
        return "PLANTA";
    }
}
