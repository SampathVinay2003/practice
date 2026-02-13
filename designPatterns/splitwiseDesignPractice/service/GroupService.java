package designPatterns.splitwiseDesignPractice.service;

import designPatterns.splitwiseDesignPractice.GroupRepo;
import designPatterns.splitwiseDesignPractice.entities.BalanceSheet;
import designPatterns.splitwiseDesignPractice.entities.Expense;
import designPatterns.splitwiseDesignPractice.entities.Group;
import designPatterns.splitwiseDesignPractice.entities.User;
import designPatterns.splitwiseDesignPractice.enums.SplitType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupService {
    List<Group> groups;
    ExpenseService expenseService;
    GroupRepo groupRepo;
    public GroupService(){
        groups = new ArrayList<>();
        expenseService = new ExpenseService();
        groupRepo = new GroupRepo();
    }
    public Group createNewGroup(String goaTrip) {
        Group group = new Group(goaTrip);
        groupRepo.addGroup(group);
        return group;
    }

    public void addExpense(Group group, String s, List<User> participants, SplitType splitType, User paidBy, double amount) throws  Exception {
        Expense expense = expenseService.getExpense(participants, splitType, amount, paidBy, s);
        participants.forEach(participant -> group.getBalanceSheetMap().putIfAbsent(participant, new BalanceSheet()));
        addExpenseToGroup(group, expense, paidBy);
    }

    private void addExpenseToGroup(Group group, Expense expense, User paidBy) {
        Group thatgroup = groupRepo.findGroupById(group.getId());
        if(thatgroup!= null){
            thatgroup.addExpense(expense);
        }
        BalanceSheetService.updateBalanceSheet(group, expense, paidBy);
    }

    public void addMember(Group group, User user) {
        group.addMember(user);
    }

    public void showBalanceSheets(Group group) {
        System.out.println("=== Balance Sheets for Group: " + group.getName() + " ===");
        
        for (Map.Entry<User, BalanceSheet> entry : group.getBalanceSheetMap().entrySet()) {
            User user = entry.getKey();
            BalanceSheet balanceSheet = entry.getValue();
            
            System.out.println("\nUser: " + user.getName());
            System.out.println("Total Paid: " + balanceSheet.getTotalPaid());
            System.out.println("Total Expense: " + balanceSheet.getTotalExpense());
            System.out.println("Net Balance: " + (balanceSheet.getTotalPaid() - balanceSheet.getTotalExpense()));
            
            System.out.println("Individual Balances:");
            for (Map.Entry<User, Double> balanceEntry : balanceSheet.getBalanceSheetMap().entrySet()) {
                User otherUser = balanceEntry.getKey();
                Double amount = balanceEntry.getValue();
                
                if (amount > 0) {
                    System.out.println("  " + otherUser.getName() + " owes " + user.getName() + ": " + amount);
                } else if (amount < 0) {
                    System.out.println("  " + user.getName() + " owes " + otherUser.getName() + ": " + Math.abs(amount));
                }
            }
        }
        System.out.println("\n=== End of Balance Sheets ===");
    }
}
