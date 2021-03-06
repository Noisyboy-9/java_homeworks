import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    private ComplexNumber number1;
    private ComplexNumber number2;

    private ComplexNumber[] resultsArray;

    public Calculator(ComplexNumber number1, ComplexNumber number2) {
        this.number1 = number1;
        this.number2 = number2;

        this.resultsArray = new ComplexNumber[0];
    }

    public void startCalculationLoop() {
        char operator = getCalculationOperator();

        while (operator != '#') {
            switch (operator) {
            case '+':
                this.handleSum();
                operator = getCalculationOperator();
                break;
            case '-':
                this.handleSubtraction();
                operator = getCalculationOperator();
                break;
            case '*':
                this.handleMultiplication();
                operator = getCalculationOperator();
                break;
            case '/':
                this.handleDivision();
                operator = getCalculationOperator();
                break;
            default:
                break;
            }
        }
    }

    private void handleDivision() {
        double magnitude = calculateMagnitude(this.number2);

        double resultReal = (this.number1.getReal() * this.number2.getReal()
                + this.number1.getImaginary() * this.number2.getImaginary()) / magnitude;

        double resultImaginary = (this.number1.getImaginary() * this.number2.getReal()
                - this.number1.getReal() * this.number2.getImaginary()) / magnitude;

        ComplexNumber result = new ComplexNumber(resultReal, resultImaginary);

        addToResults(result);
    }

    private double calculateMagnitude(ComplexNumber number) {
        return (number.getReal() * number.getReal()) + (number.getImaginary() * number.getImaginary());
    }

    private void handleMultiplication() {
        double resultReal = number1.getReal() * number2.getReal() - number1.getImaginary() * number2.getImaginary();
        double resultImaginary = number1.getReal() * number2.getImaginary()
                + number1.getImaginary() * number2.getReal();

        ComplexNumber result = new ComplexNumber(resultReal, resultImaginary);

        addToResults(result);
    }

    private void handleSubtraction() {
        ComplexNumber result = new ComplexNumber(this.number1.getReal() - this.number2.getReal(),
                this.number1.getImaginary() - this.number2.getImaginary());

        addToResults(result);
    }

    private char getCalculationOperator() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().charAt(0);
    }

    private void handleSum() {
        ComplexNumber result = new ComplexNumber(number1.getReal() + number2.getReal(),
                number1.getImaginary() + number2.getImaginary());

        addToResults(result);
    }

    private void addToResults(ComplexNumber result) {
        int insertIndex = this.resultsArray.length;

        resultsArray = Arrays.copyOf(resultsArray, this.resultsArray.length + 1);

        resultsArray[insertIndex] = result;
    }

    public void printResults() {
        for (int index = 0; index < this.resultsArray.length; index++) {
            ComplexNumber result = resultsArray[index];
            if (this.hasDecimal(result.getReal())) {
                System.out.printf("%.3f", result.getReal());
            } else {
                System.out.printf("%d", (int) result.getReal());
            }

            if (this.hasDecimal(result.getImaginary())) {
                if (result.getImaginary() >= 0) {
                    System.out.printf("+%.2fi", result.getImaginary());
                } else {
                    System.out.printf("%.2fi", result.getImaginary());
                }
            } else {
                if (result.getImaginary() >= 0) {
                    System.out.printf("+%di", (int) result.getImaginary());
                } else {
                    System.out.printf("%di", (int) result.getImaginary());
                }
            }

            System.out.println();
        }
    }

    private boolean hasDecimal(double number) {
        return (number % 1) != 0;
    }
}
