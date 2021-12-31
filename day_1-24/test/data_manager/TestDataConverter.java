package data_manager;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class TestDataConverter {

    @Test
    public void testReadLinesToMatrixNoDelimiter() throws IOException {
        List<String> lines = DataHelper.getMatrixLinesWithoutDelimiter();
        int[][] resultMatrix = DataConverter.readLinesToMatrixNoDelimiter(lines);
        int[][] expectedMatrix = DataHelper.getMatrixWithoutDelimiter();

        assertArrayEquals(expectedMatrix, resultMatrix);
    }
}
