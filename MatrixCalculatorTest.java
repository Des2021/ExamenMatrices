import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MatrixCalculatorTest {
    private MatrixCalculator calculator;

    @Before
    public void setUp() throws IOException {
        String matrixAFile = "MatrixA.txt";
        String matrixCFile = "MatrixC.txt";
        calculator = new MatrixCalculator(matrixAFile, matrixCFile);
    }

    @Test
    public void testCalculateMatrixB() {
        int[][] expectedMatrixB = {
                {6, 6, 6},
                {6, 6, 6},
                {6, 6, 6}
        };

        int[][] actualMatrixB = calculator.calculateMatrixB();

        assertArrayEquals(expectedMatrixB, actualMatrixB);
    }

    @Test
    public void testSaveAndLoadMatrixB() throws IOException {
        String matrixBFile = "MatrixB.txt";

        // Guardar la matriz B calculada en un archivo
        calculator.saveMatrixToFile(calculator.calculateMatrixB(), matrixBFile);

        // Cargar la matriz B desde el archivo
        int[][] loadedMatrixB = calculator.loadMatrixFromFile(matrixBFile);

        // Verificar si la matriz B cargada es igual a la matriz B calculada
        assertArrayEquals(calculator.calculateMatrixB(), loadedMatrixB);
    }
}

