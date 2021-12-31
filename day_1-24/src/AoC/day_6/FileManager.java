package AoC.day_6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static ArrayList<Integer> readDataFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        ArrayList<Integer> fishes = new ArrayList();

        for (String line : lines) {
            String[] values = line.split(",");
            for (int i = 0; i < values.length; i++) {
                fishes.add(Integer.parseInt(values[i]));
            }
        }
        return fishes;
    }

}
