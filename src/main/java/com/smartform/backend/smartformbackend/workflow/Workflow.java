package com.smartform.backend.smartformbackend.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCreation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.smartform.backend.smartformbackend.auth.User;
import com.smartform.backend.smartformbackend.form.VendorForm;

@Document(collection = "workflows")
public class Workflow {
    @Id
    private String id;
    private String name;
    private List<String> users = new ArrayList<String>();
    private ArrayList<VendorForm> forms = new ArrayList<VendorForm>();

    public Workflow() {

    }

    public Workflow(String name) {
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    // CRUD for name
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    // CRUD for users
    public List<String> getUsers(){
        return this.users;
    }

    public void setUsers(List<String> users){
        this.users = users;
    }

    public void addUser(String userId) {
        this.users.add(userId);
    }

    // CRUD for forms
    public ArrayList<VendorForm> getForms(){
        return this.forms;
    }

    public void setForms(ArrayList<VendorForm> forms){
        this.forms = forms;
    }

    public void insertForm() {

    }

    public void removeForm() {

    }

    public void assignForm() {

    }
}
