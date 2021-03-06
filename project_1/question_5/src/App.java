import java.util.Scanner;

public class App {
    private static ComplexNumber complexNumber1;
    private static ComplexNumber complexNumber2;

    public static void main(String[] args) {
        getInputData();
        Calculator calculator = new Calculator(complexNumber1, complexNumber2);
        calculator.startCalculationLoop();
        calculator.printResults();
    }

    private static void getInputData() {
        Scanner scanner = new Scanner(System.in);

        complexNumber1 = new ComplexNumber(scanner.nextInt(), scanner.nextInt());
        complexNumber2 = new ComplexNumber(scanner.nextInt(), scanner.nextInt());
    }
}