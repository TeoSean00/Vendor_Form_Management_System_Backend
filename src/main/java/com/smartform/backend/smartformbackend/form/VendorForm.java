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
    private String formNumber;
    private String revNumber;

    public VendorForm(String companyName, String creationDate, String formName, String formNumber, String revNumber) {
        this.companyName = companyName;
        this.creationDate = creationDate;
        this.formName = formName;
        this.formNumber = formNumber;
        this.revNumber = revNumber;
    }

}
