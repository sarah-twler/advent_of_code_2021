package data_manager;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    public static List<String> getMatrixLinesWithoutDelimiter() {
        ArrayList<String> lines = new ArrayList();
        lines.add("2199943210");
        lines.add("3987894921");
        lines.add("9856789892");
        lines.add("8767896789");
        lines.add("9899965678");
        return lines;
    }

    public static int[][] getMatrixWithoutDelimiter() {
        int[][] matrix = new int[5][10];
        matrix[0] = new int[] { 2,1,9,9,9,4,3,2,1,0 };
        matrix[1] = new int[] { 3,9,8,7,8,9,4,9,2,1 };
        matrix[2] = new int[] { 9,8,5,6,7,8,9,8,9,2 };
        matrix[3] = new int[] { 8,7,6,7,8,9,6,7,8,9 };
        matrix[4] = new int[] { 9,8,9,9,9,6,5,6,7,8 };
        return matrix;
    }
}
