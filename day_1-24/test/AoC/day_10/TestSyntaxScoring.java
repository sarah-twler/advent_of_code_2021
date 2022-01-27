package AoC.day_10;

import static org.junit.Assert.*;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSyntaxScoring {

    private static final String filePath = "./data/day_10";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static final String taskInputFile = filePath + "/task_input.txt";
    private static List<String> testfileInputList;
    private static List<String> taskfileInputList;


    @BeforeClass
    public static void init() throws IOException {
        testfileInputList = FileReader.readFileToLines(testInputFile);
        taskfileInputList = FileReader.readFileToLines(taskInputFile);
    }

    private static SyntaxScoring syntaxScoring;

    @Before
     public void setup() {
        syntaxScoring = new SyntaxScoring();
    }

    @Test
    public void testCheckForIncompleteLines() {
        assertEquals(new BigInteger("288957"), syntaxScoring.checkForIncompleteLines(testfileInputList));
    }

    @Test
    public void testCheckForCorruptedLines() {
        assertEquals(26397, syntaxScoring.checkForCorruptedLines(testfileInputList));
    }

    @Test
    public void testFindCompletionString() {
        List<Character> actualList1 = syntaxScoring.findCompletionString("[({(<(())[]>[[{[]{<()<>>");
        List<Character> expectedList1 = DataConverter.convertStringToCharacterList("}}]])})]");
        assertArrayEquals(actualList1.toArray(new Character[0]), expectedList1.toArray(new Character[0]));

        List<Character> actualList2 = syntaxScoring.findCompletionString("[(()[<>])]({[<{<<[]>>(");
        List<Character> expectedList2 = DataConverter.convertStringToCharacterList(")}>]})");
        assertArrayEquals(actualList2.toArray(new Character[0]), expectedList2.toArray(new Character[0]));

        List<Character> actualList3 = syntaxScoring.findCompletionString("(((({<>}<{<{<>}{[]{[]{}");
        List<Character> expectedList3 = DataConverter.convertStringToCharacterList("}}>}>))))");
        assertArrayEquals(actualList3.toArray(new Character[0]), expectedList3.toArray(new Character[0]));

        List<Character> actualList4 = syntaxScoring.findCompletionString("{<[[]]>}<{[{[{[]{()[[[]");
        List<Character> expectedList4 = DataConverter.convertStringToCharacterList("]]}}]}]}>");
        assertArrayEquals(actualList4.toArray(new Character[0]), expectedList4.toArray(new Character[0]));

        List<Character> actualList5 = syntaxScoring.findCompletionString("<{([{{}}[<[[[<>{}]]]>[]]");
        List<Character> expectedList5 = DataConverter.convertStringToCharacterList("])}>");
        assertArrayEquals(actualList5.toArray(new Character[0]), expectedList5.toArray(new Character[0]));

        // valid list
        List<Character> actualListValid = syntaxScoring.findCompletionString("[<>({}){}[([])<>]]");
        List<Character> expectedListValid = DataConverter.convertStringToCharacterList("");
        assertArrayEquals(actualListValid.toArray(new Character[0]), expectedListValid.toArray(new Character[0]));
    }

    @Test
    public void testGetCompletionSequenceScore() {
        assertEquals(new BigInteger("288957"),
                syntaxScoring.getCompletionSequenceScore(
                        DataConverter.convertStringToCharacterList("}}]])})]")));
        assertEquals(new BigInteger("5566"),
                syntaxScoring.getCompletionSequenceScore(
                        DataConverter.convertStringToCharacterList(")}>]})")));
        assertEquals(new BigInteger("1480781"),
                syntaxScoring.getCompletionSequenceScore(
                        DataConverter.convertStringToCharacterList("}}>}>))))")));
        assertEquals(new BigInteger("995444"),
                syntaxScoring.getCompletionSequenceScore(
                        DataConverter.convertStringToCharacterList("]]}}]}]}>")));
        assertEquals(new BigInteger("294"),
                syntaxScoring.getCompletionSequenceScore(
                        DataConverter.convertStringToCharacterList("])}>")));
    }

    @Test
    public void testCreateScoreListIncompleteLines() throws IOException {
        ArrayList<BigInteger> actualList = syntaxScoring.createScoreListIncompleteLines(FileReader.readFileToLines(testInputFile));
        ArrayList<Integer> expectedList = new ArrayList(Arrays.asList( new BigInteger[] { new BigInteger("288957"), new BigInteger("5566"), new BigInteger("1480781"), new BigInteger("995444"), new BigInteger("294") }));

        assertEquals("List size does not match!", expectedList.size(), actualList.size());
        assertTrue(expectedList.containsAll(actualList));
        assertTrue(actualList.containsAll(expectedList));
    }

    @Test
    public void testFindCorruptChar() {
        assertEquals("}", Character.toString(syntaxScoring.findCorruptChar("{([(<{}[<>[]}>{[]{[(<()>")));
        assertEquals(")", Character.toString(syntaxScoring.findCorruptChar("[[<[([]))<([[{}[[()]]]")));
        assertEquals("]", Character.toString(syntaxScoring.findCorruptChar("[{[{({}]{}}([{[{{{}}([]")));
        assertEquals(")", Character.toString(syntaxScoring.findCorruptChar("[<(<(<(<{}))><([]([]()")));
        assertEquals(">", Character.toString(syntaxScoring.findCorruptChar("<{([([[(<>()){}]>(<<{{")));

        // incomplete line
        assertEquals(0, syntaxScoring.findCorruptChar("[<>({}){}[([])<"));

        // valid line
        assertEquals(0, syntaxScoring.findCorruptChar("[<>({}){}[([])<>]]"));
    }

    @Test
    public void testMetadata() {
        assertEquals(testfileInputList.size(),
                syntaxScoring.createScoreListIncompleteLines(testfileInputList).size()
                        + countCorruptedLines(testfileInputList));

        assertEquals(taskfileInputList.size(),
                syntaxScoring.createScoreListIncompleteLines(taskfileInputList).size()
                        + countCorruptedLines(taskfileInputList));
    }

    private int countCorruptedLines(List<String> inputList) {
        int countCorruptedLines = 0;
        for (String line : inputList) {
            char out = syntaxScoring.findCorruptChar(line);
            if (out != 0)
                countCorruptedLines++;
        }
        return  countCorruptedLines;
    }
}
