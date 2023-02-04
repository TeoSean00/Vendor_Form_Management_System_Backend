package com.smartform.backend.smartformbackend.models.workflow;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workflows")
public class Workflow {
    @Id
    private String id;

    private String name;

    public void insertForm(){

    }

    public void removeForm(){

    }

    public void assignForm(){
        
    }
}
