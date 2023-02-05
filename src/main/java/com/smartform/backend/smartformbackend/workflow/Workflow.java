package com.smartform.backend.smartformbackend.workflow;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workflows")
public class Workflow {
    @Id
    private String id;

    private String name;

    private List<String> users;

    // CRUD for name
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    // CRUD for users
    public void addUser(String userId) {
        this.users.add(userId);
    }

    // CRUD for forms
    public void insertForm() {

    }

    public void removeForm() {

    }

    public void assignForm() {

    }
}
