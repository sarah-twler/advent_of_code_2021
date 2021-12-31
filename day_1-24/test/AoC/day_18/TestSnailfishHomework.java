package AoC.day_18;

import AoC.day_18.SnailfishHomework;
import org.junit.Test;

import java.lang.reflect.Array;
import java.rmi.UnexpectedException;
import java.util.*;

import static org.junit.Assert.*;

public class TestSnailfishHomework {

    @Test
    public void testAddSnailfishNumbers() throws UnexpectedException {
        testAddSnailfishNumbers("[[[[1,1],[2,2]],[3,3]],[4,4]]", "[1,1]", "[2,2]", "[3,3]", "[4,4]");
        testAddSnailfishNumbers("[[[[3,0],[5,3]],[4,4]],[5,5]]", "[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]");
        testAddSnailfishNumbers("[[[[5,0],[7,4]],[5,5]],[6,6]]", "[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]", "[6,6]");

        testAddSnailfishNumbers("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", "[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]");
//        testReduceNumber("[[[8,[7,7]],[[7,9],[5,0]]]]", "[[[7,7],[[[3,7],[4,3]],[[6,3],[8,8]]]]]]");
//
        testAddSnailfishNumbers("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
                        "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]");
////
//        testAddSnailfishNumbers("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",
//                "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
//                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]");
//
//        testAddSnailfishNumbers("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]",
//                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
//                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
//                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
//                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]");
//
//        testAddSnailfishNumbers("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]",
//                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
//                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
//                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
//                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
//                "[7,[5,[[3,8],[1,4]]]]",
//                "[[2,[2,2]],[8,[8,1]]]",
//                "[2,9]",
//                "[1,[[[9,3],9],[[9,0],[0,7]]]]",
//                "[[[5,[7,4]],7],1]",
//                "[[[[4,2],2],6],[8,7]]");
//        testAddSnailfishNumbers("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]",
//                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
//                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
//                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
//                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
//                "[7,[5,[[3,8],[1,4]]]]",
//                "[[2,[2,2]],[8,[8,1]]]",
//                "[2,9]",
//                "[1,[[[9,3],9],[[9,0],[0,7]]]]",
//                "[[[5,[7,4]],7],1]",
//                "[[[[4,2],2],6],[8,7]]");
//        testAddSnailfishNumbers("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]",
//                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
//                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
//                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
//                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
//                "[7,[5,[[3,8],[1,4]]]]",
//                "[[2,[2,2]],[8,[8,1]]]",
//                "[2,9]",
//                "[1,[[[9,3],9],[[9,0],[0,7]]]]",
//                "[[[5,[7,4]],7],1]",
//                "[[[[4,2],2],6],[8,7]]");


    }

    public void testAddSnailfishNumbers(String expectedNum, String... items) throws UnexpectedException {
        System.out.println("");
        ArrayList<String> list = new ArrayList(Arrays.asList(items));
        String actual = SnailfishHomework.addSnailfishNumbers(list);
        assertEquals(expectedNum, actual);
    }

    @Test
    public void testReduceNumber() throws UnexpectedException {
        testReduceNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");
    }

    @Test
    public void testExplodePair() throws UnexpectedException {
        testExplodePair("[[[[0,9],2],3],4]", "[[[[[9,8],1],2],3],4]", 5);
        testExplodePair("[7,[6,[5,[7,0]]]]", "[7,[6,[5,[4,[3,2]]]]]", 13);
        testExplodePair("[[6,[5,[7,0]]],3]", "[[6,[5,[4,[3,2]]]],1]", 11);
        testExplodePair("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]", 11);
        testExplodePair("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", 25);
    }

    @Test
    public void testSplitNumber() {
        LinkedList<String> expectedNum1 = SnailfishHomework.convertStringToList("[[[[0,7],4],[[7,8],[0,y]]],[1,1]]");
        expectedNum1.set(expectedNum1.indexOf("y"), "13");

        LinkedList<String> list = SnailfishHomework.convertStringToList("[[[[0,7],4],[x,[0,y]]],[1,1]]");
        list.set(list.indexOf("x"), "15");
        list.set(list.indexOf("y"), "13");
        LinkedList<String> actualNum1 = SnailfishHomework.splitNumber(list, list.indexOf("15"));

        assertEquals(expectedNum1.size(), actualNum1.size());
        assertEquals(expectedNum1.toString(), actualNum1.toString());

        LinkedList<String> expectedNum2 = SnailfishHomework.convertStringToList("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]");
        LinkedList<String> actualNum2 = SnailfishHomework.splitNumber(list, list.indexOf("13"));

        assertEquals(expectedNum2.size(), actualNum2.size());
        assertEquals(expectedNum2.toString(), actualNum2.toString());
    }

    public void testReduceNumber(String expectedNum, String inputNum) throws UnexpectedException {
//        String actualNum = SnailfishHomework.reduceNumber("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");
        String actualNum = SnailfishHomework.reduceNumberOuter("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");
        assertEquals(expectedNum, actualNum);
    }

    public void testExplodePair(String strExpectedNum, String inputNum, int index) throws UnexpectedException {
        LinkedList<String> expectedNum = SnailfishHomework.convertStringToList(strExpectedNum);
        LinkedList<String> actualNum = SnailfishHomework.explodePair(SnailfishHomework.convertStringToList(inputNum), index);
        assertEquals(expectedNum.size(), actualNum.size());
        assertEquals(expectedNum.toString(), actualNum.toString());
    }
}
