
/**
 * Handles payroll operations such as tax calculation and salary slip
 * generation.
 */

public class Payroll {
    /**
     * Calculates the tax for a given employee's total salary.
     * 
     * @param salary of employee.
     * @return The calculated tax amount.
     */

    public static double calculateTax(double salary) {
        try {

            if (salary <= 25000) {
                return salary * 0.1;
            } else if (salary <= 50000) {
                return salary * 0.2;
            }
            return salary * 0.3;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Generates a salary slip for the specified employee.
     *
     * @param employee The employee whose salary slip is to be generated.
     * @return A formatted string representing the salary slip.
     */

    public static void generatePayslip(Employee employee) {
        try {
            double totalSalary = employee.getTotalSalary();
            double tax = calculateTax(totalSalary);
            double netSalary = totalSalary - tax;
            System.out.println("--------------------------------------------------");
            System.out.println("Employee ID: " + employee.getId() + "\n"
                    + "Name: " + employee.getName() + "\n"
                    + "Department: " + employee.getDepartment() + "\n"
                    + "Total Salary: " + totalSalary + "\n"
                    + "Tax Deducted: " + tax + "\n"
                    + "Net Salary: " + netSalary + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Prints salary slips for all employees in the list.
     *
     * @param origanization.
     */

    public static void printSalarySlips(Organization organization) {
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|  Payroll Summary for Organization: " + organization.getName() + "  |");
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("| EMP_ID | EMP_NAME | BASIC_SALARY |   BONUS   | COMPENSATION |");
        for (Employee employee : organization.getAllEmployees()) {
            System.out.printf("| %-6d | %-8s | %-12.2f | %-9.1f | %-12.2f |\n", employee.getId(),
                    employee.getName(), employee.getBasicSalary(), employee.getBonus(), employee.getCompensation());
        }
    }

}