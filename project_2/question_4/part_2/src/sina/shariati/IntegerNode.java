package sina.shariati;

public class IntegerNode {
    private int value;
    private IntegerNode next;

    public IntegerNode(int value) {
        this.setValue(value);
        this.setNext(null);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IntegerNode getNext() {
        return next;
    }

    public void setNext(IntegerNode next) {
        this.next = next;
    }
}
