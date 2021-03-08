import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String string2 = scanner.nextLine();

        int editDistance = EditDistance.calculateDistance(string1, string2);
        if (editDistance == 1) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
