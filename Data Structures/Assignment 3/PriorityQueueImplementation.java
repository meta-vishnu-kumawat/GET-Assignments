import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define the PriorityQueue interface
interface PriorityQueue<T> {
    boolean offer(T element, T priority);
    T poll();
    int size();
    T peek();
    boolean isEmpty();
}

// Pair class for storing elements with priority
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
}

// PriorityQueueImplementation class
public class PriorityQueueImplementation<T extends Comparable<T>> implements PriorityQueue<T> {
    private List<Pair<T, T>> queue;
    private int size;

    public PriorityQueueImplementation(int size) {
        queue = new ArrayList<>();
        this.size = size;
    }

    @Override
    public boolean offer(T element, T priority) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return false;
        }
        queue.add(new Pair<>(element, priority));
        queue.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort by priority (descending)
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.remove(0).getKey();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.get(0).getKey();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() >= size;
    }

    // **New Method**: Get queue elements for display
    public List<Pair<T, T>> getQueue() {
        return queue;
    }

    // Main method with user input menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueueImplementation<Integer> pq = new PriorityQueueImplementation<>(5);

        while (true) {
            System.out.println("\nPriority Queue Menu:");
            System.out.println("1. Add Element");
            System.out.println("2. Remove Highest Priority Element");
            System.out.println("3. Peek Highest Priority Element");
            System.out.println("4. Display Queue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter element: ");
                    int element = scanner.nextInt();
                    System.out.print("Enter priority: ");
                    int priority = scanner.nextInt();
                    pq.offer(element, priority);
                    break;
                case 2:
                    System.out.println("Removed Element: " + pq.poll());
                    break;
                case 3:
                    System.out.println("Highest Priority Element: " + pq.peek());
                    break;
                case 4:
                    System.out.println("Current Queue:");
                    for (Pair<Integer, Integer> item : pq.getQueue()) { // Using getter method
                        System.out.println("Value: " + item.getKey() + ", Priority: " + item.getValue());
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }
}