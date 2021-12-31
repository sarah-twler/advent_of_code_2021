package AoC.day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.LinkedList;
import java.util.List;

public class Task1Day2 {

    private int horizontalPos = 0;
    private int depthPos = 0;

    public int moveSubmarine(LinkedList<DirectionsTuple> directions) throws UnexpectedException {
        for (DirectionsTuple move : directions) {
            switch(move.getDirection()) {
                case FORWARD:
                    moveForward(move.getSteps());
                    break;
                case UP:
                    moveUp(move.getSteps());
                    break;
                case DOWN:
                    moveDown(move.getSteps());
                    break;
                default:
                    throw new UnexpectedException("Something went wrong!");
            }
        }
        return calcFinalResult();
    }

    private int calcFinalResult() {
        return horizontalPos * depthPos;
    }

    private void moveForward(int steps) {
        horizontalPos += steps;
    }

    private void moveUp(int steps) {
        depthPos -= steps;
    }

    private void moveDown(int steps) {
        depthPos += steps;
    }

    public int getHorizontalPos() {
        return  horizontalPos;
    }
    public int getDepthPos() {
        return depthPos;
    }

}
