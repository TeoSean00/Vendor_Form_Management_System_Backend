package com.smartform.backend.smartformbackend.form;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "forms")
public class VendorForm {
    @Id
    private String id;
    private String companyName;
    private String creationDate;
    private String formName;
    private int formNumber;
    private int revNumber;
    private String vendorId;

    public VendorForm(String companyName, String creationDate, String formName, int formNumber, int revNumber,
            String vendorId) {
        this.companyName = companyName;
        this.creationDate = creationDate;
        this.formName = formName;
        this.formNumber = formNumber;
        this.revNumber = revNumber;
        this.vendorId = vendorId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateDate() {
        return this.creationDate;
    }

    public void setCreateDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public int getFormNumber() {
        return this.formNumber;
    }

    public void setFormNumber(int formNumber) {
        this.formNumber = formNumber;
    }

    public int getFormRevNumber() {
        return this.revNumber;
    }

    public void setFormRevNumber(int revNumber) {
        this.revNumber = revNumber;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(String id) {
        this.vendorId = id;
    }

}
