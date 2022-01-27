package AoC.data_manager;

import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileReader {

    public static List<String> readFileToLines(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }

    /**
     * Generates a list of lists that contain lines make up a block or section in the source file. Blocks or sections
     * are identified by whitespace lines.
     * @param filename path to the input file
     * @return a list containing blocks of lines
     * @throws IOException
     */
    public static LinkedList<LinkedList<String>> readFileToLinesInSectionsWhitespaceSeparator(String filename) throws IOException {
        List<String> lines = readFileToLines(filename);
        LinkedList<LinkedList<String>> sectionedLines = new LinkedList();

        int startIndex = 0;
        int endIndex = 0;

        while (endIndex != lines.size()) {
            endIndex = getNextBlockEndIndex(lines, startIndex);
            sectionedLines.add(new LinkedList(lines.subList(startIndex, endIndex)));
            startIndex = endIndex + 1;
        }
        return sectionedLines;
    }

    /**
     * Reads the lines of a file into a map. Each line is separated into a key value pair using the delimiter.
     * @param filename
     * @param delimiter
     * @return
     * @throws IOException
     */
    public static Map<String, String> readFileToMap(String filename, String delimiter) throws IOException {
        List<String> lines = readFileToLines(filename);

        Map<String, String> map = new HashMap();
        for (String line : lines) {
            if (line.contains(delimiter)) {
                String[] tuple = line.split(delimiter);
                map.put(tuple[0], tuple[1]);
            }
        }
        return map;
    }

    /**
     * @param lines list of line blocks, separated with empty lines
     * @param startIndex index of next line block
     * @return end index of next line block, or -1 if no next line block exists
     */
    public static int getNextBlockEndIndex(List<String> lines, int startIndex) {
        if (startIndex == lines.size())
            return -1;

        int endIndex = startIndex;
        while (endIndex < lines.size() && !StringUtils.isBlank(lines.get(endIndex))) {
            endIndex++;
        }
        return endIndex;
    }
}
