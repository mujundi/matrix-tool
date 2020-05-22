package processor;

import java.util.Scanner;

public class TextUserInterface {
    private Scanner scanner;

    public TextUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        String selectedOption;
        do {
            printMenu();
            selectedOption = scanner.nextLine();
            if (selectedOption.equals("0")) {
                break;
            } else if (selectedOption.isBlank()) {
                continue;
            }

            handleSelection(selectedOption);

        } while (!selectedOption.equals("0"));
    }

    public void printMenu() {
        System.out.println("\n1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    public void handleSelection(String selectedOption) {
        int option = Integer.valueOf(selectedOption);
        switch (option) {
            case 1:
                getSum();
                break;
            case 2:
                getScalarProduct();
                break;
            case 3:
                getDotProduct();
                break;
            case 4:
                getTranspose();
                break;
            case 5:
                getDeterminant();
                break;
            case 6:
                getInverse();
                break;
        }
    }

    public void getScalarProduct() {
        Matrix matrix = initializeMatrix("the");
        setMatrixFromInput(matrix);

        System.out.print("Enter the multiplier: ");
        String input = scanner.nextLine();
        int factor = Integer.valueOf(input);
        System.out.println("The multiplication result is:");
        System.out.println(matrix.multiplyBy(factor));
    }

    public void getDotProduct() {
        Matrix matrixOne = initializeMatrix("first");
        setMatrixFromInput(
                matrixOne);

        Matrix matrixTwo = initializeMatrix("second");
        setMatrixFromInput(
                matrixTwo);

        Matrix product = matrixOne.multiplyBy(matrixTwo);
        if (product != null) {
            System.out.println("The multiplication result is:");
            System.out.println(product);
        } else {
            System.out.println("ERROR");
        }
    }

    public void getSum() {
//        System.out.println("Input:\n");
        Matrix matrixOne = initializeMatrix("first");
        setMatrixFromInput(
                matrixOne);

        Matrix matrixTwo = initializeMatrix("second");
        setMatrixFromInput(
                matrixTwo);

//        System.out.println("\nOutput:\n");
        Matrix sum = matrixOne.add(matrixTwo);
        if (sum != null) {
            System.out.println(sum);
        } else {
            System.out.println("ERROR");
        }
    }

    public void getTranspose() {
        System.out.println("\n1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");

        String selectedOption = this.scanner.nextLine();
        Matrix matrix = initializeMatrix("the");
        setMatrixFromInput(matrix);

        Matrix transposed = null;
        int option = Integer.valueOf(selectedOption);
        switch (option) {
            case 1:
                transposed = matrix.transposeByMain();
                break;
            case 2:
                transposed = matrix.transposeBySide();
                break;
            case 3:
                transposed = matrix.transposeByVertical();
                break;
            case 4:
                transposed = matrix.transposeByHorizontal();
        }

        System.out.println("The result is:");
        System.out.println(transposed);
    }

    public void getDeterminant() {
        Matrix matrix = initializeMatrix("the");
        setMatrixFromInput(matrix);

        System.out.println("The result is:");
        System.out.println(matrix.findDeterminant());
    }

    public void getInverse() {
        Matrix matrix = initializeMatrix("the");
        setMatrixFromInput(matrix);

        System.out.println("The result is:");
        System.out.println(matrix.findInverse());
    }

    public Matrix initializeMatrix(String firstOrSecond) {
        System.out.print("Enter size of " + firstOrSecond + " matrix:");
        String[] dimensions = scanner.nextLine().split(" ");
        int m = Integer.valueOf(dimensions[0]);
        int n = Integer.valueOf(dimensions[1]);

        System.out.println("Enter " + firstOrSecond + " matrix:");
        return new Matrix(m, n);
    }

    public void setMatrixFromInput(Matrix matrix) {
        int m = matrix.getNumOfRows();
        int n = matrix.getNumOfColumns();
        String input;
        String[] numbers;
        for (int i = 0; i < m; i++) {
            input = scanner.nextLine();
            numbers = input.split(" ");
            for (int j = 0; j < n; j++) {
                matrix.setElement(i, j, Double.valueOf(numbers[j]));
            }
        }
    }
}
