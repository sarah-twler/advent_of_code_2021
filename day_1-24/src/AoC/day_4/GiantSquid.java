package AoC.day_4;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.LinkedList;

public class GiantSquid {

    public int playBingoFirstWin(LinkedList<Integer> drawNumbers, LinkedList<Board> boards) {

        for (int currNumber : drawNumbers) {
            for (Board currBoard : boards) {
                currBoard.markNumberInBoard(currNumber);
                if (currBoard.isWinningBoard()){
                    return currNumber * currBoard.getSumOfUnmarkedNumbers();
                }
            }
        }

        return -1;
    }

    public int playBingoLastWin(LinkedList<Integer> drawNumbers, LinkedList<Board> boards) throws UnexpectedException {

        for (int numIndex = 0; numIndex < drawNumbers.size(); numIndex++) {
            int currNumber = drawNumbers.get(numIndex);
            ArrayList<Board> boardsToRemove = new ArrayList();

            for (Board currBoard : boards) {
                currBoard.markNumberInBoard(currNumber);
                if (currBoard.isWinningBoard())
                    boardsToRemove.add(currBoard);
            }
            if (boards.size() == 1 && boardsToRemove.size() == 1)
                return currNumber * boards.get(0).getSumOfUnmarkedNumbers();
            boards.removeAll(boardsToRemove);
        }

        return -1;
    }

}
