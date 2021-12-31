package AoC.day_13;

import AoC.data_manager.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransparentOrigami {

    private final String COORDINATE_DELIMITER = ",";
    private DotMatrix origami;

    public String createOrigami(List<String> dataInput) {
        int startIndex = 0;
        int endIndex = FileReader.getNextBlockEndIndex(dataInput, startIndex);
        List<String> coordinatesList = dataInput.subList(startIndex, endIndex);
        List<String[]> coordinatesXY = new ArrayList();
        int counter = 1;
        for (String coordinates : coordinatesList) {
            System.out.println(String.format("Processing line %s ...", counter));
            coordinatesXY.add(coordinates.split(COORDINATE_DELIMITER));
            counter++;
        }
        System.out.println("Creating origami ...");
        origami = new DotMatrix(coordinatesXY);
        System.out.println("Done.");
        return origami.toString();
    }

    // EPZGKCHU

    public String foldOrigami(List<String> dataInput) {
        return foldOrigami(dataInput, Integer.MAX_VALUE);
    }

    public String foldOrigami(List<String> dataInput, int maxFolds) {
        int counter = 0;
        for (String foldInstruction : dataInput) {
            int coordinate = getCoordinate(foldInstruction);
            String axis = getAxis(foldInstruction);
            if (coordinate != -1 && axis != null) {
                System.out.println(String.format("Processing fold instruction no. %s ...", counter));
                counter++;
                origami.foldDotMatrix(axis, coordinate);
                maxFolds--;
                if (maxFolds == 0)
                    break;
            }
        }
        return origami.toString();
    }

    public int countDots() {
        return origami.countDots();
    }

     String foldOrigamiAlongXCoordinate(int xCoordinate) {
        origami.foldAlongXCoordinate(xCoordinate);
        return origami.toString();
    }

     String foldOrigamiAlongYCoordinate(int yCoordinate) {
        origami.foldAlongYCoordinate(yCoordinate);
        return origami.toString();
    }

     int getCoordinate(String foldInstruction) {
        Pattern p = Pattern.compile("\\d+");
        Matcher n = p.matcher(foldInstruction);
        if (n.find())
            return Integer.parseInt(n.group(0));
        return -1;
    }

    String getAxis(String foldInstruction) {
        Pattern p = Pattern.compile("[xy]");
        Matcher n = p.matcher(foldInstruction);
        if (n.find())
            return n.group(0);
        return null;
    }

}
