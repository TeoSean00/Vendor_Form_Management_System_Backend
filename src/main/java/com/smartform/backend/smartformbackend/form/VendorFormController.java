package com.smartform.backend.smartformbackend.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smartform.backend.smartformbackend.vendor.Vendor;
import com.smartform.backend.smartformbackend.vendor.VendorDAO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/form")
public class VendorFormController {

    @Autowired
    private VendorFormDAO vendorFormDAO;

    @Autowired
    private VendorDAO vendorDAO;

    @Autowired
    private MongoTemplate mongoTemplate;

    // anything you return is automatically coverted to JS
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<VendorForm> getAllForms() {
        return vendorFormDAO.findAll();
    }

    // need to tell spring to send the id to the method
    @RequestMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public VendorForm getForm(@PathVariable String id) {
        // call a method from the business service of the topic that has the id
        return vendorFormDAO.getVendorForm(id);
    }

    // map this method to any request that is a POST at /topics
    @RequestMapping(method = RequestMethod.POST, value = "")
    @PreAuthorize("hasRole('ADMIN')")
    // getting the the request payload
    public void addForm(@RequestBody VendorForm vendorForm) {
        String checkId = vendorForm.getVendorId();
        Vendor checkVendor = mongoTemplate.findById(checkId, Vendor.class);
        if (checkVendor != null) {
            vendorFormDAO.insertVendorForm(vendorForm);
            checkVendor.insertForm(vendorForm.getId());
            vendorDAO.updateVendor(checkId, checkVendor);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateForm(@RequestBody VendorForm workflow, @PathVariable String id) {
        vendorFormDAO.updateVendorForm(id, workflow);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteForm(@PathVariable String id) {
        vendorFormDAO.deleteWorkflow(id);
    }
}
