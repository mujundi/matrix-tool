package processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Matrix {
    private final int numOfRows;
    private final int numOfColumns;
    private double[][] matrix;

    public Matrix(int n, int m) {
        this.numOfRows = n;
        this.numOfColumns = m;
        this.matrix = new double[n][m];
    }

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public int getNumOfColumns() {
        return this.numOfColumns;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public double getElement(int i, int j) {
        return this.matrix[i][j];
    }

    public void setElement(int i, int j, double value) {
        try {
            this.matrix[i][j] = value;
        } catch (Exception e) {
            System.out.println("Failed to set new element value.");
        }
    }

    public void setMatrix(double[][] array) {
        this.matrix = Arrays.stream(array).map(double[]::clone).toArray(double[][]::new);
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

            double[][] arrayToAdd = addend.getMatrix();

            for (int i = 0; i < this.getNumOfRows(); i++) {
                for (int j = 0; j < this.getNumOfColumns(); j++) {
                    sum.setElement(i, j, this.matrix[i][j] + arrayToAdd[i][j]);
                }
            }

            return sum;
        }
    }

    public Matrix multiplyBy(double factor) {
        Matrix product = new Matrix(this.numOfRows, this.numOfColumns);
        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfColumns; j++) {
                product.setElement(i, j, this.matrix[i][j] * factor);
            }
        }

        return product;
    }

    public Matrix multiplyBy(Matrix matrixTwo) {
        if (this.numOfColumns != matrixTwo.getNumOfRows()) {
            return null;
        }
        
        Matrix product = new Matrix(this.getNumOfRows(), matrixTwo.getNumOfColumns());
        double sum = 0;
        for (int i = 0; i < product.getNumOfRows(); i++) {
            for (int j = 0; j < product.getNumOfColumns(); j++) {
                sum = 0;
                for (int k = 0; k < this.numOfColumns; k++) {
                    sum += this.getElement(i, k) * matrixTwo.getElement(k, j);
                }
                product.setElement(i, j, sum);
            }
        }
        
        return product;
    }

    public Matrix transposeByMain() {
        int m = this.numOfRows;
        int n = this.numOfColumns;
        Matrix transposed = new Matrix(n, m);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed.setElement(i, j, this.matrix[j][i]);
            }
        }

        return transposed;
    }

    public Matrix transposeBySide() {
        int m = this.numOfRows;
        int n = this.numOfColumns;
        Matrix transposed = new Matrix(n, m);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed.setElement(i, j, this.matrix[m-j-1][n-i-1]);
            }
        }

        return transposed;
    }

    public Matrix transposeByVertical() {
        int m = this.numOfRows;
        int n = this.numOfColumns;
        Matrix transposed = new Matrix(m, n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed.setElement(i, j, this.matrix[i][n-j-1]);
            }
        }

        return transposed;
    }

    public Matrix transposeByHorizontal() {
        int m = this.numOfRows;
        int n = this.numOfColumns;

        double[][] array = new double[m][n];
        for (int i = 0; i < m; i++) {
            array[i] = this.matrix[m-i-1];
        }

        Matrix transposed = new Matrix(m, n);
        transposed.setMatrix(array);

        return transposed;
    }

    public Double findDeterminant() {

        if (this.numOfRows != this.numOfColumns) {
            return null;
        }

        if (this.numOfRows == 2) {
            double determinant = this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
            return determinant;
        }

        double determinant = 0;
        int factor;
        int n = this.getNumOfRows();
        Matrix submatrix;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                factor = 1;
            } else {
                factor = -1;
            }

            submatrix = this.findSubmatrix(0, i);
            determinant +=  this.matrix[0][i] * factor * submatrix.findDeterminant();
        }

        return determinant;
    }

    public Matrix findInverse() {
        int n = this.numOfRows;
        Matrix submatrix;
        Matrix cofactors = new Matrix(n, n);
        int factor;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0){
                    factor = 1;
                } else {
                    factor = -1;
                }
                submatrix = this.findSubmatrix(i, j);
                cofactors.setElement(i, j, factor * submatrix.findDeterminant());
            }
        }

        Matrix transposedCofactors = cofactors.transposeByMain();
        double oneOverDet = 1 / this.findDeterminant();
        Matrix inverse = transposedCofactors.multiplyBy(oneOverDet);

        return inverse;
    }

    public double round(double value, int places) {
        BigDecimal decimal = BigDecimal.valueOf(value);
        decimal = decimal.setScale(places, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }

    public Matrix findSubmatrix(int row, int column) {
        int n = numOfRows;
        Matrix submatrix = new Matrix(n-1, n-1);
        int columnIndex = 0;
        int rowIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            if (i == row) {
                rowIndex++;
            }

            columnIndex = 0;
            for (int j = 0; j < n - 1; j++) {
                if (j == column) {
                    columnIndex++;
                }
                submatrix.setElement(i, j, this.matrix[rowIndex][columnIndex]);
                columnIndex++;
            }
            rowIndex++;
        }

        return submatrix;
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
        String spaces;
        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfColumns; j++) {
//                spaces = new String(new char[generation]).replace("\0", "-");
                output.append(this.round(this.matrix[i][j], 2)).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }
}
