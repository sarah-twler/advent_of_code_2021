package AoC.day_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static ArrayList<CoordinatesXY> readDataFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        ArrayList<CoordinatesXY> coordinatesXY = new ArrayList();

        for (String line : lines) {
            String[] input = line.split("\\s->\\s");

            String[] coordinates1 = input[0].split(",");
            String[] coordinates2 = input[1].split(",");

            coordinatesXY.add(
                    new CoordinatesXY(
                            parseToInt(coordinates1[0]),
                            parseToInt(coordinates1[1]),
                            parseToInt(coordinates2[0]),
                            parseToInt(coordinates2[1])));
        }
        return coordinatesXY;
    }

    private static int parseToInt(String stringValue) {
        return Integer.parseInt(stringValue);
    }
}
