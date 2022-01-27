package AoC.day_13;

import java.util.ArrayList;
import java.util.List;

public class DotMatrix {

    // x - columns
    // y - rows

    private int[][] dotMatrix;
    private int maxNumRows = 0;
    private int maxNumCols = 0;

    public DotMatrix(List<String[]> coordinatesXYList) {
        this.create(coordinatesXYList);
    }

    public void foldDotMatrix(String axis, int coordinate) {
        if (axis.equals("y"))
            foldAlongYCoordinate(coordinate);
        else if (axis.equals("x"))
            foldAlongXCoordinate(coordinate);

    }

    public int countDots() {
        int dotCount = 0;
        for (int row = 0; row < dotMatrix.length; row++) {
            for (int col = 0; col < dotMatrix[row].length; col++) {
                dotCount += dotMatrix[row][col] == 0 ? 0 : 1;
            }
        }
        return dotCount;
    }

     void foldAlongXCoordinate(int xCoordinate) {
        int[][] leftMatrix = copyMatrixLeftXCoordinate(xCoordinate);
        dotMatrix = foldRightValuesOnMatrix(xCoordinate, leftMatrix);
    }

     void foldAlongYCoordinate(int yCoordinate) {
        int[][] upperMatrix = copyMatrixAboveYCoordinate(yCoordinate);
        dotMatrix = foldLowerValuesOnMatrix(yCoordinate, upperMatrix);
    }

    private int[][] copyMatrixAboveYCoordinate(int yCoordinate) {
        int[][] upperMatrix = new int[yCoordinate][dotMatrix[0].length];
        for (int row = 0; row < yCoordinate; row++) {
            for (int col = 0; col < dotMatrix[row].length; col++) {
                upperMatrix[row][col] = dotMatrix[row][col];
            }
        }
        return upperMatrix;
    }

    private int[][] copyMatrixLeftXCoordinate(int xCoordinate) {
        int[][] leftMatrix = new int[dotMatrix.length][xCoordinate];
        for (int row = 0; row < dotMatrix.length; row++) {
            for (int col = 0; col < xCoordinate; col++) {
                leftMatrix[row][col] = dotMatrix[row][col];
            }
        }
        return leftMatrix;
    }

    private int[][] foldRightValuesOnMatrix(int xCoordinate, int[][] leftMatrix) {
        for (int row = 0; row < dotMatrix.length; row++) {
            int colCount = 1;
            for (int col = xCoordinate + 1; col < dotMatrix[row].length; col++) {
                int tmpCol = leftMatrix[row].length - colCount;
                leftMatrix[row][leftMatrix[row].length - colCount] += dotMatrix[row][col];
                colCount++;
            }
        }
        return leftMatrix;
    }

    private int[][] foldLowerValuesOnMatrix(int yCoordinate, int[][] upperMatrix) {
        int rowCount = 1;
        for (int row = yCoordinate + 1; row < dotMatrix.length; row++) {
            for (int col = 0; col < dotMatrix[row].length; col++) {
                upperMatrix[upperMatrix.length - rowCount][col] += dotMatrix[row][col];
            }
            rowCount++;
        }
        return upperMatrix;
    }

    private void create(List<String[]> coordinatesXYList) {
        ArrayList<int[]> dotCoordinates = convertToDotCoordinates(coordinatesXYList);
        createDotMatrix(dotCoordinates);
    }

    private void createDotMatrix(ArrayList<int[]> dotCoordinates) {
        dotMatrix = new int[maxNumRows + 1][maxNumCols + 1];
        for (int[] coordinate : dotCoordinates) {
            dotMatrix[coordinate[0]][coordinate[1]] = 1;
        }
    }

    private ArrayList<int[]> convertToDotCoordinates(List<String[]> coordinatesXYList) {
        ArrayList<int[]> dotCoordinates = new ArrayList();
        for (String[] xy : coordinatesXYList) {
            int col = Integer.parseInt(xy[0]);
            int row = Integer.parseInt(xy[1]);
            dotCoordinates.add(new int[] { row, col });
            setMapBoundaries(row, col);
        }
        return  dotCoordinates;
    }

    private void setMapBoundaries(int row, int col) {
        if (row > maxNumRows)
            maxNumRows = row;
        if (col > maxNumCols)
            maxNumCols = col;
    }

    @Override
    public String toString() {
        String out = "";
        for (int row = 0; row < dotMatrix.length; row++) {
            for (int col = 0; col < dotMatrix[row].length; col++) {
                out += parseIntToString(dotMatrix[row][col]);
            }
            out += "\n";
        }
        return out;
    }

    private String parseIntToString(int matrixValue) {
        return matrixValue == 0 ? "." : "#";
    }
}
