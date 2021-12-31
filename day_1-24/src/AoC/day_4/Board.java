package AoC.day_4;

import java.io.IOException;
import java.util.List;

public class Board {

    private MarkableInteger[][] board;

    public Board(List<String> lines) throws IOException {
        if (lines == null || lines.isEmpty())
            throw new IOException("Empty list!");

        this.board = new MarkableInteger[lines.size()][lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            String[] row = line.split("\\s+");
            for (int j = 0; j < row.length; j++) {
                board[i][j] = new MarkableInteger(Integer.parseInt(row[j]));
            }
        }
    }

    public int getSumOfUnmarkedNumbers() {
        int sum = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isMarked())
                    sum += board[i][j].getValue();
            }
        }
        return sum;
    }

    public boolean isWinningBoard() {
        return hasCompleteRow() || hasCompleteColumn();
    }

    public int[] markNumberInBoard(int number) {
        int[] pos = new int[] {-1, -1};
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column].getValue() == number) {
                    board[row][column].mark();
                    pos[0] = row;
                    pos[1] = column;
                    return pos;
                }
            }
        }
        return pos;
    }

    public String toString() {
        String out = "";
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                out += board[row][column].getValue() + " ";
            }
            out += "\n";
        }
        return out ;
    }

    public String toStringMarks() {
        String out = "";
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                out += board[row][column].isMarked() + " ";
            }
            out += "\n";
        }
        return out ;
    }

    private boolean hasCompleteRow() {
        boolean hasCompleteRow = false;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (!board[row][column].isMarked())
                    break;
                if (column == board[row].length -1)
                    hasCompleteRow = true;
            }
            if (hasCompleteRow)
                break;
        }
        return  hasCompleteRow;
    }

    private boolean hasCompleteColumn() {
        boolean hasCompleteColumn = false;
        for (int column = 0; column < board[0].length; column++) {
            for (int row = 0; row < board.length; row++) {
                if (!board[row][column].isMarked())
                    break;
                if (row == board.length -1)
                    hasCompleteColumn = true;
            }
            if (hasCompleteColumn)
                break;
        }
        return  hasCompleteColumn;
    }
}
