package sina.shariati.stack;

import sina.shariati.exceptions.StackUnderFlowException;


/**
 * The type Stack.
 */
public class Stack {
    private final int size;
    private final StackItem[] stackArray;
    private int topIndex;

    /**
     * Instantiates a new Stack.
     *
     * @param size the size
     */
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

    /**
     * Check to see if stack is full.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return this.topIndex == this.size - 1;
    }

    /**
     * Check to see if stack is empty.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return this.topIndex == -1;
    }

    /**
     * Push a item to the stack.
     *
     * @param item the item
     */
    public void push(StackItem item) {
        if (this.isFull()) {
            throw new StackOverflowError("stack is full");
        }

        int newTopIndex = this.topIndex + 1;
        this.stackArray[newTopIndex] = item;
        this.topIndex = newTopIndex;
    }

    /**
     * Pop a item from stack.
     *
     * @return the stack item
     * @throws StackUnderFlowException the stack under flow exception
     */
    public StackItem pop() throws StackUnderFlowException {
        if (this.isEmpty()) {
            throw new StackUnderFlowException("stack is empty");
        }

        return this.stackArray[this.topIndex--];
    }

    /**
     * See first item of the stack.
     *
     * @return the stack item
     * @throws StackUnderFlowException the stack under flow exception
     */
    public StackItem peek() throws StackUnderFlowException {
        if (this.isEmpty()) {
            throw new StackUnderFlowException("stack is empty");
        }
        return this.stackArray[topIndex];
    }
}
