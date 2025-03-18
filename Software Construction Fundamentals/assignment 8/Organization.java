import java.util.List;
import java.util.ArrayList;

/**
 * Represents an organization that manages multiple departments.
 */

public class Organization {
    private String name;
    private List<Department> departments;

    /**
     * Constructs an Organization with the specified name.
     *
     * @param name The name of the organization.
     */

    public Organization(String name) {
        this.name = name;
        departments = new ArrayList<Department>();
    }

    /**
     * return the name of Origanization
     * 
     * @return the name of Origanization
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
     * Adds a department to the organization.
     *
     * @param department The department to be added.
     * @return true if the department was successfully added, false if it already
     *         exists.
     */

    public boolean join(Department department) {
        try {
            if (department != null && !departments.contains(department)) {
                departments.add(department);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves all employees from all departments.
     *
     * @return A list of all employees.
     */

    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<Employee>();

        try {
            for (Department department : departments) {
                allEmployees.addAll(department.getEmployees());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allEmployees;
    }

    /**
     * Retrieves employee from all departments by id.
     *
     * @return Employee.
     */
    public Employee getEmployeeById(int Id) {
        List<Employee> allEmployees = new ArrayList<Employee>();

        try {
            for (Department department : departments) {
                allEmployees.addAll(department.getEmployees());
            }
            for (Employee employee : allEmployees) {
                if (employee.getId() == Id) {
                    return employee;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all departments of Origanization.
     *
     * @return A list of all department.
     */
    public List<Department> getAllDepartments() {
        try {
            return departments;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
