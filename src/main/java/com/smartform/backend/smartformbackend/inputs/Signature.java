package com.smartform.backend.smartformbackend.inputs;

public class Signature extends Base {
    private boolean signature;
    private String dateSigned;
    
    public Signature(String plainText, boolean required, String headerType, boolean signature, String dateSigned) {
        super(plainText, required, headerType);
        this.signature = signature;
        this.dateSigned = dateSigned;
    }

    public boolean isSignature() {
        return signature;
    }

    public void setSignature(boolean signature) {
        this.signature = signature;
    }

    public String getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(String dateSigned) {
        this.dateSigned = dateSigned;
    }

    
}
