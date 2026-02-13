package designPatterns.splitwiseDesignPractice.service;

import designPatterns.splitwiseDesignPractice.entities.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheetService {
    public static void updateBalanceSheet(Group group, Expense expense, User paidBy) {
        double totalAmount = expense.getSplits().stream().mapToDouble(Split::getAmount).sum();
        group.getBalanceSheetMap().get(paidBy).addTotalPaid(totalAmount);
        for(Split split: expense.getSplits()){
            User user = split.getUser();
            double amt = split.getAmount();
            group.getBalanceSheetMap().get(user).addTotalExpense(amt);
            if(!user.equals(paidBy)){
                group.getBalanceSheetMap().get(user).addBalance(paidBy, -amt);
                group.getBalanceSheetMap().get(paidBy).addBalance(user, amt);
            }
        }
    }
}
