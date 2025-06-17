import java.util.Scanner;

public class CQueue {
    private int SIZE;
    private  int front, rear;
    private int Queue[];
  
    CQueue(int size) {
      this.SIZE =size;
      Queue =  new int[size];
      front = -1;
      rear = -1;
    }
  
    boolean isFull() {
      if (front == 0 && rear == SIZE - 1) {
        return true;
      }
      if (front == rear + 1) {
        return true;
      }
      return false;
    }
  

    boolean isEmpty() {
      if (front == -1)
        return true;
      else
        return false;
    }
  
    
    void enQueue(int element) {
      if (isFull()) {
        System.out.println("Queue is full");
      } else {
        if (front == -1)
          front = 0;
        rear = (rear + 1) % SIZE;
        Queue[rear] = element;
        System.out.println("Inserted " + element);
      }
    }
  
   
    int deQueue() {
      int element;
      if (isEmpty()) {
        System.out.println("Queue is empty");
        return (-1);
      } else {
        element = Queue[front];
        if (front == rear) {
          front = -1;
          rear = -1;
        } 
        else {
          front = (front + 1) % SIZE;
        }
        return (element);
      }
    }
  
    void display() {
      int i;
      if (isEmpty()) {
        System.out.println("Empty Queue");
      } else {
        System.out.println("Front -> " + front);
        System.out.print("Items -> ");
        for (i = front; i != rear; i = (i + 1) % SIZE)
        System.out.print(Queue[i] + " ");
        System.out.print(Queue[i] + " ");
        System.out.println();
        System.out.println("Rear -> " + rear);
      }
    }
  
    public static void main(String[] args) {
  
     Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of Queue");
        int size =  scanner.nextInt();
        int choice;
       CQueue queue = new CQueue(size);
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
                        queue.enQueue(element);
                        System.out.println("Enqueued: " + element);
                    }
                    else{
                        System.out.println("Queue is full!");
                    }
                   
                    break;
                case 2:
                    if (!queue.isEmpty()) {
                        int removedElement = queue.deQueue();
                        System.out.println("Dequeued: " + removedElement);
                    } else {
                        System.out.println("Queue is empty!");
                    }
                    break;
                case 3:
                    queue.display();
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