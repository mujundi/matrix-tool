package processor;

import java.util.Arrays;

public class Matrix {
    private final int numOfRows;
    private final int numOfColumns;
    private int[][] matrix;

    public Matrix(int n, int m) {
        this.numOfRows = n;
        this.numOfColumns = m;
        this.matrix = new int[n][m];
    }

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public int getNumOfColumns() {
        return this.numOfColumns;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public int getElement(int i, int j) {
        return this.matrix[i][j];
    }

    public void setElement(int i, int j, int value) {
        try {
            this.matrix[i][j] = value;
        } catch (Exception e) {
            System.out.println("Failed to set new element value.");
        }
    }

    public void setMatrix(int[][] array) {
        this.matrix = Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
    }

    public boolean hasSameDimensionsOf(Matrix comparison) {
        return this.numOfColumns == comparison.getNumOfColumns() &&
                this.numOfRows == comparison.getNumOfRows();
    }

    public Matrix add(Matrix addend) {
        if (!this.hasSameDimensionsOf(addend)) {
            return null;
        } else {
            Matrix sum = new Matrix(this.getNumOfRows(), this.getNumOfColumns());

            int[][] arrayToAdd = addend.getMatrix();

            for (int i = 0; i < this.getNumOfRows(); i++) {
                for (int j = 0; j < this.getNumOfColumns(); j++) {
                    sum.setElement(i, j, this.matrix[i][j] + arrayToAdd[i][j]);
                }
            }

            return sum;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return this.numOfRows == matrix1.getNumOfRows() &&
                this.numOfColumns == matrix1.getNumOfColumns() &&
                Arrays.deepEquals(this.matrix, matrix1.getMatrix());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfColumns; j++) {
                output.append(this.matrix[i][j]).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }
}
