package AoC.day_17;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TestTrickShot {

    @Test
    public void testGetLowerBorder() {
        assertEquals(10, TrickShot.getLowerBorder("target area: x=20..30, y=-10..-5"));
        assertEquals(86, TrickShot.getLowerBorder("target area: x=209..238, y=-86..-59"));
    }

    @Test
    public void testGetMaxY() {
        assertEquals(9, TrickShot.getMaxY("target area: x=20..30, y=-10..-5"));
    }

    @Test
    public void testGetMaxHeight() {
        assertEquals(45, TrickShot.getMaxHeight("target area: x=20..30, y=-10..-5"));
    }

    @Test
    public void testFindAllVelocityValues() {
        ArrayList<String> actualVelocities = TrickShot.findAllVelocityValues("target area: x=20..30, y=-10..-5");
        List<String> expectedVelocities = getExpectedVelocities();
        assertEquals(112, actualVelocities.size());
        assertTrue(expectedVelocities.size() == actualVelocities.size());
        assertTrue(expectedVelocities.containsAll(actualVelocities));
        assertTrue(actualVelocities.containsAll(expectedVelocities));
    }

    private String toString(ArrayList<String> list) {
        Collections.sort(list);
        String out = "";
        for (String item : list)
            out += String.format("%s\n", item);
        return out;
    }

    private List<String> getExpectedVelocities() {
        return Arrays.asList(new String[] {"10,-1", "10,-2", "11,-1", "11,-2", "11,-3", "11,-4", "12,-2", "12,-3", "12,-4", "13,-2",
                "13,-3", "13,-4", "14,-2", "14,-3", "14,-4", "15,-2", "15,-3", "15,-4", "20,-10", "20,-5", "20,-6",
                "20,-7", "20,-8", "20,-9", "21,-10", "21,-5", "21,-6", "21,-7", "21,-8", "21,-9", "22,-10", "22,-5",
                "22,-6", "22,-7", "22,-8", "22,-9", "23,-10", "23,-5", "23,-6", "23,-7", "23,-8", "23,-9", "24,-10",
                "24,-5", "24,-6", "24,-7", "24,-8", "24,-9", "25,-10", "25,-5", "25,-6", "25,-7", "25,-8", "25,-9",
                "26,-10", "26,-5", "26,-6", "26,-7", "26,-8", "26,-9", "27,-10", "27,-5", "27,-6", "27,-7", "27,-8",
                "27,-9", "28,-10", "28,-5", "28,-6", "28,-7", "28,-8", "28,-9", "29,-10", "29,-5", "29,-6", "29,-7",
                "29,-8", "29,-9", "30,-10", "30,-5", "30,-6", "30,-7", "30,-8", "30,-9", "6,0", "6,1", "6,2", "6,3",
                "6,4", "6,5", "6,6", "6,7", "6,8", "6,9",  "7,-1", "7,0", "7,1", "7,2", "7,3", "7,4", "7,5", "7,6",
                "7,7", "7,8", "7,9",  "8,-1", "8,-2", "8,0", "8,1",  "9,-1", "9,-2", "9,0"});
    }
}
