public class ShellSort {
    private static void printArray(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; ++i)
            System.out.println(array[i] + " ");
        System.out.println();
    }

    private static void sort(int[] array) {
        int length = array.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i += 1) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 34, 54, 2, 3 };
        System.out.println("Array before sorting");
        printArray(arr);
        sort(arr
        System.out.println("Array after sorting");
        printArray(arr);
    }
}
