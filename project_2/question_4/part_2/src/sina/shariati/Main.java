package sina.shariati;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskProcessor processor = new TaskProcessor(
                new IntegerLinkedList()
        );

        Scanner scanner = new Scanner(System.in);
        String taskString = scanner.nextLine();

        do {
            processor.processNextTask(taskString);
            taskString = scanner.nextLine();
        } while (!taskString.equals("finish"));
    }
}
