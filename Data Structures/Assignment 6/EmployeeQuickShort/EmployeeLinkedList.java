import java.util.Scanner;

class Employee {
    String name;
    int salary;
    int age;
    Employee next;

    Employee(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.next = null;
    }
}

public class EmployeeLinkedList {
    private Employee head;
    private Scanner scanner = new Scanner(System.in);

    public void addEmployee() {
        System.out.print("Enter Employee Name: ");
        String name = scanner.next();
        System.out.print("Enter Salary: ");
        int salary = scanner.nextInt();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();

        Employee newEmployee = new Employee(name, salary, age);
        if (head == null) {
            head = newEmployee;
        } else {
            Employee temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newEmployee;
        }
        System.out.println("Employee added successfully!\n");
    }

    public void displayEmployees() {
        if (head == null) {
            System.out.println("No employees to display.\n");
            return;
        }
        Employee temp = head;
        System.out.println("Employee List:");
        while (temp != null) {
            System.out.println(temp.name + " | Salary: " + temp.salary + " | Age: " + temp.age);
            temp = temp.next;
        }
        System.out.println();
    }

    // Utility to get tail of a list
    private Employee getTail(Employee node) {
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }

    // Partition the list around the pivot.
    // Returns an array of [newHead, newTail, pivot]
    private Employee[] partition(Employee head, Employee end) {
        Employee pivot = end;
        Employee beforeStart = null, beforeEnd = null;
        Employee afterStart = null, afterEnd = null;
        Employee current = head;

        while (current != pivot) {
            Employee next = current.next;
            current.next = null; // Detach current from its next pointer
            // Condition: sort by salary descending; if equal, sort by age ascending.
            if (current.salary > pivot.salary || (current.salary == pivot.salary && current.age < pivot.age)) {
                // Insert node into end of before list
                if (beforeStart == null) {
                    beforeStart = current;
                    beforeEnd = current;
                } else {
                    beforeEnd.next = current;
                    beforeEnd = current;
                }
            } else {
                // Insert node into end of after list
                if (afterStart == null) {
                    afterStart = current;
                    afterEnd = current;
                } else {
                    afterEnd.next = current;
                    afterEnd = current;
                }
            }
            current = next;
        }

        // At this stage, pivot is isolated.
        // Combine before list, pivot, and after list.
        Employee newHead = (beforeStart != null) ? beforeStart : pivot;
        if (beforeEnd != null) {
            beforeEnd.next = pivot; // attach pivot after before list
        }
        pivot.next = afterStart; // attach after list
        Employee newTail = (afterEnd != null) ? afterEnd : pivot;

        return new Employee[] { newHead, newTail, pivot };
    }

    // Recursive quicksort
    private Employee quickSortRecursive(Employee head, Employee tail) {
        if (head == null || head == tail)
            return head;

        // Partition and get new head, tail, and pivot.
        Employee[] partitioned = partition(head, tail);
        Employee newHead = partitioned[0];
        Employee newTail = partitioned[1];
        Employee pivot = partitioned[2];

        // If pivot is the smallest element, no need to recur for the left part.
        if (newHead != pivot) {
            // Find the node just before the pivot.
            Employee temp = newHead;
            while (temp.next != pivot)
                temp = temp.next;
            temp.next = null; // disconnect left sublist from pivot

            // Recursively sort the left sublist.
            newHead = quickSortRecursive(newHead, temp);

            // Get tail of the sorted left sublist.
            Employee leftTail = getTail(newHead);
            leftTail.next = pivot; // attach pivot at the end.
        }
        // Recursively sort the right sublist.
        pivot.next = quickSortRecursive(pivot.next, newTail);
        return newHead;
    }

    public void sortEmployees() {
        if (head == null) {
            System.out.println("No employees to sort.\n");
            return;
        }
        Employee tail = getTail(head);
        head = quickSortRecursive(head, tail);
        System.out.println("Employees sorted successfully!\n");
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nMENU:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Sort Employees (By Salary Descending, Age Ascending if tie) - Quick Sort");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    sortEmployees();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        } while (choice != 4);
    }

    public static void main(String[] args) {
        EmployeeLinkedList employeeList = new EmployeeLinkedList();
        employeeList.displayMenu();
    }
}
