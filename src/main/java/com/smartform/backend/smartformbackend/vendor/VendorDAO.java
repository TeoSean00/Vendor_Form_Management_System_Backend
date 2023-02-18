package com.smartform.backend.smartformbackend.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VendorDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    // get all topics
    public List<Vendor> findAll() {
        return mongoTemplate.findAll(Vendor.class);
    }

    public void saveAll(final List<Vendor> vendors) {
        mongoTemplate.insertAll(vendors);
    }

    // update function
    public void updateVendor(String id, Vendor vendor) {
        mongoTemplate.save(vendor);

    }

    // insert topic
    public void insertVendor(Vendor vendor) {
        mongoTemplate.insert(vendor);
    }

    // delete topic
    public void deleteVendor(String id) {
        Vendor deleteObject = getVendor(id);
        mongoTemplate.remove(deleteObject);
    }

    public Vendor getVendor(final String topicId) {
        return mongoTemplate.findById(topicId, Vendor.class);
    }
}
