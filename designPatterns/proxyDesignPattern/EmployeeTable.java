package designPatterns.proxyDesignPattern;

public interface EmployeeTable {
    Employee getEmployee();

    void deleteEmployee(String user);

    void updateEmployee();

    void createEmployee(String user);
}
