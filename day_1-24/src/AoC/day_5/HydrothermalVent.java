package AoC.day_5;

import java.util.ArrayList;

public class HydrothermalVent {

    public int countDangerZones(int[][] diagram) {
        int countDangerZones = 0;
        for (int x = 0; x < diagram.length; x++) {
            for (int y = 0; y < diagram[x].length; y++) {
                if (diagram[x][y] > 1)
                    countDangerZones++;
            }
        }
        return countDangerZones;
    }

    public String diagramToString(int[][] diagram) {
        String out = "";
        for (int x = 0; x < diagram.length; x++) {
            String line = "";
            for (int y = 0; y < diagram[x].length; y++) {
                line += diagram[x][y];
            }
            out += line + "\n";
        }
        return out;
    }

    public void printDiagram(int[][] diagram) {
        System.out.println(diagramToString(diagram));
    }

    public int[][] createDiagram(ArrayList<CoordinatesXY> coordinatesList, boolean considerDiagonals) {
        int[] maxCoordinates = getMaxXYCoordinates(coordinatesList);
        int[][] diagram = new int[maxCoordinates[1] + 1][maxCoordinates[0] + 1];

        for (CoordinatesXY coordinates : coordinatesList) {
            if (coordinates.isHorizontalOrVerticalLine()) {
                diagram = calcHorizontalVerticalOverlap(diagram, coordinates);
            } else if (considerDiagonals && coordinates.isDiagonalLine()) {
                diagram = calcDiagonalOverlap(diagram, coordinates);
            }
        }
        return diagram;
    }

    private int[][] calcDiagonalOverlap(int[][] diagram, CoordinatesXY coordinates) {
        int x = coordinates.getX1();
        int y = coordinates.getY1();
        int x2 = coordinates.getX2();
        int y2 = coordinates.getY2();

        int gradientX = x < x2 ? 1 : -1;
        int gradientY = y < y2 ? 1 : -1;

        while (x != x2 + gradientX && y != y2 + gradientY) {
            diagram[y][x]++;
            x += gradientX;
            y += gradientY;
        }

        return diagram;
    }

    private int[][] calcHorizontalVerticalOverlap(int[][] diagram, CoordinatesXY coordinates) {
        for (int x = coordinates.getLowestX(); x <= coordinates.getBiggestX(); x++) {
            for (int y = coordinates.getLowestY(); y <= coordinates.getBiggestY(); y++) {
                diagram[y][x]++;
            }
        }
        return diagram;
    }

    private int[] getMaxXYCoordinates(ArrayList<CoordinatesXY> coordinatesList) {
        int maxCoordinateX = 0;
        int maxCoordinateY = 0;

        for (CoordinatesXY coordinates : coordinatesList) {
            if (coordinates.getBiggestX() > maxCoordinateX)
                maxCoordinateX = coordinates.getBiggestX();
            if (coordinates.getBiggestY() > maxCoordinateY)
                maxCoordinateY = coordinates.getBiggestY();
        }
        return new int[] {maxCoordinateX, maxCoordinateY};
    }
}
