package AoC.day_10;


import AoC.data_manager.DataConverter;

import java.math.BigInteger;
import java.util.*;

public class SyntaxScoring {

    static private final Map<Character, Character> OPENING_CLOSING_CHARS = Map.of(
            "(".charAt(0), ")".charAt(0),
            "[".charAt(0), "]".charAt(0),
            "{".charAt(0), "}".charAt(0),
            "<".charAt(0), ">".charAt(0)
    );

    static private final Map<Character, Integer> SCORE_MAP_CORRUPT_CHARS = Map.of(
            ")".charAt(0), 3,
            "]".charAt(0), 57,
            "}".charAt(0), 1197,
            ">".charAt(0), 25137
    );

    static private final Map<Character, Integer> SCORE_MAP_COMPLETE_CHARS = Map.of(
            ")".charAt(0), 1,
            "]".charAt(0), 2,
            "}".charAt(0), 3,
            ">".charAt(0), 4
    );

    public BigInteger checkForIncompleteLines(List<String> lines) {
        ArrayList<BigInteger> scoreCompletionList = createScoreListIncompleteLines(lines);
        Collections.sort(scoreCompletionList);
        Double middle = ((double)scoreCompletionList.size() / 2) - 0.5;
        return scoreCompletionList.get(middle.intValue());
    }

    public int checkForCorruptedLines(List<String> lines) {
        int totalScore = 0;

        for (String line : lines) {
            char corruptChar = findCorruptChar(line);
            if (corruptChar != 0)
                totalScore += getCorruptCharScore(corruptChar);
        }
        return totalScore;
    }

    ArrayList<BigInteger> createScoreListIncompleteLines(List<String> lines) {
        ArrayList<BigInteger> scoreCompletionList = new ArrayList();
        for (String line : lines) {
            if (findCorruptChar(line) == 0) {
                BigInteger score = getCompletionSequenceScore(findCompletionString(line));
                if (score.compareTo(BigInteger.valueOf(0)) == 1)
                    scoreCompletionList.add(score);
            }
        }
        return scoreCompletionList;
    }

    /** @return return a sequence of Characters that complete the line, if line is complete the sequence is empty */
    List<Character> findCompletionString(String line) {
        LinkedList<Character> chars = new LinkedList(DataConverter.convertStringToCharacterList(line));

        LinkedList<Character> sequence = new LinkedList();
        for (Character element : chars) {
            if (OPENING_CLOSING_CHARS.keySet().contains(element))
                sequence.add(element);
            else if (OPENING_CLOSING_CHARS.values().contains(element))
                if (OPENING_CLOSING_CHARS.get(sequence.getLast()).equals(element))
                    sequence.removeLast();
        }

        List<Character> completionSequence = new LinkedList();
        while (!sequence.isEmpty()) {
            completionSequence.add(OPENING_CLOSING_CHARS.get(sequence.getLast()));
            sequence.removeLast();
        }
        return completionSequence;
    }

    /** @return the first corrupted character in the sequence, or 0 if sequence is not corrupted */
    char findCorruptChar(String line) {
        LinkedList<Character> chars = new LinkedList(DataConverter.convertStringToCharacterList(line));

        LinkedList<Character> sequence = new LinkedList();
        for (Character element : chars)
            if (OPENING_CLOSING_CHARS.keySet().contains(element))
                sequence.add(element);
            else if (OPENING_CLOSING_CHARS.values().contains(element))
                if (OPENING_CLOSING_CHARS.get(sequence.getLast()).equals(element))
                    sequence.removeLast();
                else
                    return element;

        return 0;
    }

    BigInteger getCompletionSequenceScore(List<Character> sequence) {
        BigInteger sequenceScore = BigInteger.valueOf(0);
        for (Character element : sequence) {
            sequenceScore = sequenceScore
                    .multiply(BigInteger.valueOf(5))
                            .add(BigInteger.valueOf(SCORE_MAP_COMPLETE_CHARS.get(element)));
        }
        return sequenceScore;
    }

    int getCorruptCharScore(Character corruptedChar) {
        return SCORE_MAP_CORRUPT_CHARS.get(corruptedChar);
    }


}
