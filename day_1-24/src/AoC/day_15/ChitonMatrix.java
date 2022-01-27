package AoC.day_15;

import AoC.data_manager.MatrixHelper;

import java.rmi.UnexpectedException;
import java.util.LinkedList;

public class ChitonMatrix {

    private final int[][] chitonMatrix;
    private final int NUM_OF_TILES = 5;

    public ChitonMatrix(int[][] chitonMatrix) {
        this.chitonMatrix = chitonMatrix;
    }

    public int calcEndRiskValue() {
        return calcEndRiskValue(chitonMatrix);
    }

    public int calcEndRiskValue(int[][] sourceMatrix) {
        int[][] cascadingRiskMatrix = calcCascadingRiskMatrix(sourceMatrix);
        return cascadingRiskMatrix
                [cascadingRiskMatrix.length - 1]
                [cascadingRiskMatrix[cascadingRiskMatrix.length - 1].length - 1];
    }

    public LinkedList<String> calcPathLowestRisk() throws UnexpectedException {
        return calcPathLowestRisk(chitonMatrix);
    }

    public LinkedList<String> calcPathLowestRisk(int[][] sourceMarix) throws UnexpectedException {
        int[][] cascadingRiskMatrix = calcCascadingRiskMatrix(sourceMarix);
        return calcShortestPath(cascadingRiskMatrix);
    }

    private LinkedList<String> calcShortestPath(int[][] cascadingRiskMatrix) throws UnexpectedException {
        LinkedList<String> path = new LinkedList();
        int row = cascadingRiskMatrix.length - 1;
        int col = cascadingRiskMatrix[row].length - 1 ;
        path.add(String.format("<%s|%s>", row, col));
        while (row != 0 || col != 0) {
            int nextRow = MatrixHelper.isRowInRange(row - 1, cascadingRiskMatrix) ? row - 1 : -1;
            int nextCol = MatrixHelper.isColInRange(row, col - 1, cascadingRiskMatrix) ? col - 1 : -1;

            if (nextCol == -1 || nextRow != -1 && cascadingRiskMatrix[nextRow][col] < cascadingRiskMatrix[row][nextCol])
                row--;
            else if (nextRow == -1 || nextCol != -1 & cascadingRiskMatrix[row][nextCol] < cascadingRiskMatrix[nextRow][col])
                col--;
            else if (nextRow != -1 && nextCol != -1 & cascadingRiskMatrix[row][nextCol] == cascadingRiskMatrix[nextRow][col])
                col--;
            else
                throw new UnexpectedException("Invalid state in path! Cannot get shortest path!");

            path.add(0, String.format("<%s|%s>", row, col));
        }
        return path;
    }

    public int[][] rotateMatrix() {
        return rotateMatrix(chitonMatrix);
    }

    public int[][] rotateMatrix(int[][] sourceMatrix) {
        int[][] rotatedMatrix = new int[sourceMatrix[0].length][sourceMatrix.length];
        for (int i = 0; i < sourceMatrix.length; i++) {
            int col = sourceMatrix[i].length - 1 - i;
            for (int j = 0; j < sourceMatrix[i].length; j++) {
                int row = j;
                rotatedMatrix[i][j] = sourceMatrix[row][col];
            }
        }
        return rotatedMatrix;
    }

    public int[][] enlargeChitonMatrix() {
        int rowsPerTile = chitonMatrix.length; // row
        int colsPerTile = chitonMatrix[0].length; // col
        int[][] enlargedMatrix = new int[colsPerTile * 5][rowsPerTile * 5];

        int[][] copy = chitonMatrix;
        int[][] increment = increaseValues(chitonMatrix);
        int rowTileCount = 0;
        int colTileCount = 0;
        while (rowTileCount < NUM_OF_TILES) {
            if (colTileCount == 0) {
                insertMatrixAtIndex(rowTileCount * rowsPerTile, colTileCount * colsPerTile, copy, enlargedMatrix);
                copy = increaseValues(copy);
                increment = copy;
                colTileCount++;
            }

            insertMatrixAtIndex(rowTileCount * rowsPerTile, colTileCount * colsPerTile, increment, enlargedMatrix);
            increment = increaseValues(increment);
            colTileCount++;
            if (colTileCount == NUM_OF_TILES) {
                colTileCount = 0;
                rowTileCount++;
            }
        }
        return enlargedMatrix;
    }

    private void insertMatrixAtIndex(int atRow, int atCol, int[][] sourceMatrix, int[][] targetMatrix) {
        int tileWidth = sourceMatrix.length;
        int tileLength = sourceMatrix[0].length;

        for (int row = 0; row < tileWidth; row++) {
            for (int col = 0; col < tileLength; col++) {
                targetMatrix[atRow + row][atCol + col] = sourceMatrix[row][col];
            }
        }
    }

    private int[][] increaseValues(int[][] matrix) {
        int[][] increment = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int value = matrix[row][col] + 1;
                increment[row][col] = value > 9 ? 1 : value;
            }
        }
        return increment;
    }

    private int[][] calcCascadingRiskMatrix(int[][] sourceMatrix) {
        int[][] cascadingRiskMatrix = new int[sourceMatrix.length][sourceMatrix[0].length];
        for (int row = 0; row < sourceMatrix.length; row++) {
            for (int col = 0; col < sourceMatrix[row].length; col++) {
                if (row == 0 && col == 0)
                    continue;
                int localRisk = sourceMatrix[row][col];
                int riskTop = row > 0 ? cascadingRiskMatrix[row - 1][col] : Integer.MAX_VALUE;
                int riskLeft = col > 0 ? cascadingRiskMatrix[row][col - 1] : Integer.MAX_VALUE;

                cascadingRiskMatrix[row][col] = localRisk + Math.min(riskTop, riskLeft);
            }
        }
        return cascadingRiskMatrix;
    }

}
