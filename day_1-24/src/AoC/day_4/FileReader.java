package AoC.day_4;

import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileReader {

    public static LinkedList<Integer> readDrawNumbers(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        String drawNumberAsString = "";
        for (String line : lines) {
            if (StringUtils.isBlank(line))
                break;
            drawNumberAsString += line;
        }
        LinkedList<String> drawNumbersAsStrings =  new LinkedList<String>(Arrays.asList(drawNumberAsString.split(",")));
        LinkedList<Integer> drawsNumbersAsInteger = new LinkedList();
        for (String numberAsString : drawNumbersAsStrings) {
            drawsNumbersAsInteger.add(Integer.parseInt(numberAsString));
        }
        return drawsNumbersAsInteger;
    }

    public static LinkedList<Board> readBoards(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        LinkedList<Board> boards = new LinkedList();

        int startIndex = getNextEndIndex(lines, 0) + 1;
        int endIndex = 0;

        while (endIndex != lines.size()) {
            endIndex = getNextEndIndex(lines, startIndex);
            boards.add(new Board(lines.subList(startIndex, endIndex)));
            startIndex = endIndex + 1;
        }
        return boards;
    }

    /**
     * @param lines list of board lines blocks, separated with empty lines
     * @param startIndex index of next board lines block
     * @return end index of next board lines block, or -1 if no next board lines block exists
     */
     static int getNextEndIndex(List<String> lines, int startIndex) {
        if (startIndex == lines.size())
            return -1;

        int endIndex = startIndex;
        while (endIndex < lines.size() && !StringUtils.isBlank(lines.get(endIndex))) {
            endIndex++;
        }
        return endIndex;
    }
}
