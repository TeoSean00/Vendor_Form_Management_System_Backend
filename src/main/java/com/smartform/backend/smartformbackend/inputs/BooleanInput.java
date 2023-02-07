package com.smartform.backend.smartformbackend.inputs;

public class BooleanInput extends Base{
    private boolean boolInput;
    private String option1;
    private String option2;
    
    public BooleanInput(String plainText, boolean required, String headerType, String option1, String option2) {
        super(plainText, required, headerType);
        this.option1 = option1;
        this.option2 = option2;
    }

    public boolean isBoolInput() {
        return boolInput;
    }

    public void setBoolInput(boolean boolInput) {
        this.boolInput = boolInput;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    

    
}
