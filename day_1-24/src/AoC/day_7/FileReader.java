package AoC.day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {

    private static ArrayList<Integer> readDataFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        ArrayList<Integer> positionsList = new ArrayList();

        for (String line : lines) {
            String[] values = line.split(",");
            for (int i = 0; i < values.length; i++) {
                positionsList.add(Integer.parseInt(values[i]));
            }
        }
        return positionsList;
    }

    public static Map<Integer, Integer> readMappedDataFromFile(String filename) throws IOException {
        ArrayList<Integer> dataList = readDataFromFile(filename);
        Map<Integer, Integer> mappedData = new HashMap();

        for (int value : dataList) {
            mappedData.putIfAbsent(value, 0);
            mappedData.put(value, mappedData.get(value) + 1);
        }

        return mappedData;
    }


}
