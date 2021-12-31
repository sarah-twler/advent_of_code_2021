package AoC.day_17;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrickShot {

    public static int getLowerBorder(String coordTarget) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(coordTarget);
        matcher.find();
        matcher.find();
        matcher.find();
        return Integer.parseInt(matcher.group());
    }

    public static ArrayList<String> findAllVelocityValues(String coordTarget) {
        ArrayList<String> velocities = new ArrayList();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(coordTarget);
        int startCol = getMatchingGroup(matcher);
        int endCol = getMatchingGroup(matcher);
        int endRow = getMatchingGroup(matcher);
        int startRow = getMatchingGroup(matcher);

        // all that land directly in target
        for (int forwardX = startCol; forwardX <= endCol; forwardX++) {
            for (int upwardY = startRow; upwardY <= endRow; upwardY++) {
                velocities.add(String.format("%s,%s", forwardX, upwardY * -1));
            }
        }

        // get min that lands with highest height in target
        int coordX = 0;
        int minVelocityX = 0;
        while (coordX < startCol) {
            minVelocityX++;
            coordX = coordX + minVelocityX;
        }

        int maxVelocityY = getMaxY(coordTarget);
        int velocityX = minVelocityX;
        int velocityY = maxVelocityY;

        while (velocityY >= maxVelocityY * -1) {
            while (velocityX < startCol) {
                int progX = 0;
                int progY = 0;
                int stepForward = velocityX;
                int stepUpward = velocityY;
                // walk
                while (true) {
                    progX += stepForward;
                    progY += stepUpward;
                    if (progX > endCol || progY < 0 && progY * -1 > endRow) {
                        // out of range!
                        break;
                    }
                    if (startCol <= progX && progX <= endCol && progY < 0 && startRow <= progY * -1 && progY * -1 <= endRow) {
                        // hit!
                        velocities.add(String.format("%s,%s", velocityX, velocityY));
                        break;
                    }
                    if (stepForward > 0)
                        stepForward--;
                    stepUpward--;
                }
                velocityX++;
            }
            velocityX = minVelocityX;
            velocityY--;
        }
        return velocities;
    }

    private static int getMatchingGroup(Matcher matcher) {
        matcher.find();
        return Integer.parseInt(matcher.group());
    }

    public static int getMaxY(String coordTarget) {
        int lowerBorder = getLowerBorder(coordTarget);
        return lowerBorder - 1;
    }

    public static int getMaxHeight(String coordTarget) {
        int y = getMaxY(coordTarget);
        int height = 0;
        while (y > 0) {
            height += y;
            y--;
        }
        return height;
    }

}
