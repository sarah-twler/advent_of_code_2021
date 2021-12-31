package AoC.day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileReader {

    public static LinkedList<DirectionsTuple> readDataFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        LinkedList<DirectionsTuple> directions = new LinkedList();
        for (String str : lines) {
            directions.add(new DirectionsTuple(str.split(" ")[0], Integer.parseInt(str.split(" ")[1])));
        }
        return directions;
    }
}
