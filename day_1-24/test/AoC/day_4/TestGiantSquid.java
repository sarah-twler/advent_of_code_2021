package AoC.day_4;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestGiantSquid {

    private static final String filePath = "./data/day_4";
    private static final String testInputFile = filePath + "/test_input.txt";

    @Test
    public void testPlayBingoLastWin() throws IOException {
        GiantSquid task = new GiantSquid();

        LinkedList<Integer> drawNumbers = FileReader.readDrawNumbers(testInputFile);
        LinkedList<Board> boards = FileReader.readBoards(testInputFile);

        assertEquals(1924, task.playBingoLastWin(drawNumbers, boards));
        assertEquals(148, boards.get(0).getSumOfUnmarkedNumbers());
    }

    @Test
    public void testPlayBingoFirstWin() throws IOException {
        GiantSquid task = new GiantSquid();

        LinkedList<Integer> drawNumbers = FileReader.readDrawNumbers(testInputFile);
        LinkedList<Board> boards = FileReader.readBoards(testInputFile);

        // round 1
        LinkedList<Integer> drawNumbersR1 = new LinkedList(drawNumbers.subList(0, 1));
        assertEquals(-1, task.playBingoFirstWin(drawNumbersR1, boards));
        assertEquals(293, boards.get(0).getSumOfUnmarkedNumbers());
        assertEquals(317, boards.get(1).getSumOfUnmarkedNumbers());
        assertEquals(318, boards.get(2).getSumOfUnmarkedNumbers());

        // round 5
        LinkedList<Integer> drawNumbersR5 = new LinkedList(drawNumbers.subList(1, 5));
        assertEquals(-1, task.playBingoFirstWin(drawNumbersR5, boards));
        assertEquals(264, boards.get(0).getSumOfUnmarkedNumbers());
        assertEquals(288, boards.get(1).getSumOfUnmarkedNumbers());
        assertEquals(289, boards.get(2).getSumOfUnmarkedNumbers());

        // round 11
        LinkedList<Integer> drawNumbersR11 = new LinkedList(drawNumbers.subList(5, 11));
        assertEquals(-1, task.playBingoFirstWin(drawNumbersR11, boards));
        assertEquals(187, boards.get(0).getSumOfUnmarkedNumbers());
        assertEquals(211, boards.get(1).getSumOfUnmarkedNumbers());
        assertEquals(212, boards.get(2).getSumOfUnmarkedNumbers());

        // round 12
        assertFalse(boards.get(0).isWinningBoard());
        assertFalse(boards.get(1).isWinningBoard());
        assertFalse(boards.get(2).isWinningBoard());

        LinkedList<Integer> drawNumbersR12 = new LinkedList(drawNumbers.subList(11, 12));
        assertEquals(4512, task.playBingoFirstWin(drawNumbersR12, boards));
        assertEquals(163, boards.get(0).getSumOfUnmarkedNumbers());
        assertEquals(187, boards.get(1).getSumOfUnmarkedNumbers());
        assertEquals(188, boards.get(2).getSumOfUnmarkedNumbers());

        assertFalse(boards.get(0).isWinningBoard());
        assertFalse(boards.get(1).isWinningBoard());
        assertTrue(boards.get(2).isWinningBoard());
    }

    @Test
    public void testMarkNumberInBoard() throws IOException {
        LinkedList<Board> boards = FileReader.readBoards(testInputFile);

        assertArrayEquals("Board 0", new int[] {2,0}, boards.get(0).markNumberInBoard(21));
        assertArrayEquals("Board 1", new int[] {4,1}, boards.get(1).markNumberInBoard(21));
        assertArrayEquals("Board 2", new int[] {0,1}, boards.get(2).markNumberInBoard(21));
    }

    @Test
    public void testReadDrawNumbers() throws IOException {
        LinkedList<Integer> drawNumbers = FileReader.readDrawNumbers(testInputFile);

        assertArrayEquals(drawNumbers.toArray(new Integer[drawNumbers.size()]), getExpectedDrawNumbers());
    }

    @Test
    public void testIsWinningBoard() throws IOException {
        LinkedList<Board> boards = FileReader.readBoards(testInputFile);

        Board board0 = boards.get(0);
        assertFalse(board0.isWinningBoard());
        board0.markNumberInBoard(21);
        assertFalse(board0.isWinningBoard());
        board0.markNumberInBoard(9);
        assertFalse(board0.isWinningBoard());
        board0.markNumberInBoard(14);
        assertFalse(board0.isWinningBoard());
        board0.markNumberInBoard(16);
        assertFalse(board0.isWinningBoard());
        board0.markNumberInBoard(7);
        assertTrue(board0.isWinningBoard());

        Board board1 = boards.get(1);
        assertFalse(board1.isWinningBoard());
        board1.markNumberInBoard(0);
        assertFalse(board1.isWinningBoard());
        board1.markNumberInBoard(13);
        assertFalse(board1.isWinningBoard());
        board1.markNumberInBoard(7);
        assertFalse(board1.isWinningBoard());
        board1.markNumberInBoard(10);
        assertFalse(board1.isWinningBoard());
        board1.markNumberInBoard(16);
        assertTrue(board1.isWinningBoard());
    }

    
    @Test
    public void testReadBoards() throws IOException {
        LinkedList<Board> boards = FileReader.readBoards(testInputFile);

        assertEquals(3, boards.size());
        assertEquals(300, boards.get(0).getSumOfUnmarkedNumbers());
        assertEquals(324, boards.get(1).getSumOfUnmarkedNumbers());
        assertEquals(325, boards.get(2).getSumOfUnmarkedNumbers());

        String expected1 = "22 13 17 11 0 \n" +
                "8 2 23 4 24 \n" +
                "21 9 14 16 7 \n" +
                "6 10 3 18 5 \n" +
                "1 12 20 15 19 \n";
        assertEquals(expected1, boards.get(0).toString());

        String expected2 = "3 15 0 2 22 \n" +
                "9 18 13 17 5 \n" +
                "19 8 7 25 23 \n" +
                "20 11 10 24 4 \n" +
                "14 21 16 12 6 \n";
        assertEquals(expected2, boards.get(1).toString());

        String expected3 = "14 21 17 24 4 \n" +
                "10 16 15 9 19 \n" +
                "18 8 23 26 20 \n" +
                "22 11 13 6 5 \n" +
                "2 0 12 3 7 \n";
        assertEquals(expected3, boards.get(2).toString());
    }

    @Test
    public void testGetNextEndIndex() {
        List<String> boardLines = createBoardLines();
        assertEquals(6, FileReader.getNextEndIndex(boardLines, 1));
        assertEquals(12, FileReader.getNextEndIndex(boardLines, 7));
        assertEquals(18, FileReader.getNextEndIndex(boardLines, 13));
    }

    private Integer[] getExpectedDrawNumbers() {
        return new Integer[] {7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1,7,4,9,5,11,17,23,2,0,14,21,24,10};
    }

    private List<String> createBoardLines() {
        List<String> lines = new ArrayList();

        lines.add("");                  // 0
        lines.add("22 13 17 11  0");    // 1
        lines.add("8  2 23  4 24");     // 2
        lines.add("21  9 14 16  7");    // 3
        lines.add("6 10  3 18  5");     // 4
        lines.add("1 12 20 15 19");     // 5
        lines.add("");                  // 6
        lines.add("3 15  0  2 22");     // 7
        lines.add("9 18 13 17  5");     // 8
        lines.add("19  8  7 25 23");    // 9
        lines.add("20 11 10 24  4");    // 10
        lines.add("14 21 16 12  6");    // 11
        lines.add("");                  // 12
        lines.add("14 21 17 24  4");    // 13
        lines.add("10 16 15  9 19");    // 14
        lines.add("18  8 23 26 20");    // 15
        lines.add("22 11 13  6  5");    // 16
        lines.add("2  0 12  3  7");     // 17

        return lines;
    }

}
