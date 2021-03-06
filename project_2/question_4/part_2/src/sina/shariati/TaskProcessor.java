package sina.shariati;

/**
 * Command line task processor for given linkedList
 */
public class TaskProcessor {
    private IntegerLinkedList linkedList;

    /**
     * Instantiates a new Task processor and set the linkedList property from given input
     *
     * @param linkedList the linked list
     */
    public TaskProcessor(IntegerLinkedList linkedList) {
        this.linkedList = linkedList;
    }

    /**
     * Read the next task from system.in and execute it
     *
     * @param taskString the task string
     */
    public void processNextTask(String taskString) {
        String taskArray[] = this.splitTaskString(taskString);
        String taskFunction = taskArray[0];
        int taskInput = -1; // if task does not need input this is the default value

        if (taskArray.length == 2) {
//            if this is the case, the tasks needs an input too
            taskInput = Integer.parseInt(taskArray[1]);
        }

        this.executeTask(
                taskFunction,
                taskInput
        );
    }


    private void executeTask(String function, int input) {
        if (function.equals("add")) {
            this.linkedList.addToEnd(input);
        } else if (function.equals("addFirst")) {
            this.linkedList.addToBeginning(input);
        } else if (function.equals("findMiddle")) {
            System.out.println(this.linkedList.findMiddle());
        } else if (function.equals("removeMiddle")) {
            this.linkedList.removeMiddle();
        } else if (function.equals("contains")) {
            System.out.println(this.linkedList.contains(input));
        } else if (function.equals("removeIndex")) {
            this.linkedList.removeIndex(input);
        } else {
            this.linkedList.print();
        }
    }

    private String[] splitTaskString(String taskString) {
        return taskString.split("\\s+");
    }
}
