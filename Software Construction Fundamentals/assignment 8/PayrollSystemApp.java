import java.util.*;

/**
 * PayrollSystemApp is the main class for the Payroll Management System.
 * It provides an interactive menu-driven interface to manage departments,
 * employees, and payroll operations.
 */

public class PayrollSystemApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Organization organization = new Organization("Metacube");

    /**
     * The main method serves as the entry point of the application.
     * It provides an interactive menu to manage departments, employees, and
     * generate payroll.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("\n===== Payroll Management System =====");
                System.out.println("1. Add Department");
                System.out.println("2. Add Employee");
                System.out.println("3. View All Employees");
                System.out.println("4. Print Salary Slips");
                System.out.println("5. View All Departments");
                System.out.println("6.Print Salary Slip of Employee");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = getIntInput(scanner, 1, 7);

                switch (choice) {
                    case 1 -> addDepartment();
                    case 2 -> addEmployee();
                    case 3 -> viewAllEmployees();
                    case 4 -> printSalarySlips();
                    case 5 -> getAllDepartments();
                    case 6 -> printPayslipsById();
                    case 7 -> {
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new department to the organization.
     */

    private static void addDepartment() {
        try {
            System.out.print("Enter Department Name: ");
            String departmentName = scanner.next();
            Department department = new Department(departmentName);

            if (organization.join(department)) {
                System.out.println("Department added successfully.");
            } else {
                System.out.println("Department already exists.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new employee to an existing department.
     */

    private static void addEmployee() {
        try {
            System.out.print("Enter Department Name: ");
            String departmentName = scanner.next();

            Department department = findDepartmentByName(departmentName);
            if (department == null) {
                System.out.println("Department not found. Please add the department first.");
                return;
            }

            System.out.print("Enter Employee ID: ");
            int id = getIntInput(scanner, 100, 100000);
            Employee employee = organization.getEmployeeById(id);
            if (employee != null) {
                System.out.print("Employee already Exist..");
                return;
            }
            System.out.print("Enter Employee Name: ");
            String name = scanner.next();

            System.out.print("Enter Basic Salary: ");
            double basicSalary = getIntInput(scanner, 10000, 150000);

            System.out.println("Select Employee Type:");
            System.out.println("1. Developer");
            System.out.println("2. Manager");
            int type = getIntInput(scanner, 1, 2);

            if (type == 1) {
                System.out.print("Enter Overtime Hours: ");
                double overtimeHours = getIntInput(scanner, 1, 10);

                System.out.print("Enter Overtime Rate: ");
                double overtimeRate = getIntInput(scanner, 500, 1000);

                employee = new Developer(id, name, departmentName, basicSalary, overtimeHours, overtimeRate);

            } else if (type == 2) {
                System.out.print("Enter Performance Bonus: ");
                double performanceBonus = scanner.nextDouble();

                employee = new Manager(id, name, departmentName, basicSalary, performanceBonus);

            } else {
                System.out.println("Invalid choice! Employee not added.");
                return;
            }

            if (department.join(employee)) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Employee already exists or invalid.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays all employees across all departments.
     */

    private static void viewAllEmployees() {
        try {
            List<Employee> employees = organization.getAllEmployees();

            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }

            System.out.println("\n=== All Employees ===");
            System.out.println("| EMP_ID | EMP_NAME | EMP_DEPARTMENT|");
            for (Employee employee : employees) {
                System.out.printf("| %-6d | %-8s | %-13s |\n", employee.getId(),
                        employee.getName(), employee.getDepartment());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * * Prints salary slips for all employees in the organization.
     */
    private static void printSalarySlips() {
        try {
            List<Employee> employees = organization.getAllEmployees();

            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }

            System.out.println("\n=== Salary Slips ===");
            Payroll.printSalarySlips(organization);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * find department in organization.
     * 
     * @param name name of department
     * @return department
     */

    private static Department findDepartmentByName(String name) {
        try {
            for (Department department : organization.getAllDepartments()) {
                if (department.getName().equalsIgnoreCase(name)) {
                    return department;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Displays all departments in the organization.
     */

    private static void getAllDepartments() {
        try {
            if (organization.getAllDepartments().isEmpty()) {
                System.out.println("Department not found. Please add the department first.");
                return;
            }
            System.out.println("\n=== All Departments ===");
            for (Department department : organization.getAllDepartments()) {

                System.out.println(department.getName() + " ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the salary slip of a specific employee by their ID.
     */

    private static void printPayslipsById() {
        try {
            System.out.println("Enter the Id of Employee: ");
            int id = getIntInput(scanner, 100, 10000);
            Employee employee = organization.getEmployeeById(id);

            if (employee == null) {
                System.out.println("No employees found.");
                return;
            }

            System.out.println("\n=== Salary Slip of " + employee.getName() + " ===");
            Payroll.generatePayslip(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
            } catch (InputMismatchException e) {
                System.out.println("Input invalid. please enter a valid integer...");
                sc.nextLine();
            }
        }
    }
}
