import java.util.Scanner;
import java.util.InputMismatchException;

public class SparseMatrixMenu {
    SparseMatrix matrix1, matrix2;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            SparseMatrixMenu menu = new SparseMatrixMenu();
            menu.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * print option for sparse matrix
     */
    public void run() {

        try {
            int choice;

            do {
                System.out.println("\n==== Sparse Matrix Menu ====");
                System.out.println("1. Input Matrix");
                System.out.println("2. Display Matrix");
                System.out.println("3. Transpose Matrix");
                System.out.println("4. Check Symmetric");
                System.out.println("5. Add Matrices");
                System.out.println("6. Multiply Matrices");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                choice = inputValidator();

                switch (choice) {
                    case 1:
                        inputMatrices(scanner);
                        break;
                    case 2:
                        displayMatrices();
                        break;
                    case 3:
                        transposeMatrix();
                        break;
                    case 4:
                        checkSymmetric();
                        break;
                    case 5:
                        addMatrices();
                        break;
                    case 6:
                        multiplyMatrices();
                        break;
                    case 7:
                        System.out.println("Exiting program. Bye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 7);

            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Take input matrix
     * 
     * @param scanner object
     */
    private void inputMatrices(Scanner scanner) {
        try {
            System.out.print("Enter rows and columns of first matrix: ");
            int rows1 = inputValidator(), cols1 = inputValidator();
            matrix1 = new SparseMatrix(rows1, cols1);
            matrix1.inputMatrix(scanner);

            System.out.print("Do you want to input a second matrix? (yes = 1 / no = 0): ");
            int choice = inputValidator();
            if (choice == 1) {
                System.out.print("Enter rows and columns of second matrix: ");
                int rows2 = inputValidator(), cols2 = inputValidator();
                matrix2 = new SparseMatrix(rows2, cols2);
                matrix2.inputMatrix(scanner);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to display matrices
     */
    private void displayMatrices() {
        try {
            if (matrix1 != null) {
                System.out.println("First Matrix:");
                matrix1.displayMatrix();
            } else {
                System.out.println("First matrix not available.");
            }

            if (matrix2 != null) {
                System.out.println("Second Matrix:");
                matrix2.displayMatrix();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to transpose a matrix
     */
    private void transposeMatrix() {
        try {
            if (matrix1 == null) {
                System.out.println("No matrix available.");
                return;
            }
            SparseMatrix transposed = matrix1.transpose();
            System.out.println("Transposed Matrix:");
            transposed.displayMatrix();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to check if a matrix is symmetric
     */
    private void checkSymmetric() {
        try {
            if (matrix1 == null) {
                System.out.println("No matrix available.");
                return;
            }
            boolean symmetric = matrix1.isSymmetric();
            System.out.println("The matrix is " + (symmetric ? "Symmetric" : "Not Symmetric"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to add two matrices
     */
    private void addMatrices() {
        try {
            if (matrix1 == null || matrix2 == null) {
                System.out.println("Both matrices must be initialized.");
                return;
            }
            SparseMatrix result = matrix1.addMatrix(matrix2);
            if (result != null) {
                System.out.println("Result of Addition:");
                result.displayMatrix();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to multiply two matrices
     */
    private void multiplyMatrices() {
        try {
            if (matrix1 == null || matrix2 == null) {
                System.out.println("Both matrices must be initialized.");
                return;
            }
            SparseMatrix result = matrix1.multiplyMatrix(matrix2);
            if (result != null) {
                System.out.println("Result of Multiplication:");
                result.displayMatrix();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
}
