package AoC.day_2;

public class DirectionsTuple {

    private int steps;
    private DirectionsEnum direction;

    DirectionsTuple(String directions, int steps) {
        this.direction = DirectionsEnum.valueOfLabel(directions);
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }

    public DirectionsEnum getDirection() {
        return direction;
    }

}
