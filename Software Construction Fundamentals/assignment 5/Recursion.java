import java.util.InputMismatchException;
import java.util.Scanner;

public class Recursion {
    private static Scanner sc = new Scanner(System.in);

    /**
     * H.C.F of two numbers x and y using Euclid’s algorithm. Receive x and y as method parameters and return computed value.Find the HCF using
     * @param x first number
     * @param y second number
     * @return H.C.F of given numbers 
     */
    public int findHCF(int x, int y) {
        try {
            if (y == 0) {
                return x;
            }
            return findHCF(y, x % y);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }

    /**
     * L.C.M. of two numbers x and y. Receive x and y as method parameters and return computed value.
     * @param x first number
     * @param y second number
     * @return L.C.F of given numbers 
     */
    public int findLCF(int x, int y) {
        try {
            int hcf = findHCF(x, y);
            return (x * y) / hcf;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Validate that user enter correct integer or not
     * 
     * @return Integer value enter by user
     */
    public static int inputValidator() {

        try {
            int integer = 0;
            while (true) {
                try {
                    integer = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {

                    System.out.println("Input invalid. please enter a valid integer...");
                    sc.nextLine();
                }
            }

            return integer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }

    }

    /**
     * Validate that user enter correct integer or not
     * 
     * @return Integer value enter by user
     */
    public static int numValidator() {

        try {
            int integer = 0;
            while (true) {
                try {
                    integer = sc.nextInt();
                    if (integer <= 0) {
                        System.out.println("Enter Only positive number");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {

                    System.out.println("Input invalid. please enter a valid integer...");
                    sc.nextLine();
                }
            }

            return integer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }

    }

    /**
     * Validate the option which is selected by user
     * 
     * @param min
     * @param max
     * @param option
     * @return option enter my user
     */
    public static int optionValidator(int min, int max, int option) {

        try {
            boolean flag = false;
            while (!flag) {
                if (option >= min && option <= max) {
                    flag = true;
                } else {
                    System.out.println("Input invalid. please enter a valid option...");
                    sc.nextLine();
                    option = sc.nextInt();
                }
            }
            return option;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }


    public static void main(String[] args) {
        try {
            Recursion R = new Recursion();
            Search S = new Search();
            NQueen Q = new NQueen();
            KnightsTourProblem K = new KnightsTourProblem();

            boolean flag = true;
            while (flag) {
                System.out.println("Select option");
                System.out.println(
                        "1. Find HCF  \n 2. Find LCM   \n 3. Search Using linear \n 4. Search Using binary  \n 5.NQueen problem \n 6.knight on Tour problem \n 7.Exit");

                System.out.println("Select option");
                int option = inputValidator();
                option = optionValidator(1, 7, option);
                int num1 = Integer.MAX_VALUE;
                int num2 = Integer.MAX_VALUE;
                int N = Integer.MAX_VALUE;
                if (option == 7) {
                    flag = false;
                    continue;
                } else if (option == 1 || option == 2) {
                    System.out.print("Enter the first number :");
                    num1 = numValidator();
                    System.out.print("Enter the second  number :");
                    num2 = numValidator();
                } else if (option == 5 || option == 6) {
                    System.out.print("Enter the size of board:");
                    N = numValidator();
                }

                System.out.println("=============================");

                switch (option) {
                    case 1:
                        System.out.println("HCM of given Numbers is = " + R.findHCF(num1, num2));

                        break;
                    case 2:
                        System.out.println("LCM of given Numbers is = " + R.findLCF(num1, num2));
                        break;
                    case 3:
                        S.searchImplemention(option);
                        break;
                    case 4:
                        int result = S.searchImplemention(option);
                        while (result == 0) {
                            result = S.searchImplemention(option);
                        }
                        break;
                    case 5:
                          Q.solveNQ(N);
                        break;
                        case 6:
                         K.solveKnightTour(N);
                        break;
                    default:
                        System.out.println("Select valid option . Try Again...!!");

                        break;
                }
                System.out.println("=============================");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}