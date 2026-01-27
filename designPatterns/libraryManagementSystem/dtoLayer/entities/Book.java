package designPatterns.libraryManagementSystem.dtoLayer.entities;

import designPatterns.libraryManagementSystem.dtoLayer.enums.Department;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.AuthorRepository;

import java.util.UUID;

public class Book {
    String Id;
    String name;
    String authorId;
    Department department;
    AuthorRepository authorRepository;
    public String getId() {
        return Id;
    }

     public Book(String name, String authorName, Department department){
         authorRepository = AuthorRepository.getInstance();
        this.name = name;
        this.Id = UUID.randomUUID().toString();
        this.department = department;
        this.authorId = authorRepository.getAuthorId(authorName);
    }

    public String getName() {
        return name;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId){
        this.authorId = authorId;
    }
    public Department getDepartment(){
        return department;
    }

}
