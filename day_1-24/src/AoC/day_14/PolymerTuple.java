package AoC.day_14;

public class PolymerTuple {

    private final String element1;
    private final String element2;

    public PolymerTuple(String element1, String element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public String getElement1() {
        return element1;
    }

    public String getElement2() {
        return element2;
    }

    @Override
    public String toString() {
        return element1 + element2;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  PolymerTuple))
            return false;
        PolymerTuple tuple = (PolymerTuple) obj;
        return this.toString().equals(tuple.toString());
    }

    @Override
    public int hashCode() {
        return element1.hashCode() ^ element2.hashCode();
    }
}
