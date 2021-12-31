package AoC.day_8;

import AoC.data_manager.DataConverter;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.*;

public class SevenSegmentSearch {

    private final String SECTION_DELIMITER = "\\|";
    private final String SEGMENT_DELIMITER = " ";
    private final int NUM_OF_DIGITS = 10;

    private final int LENGTH_OF_DIGIT_ONE = 2;              //    aaaa
    private final int LENGTH_OF_DIGIT_FOUR = 4;             //   b    c
    private final int LENGTH_OF_DIGIT_SEVEN = 3;            //   b    c
    private final int LENGTH_OF_DIGIT_EIGHT = 7;            //    dddd
    private final int LENGTH_OF_DIGIT_NINE_SIX_ZERO = 6;    //   e    f
    private final int LENGTH_OF_DIGIT_FIVE_TWO_THREE = 5;   //   e    f
                                                            //    gggg

    public int countUniqueDigitPatterns(List<String> inputList) {
        int totalCount = 0;
        for (String input : inputList) {
            String[] segments = getMessedUpOutputDigitSignals(input);
            for (int i = 0; i < segments.length; i++) {
                String segment = segments[i];
                if (hasLengthOf(LENGTH_OF_DIGIT_ONE, segment)
                        || hasLengthOf(LENGTH_OF_DIGIT_FOUR, segment)
                        || hasLengthOf(LENGTH_OF_DIGIT_SEVEN, segment)
                        || hasLengthOf(LENGTH_OF_DIGIT_EIGHT, segment))
                    totalCount++;
            }
        }
        return totalCount;
    }

    public int getOutputValueAllLines(List<String> inputLines) throws IOException {
        int out = 0;
        for (String line : inputLines) {
            out += getOutputValueForLine(line);
        }
        return out;
    }

    int getOutputValueForLine(String inputLine) throws UnexpectedException {
        Map<String, String> decodingMap = createSegmentMapForLine(inputLine);
        return newAttemptDecode(decodingMap, inputLine);
    }

    public Map<String, String> createSegmentMapForLine(String fullInputLine) {
        Map<String, String> conversionMap = new HashMap();
        String[] messedUpDigitsArray = getMessedUpInputDigitSignals(fullInputLine);
        String keyOf4 = "";
        String keyOf7 = "";
        // fill unique digits
        ArrayList<String> nonUniqueDigits = new ArrayList();
        for (int i = 0; i < messedUpDigitsArray.length; i++) {
            String signals = sortString(messedUpDigitsArray[i]);
            if (hasLengthOf(LENGTH_OF_DIGIT_ONE, signals))
                conversionMap.put(signals, "1");
            else if (hasLengthOf(LENGTH_OF_DIGIT_FOUR, signals)) {
                conversionMap.put(signals, "4");
                keyOf4 = signals;
            }
            else if (hasLengthOf(LENGTH_OF_DIGIT_SEVEN, signals)) {
                conversionMap.put(signals, "7");
                keyOf7 = signals;
            }
            else if (hasLengthOf(LENGTH_OF_DIGIT_EIGHT, signals))
                conversionMap.put(signals, "8");
            else nonUniqueDigits.add(signals);
        }

        List<Character> mappedChars4 = DataConverter.convertStringToCharacterList(keyOf4);
        List<Character> mappedChars7 = DataConverter.convertStringToCharacterList(keyOf7);
        for (String signals : nonUniqueDigits) {
            signals = sortString(signals);
            if (hasLengthOf(LENGTH_OF_DIGIT_FIVE_TWO_THREE, signals)) {
                List<Character> signalChars = DataConverter.convertStringToCharacterList(signals);
                signalChars.retainAll(mappedChars7);
                if (signalChars.size() == LENGTH_OF_DIGIT_SEVEN) {
                    conversionMap.put(signals, "3");
                    continue;
                }
                signalChars = DataConverter.convertStringToCharacterList(signals);
                signalChars.retainAll(mappedChars4);
                if (signalChars.size() == 2) {
                    conversionMap.put(signals, "2");
                    continue;
                }
                conversionMap.put(signals, "5");
                continue;
            }  else if (hasLengthOf(LENGTH_OF_DIGIT_NINE_SIX_ZERO, signals)) {
                List<Character> signalChars = DataConverter.convertStringToCharacterList(signals);
                signalChars.retainAll(mappedChars4);
                if (signalChars.size() == LENGTH_OF_DIGIT_FOUR) {
                    conversionMap.put(signals, "9");
                    continue;
                }
                signalChars = DataConverter.convertStringToCharacterList(signals);
                signalChars.retainAll(mappedChars7);
                if (signalChars.size() == 2) {
                    conversionMap.put(signals, "6");
                    continue;
                }
                conversionMap.put(signals, "0");
            }
        }
        return conversionMap;
    }

    private String sortString(String inputString) {
        char[] charArray = inputString.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private int newAttemptDecode(Map<String, String> conversionMap, String fullInputLine) throws UnexpectedException {
        String[] messedUpOutputDigitsArray = getMessedUpOutputDigitSignals(fullInputLine);
        String decodedValue = "";
        for (int i = 0; i < messedUpOutputDigitsArray.length; i++) {
            String signals = messedUpOutputDigitsArray[i];
            decodedValue = decodedValue + conversionMap.get(sortString(signals));
        }
        if (messedUpOutputDigitsArray.length != decodedValue.length() && decodedValue.length() != 4)
            throw new UnexpectedException("Something went wrong!");
        return Integer.parseInt(decodedValue);
    }

    private String[] getMessedUpInputDigitSignals(String input) {
        return getSectionSegments(input, 0);
    }

    private String[] getMessedUpOutputDigitSignals(String input) {
        return getSectionSegments(input, 1);
    }

    private String[] getSectionSegments(String input, int sectionIndex) {
        return input.split(SECTION_DELIMITER)[sectionIndex].trim().split(SEGMENT_DELIMITER);
    }

    private boolean hasLengthOf(int length, String digitSignals) {
        return !StringUtils.isBlank(digitSignals) && digitSignals.length() == length;
    }
}
