package AoC.day_16;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class TestPacketDecoder {

    @Test
    public void testConvertHexToBin() {
        assertEquals("110100101111111000101000", PacketDecoder.convertHexToBin("D2FE28"));
        assertEquals("00111000000000000110111101000101001010010001001000000000",
                PacketDecoder.convertHexToBin("38006F45291200"));
        assertEquals("11101110000000001101010000001100100000100011000001100000",
                PacketDecoder.convertHexToBin("EE00D40C823060"));
    }

    @Test
    public void testGetExpressionResult() throws IOException {
        PacketDecoder decoder1 = new PacketDecoder("C200B40A82");
        assertEquals(new BigInteger("3"), decoder1.getExpressionResult());

        PacketDecoder decoder2 = new PacketDecoder("04005AC33890");
        assertEquals(new BigInteger("54"), decoder2.getExpressionResult());

        PacketDecoder decoder3 = new PacketDecoder("880086C3E88112");
        assertEquals(new BigInteger("7"), decoder3.getExpressionResult());

        PacketDecoder decoder4 = new PacketDecoder("CE00C43D881120");
        assertEquals(new BigInteger("9"), decoder4.getExpressionResult());

        PacketDecoder decoder5 = new PacketDecoder("D8005AC2A8F0");
        assertEquals(new BigInteger("1"), decoder5.getExpressionResult());

        PacketDecoder decoder6 = new PacketDecoder("F600BC2D8F");
        assertEquals(new BigInteger("0"), decoder6.getExpressionResult());

        PacketDecoder decoder7 = new PacketDecoder("9C005AC2F8F0");
        assertEquals(new BigInteger("0"), decoder7.getExpressionResult());

        PacketDecoder decoder8 = new PacketDecoder("9C0141080250320F1802104A08");
        assertEquals(new BigInteger("1"), decoder8.getExpressionResult());
    }

    @Test
    public void testGetSumVersionNumber() throws IOException {
        PacketDecoder converter1 = new PacketDecoder("8A004A801A8002F478");
        assertEquals(16, converter1.getSumVersionNumbers());

        PacketDecoder converter2 = new PacketDecoder("620080001611562C8802118E34");
        assertEquals(12, converter2.getSumVersionNumbers());

        PacketDecoder converter3 = new PacketDecoder("C0015000016115A2E0802F182340");
        assertEquals(23, converter3.getSumVersionNumbers());

        PacketDecoder converter4 = new PacketDecoder("A0016C880162017C3686B18A3D4780");
        assertEquals(31, converter4.getSumVersionNumbers());
    }

    @Test
    public void testGetSumVersionNumbersLiteral() throws IOException {
        PacketDecoder converter = new PacketDecoder("D2FE28");
        assertEquals(6, converter.getSumVersionNumbers());
    }

    @Test
    public void testGetSumVersionNumbersOperatorType0() throws IOException {
        PacketDecoder converter = new PacketDecoder("38006F45291200");
        assertEquals(9, converter.getSumVersionNumbers());
    }

    @Test
    public void testGetSumVersionNumberOperatorType1() throws IOException {
        PacketDecoder converter = new PacketDecoder("EE00D40C823060");
        assertEquals(14, converter.getSumVersionNumbers());
    }
}
