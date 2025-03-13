import java.util.Scanner;

public class ImmutableIntSet {
    private static final Scanner sc = new Scanner(System.in);
    private final boolean[] setOfElements;
    public static final int UNIVERSAL_SET_SIZE = 1000;
    private int currSize = 0;

    /**
     * Constructs an ImmutableIntSet from an array of integers.
     * 
     * @param nums Array of integers to be included in the set.
     */
    public ImmutableIntSet(int[] nums) {
        setOfElements = new boolean[ImmutableIntSet.UNIVERSAL_SET_SIZE + 1];
        for (int num : nums) {
            if (num > 0 && num <= 1000 && !setOfElements[num]) {
                currSize++;
                setOfElements[num] = true;
            }
        }
    }

    /**
     * Checks if a given number is a member of the set.
     * 
     * @param num The number to check.
     * @return True if the number is in the set, false otherwise.
     */
    public boolean isMember(int num) {
        try {
            return (num >= 0 && num <= 1000 && setOfElements[num]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return The size of the set.
     */
    public int size() {
        return currSize;
    }

    /**
     * Checks if the current set is a subset of another set.
     * 
     * @param s The set to compare with.
     * @return True if the current set is a subset of s, false otherwise.
     */
    public boolean isSubSet(ImmutableIntSet s) {
        try {
            for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
                if (this.setOfElements[i] != s.isMember(i)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return false;
    }

    /**
     * Returns the complement of the current set within the universal set.
     * 
     * @return A new ImmutableIntSet representing the complement.
     */
    public ImmutableIntSet getCompliment() {
        try {
            int[] complimentedSet = new int[UNIVERSAL_SET_SIZE - this.size()];
            int index = 0;
            for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
                if (!setOfElements[i]) {
                    complimentedSet[index++] = i;
                }
            }
            return new ImmutableIntSet(complimentedSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return new ImmutableIntSet(new int[] { -1 });
    }

    /**
     * Computes the union of the current set with another set.
     * 
     * @param s The set to union with.
     * @return A new ImmutableIntSet representing the union.
     */
    public ImmutableIntSet union(ImmutableIntSet s) {
        try {
            int[] unionSet = new int[s.size() + this.size()];
            int index = 0;
            for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
                if (this.setOfElements[i] || s.isMember(i)) {
                    unionSet[index++] = i;
                }
            }
            return new ImmutableIntSet(unionSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return new ImmutableIntSet(new int[] { -1 });
    }

    /**
     * Displays the elements of the set.
     */
    public void displaySet() {
        try {
            System.out.print("{ ");
            for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
                if (this.setOfElements[i]) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println("}");
            System.out.println("Size of set is = " + this.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new ImmutableIntSet by taking user input.
     * 
     * @return An instance of ImmutableIntSet created from user input.
     */
    private static ImmutableIntSet createSet() {
        try {
            System.out.println("Enter the size of Set ");
            int size = getIntInput(sc, 1, 1000);
            System.out.println("Enter the Elements of Set");
            int arr[] = new int[size];
            for (int i = 0; i < size; i++) {
                System.out.println("Enter the element " + (i + 1));
                arr[i] = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            return new ImmutableIntSet(arr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ImmutableIntSet(new int[] { -1 });

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
        int num = 0;
        try {
            while (true) {
                try {
                    num = sc.nextInt();
                    sc.nextLine();
                    if (num >= min && num <= max)
                        return num;
                    else
                        System.out.println("Enter a valid number between " + min + " & " + max);
                } catch (Exception e) {
                    System.out.println("Enter a valid number between " + min + " & " + max);
                    sc.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return num;
    }

    public static void main(String[] args) {
        try {
            ImmutableIntSet set1 = null, set2 = null;
            while (true) {
                System.out.println("\n===== Immutable Int Set Operations =====");
                System.out.println("1. Create Set 1");
                System.out.println("2. Create Set 2");
                System.out.println("3. Display Set 1");
                System.out.println("4. Display Set 2");
                System.out.println("5. Union of Sets");
                System.out.println("6. Check MemberShip in set 1");
                System.out.println("7. Check MemberShip in set 2");
                System.out.println("8. Complement of Set 1");
                System.out.println("9. Check if Set1 is Subset of Set2");
                System.out.println("10. Exit");
                System.out.println("=========================================");
                System.out.print("Enter your choice: ");

                int choice = getIntInput(sc, 1, 10);
                switch (choice) {
                    case 1:
                        set1 = createSet();
                        break;
                    case 2:
                        set2 = createSet();
                        break;
                    case 3:
                        if (set1 != null)
                            set1.displaySet();
                        else
                            System.out.println("Set 1 is not created yet.");
                        break;
                    case 4:
                        if (set2 != null)
                            set2.displaySet();
                        else
                            System.out.println("Set 2 is not created yet.");
                        break;
                    case 5:
                        if (set1 != null && set2 != null)
                            set1.union(set2).displaySet();
                        else
                            System.out.println("Both sets must be created first.");
                        break;
                    case 6:
                        if (set1 != null) {
                            System.out.println("Enter the element to check membership");
                            int num = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            System.out.println(set1.isMember(num));
                        } else
                            System.out.println("Both sets must be created first.");
                        break;
                    case 7:
                        if (set2 != null) {
                            System.out.println("Enter the element to check membership");
                            int num = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            System.out.println(set2.isMember(num));
                        } else
                            System.out.println("Both sets must be created first.");
                        break;
                    case 8:
                        if (set1 != null)
                            set1.getCompliment().displaySet();
                        else
                            System.out.println("Set 1 is not created yet.");
                        break;
                    case 9:
                        if (set1 != null && set2 != null)
                            System.out.println(set1.isSubSet(set2) ? "Set 1 is a subset of Set 2"
                                    : "Set 1 is not a subset of Set 2");
                        else
                            System.out.println("Both sets must be created first.");
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}