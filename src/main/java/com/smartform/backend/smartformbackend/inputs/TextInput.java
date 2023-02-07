package com.smartform.backend.smartformbackend.inputs;

public class TextInput extends Base {
    private String textInput;

    public TextInput(String plainText, boolean required, String headerType) {
        super(plainText, required, headerType);
    }

    public String getTextInput() {
        return textInput;
    }

    public void setTextInput(String textInput) {
        this.textInput = textInput;
    }

    
}
