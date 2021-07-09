public class Main {
    public static void main(String[] args) {
        Thing t = new Thing();
        int ret = t.nextTurn("PLANT");
        String turnInformation;
        switch (ret) {
            case -1:{
                turnInformation = "INVALID OR MISSING TYPE OF PLANT";
                break;
            }
            case -2: {
                turnInformation = "INVALID ACTION";
                break;
            }
            default:
                turnInformation = String.format("TURN DONE SUCCESSFULLY AND EARNED %d POINTS\n%s", ret, t);
        }
        System.out.println(turnInformation);
    }
}
