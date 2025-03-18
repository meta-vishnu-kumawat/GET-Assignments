/**
 * Represents a manager employee.
 * Includes performance bonus calculations.
 */

public class Manager extends Employee {

    private double performanceBonus;

    /**
     * Constructs a Manager with the specified details.
     *
     * @param id               The unique ID of the manager.
     * @param name             The name of the manager.
     * @param department       The department name the manager belongs to.
     * @param basicSalary      The basic salary of the manager.
     * @param performanceBonus The performance bonus of the manager.
     */

    public Manager(int id, String name, String department, double basicSalary, double performanceBonus) {
        super(id, name, department, basicSalary);

        this.performanceBonus = performanceBonus;
    }

    public double getBonus() {
        try {
            return basicSalary * .20;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double getCompensation() {
        try {
            return performanceBonus;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
