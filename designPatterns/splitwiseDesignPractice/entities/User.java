package designPatterns.splitwiseDesignPractice.entities;

public class User {
    private static long ID = 1;
    private String id;
    private String name;

    public static long getID() {
        return ID;
    }

    public static void setID(long ID) {
        User.ID = ID;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private long phoneNumber;
    public User(String name){
        this.name = name;
        this.id = String.valueOf(ID++);
    }
    public User setNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

}
