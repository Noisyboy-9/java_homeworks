package sina.shariati;

import sina.shariati.exceptions.StackUnderFlowException;
import sina.shariati.linkedList.IntegerLinkedList;
import sina.shariati.linkedList.IntegerNode;
import sina.shariati.stack.Stack;
import sina.shariati.stack.StackItem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws StackUnderFlowException {
        IntegerLinkedList linkedList = createLinkedListFromInput();
        Stack reverserStack = getReverserStack(linkedList);
        IntegerLinkedList reversedLinkedList = createLinkedListFromStack(reverserStack);
        printLinkedList(reversedLinkedList);
    }

    private static void printLinkedList(IntegerLinkedList reversedLinkedList) {
        IntegerNode iterationNode = reversedLinkedList.getHead();
        while (iterationNode != null) {
            System.out.print(iterationNode.getValue() + " ");
            iterationNode = iterationNode.getNext();
        }
    }

    private static IntegerLinkedList createLinkedListFromStack(Stack reverserStack) throws StackUnderFlowException {
        IntegerLinkedList linkedList = new IntegerLinkedList();

        while (!reverserStack.isEmpty()) {
            StackItem item = reverserStack.pop();
            linkedList.addToEnd(item.getValue());
        }

        return linkedList;
    }

    private static Stack getReverserStack(IntegerLinkedList linkedList) {
        Stack reverserStack = new Stack(linkedList.size());
        IntegerNode iterationNode = linkedList.getHead();

        while (iterationNode != null) {
            reverserStack.push(
                    new StackItem(iterationNode.getValue())
            );

            iterationNode = iterationNode.getNext();
        }

        return reverserStack;
    }

    private static IntegerLinkedList createLinkedListFromInput() {
        IntegerLinkedList linkedList = new IntegerLinkedList();

        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        String[] inputSpliced = inputString.split("\\s+");

        for (String input : inputSpliced) {
            int value = Integer.parseInt(input);
            linkedList.addToEnd(value);
        }

        return linkedList;
    }
}
