package sina.shariati.stack;

import sina.shariati.exceptions.StackUnderFlowException;


public class Stack {
    private final int size;
    private final StackItem[] stackArray;
    private int topIndex;

    public Stack(int size) {
        this.size = size;
        this.stackArray = new StackItem[size];
        this.topIndex = -1;
    }

    @Override
    public String toString() {
        String test = "";

        for (StackItem item : this.stackArray) {
            test = test.concat(item.getValue() + ", ");
        }

        return test;
    }

    public boolean isFull() {
        return this.topIndex == this.size - 1;
    }

    public boolean isEmpty() {
        return this.topIndex == -1;
    }

    public void push(StackItem item) {
        if (this.isFull()) {
            throw new StackOverflowError("stack is full");
        }

        int newTopIndex = this.topIndex + 1;
        this.stackArray[newTopIndex] = item;
        this.topIndex = newTopIndex;
    }

    public StackItem pop() throws StackUnderFlowException {
        if (this.isEmpty()) {
            throw new StackUnderFlowException("stack is empty");
        }

        return this.stackArray[this.topIndex--];
    }

    public StackItem peek() throws StackUnderFlowException {
        if (this.isEmpty()) {
            throw new StackUnderFlowException("stack is empty");
        }
        return this.stackArray[topIndex];
    }
}
