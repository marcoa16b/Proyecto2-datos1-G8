package B_Cliente.enums;

public enum DataCheckers {

    ID(0),
    SHIP2(1),
    MOVE_RIGHT(5),
    MOVE_LEFT(6),
    SHOT(7);

    int value;

    DataCheckers(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
