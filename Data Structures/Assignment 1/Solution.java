import java.util.Scanner;

public class Solution {
    public static Scanner sc = new Scanner(System.in);

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

    }

    public static Node rotateSublist(Node head, int left, int right, int n) {
        if (head == null || left >= right)
            return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prevLeft = dummy;

        for (int i = 1; i < left; i++) {
            prevLeft = prevLeft.next;
        }

        Node subHead = prevLeft.next;
        Node subTail = subHead;
        for (int i = 0; i < right - left; i++) {
            subTail = subTail.next;
        }
        Node postSub = subTail.next;

        int len = right - left + 1;
        n = n % len;
        if (n == 0)
            return dummy.next;

        Node newTail = subHead;
        for (int i = 1; i < len - n; i++) {
            newTail = newTail.next;
        }
        Node newSubHead = newTail.next;

        newTail.next = null;
        subTail.next = subHead;

        prevLeft.next = newSubHead;

        Node current = newSubHead;
        while (current.next != null) {
            current = current.next;
        }
        current.next = postSub;

        return dummy.next;

    }

    public static boolean findLoop(Node head) {
        if (head == null || head.next == null)
            return false;

        Node slow = head;
        Node fast = head;
        while (fast.next.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow)
                return true;
        }
        return false;

    }

    public static void printLinkedList(Node head) {
        if (head == null)
            return;

        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + "->");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static Node makeLinkedList() {
        System.out.println("Enter the size of linked list");
        int size = getIntInput(sc, 0, 100);
        Node head = new Node(-1);
        Node curr = head;
        for (int i = 0; i < size; i++) {
            System.out.println("Enter element " + (i + 1));
            curr.next = new Node(getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE));
            curr = curr.next;
        }
        // curr.next = head;
        return head.next;
    }

    public static void main(String[] args) {
        Node head = makeLinkedList();

        System.out.println("Enter left , right & Number of rotation");
        int left = sc.nextInt();
        int right = sc.nextInt();
        int n = sc.nextInt();
        printLinkedList(rotateSublist(head, left, right, n));
        // System.out.println(findLoop(head));
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
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                if (num >= min && num <= max)
                    return num;
                else
                    System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (Exception e) {
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }
}