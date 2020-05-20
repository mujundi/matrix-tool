package processor;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUserInterface {
    private Scanner scanner;

    public TextUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        getSum();
    }

    public void getSum() {
//        System.out.println("Input:\n");

        String[] dimensions = scanner.nextLine().split(" ");
        int m = Integer.valueOf(dimensions[0]);
        int n = Integer.valueOf(dimensions[1]);
        Matrix matrixOne = new Matrix(m, n);
        setMatrixFromInput(m, n, matrixOne);

        dimensions = scanner.nextLine().split(" ");
        m = Integer.valueOf(dimensions[0]);
        n = Integer.valueOf(dimensions[1]);
        Matrix matrixTwo = new Matrix(m, n);
        setMatrixFromInput(m, n, matrixTwo);

//        System.out.println("\nOutput:\n");
        Matrix sum = matrixOne.add(matrixTwo);
        if (sum != null) {
            System.out.println(sum);
        } else {
            System.out.println("ERROR");
        }
    }

    public void setMatrixFromInput(int m, int n, Matrix matrix) {

        String input;
        String[] numbers = new String[n];
        for (int i = 0; i < m; i++) {
            input = scanner.nextLine();
            numbers = input.split(" ");
            for (int j = 0; j < n; j++) {
                matrix.setElement(i, j, Integer.valueOf(numbers[j]));
            }
        }
    }
}
