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

    
    private Employee sortedInsert(Employee sorted, Employee newNode) {
        if (sorted == null || newNode.salary > sorted.salary || 
            (newNode.salary == sorted.salary && newNode.age < sorted.age)) {
            newNode.next = sorted;
            return newNode;
        }

        Employee current = sorted;
        while (current.next != null && 
              (current.next.salary > newNode.salary || 
              (current.next.salary == newNode.salary && current.next.age < newNode.age))) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        return sorted;
    }

   
    public void sortEmployees() {
        if (head == null) {
            System.out.println("No employees to sort.\n");
            return;
        }

        Employee sorted = null;
        Employee current = head;

        while (current != null) {
            Employee next = current.next;
            sorted = sortedInsert(sorted, current);
            current = next;
        }

        head = sorted;
        System.out.println("Employees sorted successfully!\n");
    }

   
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nMENU:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Sort Employees (By Salary Descending, Age Ascending if tie)");
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