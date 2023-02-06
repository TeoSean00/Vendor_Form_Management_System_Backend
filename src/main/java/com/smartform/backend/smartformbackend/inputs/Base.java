package com.smartform.backend.smartformbackend.inputs; //Edit accordingly hahaha

public class Base {
    private String plainText;
    private boolean required;
    private String headerType;

    public static void main(String[] args) {

    }

    public Base(String plainText, boolean required, String headerType) {
        this.plainText = plainText;
        this.required = required;
        this.headerType = headerType;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getHeaderType() {
        return headerType;
    }

    public void setHeaderType(String headerType) {
        this.headerType = headerType;
    }
    
    
}
