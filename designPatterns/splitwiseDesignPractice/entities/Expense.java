package designPatterns.splitwiseDesignPractice.entities;

import designPatterns.splitwiseDesignPractice.enums.SplitType;
import java.util.List;


public class Expense {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    private String id;
    private String desc;
    private List<Split> splits;
    private User paidBy;
    private double amount;
    private SplitType splitType;
    public Expense(String id, String desc, List<Split> splits, User paidBy, double amount, SplitType splitType) {
        this.id = id;
        this.desc = desc;
        this.splits = splits;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splitType = splitType;
    }
}