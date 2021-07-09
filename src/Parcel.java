import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parcel {
    private static final int MAX_SOIL_QUALITY = 1000;
    private static final int FERTILIZER_BONUS = 300;
    private static final int NO_PLANT_VALUE = -1;
    private static final int TYPE_A_NUTRIENT_NEED = 10;
    private static final int TYPE_B_NUTRIENT_NEED = 20;
    private static final int TYPE_A_HARVEST_GAIN = 1;
    private static final int TYPE_B_HARVEST_GAIN = 2;
    private static final int PLANT_AGE_TO_BE_READY_TO_HARVEST = 10;
    private static final int PLANT_LIFESPAN = 11;

    public static final int INVALID_PLANT = -1;
    public static final int INVALID_ACTION = -2;



    private int plant;
    private char plantType;
    private int soilQuality;

    public Parcel() {
        this.plant = -1;
        this.soilQuality = 1000;
    }

    public int playNextTurn(String action) {
        int pointsEarned = 0;
        if (action != null) {
            if (action.equals("FERTILIZE")) {
                soilQuality = Math.min(MAX_SOIL_QUALITY, soilQuality + FERTILIZER_BONUS);
            } else if (action.equals("HARVEST")) {
                if (plant != NO_PLANT_VALUE) {
                    if (plant >= PLANT_AGE_TO_BE_READY_TO_HARVEST && plant <= PLANT_LIFESPAN) {
                        if (plantType == 'A') {
                            pointsEarned = TYPE_A_HARVEST_GAIN;
                        } else if (plantType == 'B') {
                            pointsEarned = TYPE_B_HARVEST_GAIN;
                        }
                    }
                    plant = NO_PLANT_VALUE;
                }
            } else if (action.startsWith("PLANT")) {
                if (plant == NO_PLANT_VALUE) {
                    if (action.length() > 5 && "AB".indexOf(action.charAt(5)) != -1) {
                        plantType = action.charAt(5);
                        plant = 0;
                    } else {
                        return INVALID_PLANT;
                    }
                }
            } else {
                return INVALID_ACTION;
            }
        }
        if (plant >= 0) {
            plant++;
            if (plantType == 'A') {
                soilQuality = Math.max(0, soilQuality - TYPE_A_NUTRIENT_NEED);
            } else if (plantType == 'B') {
                soilQuality = Math.max(0, soilQuality - TYPE_B_NUTRIENT_NEED);
            }
            if (soilQuality == 0) {
                plant = 110;
            }
        }
        return pointsEarned;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "plant=" + plant +
                ", plantType=" + plantType +
                ", soilQuality=" + soilQuality +
                '}';
    }
}
