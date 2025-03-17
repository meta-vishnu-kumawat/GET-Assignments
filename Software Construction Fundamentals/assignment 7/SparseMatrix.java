import java.util.InputMismatchException;
import java.util.Scanner;

class SparseMatrix {
    private int rows, cols;
    private int[][] matrix;
    public Scanner scanner = new Scanner(System.in);

    /**
     * Constructor: Initializes a matrix of given rows and columns.
     *
     * @param rows Number of rows.
     * @param cols Number of columns.
     */
    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
    }

    /**
     * Takes user input to populate the matrix with non-zero values.
     *
     * @param scanner Scanner object for reading user input.
     */
    public void inputMatrix(Scanner scanner) {
        try {
            System.out.println("Enter number of non-zero elements:");
            int nonZero = inputValidator();
            if (nonZero > ((rows * cols) / 2)) {
                System.out.println(
                        "For sparse matrix nonzero element should be less then zeros . Enter again number of non-zero elements:");
                nonZero = inputValidator();
            }
            System.out.println("Enter row, column, and value of non-zero elements:");
            for (int i = 0; i < nonZero; i++) {
                System.out.println("Enter row, column of " + (i + 1) + " non-zero element:");
                int r = getIntInput(scanner, 0, rows) - 1;
                int c = getIntInput(scanner, 0, cols) - 1;
                System.out.println("Enter value of matrix[" + (r + 1) + "][" + (c + 1) + "]");
                int val = inputValidator();
                matrix[r][c] = val;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays the matrix in row-column format.
     */
    public void displayMatrix() {
        try {
            System.out.println("Matrix:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds two matrices and returns the result.
     *
     * @param other The matrix to be added.
     * @return The resulting matrix after addition.
     */
    public SparseMatrix addMatrix(SparseMatrix other) {
        try {
            if (this.rows != other.rows || this.cols != other.cols) {
                System.out.println("Matrix dimensions do not match for addition.");
                return null;
            }
            SparseMatrix result = new SparseMatrix(rows, cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SparseMatrix(rows, cols);
        }
    }

    /**
     * Transposes the matrix and returns the result.
     *
     * @return The transposed matrix.
     */
    public SparseMatrix transpose() {
        SparseMatrix transposed = new SparseMatrix(cols, rows);
        try {

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    transposed.matrix[j][i] = this.matrix[i][j];
                }
            }
            return transposed;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transposed;
    }

    /**
     * Checks whether the matrix is symmetric.
     *
     * @return True if symmetric, otherwise false.
     */
    public boolean isSymmetric() {
        try {
            if (rows != cols)
                return false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] != matrix[j][i])
                        return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Multiplies the current matrix with another matrix.
     *
     * @param other The matrix to multiply with.
     * @return The resulting matrix after multiplication.
     */
    public SparseMatrix multiplyMatrix(SparseMatrix other) {
        try {
            if (this.cols != other.rows) {
                System.out.println("Matrix dimensions do not match for multiplication.");
                return null;
            }
            SparseMatrix result = new SparseMatrix(this.rows, other.cols);
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < other.cols; j++) {
                    for (int k = 0; k < this.cols; k++) {
                        result.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
                    }
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new SparseMatrix(rows, cols);
    }

    /**
     * Validate that user enter correct integer or not
     * 
     * @return Integer value enter by user
     */
    public int inputValidator() {

        try {
            int integer = 0;
            while (true) {
                try {
                    integer = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {

                    System.out.println("Input invalid. please enter a valid integer...");
                    scanner.nextLine();
                }
            }

            return integer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }

    }

    /**
     * Ensures valid integer input from the user within a specified range.
     * 
     * @param sc  Scanner object for input.
     * @param min Minimum valid value.
     * @param max Maximum valid value.
     * @return The validated integer input.
     */
    public static int getIntInput(Scanner sc, int min, int max) {
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                if (num > min && num <= max)
                    return num;
                else
                    System.out.println("Enter a valid number between " + (min + 1) + " & " + (max));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
