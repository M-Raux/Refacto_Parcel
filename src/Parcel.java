import java.util.Arrays;

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
    private static final String[] PLANT_TYPES = {"A", "B"};

    public static final int INVALID_PLANT_ERROR = -1;
    public static final int INVALID_ACTION_ERROR = -2;

    private int plant;
    private String plantType;
    private int soilQuality;

    public Parcel() {
        this.plant = NO_PLANT_VALUE;
        this.soilQuality = MAX_SOIL_QUALITY;
        this.plantType = "";
    }

    public int playNextTurn(String action) {
        int pointsEarned = 0;
        if (action != null) {
            pointsEarned = playAction(action);
        }
        if (hasPlant()) {
            growPlant();
            alterSoilQualityFromPlant();
            if (hasBadSoilQuality()) {
                killPlant();
            }
        }
        return pointsEarned;
    }

    private boolean hasBadSoilQuality() {
        return soilQuality == 0;
    }

    private int playAction(String action) {
        int pointsEarned = 0;
        if (action.equals("FERTILIZE")) {
            pointsEarned = fertilize();
        } else if (action.equals("HARVEST")) {
            pointsEarned = harvest();
        } else if (action.startsWith("PLANT")) {
            String chosenPlantType = action.substring(5);
            pointsEarned = plant(chosenPlantType);
        } else {
            return INVALID_ACTION_ERROR;
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

    private int plant(String chosenPlantType) {
        if (!hasPlant()) {
            if (checkValidPlantType(chosenPlantType)) {
                plant = 0;
                plantType = chosenPlantType;
            } else {
                return INVALID_PLANT_ERROR;
            }
        }
        return 0;
    }

    private boolean checkValidPlantType(String plantType) {
        return Arrays.asList(PLANT_TYPES).contains(plantType);
    }

    private int getPlantValue() {
        int plantValue = 0;
        if (plantIsReady()) {
            if (plantType.equals("A")) {
                plantValue = TYPE_A_HARVEST_GAIN;
            } else if (plantType.equals("B")) {
                plantValue = TYPE_B_HARVEST_GAIN;
            }
        }
        return plantValue;
    }

    private void alterSoilQualityFromPlant() {
        if (plantType.equals("A")) {
            soilQuality = Math.max(0, soilQuality - TYPE_A_NUTRIENT_NEED);
        } else if (plantType.equals("B")) {
            soilQuality = Math.max(0, soilQuality - TYPE_B_NUTRIENT_NEED);
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
