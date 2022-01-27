package AoC.day_18;

import org.junit.platform.commons.util.StringUtils;

import java.io.Serializable;
import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class SnailfishHomework {

    private static final Pattern pattern = Pattern.compile("^\\d*$");

    public static String addSnailfishNumbers(List<String> lines) throws UnexpectedException {
        String snailNumber = lines.get(0);
        for (int i = 1; i < lines.size(); i++) {
            snailNumber = addNumber(snailNumber, lines.get(i));
        }
        return snailNumber;
    }

    public static String addNumber(String snailNumber1, String snailNumber2) throws UnexpectedException {
//        return  String.format(String.format("[%s,%s]", reduceNumber(snailNumber1), reduceNumber(snailNumber2)));
        return reduceNumberOuter(String.format("[%s,%s]", snailNumber1, snailNumber2));
    }

    static String reduceNumberOuter(String snailNumber) throws UnexpectedException {
        LinkedList<String> snailList = convertStringToList(snailNumber);
        return reduceNumber(snailList);
    }

    static String reduceNumber(LinkedList<String> snailList) throws UnexpectedException {
        String current = convertListToString(snailList);
        int index = 0;
        while (index < snailList.size()) {
            String item = snailList.get(index);
            if (!isNumeric(item)) {
                index++;
                continue;
            }
            int num1 = Integer.parseInt(item);
            if (num1 >= 10) {
                splitNumber(snailList, index);
                current = convertListToString(snailList);
                System.out.println(convertListToString(snailList));
                continue;
            } else if (countOpenBrackets(snailList, index) > 4) {
                if (isSimplePair(snailList, index)) {
                    int num2 = Integer.parseInt(snailList.get(index + 2));
                    if (num2 >= 10) {
                        index = index + 2;
                        continue;
                    } else {
                        String before = convertListToString(snailList);
                        explodePair(snailList, index);
                        index = index - 1;
                        current = convertListToString(snailList);
                        System.out.println(convertListToString(snailList));
                        index = findNextNumIndex(snailList, index, -1);
                    }
                } else {
                    index++;
                    continue;
                }
            } else {
                index++;
            }
        }
        return convertListToString(snailList);
    }

    private static int countOpenBrackets(LinkedList<String> snailList, int untilIndex) {
        int index = 0;
        int bracketCount = 0;
        while (index <= untilIndex) {
            String item = snailList.get(index);
            if ("[".equals(item))
                bracketCount++;
            else if ("]".equals(item))
                bracketCount--;
            index++;
        }
        return bracketCount;
    }

     static String reduceNumber2(String snailNumber) throws UnexpectedException {
        LinkedList<String> snailList = convertStringToList(snailNumber);

        boolean isDone = false;
        while (!isDone) {
            int bracketCounter = 0;
            for (int i = 0; i < snailList.size(); i++) {
                String item = snailList.get(i);
                if ("[".equals(item))
                    bracketCounter++;
                else if ("]".equals(item))
                    bracketCounter--;
                else if (isNumeric(item)) {
                    int num = Integer.parseInt(item);
                    if (num >= 10) {
                        splitNumber(snailList, i);
                        bracketCounter++;
                        i++;
//                        break;
                    }
                    if (bracketCounter > 4 && isSimplePair(snailList, i) && Integer.parseInt(snailList.get(i + 2)) < 10) {
                        explodePair(snailList, i);
                        break;
                    }
                }
            }
            System.out.println(convertListToString(snailList));
            if (bracketCounter == 0)
//                throw new UnexpectedException("Bracket counter is not 0 at the end of processing the entire snail number!");
                isDone = true;
        }
        return convertListToString(snailList);
    }



    /**
     * @param snailNumber
     * @param index of number
     * @return
     */
    static LinkedList<String> splitNumber(LinkedList<String> snailNumber, int index) {
        int snailNum = Integer.parseInt(snailNumber.get(index));
        int split1 = (int) ((double) snailNum / 2);
        int split2 = split1 * 2 == snailNum ? split1 : split1 + 1;
        List<String> newItem = Arrays.asList("[", String.valueOf(split1), ",", String.valueOf(split2), "]");
        snailNumber.remove(index);
        snailNumber.addAll(index, newItem);
        return snailNumber;
    }

    /**
     * @param snailNumber
     * @param index of first digit in pair
     * @return
     */
     static LinkedList<String> explodePair(LinkedList<String> snailNumber, int index) throws UnexpectedException {
        int origSize = snailNumber.size();
        addToNeighbour(snailNumber, index, -1);
        addToNeighbour(snailNumber, index + 2, 1);
        int tmpIndex = index - 1;
        int counter = 5;
        while (counter-- > 0)
            snailNumber.remove(tmpIndex);
        snailNumber.add(tmpIndex, String.valueOf(0));
        if (snailNumber.size() != origSize - 4)
            throw new UnexpectedException("Something went wrong with exploding the pair!");
        return snailNumber;
     }

    private static void addToNeighbour(LinkedList<String> snailNumber, int index, int direction) {
        int tmpIndex = index + direction;
        String item = "x";
        int maxIndex = snailNumber.size() - 1;
        while (tmpIndex >= 0 && tmpIndex <= maxIndex && !isNumeric(item)) {
            item = snailNumber.get(tmpIndex);
            tmpIndex = tmpIndex + direction;
        }
        tmpIndex = tmpIndex + direction * -1;
        if (tmpIndex > 0 && tmpIndex < maxIndex) { // findNextNumIndex != -1
            int newNum = Integer.parseInt(snailNumber.get(index)) + Integer.parseInt(item);
            snailNumber.set(tmpIndex, String.valueOf(newNum));
        }
    }

    private static int findNextNumIndex(LinkedList<String> snailNumber, int index, int direction) {
        int tmpIndex = index + direction;
        String item = "x";
        int maxIndex = snailNumber.size() - 1;
        while (tmpIndex >= 0 && tmpIndex <= maxIndex && !isNumeric(item)) {
            item = snailNumber.get(tmpIndex);
            tmpIndex = tmpIndex + direction;
        }
        tmpIndex = tmpIndex + direction * -1;
        return tmpIndex >= 0 && tmpIndex <= maxIndex ? tmpIndex : -1;
    }

    private static boolean isNumeric(String strNum) {
        if (StringUtils.isBlank(strNum))
            return false;
        return pattern.matcher(strNum).matches();
    }

    static String convertListToString(LinkedList<String> list) {
        String str = "";
        for (String item : list) {
            str += item;
        }
        return str;
    }

    static LinkedList<String> convertStringToList(String string) {
        LinkedList<String> list = new LinkedList();
        for (int  i = 0; i < string.length(); i++) {
            list.add(String.valueOf(string.charAt(i)));
        }
        return list;
    }

    /**
     * @param snailNumber
     * @param index of first digit in pair
     */
    private static boolean isSimplePair(LinkedList<String> snailNumber, int index) {
        return "[".equals(snailNumber.get(index - 1))
                && isNumeric(snailNumber.get(index))
                && ",".equals(snailNumber.get(index + 1))
                && isNumeric(snailNumber.get(index + 2))
                && "]".equals(snailNumber.get(index + 3));
    }


}
