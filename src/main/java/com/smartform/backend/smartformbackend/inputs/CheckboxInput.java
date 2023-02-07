package com.smartform.backend.smartformbackend.inputs;

import java.util.ArrayList;

public class CheckboxInput extends Base{
    private ArrayList<String> options = new ArrayList<String>();

    public CheckboxInput(String plainText, boolean required, String headerType) {
        super(plainText, required, headerType);
    }

    public void addOption(String option){
        this.options.add(option);
    }

    public void removeOption(String option) {
        this.options.remove(option);
    }
}

