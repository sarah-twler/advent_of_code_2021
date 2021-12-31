package AoC.data_manager;

public enum AdjacencyEnum {
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    RIGHT(0, 1),
    DOWN_RIGHT(1, 1),
    DOWN(1, 0),
    DOWN_LEFT(1, -1),
    LEFT(0, -1),
    UP_LEFT(-1, -1);

    private final int rowDirection;
    private final int colDirection;

    AdjacencyEnum(int rowDirection, int colDirection) {
        this.rowDirection = rowDirection;
        this.colDirection = colDirection;
    }

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColDirection() {
        return colDirection;
    }

    public static AdjacencyEnum[] valuesNonDiagonal() {
        return new AdjacencyEnum[] { UP, RIGHT, DOWN, LEFT };
    }
}
