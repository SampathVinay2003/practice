package designPatterns.splitwiseDesignPractice.service;

import designPatterns.splitwiseDesignPractice.entities.Expense;
import designPatterns.splitwiseDesignPractice.entities.Split;
import designPatterns.splitwiseDesignPractice.entities.User;
import designPatterns.splitwiseDesignPractice.enums.SplitType;
import designPatterns.splitwiseDesignPractice.strategies.SplitStrategy;
import designPatterns.splitwiseDesignPractice.strategies.SplitStrategyEqual_Impl;
import designPatterns.splitwiseDesignPractice.strategies.SplitStrategyPercentage_Impl;

import java.util.List;
import java.util.UUID;

public class ExpenseService {
    SplitStrategy splitStrategy;

    public Expense getExpense(List<User> participants, SplitType splitType, double amount, User paidBy, String desc) throws Exception {
        switch (splitType) {
            case SplitType.Equal -> splitStrategy = new SplitStrategyEqual_Impl();
            default -> splitStrategy = new SplitStrategyPercentage_Impl();
        }
        List<Split> splits = splitStrategy.splits(participants, amount, null);
        return new Expense(UUID.randomUUID().toString(), desc, splits, paidBy, amount, splitType);
    }
}
