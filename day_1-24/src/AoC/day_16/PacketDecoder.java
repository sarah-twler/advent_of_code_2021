package AoC.day_16;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PacketDecoder {

    private final char[] binary;
    private int totalSumVersion = 0;

    public PacketDecoder(String hexadecimal) {
        binary = initBinary(hexadecimal);
    }

    private char[] initBinary(String hexadecimal) {
        return convertHexToBin(hexadecimal).toCharArray();
    }

    public int getSumVersionNumbers() throws IOException {
        totalSumVersion = 0;
        evaluateExpression(0);
        return totalSumVersion;
    }

    public BigInteger getExpressionResult() throws IOException {
        return evaluateExpression(0)[1];
    }

    private BigInteger[] evaluateExpression(int pointerHead) throws IOException {
        if (pointerHead >= binary.length)
            return new BigInteger[] { BigInteger.valueOf(pointerHead), BigInteger.valueOf(-1) };

        int headerVersion = convertBitsToInt(Arrays.copyOfRange(binary, pointerHead, pointerHead + 3));
        totalSumVersion += headerVersion;
        int packetType = convertBitsToInt(Arrays.copyOfRange(binary, pointerHead + 3, pointerHead + 6));

        if (packetType == 4)
            return evaluateLiteral(pointerHead);

        char lengthTypeId = binary[pointerHead + 6];
        if (lengthTypeId == '0')
            return evaluateOperatorType0(pointerHead, packetType);
        if (lengthTypeId == '1')
            return evaluateOperatorType1(pointerHead, packetType);

        throw new UnexpectedException("Unexpected state!");
    }

    private BigInteger[] evaluateOperatorType1(int pointerHead, int packetType) throws IOException {
        char[] bitsNumSubPac = Arrays.copyOfRange(binary, pointerHead + 7, pointerHead + 18);
        int numSubPac = convertBitsToInt(bitsNumSubPac);
        int nextPointer = pointerHead + 18;
        ArrayList<BigInteger> exprVals = new ArrayList();
        while (numSubPac > 0) {
            BigInteger[] pointerVal = evaluateExpression(nextPointer);
            nextPointer = 1 + pointerVal[0].intValue();
            exprVals.add(pointerVal[1]);
            numSubPac--;
        }
        return new BigInteger[] { BigInteger.valueOf(nextPointer - 1), evaluateExpressionValues(exprVals, packetType) };
    }

    private BigInteger[] evaluateOperatorType0(int pointerHead, int packetType) throws IOException {
        char[] bitsLenSubPac = Arrays.copyOfRange(binary, pointerHead + 7, pointerHead + 22);
        int lenSubPac = convertBitsToInt(bitsLenSubPac);
        int nextPointer = pointerHead + 22;
        ArrayList<BigInteger> exprVals = new ArrayList();
        while (lenSubPac > 0) {
            int start = nextPointer;
            BigInteger[] pointerVal = evaluateExpression(nextPointer);
            int end = pointerVal[0].intValue();
            exprVals.add(pointerVal[1]);
            nextPointer = 1 + end;
            lenSubPac -= end - start + 1;
        }
        return new BigInteger[] { BigInteger.valueOf(nextPointer - 1), evaluateExpressionValues(exprVals, packetType) };
    }

    private BigInteger[] evaluateLiteral(int pointerHead) {
        int pointer = pointerHead + 6;
        char prefix = 'x';
        String binVal = "";
        while (prefix != '0') {
            prefix = binary[pointer];
            binVal += String.valueOf(Arrays.copyOfRange(binary, pointer + 1, pointer + 5));
            pointer = pointer + 5;
        }
        return new BigInteger[] { BigInteger.valueOf(pointer - 1), binaryToBigInt(binVal) };
    }

    private BigInteger evaluateExpressionValues(ArrayList<BigInteger> values, int packetType) throws IOException {
        if (values == null || values.isEmpty())
            throw new IOException("Empty value list! Cannot evaluate expression results!");

        switch (packetType) {
            case 0:
                BigInteger result0 = new BigInteger("0");
                for (BigInteger val : values) {
                    result0 = result0.add(val);
                }
                return result0;
            case 1:
                BigInteger result1 = new BigInteger("1");
                for (BigInteger val : values) {
                    result1 = result1.multiply(val);
                }
                return result1;
            case 2:
                Collections.sort(values);
                return values.get(0);
            case 3:
                Collections.sort(values);
                return values.get(values.size() - 1);
            case 4:
                throw new IOException("Unexpected packet type 4 found! Type 4 should not occur here!");
        }

        if (values.size() != 2)
            throw new IOException(String.format("Unexpected number od values! Expected 2, got %s!", values.size()));

        switch (packetType) {
            case 5:
                return values.get(0).compareTo(values.get(1)) == 1 ? new BigInteger("1") : new BigInteger("0");
            case 6:
                return values.get(0).compareTo(values.get(1)) == -1 ? new BigInteger("1") : new BigInteger("0");
            case 7:
                return values.get(0).compareTo(values.get(1)) == 0 ? new BigInteger("1") : new BigInteger("0");
        }

        throw new IOException(String.format("Unexpected packet type found! Expected packet type in range [0-7] excl 4, but found %s!", packetType));
    }

    private static int convertBitsToInt(char... bits) {
        return binaryToInt(String.valueOf(bits));
    }

    private static int binaryToInt(String binary) {
        return Integer.parseInt(binary, 2);
    }

    private static BigInteger binaryToBigInt(String binary) {
        return new BigInteger(binary, 2);
    }

    public static String convertHexToBin(String hexadecimal) {
        hexadecimal = hexadecimal.replaceAll("0", "0000");
        hexadecimal = hexadecimal.replaceAll("1", "0001");
        hexadecimal = hexadecimal.replaceAll("2", "0010");
        hexadecimal = hexadecimal.replaceAll("3", "0011");
        hexadecimal = hexadecimal.replaceAll("4", "0100");
        hexadecimal = hexadecimal.replaceAll("5", "0101");
        hexadecimal = hexadecimal.replaceAll("6", "0110");
        hexadecimal = hexadecimal.replaceAll("7", "0111");
        hexadecimal = hexadecimal.replaceAll("8", "1000");
        hexadecimal = hexadecimal.replaceAll("9", "1001");
        hexadecimal = hexadecimal.replaceAll("A", "1010");
        hexadecimal = hexadecimal.replaceAll("B", "1011");
        hexadecimal = hexadecimal.replaceAll("C", "1100");
        hexadecimal = hexadecimal.replaceAll("D", "1101");
        hexadecimal = hexadecimal.replaceAll("E", "1110");
        hexadecimal = hexadecimal.replaceAll("F", "1111");
        return hexadecimal;
    }

}
