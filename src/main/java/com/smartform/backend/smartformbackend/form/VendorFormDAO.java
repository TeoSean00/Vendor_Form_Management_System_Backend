package com.smartform.backend.smartformbackend.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class VendorFormDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    // get all forms
    public List<VendorForm> findAll() {
        return mongoTemplate.findAll(VendorForm.class);
    }

    // get all forms by Vendor
    public List<VendorForm> findAllVendorForms(String vendorId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("vendorId").is(vendorId));
        return mongoTemplate.find(query, VendorForm.class);
    }

    public void saveAll(final List<VendorForm> forms) {
        mongoTemplate.insertAll(forms);
    }

    // update function
    public void updateVendorForm(String id, VendorForm vendorForm) {
        mongoTemplate.save(vendorForm);
    }

    // insert topic
    public void insertVendorForm(VendorForm vendorForm) {
        mongoTemplate.insert(vendorForm);
    }

    // delete topic
    public void deleteVendorForm(String id) {
        VendorForm deleteObject = getVendorForm(id);
        mongoTemplate.remove(deleteObject);
    }

    public VendorForm getVendorForm(final String formId) {
        return mongoTemplate.findById(formId, VendorForm.class);
    }
}
