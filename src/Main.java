public class Main {
    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        int pointsGained = parcel.playNextTurn("PLANTA");
        String turnInformation;
        switch (pointsGained) {
            case Parcel.INVALID_PLANT:{
                turnInformation = "INVALID OR MISSING TYPE OF PLANT";
                break;
            }
            case Parcel.INVALID_ACTION: {
                turnInformation = "INVALID ACTION";
                break;
            }
            default:
                turnInformation = String.format("TURN DONE SUCCESSFULLY AND EARNED %d POINTS\n%s", pointsGained, parcel);
        }
        System.out.println(turnInformation);
    }
}
