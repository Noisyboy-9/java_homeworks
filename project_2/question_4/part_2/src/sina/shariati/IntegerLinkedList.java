package sina.shariati;

public class IntegerLinkedList {
    private IntegerNode head;

    public IntegerLinkedList() {
        this.head = null;
    }


    public void addToEnd(int input) {
        if (this.head == null) {
//            create the first node
            this.head = new IntegerNode(input);
            return;
        }


        IntegerNode newNode = new IntegerNode(input);

        IntegerNode endNode = this.findEnd();
        endNode.setNext(newNode);
    }

    public void addToBeginning(int input) {
        IntegerNode newHead = new IntegerNode(input);
        newHead.setNext(this.head);

        this.head = newHead;
    }

    private IntegerNode findEnd() {
        IntegerNode iterationNode = head;

        while (iterationNode.getNext() != null) {
            iterationNode = iterationNode.getNext();
        }

        return iterationNode;
    }

    public int findMiddle() {
        int size = this.findSize();
        if (size == 0) {
            return -1;
        }

        if (size % 2 == 1) {
//            size is odd
            int middleIndex = ((size + 1) / 2) - 1;
            return this.findNodeByIndex(middleIndex).getValue();
        } else {
//            size is even
            int middleIndex = size / 2;
            return this.findNodeByIndex(middleIndex).getValue();
        }
    }

    private IntegerNode findNodeByIndex(int index) {
        if (this.head == null) {
            return null;
        }
        if (index < 0) {
            return null;
        }

        if (this.findSize() < index + 1) {
//            the index is out of bounds
            return null;
        }

        IntegerNode iterationNode = head;

        for (int i = 0; i < index; i++) {
            iterationNode = iterationNode.getNext();
        }

        return iterationNode;
    }

    private int findSize() {
        if (this.head == null) {
            return 0;
        }

        IntegerNode iterationNode = head;
        int size = 0;
        while (iterationNode != null) {
            size++;
            iterationNode = iterationNode.getNext();
        }

        return size;

    }

    public void removeMiddle() {
        int size = this.findSize();

        int middleIndex = size % 2 == 1 ? ((size + 1) / 2) - 1 : (size / 2) - 1;

        this.removeIndex(middleIndex);
    }

    public int contains(int input) {
        if (this.head == null) {
            return -1;
        }

        IntegerNode iterationNode = this.head;
        int index = 0;
        while (iterationNode != null) {
            if (iterationNode.getValue() == input) {
                return index;
            }

            iterationNode = iterationNode.getNext();
            index++;
        }

//        the linked list does not contain the given input
        return -1;
    }

    public void removeIndex(int index) {
        IntegerNode beforeNode = this.findNodeByIndex(index - 1);
        IntegerNode afterNode = this.findNodeByIndex(index + 1);

        if (beforeNode == null && afterNode == null) {
//            the linked list is empty
            return;
        }

        if (beforeNode == null) {
//            delete from beginning
            this.head = afterNode;
        } else if (afterNode == null) {
//            delete from the end
            beforeNode.setNext(null);
        } else {
//        delete from the middle
            beforeNode.setNext(afterNode);
        }
    }

    public void print() {
        if (this.head == null) {
            return;
        }

        IntegerNode iterationNode = this.head;
        while (iterationNode != null) {
            System.out.printf("%d ", iterationNode.getValue());
            iterationNode = iterationNode.getNext();
        }

        System.out.printf("\n");
    }
}

