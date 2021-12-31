package AoC.day_2;

import java.rmi.UnexpectedException;
import java.util.LinkedList;

public class Task2Day2 {

    private int horizontalPos = 0;
    private int depthPos = 0;
    private int aim = 0;

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

    int getHorizontalPos() {
        return  horizontalPos;
    }
    int getDepthPos() {
        return depthPos;
    }

    private int calcFinalResult() {
        return horizontalPos * depthPos;
    }

    private void moveForward(int steps) {
        horizontalPos += steps;
        depthPos += aim * steps;
    }

    private void moveUp(int steps) {
        aim -= steps;
    }

    private void moveDown(int steps) {
        aim += steps;
    }

}
