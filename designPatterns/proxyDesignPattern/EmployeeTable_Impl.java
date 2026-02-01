package designPatterns.proxyDesignPattern;

public class EmployeeTable_Impl implements EmployeeTable {
    @Override
    public Employee getEmployee() {
        return null;
    }

    @Override
    public void deleteEmployee(String user) {
        System.out.println("Deleted!!");
    }

    @Override
    public void updateEmployee() {
        System.out.println("Updated!!");
    }

    @Override
    public void createEmployee(String user) {
        System.out.println("Created!");
    }
}
