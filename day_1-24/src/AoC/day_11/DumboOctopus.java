package AoC.day_11;

import AoC.data_manager.AdjacencyEnum;

import static AoC.data_manager.MatrixHelper.isColInRange;
import static AoC.data_manager.MatrixHelper.isRowInRange;

public class DumboOctopus {

    static private final int MIN_ENERGY_LEVEL = 0;
    static private final int MAX_ENERGY_LEVEL = 9;
    static private final int ENERGY_INCREMENT = 1;

    private int[][] octopusMatrix;

    public DumboOctopus(int[][] octopusMatrix) {
        this.octopusMatrix = octopusMatrix;
    }

    public int stepThroughOctopuses(int steps) {
        int totalFlashCount = 0;
        for (int stepCounter = 0; stepCounter < steps; stepCounter++) {
            increaseEnergyLevelOctopusMatrix();
            totalFlashCount += flashOctopusMatrix();
        }
        return totalFlashCount;
    }

    public int stepThroughOctopusesUntilTotalFlash() {
        int stepCounter = 0;
        while (stepCounter < Integer.MAX_VALUE) {
            stepCounter++;
            increaseEnergyLevelOctopusMatrix();
            flashOctopusMatrix();
            if (isSimultaneousFlash())
                return stepCounter;
        }
        return -1;
    }

    private int flashOctopusMatrix() {
        int totalFlashCount = 0;
        for (int row = 0; row < octopusMatrix.length; row ++) {
            for (int col = 0; col < octopusMatrix[row].length; col++) {
                totalFlashCount += flashOctopusIfEnergyCharged(row, col);
            }
        }
        return totalFlashCount;
    }

    private boolean isFlashed(int row, int col) {
        return octopusMatrix[row][col] == MIN_ENERGY_LEVEL;
    }

    private boolean isCharged(int row, int col) {
        return octopusMatrix[row][col] > MAX_ENERGY_LEVEL;
    }

    private int flashOctopusIfEnergyCharged(int row, int col) {
        if (octopusMatrix[row][col] > MAX_ENERGY_LEVEL) {
            return flashOctopus(row, col);
        }
        return 0;
    }

    private int flashOctopus(int row, int col) {
        int countFlashes = 1;
        octopusMatrix[row][col] = MIN_ENERGY_LEVEL;
        for (AdjacencyEnum adjacency : AdjacencyEnum.values()) {
            int adjacentRow = row + adjacency.getRowDirection();
            int adjacentCol = col + adjacency.getColDirection();
            if (isRowInRange(adjacentRow, octopusMatrix)
                    && isColInRange(adjacentRow, adjacentCol, octopusMatrix)
                    && !isFlashed(adjacentRow, adjacentCol)) {
                octopusMatrix[adjacentRow][adjacentCol] += ENERGY_INCREMENT;
                countFlashes += flashOctopusIfEnergyCharged(adjacentRow, adjacentCol);
            }
        }
        return countFlashes;
    }

    private void increaseEnergyLevelOctopusMatrix() {
        for (int row = 0; row < octopusMatrix.length; row ++)
            for (int col = 0; col < octopusMatrix[row].length; col++)
                octopusMatrix[row][col] += ENERGY_INCREMENT;
    }

    int[][] getOctopusMatrix() {
        return octopusMatrix;
    }

    private boolean isSimultaneousFlash() {
        for (int row = 0; row < octopusMatrix.length; row++)
            for (int col = 0; col < octopusMatrix[row].length; col++) {
                if (octopusMatrix[row][col] != MIN_ENERGY_LEVEL)
                    return false;
            }
        return true;
    }
}
