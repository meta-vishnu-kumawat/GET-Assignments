import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class CountUnique {

    private static final Map<String, Integer> cache = new HashMap<>();

    public static int CountUniqueChar(String str) {
        Set<Character> countUnique = new HashSet<>();

        if (!cache.containsKey(str)) {
            for (char ch : str.toCharArray()) {
                countUnique.add(ch);
            }

            cache.put(str, countUnique.size());
            return countUnique.size();
        }
        System.out.println("Retrieving cached result...");
        return cache.get(str);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Enter a string to count unique characters");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter a string: ");
                String userInput = scanner.nextLine();
                int result = CountUniqueChar(userInput);
                System.out.println("Unique character count: " + result);
            } else if (choice == 2) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
