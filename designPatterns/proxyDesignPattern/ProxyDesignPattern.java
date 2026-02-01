package designPatterns.proxyDesignPattern;

public class ProxyDesignPattern {
    public static void main(String[] args) {
        EmployeeTable employeeTable = new EmployeeTable_proxyImpl();
        try {
            employeeTable.createEmployee("admin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
