package designPatterns.proxyDesignPattern;

public class EmployeeTable_proxyImpl implements EmployeeTable {

    private EmployeeTable employeeTable;

    private EmployeeTable getRealEmployeeTable() {
        if (employeeTable == null) {
            employeeTable = new EmployeeTable_Impl();
        }
        return employeeTable;
    }

    @Override
    public Employee getEmployee() {
        return getRealEmployeeTable().getEmployee();
    }

    @Override
    public void deleteEmployee(String user) {
        if (user.equals("admin")) {
            getRealEmployeeTable().deleteEmployee(user);
        } else {
            throw new RuntimeException("You are not authorized to delete employee");
        }
    }

    @Override
    public void updateEmployee() {
        getRealEmployeeTable().updateEmployee();
    }

    @Override
    public void createEmployee(String user) {
        if (user.equals("admin")) {
            getRealEmployeeTable().createEmployee(user);
        } else {
            throw new RuntimeException("You are not authorized to create employee");
        }
    }
}
