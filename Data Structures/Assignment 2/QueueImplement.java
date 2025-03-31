import java.util.*;
public class QueueImplement {
     
    private int[]Queue;
    private int size,front,rear;

    public QueueImplement(int size){
        Queue = new int[size];
        this.size = size;
        front = rear = -1;
    }

    public boolean add(int data){
        if(rear==size){
            System.out.println("Queue is Full");

            }
            if(front == -1){
                front++;
            }
        Queue[++rear] = data;
        return true;
    }
    
    public int remove(){
        if(front ==-1){
            System.out.println("Queue is Empty");
            return -1;
        }
        int data = Queue[front];
        Queue[front++] = 0;
        return data;
    }

    public boolean isEmpty(){
        return front == -1 || front == size;
    }
    public boolean isFull(){
        return rear+1 == size;
    }
    public int getSize(){
        return size;
    }
 

    @Override
    public String toString(){
        return Arrays.toString(Queue);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of Queue");
        int size =  scanner.nextInt();
        int choice;
QueueImplement queue = new QueueImplement(size);
        do {
            System.out.println("\nQueue Operations Menu:");
            System.out.println("1. Enqueue (Add an element)");
            System.out.println("2. Dequeue (Remove an element)");
            System.out.println("3. Display (Show all elements)");
            System.out.println("4. Check if queue is empty");
            System.out.println("5. Check if queue is full");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element to enqueue: ");
                    int element = scanner.nextInt();
                    if(!queue.isFull()){
                        queue.add(element);
                        System.out.println("Enqueued: " + element);
                    }
                    else{
                        System.out.println("Queue is full!");
                    }
                   
                    break;
                case 2:
                    if (!queue.isEmpty()) {
                        int removedElement = queue.remove();
                        System.out.println("Dequeued: " + removedElement);
                    } else {
                        System.out.println("Queue is empty!");
                    }
                    break;
                case 3:
                    System.out.println("Queue elements: " + queue);
                    break;
                case 4:
                    System.out.println("Is the queue empty? " + queue.isEmpty());
                    break;
                case 5:
                System.out.println("Is the queue full? " + queue.isFull());
                    break;
                    case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

}