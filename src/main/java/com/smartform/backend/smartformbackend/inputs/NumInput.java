package com.smartform.backend.smartformbackend.inputs;

public class NumInput extends Base {
    private int numInput;

    public NumInput(String plainText, boolean required, String headerType, int numInput) {
        super(plainText, required, headerType);
        this.numInput = numInput;
    }

    public int getNumInput() {
        return numInput;
    }

    public void setNumInput(int numInput) {
        this.numInput = numInput;
    }
    
    
}
