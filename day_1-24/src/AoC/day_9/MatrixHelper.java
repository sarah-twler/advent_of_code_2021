package AoC.day_9;

import java.io.IOException;

public class MatrixHelper {

    private final static int BASIN_MARKER = 1;
    private final static int NOT_BASIN_INDICATOR = 9;

    public static boolean isRowInRange(int row, int[][] map) {
        return 0 <= row && row < map.length;
    }

    public static boolean isColInRange(int row, int col, int[][] map) {
        return 0 <= col && col < map[row].length;
    }

    public static boolean isValueAboveHigher(int row, int col, int[][] map) {
        return row - 1 < 0 || map[row - 1][col] > map[row][col];
    }

    public static boolean isValueBelowHigher(int row, int col, int[][] map) {
        return row + 1 >= map.length || map[row + 1][col] > map[row][col];
    }

    public static boolean isValueLeftHigher(int row, int col, int[][] map) {
        return col - 1 < 0 || map[row][col - 1] > map[row][col];
    }

    public static boolean isValueRightHigher(int row, int col, int[][] map) {
        return col + 1 >= map[row].length || map[row][col + 1] > map[row][col];
    }

    public static boolean isBasinIndicator(int row, int col, int[][] map) {
        return map[row][col] != NOT_BASIN_INDICATOR;
    }

    public static int[][] createEmptyMapSameSize(int[][] map) throws IOException {
        if (map == null || map.length == 0)
            throw new IOException("Cannot create new map from empty input map!");
        return new int[map.length][map[0].length];
    }

    public static boolean isMarked(int row, int col, int[][] basinMap) {
        return basinMap[row][col] != 0;
    }

    public static void markPosition(int row, int col, int[][] basinMap) {
        basinMap[row][col] = BASIN_MARKER;
    }
}
