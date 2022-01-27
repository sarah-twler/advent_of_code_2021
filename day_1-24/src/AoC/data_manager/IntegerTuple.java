package AoC.data_manager;

public class IntegerTuple {

    private final int int1;
    private final int int2;

    public IntegerTuple(int int1, int int2) {
        this.int1 = int1;
        this.int2 = int2;
    }

    public int getInt1() {
        return int1;
    }

    public int getInt2() {
        return int2;
    }

    @Override
    public String toString() {
        return String.format("<%s|%s>", int1, int2);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegerTuple))
            return  false;
        IntegerTuple tuple = (IntegerTuple) obj;
        return this.getInt1() == tuple.getInt1() && this.int2 == tuple.getInt2();
    }

    @Override
    public int hashCode() {
        return int1 ^ int2;
    }
}
