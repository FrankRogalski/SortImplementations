package sorting;

public class Change {
    private ChangeType changeType;
    private int number1 = -1;
    private int number2 = -1;
    private int value;

    public Change(final ChangeType changeType, final int number1) {
        this.changeType = changeType;
        this.number1 = number1;
    }

    public Change(final ChangeType changeType, final int number1, final int number2) {
        this.changeType = changeType;
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(final int number2) {
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(final int number1) {
        this.number1 = number1;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(final ChangeType changeType) {
        this.changeType = changeType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }
}
