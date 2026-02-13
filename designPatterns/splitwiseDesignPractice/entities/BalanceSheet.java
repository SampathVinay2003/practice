package designPatterns.splitwiseDesignPractice.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    private double totalPaid = 0;
    private double totalExpense = 0;
    private Map<User, Double> balanceSheetMap = new ConcurrentHashMap<>();
    public void addTotalPaid(double amount){
        totalPaid += amount;
    }
    public void addTotalExpense(double amount){
        totalExpense += amount;
    }
    public void clearBalance(){
        balanceSheetMap.clear();
    }

    public void addBalance(User user, double amount) {
        balanceSheetMap.put(user, balanceSheetMap.getOrDefault(user, 0.0)+ amount);
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public Map<User, Double> getBalanceSheetMap() {
        return balanceSheetMap;
    }
}
