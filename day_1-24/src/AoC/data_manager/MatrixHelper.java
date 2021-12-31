package AoC.data_manager;

import java.io.IOException;

public class MatrixHelper {

    public static boolean isCoordinatesInRange(int row, int col, int[][] matrix) {
        return isRowInRange(row, matrix) && isColInRange(row, col, matrix);
    }

    public static boolean isRowInRange(int row, int[][] map) {
        return 0 <= row && row < map.length;
    }

    public static boolean isColInRange(int row, int col, int[][] map) {
        return 0 <= col && col < map[row].length;
    }

    public static int[][] createEmptyMapSameSize(int[][] map) throws IOException {
        if (map == null || map.length == 0)
            throw new IOException("Cannot create new map from empty input map!");
        return new int[map.length][map[0].length];
    }

}
