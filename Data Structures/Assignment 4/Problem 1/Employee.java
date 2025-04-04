import java.util.Objects;

class Employee implements Comparable<Employee> {
    private int empId;
    private String name;
    private String address;

    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int compareTo(Employee other) {
        return this.empId - other.empId;
    }

    @Override
    public String toString() {
        return "Employee{empId=" + empId + ", name='" + name + "', address='" + address + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return empId == employee.empId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }
}

