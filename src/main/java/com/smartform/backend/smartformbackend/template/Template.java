package com.smartform.backend.smartformbackend.template;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

@Document(collection = "templates")
public class Template {
    @Id
    private String id;
    private Map<String, Object> details;

    @JsonCreator
    public Template(Map<String, Object> details) {
        this.details = details;
    }

    public Template(Object details2) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

}
