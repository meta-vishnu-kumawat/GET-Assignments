import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager{


    public static void addEmployee(List<Employee> employees,Scanner scanner){
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Address: ");
        String address = scanner.nextLine();
            Employee newEmp = new Employee(empId, name, address);
            for(Employee emp :employees){
                if(emp.equals(newEmp)){
                    System.out.println("Employee Already Exist");
                    return;
                }
            }
        employees.add(newEmp);
        System.out.println("Employee added successfully!");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Sort Employees by Natural Order (empId)");
            System.out.println("3. Sort Employees by Name (Ascending)");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                   addEmployee(employees,scanner);
                    break;

                case 2:
                    Collections.sort(employees);
                    System.out.println("Employees sorted by Employee ID!");
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;

                case 3:
                    employees.sort(Comparator.comparing(Employee::getName));
                    System.out.println("Employees sorted by Name (Ascending)!");
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;

                case 4:
                    System.out.println("\nList of Employees:");
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}