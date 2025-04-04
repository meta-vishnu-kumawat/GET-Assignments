package PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

class Bolwer {
    private String name;
    private int remainingBall;

    public Bolwer(String name, int remainingBall) {
        this.name = name;
        this.remainingBall = remainingBall;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return remainingBall;
    }
    public void setRemainingBall(int remainingBall){
        this.remainingBall = remainingBall;
    }
}

class PriorityQueue implements PriorityQueueInterface {

    private ArrayList<Bolwer> priorityQueue;
    private int size;

    public PriorityQueue(int size) {
        priorityQueue = new ArrayList<>(size);
        this.size = size;
    }
    public PriorityQueue(){

    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }
    @Override
    public boolean insert(Bolwer bolwer) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return false;
        }
        priorityQueue.add(bolwer);
        int index = priorityQueue.size() - 1;
        while (index > 0 && priorityQueue.get(parent(index)).getPriority() <= priorityQueue.get(index).getPriority()) {
            swap(index, parent(index));
            index = parent(index);

        }
        return true;

    }
    @Override
    public Bolwer extractMax() {
        if (priorityQueue.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        Bolwer min = priorityQueue.get(0);
        if(priorityQueue.size()>1){
            priorityQueue.set(0, priorityQueue.remove(priorityQueue.size() - 1));
        heapify(0);
        }
        else{
            priorityQueue.remove(0);
        }
        return min;
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int biggest = i;
        if (left < priorityQueue.size()
                && priorityQueue.get(left).getPriority() > priorityQueue.get(biggest).getPriority()) {
            biggest = left;
        }
        if (right < priorityQueue.size()
                && priorityQueue.get(right).getPriority() > priorityQueue.get(biggest).getPriority()) {
            biggest = right;
        }
        if (biggest != i) {
            swap(i, biggest);
            heapify(biggest);
        }
    }

    private void swap(int i, int j) {
        Bolwer temp = priorityQueue.get(i);
        priorityQueue.set(i, priorityQueue.get(j));
        priorityQueue.set(j, temp);
    }

    public int size() {
        return priorityQueue.size();
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("[");
        for (Bolwer s : priorityQueue) {
            ans.append("name: " + s.getName() + " Priority: " + s.getPriority() + ",");
        }
        ans.append("]");
        return ans.toString();
    }
    @Override
    public Bolwer peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return priorityQueue.get(0);
    }
    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }
    @Override
    public boolean isFull() {
        return priorityQueue.size() >= size;
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of Priority Queue");
        int size = scanner.nextInt();
        PriorityQueue pq = new PriorityQueue(size);

        while (true) {
            System.out.println("\nPriority Queue Menu:");
            System.out.println("1. Add Bolwer ");
            System.out.println("2. Remove Highest Priority Bolwer");
            System.out.println("3. Peek Highest Priority Bolwer");
            System.out.println("4. Display Queue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
             scanner.nextLine();
            switch (choice) {
                case 1:
                    if(!pq.isFull()){
                        System.out.print("Enter Bolwer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter remaining Balls: ");
                    int balls = scanner.nextInt();
                    pq.insert(new Bolwer(name, balls));
                    }
                    else{
                        System.out.println("Queue is Full");  
                    }
                    break;
                case 2:
                   if(!pq.isEmpty()){
                    System.out.println("Removed Bolwer: " + pq.extractMax().getName());
                   }
                   else{
                    throw new IllegalStateException("Queue is empty");
                   }
                    break;
                case 3:
                    System.out.println("Highest Priority Bolwer: " + pq.peek().getName());
                    break;
                case 4:
                    System.out.println("Current Queue:");
                   System.out.println(pq);
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