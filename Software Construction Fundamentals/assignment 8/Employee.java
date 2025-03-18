
/**
 * Abstract class representing a generic employee.
 * Provides common attributes and methods for all employee types.
 */

public abstract class Employee {

    private int id;
    private String name;
    private String departmentName;
    protected double basicSalary;

    /**
     * Constructs an Employee with the specified details.
     *
     * @param id          The unique ID of the employee.
     * @param name        The name of the employee.
     * @param department  The name of the department the employee belongs to.
     * @param basicSalary The basic salary of the employee.
     */

    public Employee(int id, String empName, String department, double basicSalary) {
        this.id = id;
        this.name = empName;
        this.departmentName = department;
        this.basicSalary = basicSalary;
    }

    /**
     * Gets the employee's ID.
     *
     * @return The employee's ID.
     */

    public int getId() {
        try {
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Gets the employee's name.
     *
     * @return The employee's name.
     */

    public String getName() {
        try {
            return name;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Gets the employee's department name.
     *
     * @return The employee's department name.
     */

    public String getDepartment() {
        try {
            return this.departmentName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Gets the employee's basic salary.
     *
     * @return The employee's basic salary.
     */

    public double getBasicSalary() {
        try {
            return basicSalary;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Calculates the bonus for the employee.
     *
     * @return The calculated bonus.
     */
    public abstract double getBonus();

    /**
     * Calculates additional compensation for the employee.
     *
     * @return The calculated additional compensation.
     */

    public abstract double getCompensation();

    /**
     * Calculates the total salary for the employee.
     *
     * @return The total salary including basic salary, bonus, and compensation.
     */

    double getTotalSalary() {
        try {
            return basicSalary + getBonus() + getCompensation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}