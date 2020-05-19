package processor;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixTest extends TestCase {
    private Matrix matrix;

    @Before
    public void setUp() throws Exception {
        matrix = new Matrix(3, 3);
    }

    @Test
    public void testGetMatrix() {
        int[][] array = new int[3][3];
        assertTrue(Arrays.deepEquals(array, matrix.getMatrix()));
    }

    @Test
    public void testSetMatrix() {
        int[][] array = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        matrix.setMatrix(array);
        assertTrue(Arrays.deepEquals(array, matrix.getMatrix()));
    }

    @Test
    public void testToString() {
        int[][] array = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        matrix.setMatrix(array);
        String expectedString = "1 2 3 \n4 5 6 \n7 8 9 \n";
        assertEquals(expectedString, matrix.toString());

    }

    @Test
    public void testGetNumOfRows() {
        assertEquals(3, matrix.getNumOfRows());
    }

    @Test
    public void testGetNumOfColumns() {
        assertEquals(3, matrix.getNumOfColumns());
    }

    @Test
    public void testHasSameDimensionsOf() {
        boolean test1 = matrix.hasSameDimensionsOf(new Matrix(3, 3));
        boolean test2 = matrix.hasSameDimensionsOf(new Matrix(2, 3));
        boolean test3 = matrix.hasSameDimensionsOf(new Matrix(3, 5));
        assertTrue(test1 && !test2 && !test3);
    }
}