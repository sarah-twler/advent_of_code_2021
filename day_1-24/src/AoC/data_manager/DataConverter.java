package AoC.data_manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {

    public static List<Character> convertStringToCharacterList(String sequence) {
        return sequence.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    }

    public static LinkedList<String> convertStringToStringList(String sequence) {
        LinkedList<String> out = new LinkedList();
        List<Character> charList = convertStringToCharacterList(sequence);
        for (Character c : charList) {
            out.add(String.valueOf(c));
        }
        return out;
    }

    public static int[][] readLinesToMatrixNoDelimiter(List<String> lines) throws IOException {
        if (lines == null || lines.isEmpty() || lines.get(0).isBlank())
            throw new IOException("Cannot convert blank list to matrix!");

        int[][] matrix = new int[lines.size()][lines.get(0).length()];

        for (int row = 0; row < lines.size(); row++) {
            char[] lineAsArray = lines.get(row).toCharArray();
            for (int col = 0; col < lineAsArray.length; col++) {
                int intValue = Character.getNumericValue(lineAsArray[col]);
                if (intValue < 0)
                    throw new IOException(String.format("Cannot convert char '%s' to a valid int!", intValue));
                matrix[row][col] = intValue;
            }
        }

        return matrix;
    }

    public static ArrayList<String> deepCopyList(List<String> list) {
        ArrayList<String> newList = new ArrayList();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext())
            newList.add(iterator.next());
        return newList;
    }

    /**
     * @param list
     * @return a string representation of the list
     */
    public static String convertListToString(List<String> list) {
        return list.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }


}
