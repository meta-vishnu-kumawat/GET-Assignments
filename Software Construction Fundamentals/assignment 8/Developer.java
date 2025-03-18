/**
 * Represents a developer employee.
 * Includes overtime calculations as additional compensation.
 */

public class Developer extends Employee {
    private double overTimeHours;
    private double overTimeRate;

    /**
     * Constructs a Developer with the specified details.
     *
     * @param id             The unique ID of the developer.
     * @param name           The name of the developer.
     * @param departmentName The department name the developer belongs to.
     * @param basicSalary    The basic salary of the developer.
     * @param overtimeHours  The number of overtime hours worked.
     * @param overtimeRate   The rate of pay for overtime work.
     */

    public Developer(int id, String name, String department, double basicSalary, double overTimeHours,
            double overTimeRate) {
        super(id, name, department, basicSalary);
        this.overTimeHours = overTimeHours;
        this.overTimeRate = overTimeRate;
    }

    public double getBonus() {
        try {
            return basicSalary * .10;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double getCompensation() {
        try {
            return overTimeHours * overTimeRate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
