package designPatterns.libraryManagementSystem.dtoLayer.entities;

import java.util.UUID;

public class Author {
    String Id;
    String name;
    String country;
    String rating;
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getId() {
        return Id;
    }

    public Author(String name){
        this.name = name;
        this.Id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }
}
