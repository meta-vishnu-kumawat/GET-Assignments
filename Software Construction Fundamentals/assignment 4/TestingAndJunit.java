
import java.util.Scanner;
import java.util.InputMismatchException;

public class TestingAndJunit {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * helper function for Assertion error handling
     * 
     * @param arr as input for operation
     */
    private void handleAssertionError(int[] arr) {
        if (arr.length == 0)
            throw new AssertionError("Array cannot be empty...!");
    }

    /**
     * Mirror section in an array is a group of contiguous elements such that
     * somewhere in the array, the same group appears in reverse order.
     * 
     * @param arr
     * @return Length of largest mirror section of array
     * @throws AssertionError
     */
    public int largestMirrorSection(int[] arr) throws AssertionError {
        handleAssertionError(arr);
        try {
            int maxLength = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = arr.length - 1; j >= i; j--) {
                    int length = 0;
                    while (i + length < arr.length && j - length >= 0 && arr[i + length] == arr[j - length]) {
                        length++;

                    }
                    maxLength = maxLength > length ? maxLength : length;
                }
            }
            return maxLength;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Count the number of clums in arr.Clump in an array is a series of 2 or more
     * adjacent elements of the same value.
     * 
     * @param arr
     * @return Count of clums
     * @throws AssertionError
     */
    public int clumps(int[] arr) throws AssertionError {
        handleAssertionError(arr);

        try {
            int countClumps = 0;
            int currClumpValue = arr[0];
            boolean flag = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == currClumpValue) {
                    if (!flag) {
                        countClumps++;
                        flag = !flag;
                    }

                } else {
                    currClumpValue = arr[i];
                    if (flag) {
                        flag = false;
                    }
                }
            }
            return countClumps;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Return an array that contains exactly the same numbers as the input array,
     * but rearranged so that every X is immediately followed by a Y. Do not move X
     * within array, but every other number may move.
     * 
     * @param arr
     * @param x   value of x
     * @param y   value of y
     * @return Rearranged arr as every X is immediately followed by a Y
     * @throws AssertionError
     */
    public int[] fixXY(int[] arr, int x, int y) {
        handleAssertionError(arr);
        try {
            if (arr[arr.length - 1] == x)
                throw new AssertionError("X occurs at the last index of array");

            int countX = 0, countY = 0;
            boolean flag = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == x) {
                    countX++;
                    if (arr[i + 1] == x) {
                        flag = true;
                    }
                } else if (arr[i] == y) {
                    countY++;
                }
            }
            if (countX != countY)
                throw new AssertionError(" Unequal numbers of X and Y in input array.");
            if (flag)
                throw new AssertionError(" two adjacents X values are there.");

            int posY = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] == x) {
                    while (arr[posY] != y) {
                        posY++;
                    }

                    int temp = arr[i];
                    arr[i] = arr[posY];
                    arr[posY] = temp;
                    posY = i + 1;
                }
            }
            return arr;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new int[] { -1 };
        }

    }

    /**
     * Return the index if there is a place to split the input array so that the sum
     * of the numbers on one side is equal to the sum of the numbers on the other
     * side.
     * 
     * @param arr
     * @return idex of array from that array can be spitted if not then -1.
     * @throws AssertionError
     */
    public int splitArray(int[] arr) {
        handleAssertionError(arr);
        try {
            int leftSum = 0;
            for (int i = 0; i < arr.length; i++) {
                leftSum += arr[i];
            }
            int rightSum = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                rightSum += arr[i];
                leftSum -= arr[i];
                if (leftSum == rightSum)
                    return i;
            }
            return -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MAX_VALUE;
        }
    }

    public void printArray(int arr[]) {

        try {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "  ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
            TestingAndJunit T = new TestingAndJunit();
            Scanner sc = new Scanner(System.in);

            boolean flag = true;
            while (flag) {
                System.out.println("Select option");
                System.out.println(
                        "1. Find largest mirror section  \n 2. Find clums   \n 3. Fit XY \n 4. Spit index  \n 5.Exit");

                System.out.println("Select option");
                int option = inputValidator();
                option = optionValidator(1, 5, option);
                if (option == 5) {
                    flag = false;
                    continue;
                }
                System.out.println("Enter the size of Array");
                int size = inputValidator();
                System.out.println("Enter the Elements of array");
                int arr[] = new int[size];
                for (int i = 0; i < size; i++) {
                    System.out.println("Enter the element " + (i + 1));
                    arr[i] = inputValidator();
                }

                System.out.println("=============================");

                switch (option) {
                    case 1:
                        System.out.println(
                                " The length of largest mirror section in given array -> "
                                        + T.largestMirrorSection(arr));

                        break;
                    case 2:
                        System.out.println("Clums in given array is -> " + T.clumps(arr));
                        break;
                    case 3:
                        System.out.println("Enter the value of X");
                        int x = inputValidator();
                        System.out.println("Enter the value of Y");
                        int y = inputValidator();
                        System.out.print("After fit XY array is  = ");
                        T.printArray(T.fixXY(arr, x, y));
                        System.out.println();
                        break;
                    case 4:

                        System.out.println("Spit index of array is  -> " + T.splitArray(arr));
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
        sc.close();
    }
}