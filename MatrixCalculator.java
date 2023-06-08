import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixCalculator {
    private int[][] matrixA;
    private int[][] matrixC;

    public MatrixCalculator(String matrixAFile, String matrixCFile) throws IOException {
        matrixA = loadMatrixFromFile(matrixAFile);
        matrixC = loadMatrixFromFile(matrixCFile);
    }

    public int[][] calculateMatrixB() {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[][] matrixB = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixB[i][j] = matrixC[i][j] - matrixA[i][j];
            }
        }

        return matrixB;
    }

    private int[][] loadMatrixFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int rows = 0;
        int cols = 0;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] values = line.split(" ");
                cols = values.length;
                rows++;
            }
        }

        int[][] matrix = new int[rows][cols];
        reader.close();

        reader = new BufferedReader(new FileReader(fileName));
        int currentRow = 0;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] values = line.split(" ");

                for (int i = 0; i < cols; i++) {
                    matrix[currentRow][i] = Integer.parseInt(values[i]);
                }

                currentRow++;
            }
        }

        reader.close();

        return matrix;
    }

    private void saveMatrixToFile(int[][] matrix, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                writer.write(String.valueOf(matrix[i][j]));

                if (j < matrix[0].length - 1) {
                    writer.write(" ");
                }
            }

            writer.write(System.lineSeparator());
        }

        writer.close();
    }

    public static void main(String[] args) {
        String matrixAFile = "MatrixA.txt";
        String matrixCFile = "MatrixC.txt";
        String matrixBFile = "MatrixB.txt";

        try {
            MatrixCalculator calculator = new MatrixCalculator(matrixAFile, matrixCFile);
            int[][] matrixB = calculator.calculateMatrixB();
            calculator.saveMatrixToFile(matrixB, matrixBFile);
            System.out.println("Matriz B calculada y guardada en " + matrixBFile);
        } catch (IOException e) {
            System.err.println("Error al procesar las matrices: " + e.getMessage());
        }
    }
}












