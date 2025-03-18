import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test class to validate the payroll system.
 */
public class PayrollTest {

    /**
     * Test to create an organization with departments and employees,
     * then prints the salary slips.
     */
    @Test
    public void testPayroll() {
        // Step 1: Create the organization
        Organization org = new Organization("Metacube");
        
        // Step 2: Create a department for developers
        Department devDept = new Department("Development");

        // Step 3: Create developer employees
        Developer dev1 = new Developer(101, "Alice", "Hr", 5000, 500, 200);
        Developer dev2 = new Developer(102, "Bob", "It", 6000, 600, 300);

        // Step 4: Add developers to the department
        assertTrue(devDept.join(dev1), "Alice should join the department.");
        assertTrue(devDept.join(dev2), "Bob should join the department.");

        // Step 5: Add the department to the organization
        assertTrue(org.join(devDept), "Department should be added to the organization.");

        // Step 6: Create a Payroll instance and print salary slips
        Payroll payroll = new Payroll();
        payroll.printSalarySlips(org);
    }
}
