package designPatterns.libraryManagementSystem.dtoLayer.entities;

import java.util.UUID;

public class LibraryBranch {
    String Id;
    String name;
    String phoneNumber;
    String address;

    public String getId() {
        return Id;
    }

    public LibraryBranch(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.Id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
