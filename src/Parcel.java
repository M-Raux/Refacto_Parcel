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

    public static final int INVALID_PLANT_ERROR = -1;
    public static final int INVALID_ACTION = -2;

    private int plant;
    private char plantType;
    private int soilQuality;

    public Parcel() {
        this.plant = -1;
        this.soilQuality = MAX_SOIL_QUALITY;
    }

    public int playNextTurn(String action) {
        int pointsEarned = 0;
        if (action != null) {
            pointsEarned = playAction(action);
        }
        if (hasPlant()) {
            growPlant();
            alterSoilQualityFromPlant();
        }
        return pointsEarned;
    }

    private int playAction(String action) {
        int pointsEarned = 0;
        if (action.equals("FERTILIZE")) {
            pointsEarned = fertilize();
        } else if (action.equals("HARVEST")) {
            pointsEarned = harvest();
        } else if (action.startsWith("PLANT")) {
            pointsEarned = plant(action);
        } else {
            return INVALID_ACTION;
        }
        return pointsEarned;
    }

    private int fertilize() {
        soilQuality = Math.min(MAX_SOIL_QUALITY, soilQuality + FERTILIZER_BONUS);
        return 0;
    }

    private int harvest() {
        int pointsEarned = 0;
        if (hasPlant()) {
            pointsEarned = getPlantValue();
            removePlant();
        }
        return pointsEarned;
    }

    private int plant(String action) {
        if (!hasPlant()) {
            String chosenPlantType = action.substring(5);
            if (chosenPlantType.equals("A") || chosenPlantType.equals("B")) {
                plantType = chosenPlantType.charAt(0);
                plant = 0;
            } else {
                return INVALID_PLANT_ERROR;
            }
        }
        return 0;
    }

    private int getPlantValue() {
        int plantValue = 0;
        if (plantIsReady()) {
            if (plantType == 'A') {
                plantValue = TYPE_A_HARVEST_GAIN;
            } else if (plantType == 'B') {
                plantValue = TYPE_B_HARVEST_GAIN;
            }
        }
        return plantValue;
    }

    private void alterSoilQualityFromPlant() {
        if (plantType == 'A') {
            soilQuality = Math.max(0, soilQuality - TYPE_A_NUTRIENT_NEED);
        } else if (plantType == 'B') {
            soilQuality = Math.max(0, soilQuality - TYPE_B_NUTRIENT_NEED);
        }
        if (soilQuality == 0) {
            killPlant();
        }
    }

    private void growPlant() {
        plant++;
    }

    private void killPlant() {
        plant = PLANT_LIFESPAN;
    }



    private boolean plantIsReady() {
        return plant >= PLANT_AGE_TO_BE_READY_TO_HARVEST && plant <= PLANT_LIFESPAN;
    }

    private boolean hasPlant() {
        return plant != NO_PLANT_VALUE;
    }

    private void removePlant() {
        plant = NO_PLANT_VALUE;
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
