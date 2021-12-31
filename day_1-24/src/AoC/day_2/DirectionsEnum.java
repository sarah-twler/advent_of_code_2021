package AoC.day_2;

public enum DirectionsEnum {
    FORWARD("forward"),
    DOWN("down"),
    UP("up")
    ;

    private final String direction;

    DirectionsEnum(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public static DirectionsEnum valueOfLabel(String label) {
        for (DirectionsEnum e : values()) {
            if (e.direction.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
