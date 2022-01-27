package AoC.day_5;

public class CoordinatesXY {

    private final int x1;
    private final int y1;

    private final int x2;
    private final int y2;

    public CoordinatesXY(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean isHorizontalOrVerticalLine() {
        return isHorizontalLine() || isVerticalLine();
    }

    public boolean isHorizontalLine() {
        return x1 == x2;
    }

    public boolean isVerticalLine() {
        return y1 == y2;
    }

    public boolean isDiagonalLine() {
        return (Math.abs(x2 - x1) == Math.abs(y2 - y1));
    }

    public int getBiggestX() {
        return x1 > x2 ? x1 : x2;
    }

    public int getBiggestY() {
        return y1 > y2 ? y1 : y2;
    }

    public int getLowestX() {
        return x1 < x2 ? x1 : x2;
    }

    public int getLowestY() {
        return y1 < y2 ? y1 : y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

}
