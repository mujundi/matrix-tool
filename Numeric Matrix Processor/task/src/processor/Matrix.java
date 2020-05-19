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

    public void setMatrix(int[][] array) {
        this.matrix = Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
    }

    public boolean hasSameDimensionsOf(Matrix addend) {
        return this.numOfColumns == addend.getNumOfColumns() &&
                this.numOfRows == addend.getNumOfRows();
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
