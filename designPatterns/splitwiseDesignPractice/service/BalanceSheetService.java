package designPatterns.splitwiseDesignPractice.service;

import designPatterns.splitwiseDesignPractice.entities.Expense;
import designPatterns.splitwiseDesignPractice.entities.Group;
import designPatterns.splitwiseDesignPractice.entities.Split;
import designPatterns.splitwiseDesignPractice.entities.User;

public class BalanceSheetService {
    public static void updateBalanceSheet(Group group, Expense expense, User paidBy) {
        double totalAmount = expense.getAmount();
        group.getBalanceSheetMap().get(paidBy).addTotalPaid(totalAmount);
        for(Split split: expense.getSplits()){
            User user = split.getUser();
            double amt = split.getAmount();
            group.getBalanceSheetMap().get(user).addTotalExpense(amt);
            if(!user.equals(paidBy)){
                group.getBalanceSheetMap().get(user).addBalance(paidBy, -amt);//user owes paidBy user(paidBy gets)
                group.getBalanceSheetMap().get(paidBy).addBalance(user, amt);//user owes paidBy user(paidBy gets)
            }
        }
    }
}
