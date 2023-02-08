package com.smartform.backend.smartformbackend.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VendorFormDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    // get all topics
    public List<VendorForm> findAll() {
        return mongoTemplate.findAll(VendorForm.class);
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
    public void deleteWorkflow(String id) {
        VendorForm deleteObject = getVendorForm(id);
        mongoTemplate.remove(deleteObject);
    }

    public VendorForm getVendorForm(final String topicId) {
        return mongoTemplate.findById(topicId, VendorForm.class);
    }
}
