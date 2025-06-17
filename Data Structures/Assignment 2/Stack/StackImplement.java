package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StackImplement<T> implements Stack<T> {
    private int top;
    private int size;
    private List<T> elements;

    public StackImplement(int size) {
        this.size = size;
        this.top = -1;
        elements = new ArrayList<>(size);
    }

    public StackImplement() {
        this.size = 100;
        this.top = -1;
        elements = new ArrayList<>(size);
    }

    public int getTop() {
        return top;
    }

    @Override
    public boolean push(T element) {
        if (isFull()) {
            return false;
        }
        top++;
        elements.add(element);
        return true;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return null;
        }
        T element = elements.get(top);
        elements.remove(top);
        top--;
        return element;

    }

    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == size - 1;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return null;
        }
        return elements.get(top);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = top; i >= 0; i--) {
            s.append(elements.get(i)).append(" ");

        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Enter the size of stack");
        StackImplement<Integer> stack = new StackImplement<>(scanner.nextInt());
        do {
            System.out.println("\nStack Operations Menu:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. check isFull");
            System.out.println("6. check isEmpty");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (!stack.isFull()) {
                        System.out.print("Enter an element to push: ");
                        int element = scanner.nextInt();
                        stack.push(element);
                        System.out.println(element + " pushed onto stack.");
                    } else {
                        System.out.println("stack is Full");
                    }
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        System.out.println(stack.pop() + " popped from stack.");
                    } else {
                        System.out.println("Stack is empty!");
                    }
                    break;
                case 3:
                    if (!stack.isEmpty()) {
                        System.out.println("Top element is: " + stack.peek());
                    } else {
                        System.out.println("Stack is empty!");
                    }
                    break;
                case 4:
                    if (!stack.isEmpty()) {
                        System.out.println("Stack elements: " + stack);
                    } else {
                        System.out.println("Stack is empty!");
                    }
                    break;
                case 5:
                    System.out.println("Stack is Full?" + stack.isFull());
                    break;
                case 6:
                    System.out.println("Stack is Empty?" + stack.isEmpty());
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);

        scanner.close();

    }

}
