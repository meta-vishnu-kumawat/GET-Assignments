import java.util.InputMismatchException;
import java.util.Scanner;

public class Search {
    private static Scanner sc = new Scanner(System.in);


    /**
     * Search key in array lineary but using recursion
     * @param arr input array
     * @param key key which have to find
     * @param index current index of array on which checking
     * @return index of array if found otherwise -1
     */
    public int linearSearch(int arr[], int key, int index) {
        try {
            if (index + 1 == arr.length && arr[index] != key) {
                return -1;
            }
            if (arr[index] == key) {
                return index;
            }
            return linearSearch(arr, key, index + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }
/**
     * Binary search is a search algorithm that finds the position of a target value within a sorted array.
     * @param arr input array
     * @param key key which have to find
     * @param start starting index of current array 
     * * @param end ending index of  current array 
     * @return index of array if found otherwise -1
     */
    public int binarySearch(int arr[], int start, int end, int key) {
       
        try {
            if (start <= end) {
                int mid = (start + end) / 2;
                if (arr[mid] == key) {
                    return mid;
                } else if (arr[mid] > key) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            
                return binarySearch(arr, start, end, key);
            }
            return -1;

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
 * Check wheather the given array is Sorted or not . 
 * @param arr 
 * @return true if sorted oterwise false
 */
    public boolean checkSorted(int arr[]){
        try {
            for(int i = 0;i<arr.length-1;i++){
          if(arr[i]>arr[i+1]){
            return false;
          }
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
        }
        return true;
    }

/**
 *  function that  take input from user and call function according user 
 * @param option 
 * @return 0 and 1 for sorted array
 */
    public int searchImplemention(int option) {
        System.out.println("Enter the size of Array");
        int size = inputValidator();
        System.out.println("Enter the Elements of array");
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Enter the element " + (i + 1));
            arr[i] = inputValidator();
        }
        System.out.println("Enter the key you want to search in  array");
        int key = inputValidator();
        int index = 0;
        if (option == 3) {
            index = linearSearch(arr, key, index);
        }
        else if (option == 4) {
            boolean isSorted = checkSorted(arr);
            if (isSorted) {
                index = binarySearch(arr, 0,arr.length-1, key);
            }
            else{
                System.out.println("Enter Sorted Array . And Try Again...!!");
                return 0;
            } 
           
        }
        if(index != -1){
            System.out.println("Entered Element Found at index " +(index+1));
        }
        else{
            System.out.println("Entered Element not found in Array ");
        }
        return 1;
    }

}