package designPatterns.splitwiseDesignPractice.entities;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Group {
    public static long getID() {
        return ID;
    }

    public static void setID(long ID) {
        Group.ID = ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Map<User, BalanceSheet> getBalanceSheetMap() {
        return balanceSheetMap;
    }

    public void setBalanceSheetMap(Map<User, BalanceSheet> balanceSheetMap) {
        this.balanceSheetMap = balanceSheetMap;
    }

    static long ID = 0;
    private String id;
    private String name;
    private Set<User> members;
    private List<Expense> expenses;
    private Map<User, BalanceSheet> balanceSheetMap;
    public Group(String name){
        this.name = name;
        this.id = String.valueOf(ID++);
        this.expenses = new ArrayList<>();
        this.balanceSheetMap = new ConcurrentHashMap<>();
        this.members = new HashSet<>();
    }


    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void addMember(User user) {
        members.add(user);
    }
}
