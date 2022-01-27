package AoC.data_manager;

public class DataPrinter {

    public static void printMatrix(int[][] matrix) {
        String out = "";
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                out += matrix[row][col];
            }
            out += "\n";
        }
        System.out.println(out);
    }
}
