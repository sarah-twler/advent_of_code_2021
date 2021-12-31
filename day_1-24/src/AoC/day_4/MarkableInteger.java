package AoC.day_4;

public class MarkableInteger {

    private int value;
    private boolean isMarked;

    public MarkableInteger(int value) {
        this.value = value;
        this.isMarked = false;
    }

    public MarkableInteger(int value, boolean isMarked) {
        this.value = value;
        this.isMarked = isMarked;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void mark() {
        isMarked = true;
    }

    public void unmark() {
        isMarked = false;
    }
}
