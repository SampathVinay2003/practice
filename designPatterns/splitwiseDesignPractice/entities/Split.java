package designPatterns.splitwiseDesignPractice.entities;

public class Split {
    private String id;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private double amount;
    public Split(double amount, User user){
        this.amount = amount;
        this.user = user;
    }
}
