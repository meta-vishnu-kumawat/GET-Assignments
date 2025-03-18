import java.util.List;
import java.util.ArrayList;

/**
 * Represents a department within an organization.
 * It manages employees and provides methods to add, remove, and list employees.
 */

public class Department {
    private String name;
    private List<Employee> employees;

    /**
     * Constructs a new Department with the specified name.
     *
     * @param name The name of the department.
     */

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
    }

    /**
     * Retrieves the name of the department.
     *
     * @return The name of the department.
     */

    public String getName() {
        try {
            return this.name;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return " ";
    }

    /**
     * Adds an employee to the department if they are not already present.
     * 
     * @param employee The employee to be added.
     * @return true if the employee was successfully added, false if the employee is
     *         already present or null.
     * @throws IllegalArgumentException if the employee is null.
     */

    public boolean join(Employee employee) {
        try {
            if (employee != null && !employees.contains(employee)) {
                employees.add(employee);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Removes an employee from the department if they exist.
     * 
     * @param employee The employee to be removed.
     * @return true if the employee was successfully removed, false if the employee
     *         was not found.
     */

    public boolean relieve(Employee employee) {
        try {
            return employees.remove(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves a list of all employees currently in the department.
     *
     * @return A list of all employees in the department.
     */

    public List<Employee> getEmployees() {
        try {
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
