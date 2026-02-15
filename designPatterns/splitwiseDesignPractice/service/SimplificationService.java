package designPatterns.splitwiseDesignPractice.service;

import designPatterns.splitwiseDesignPractice.entities.BalanceSheet;
import designPatterns.splitwiseDesignPractice.entities.Group;
import designPatterns.splitwiseDesignPractice.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class SimplificationService {
    private static SimplificationService instance;

    public static SimplificationService getInstance() {
        if (instance == null) {
            synchronized (SimplificationService.class) {
                if (instance == null) {
                    return instance = new SimplificationService();
                }
            }
        }
        return instance;
    }

    private SimplificationService() {
    }

    public void simplify(Group group) {
        Map<User, BalanceSheet> groupBalanceSheetMap = group.getBalanceSheetMap();
        Set<User> users = group.getMembers();
        Map<User, Double> netBalances = new HashMap<>();
        groupBalanceSheetMap.forEach((user, balanceSheet) -> {
            netBalances.put(user, balanceSheet.getTotalPaid() - balanceSheet.getTotalExpense());
        });
        users.forEach(user -> {
            groupBalanceSheetMap.get(user).clearBalance();
        });

        PriorityQueue<User> receivers = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(b), netBalances.get(a)));
        PriorityQueue<User> payers = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(a), netBalances.get(b)));
        netBalances.forEach(((user, aDouble) -> {
            if (aDouble > 0) {
                receivers.add(user);
            } else {
                payers.add(user);
            }
        }));

        while (!receivers.isEmpty() && !payers.isEmpty()) {
            User receiver = receivers.poll();
            User payer = payers.poll();
            double receiverBalance = netBalances.get(receiver);
            double payerBalance = netBalances.get(payer);
            double amount = Math.min(receiverBalance, -payerBalance);
            groupBalanceSheetMap.get(receiver).addBalance(payer, amount);
            groupBalanceSheetMap.get(payer).addBalance(receiver, -amount);
            netBalances.put(receiver, receiverBalance - amount);
            netBalances.put(payer, payerBalance + amount);
            if (netBalances.get(receiver) > 0) {
                receivers.add(receiver);
            }
            if (netBalances.get(payer) < 0) {
                payers.add(payer);
            }
        }
    }
}
