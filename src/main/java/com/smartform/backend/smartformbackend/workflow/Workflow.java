package com.smartform.backend.smartformbackend.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCreation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.smartform.backend.smartformbackend.form.VendorForm;

@Document(collection = "workflows")
public class Workflow {
    @Id
    private String id;
    private String name;
    private List<String> users;
    private ArrayList<VendorForm> forms;

    public Workflow() {

    }

    public Workflow(String name) {
        this.name = name;
        this.users = new ArrayList<String>();
        this.forms = new ArrayList<VendorForm>();
    }

    public Workflow(String name, ArrayList<VendorForm> forms) {
        this.name = name;
        this.forms = forms;
    }

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
