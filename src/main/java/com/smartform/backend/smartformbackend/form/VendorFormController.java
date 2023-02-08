package com.smartform.backend.smartformbackend.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.smartform.backend.smartformbackend.workflow.Workflow;
import com.smartform.backend.smartformbackend.workflow.WorkflowDAO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/form")
public class VendorFormController {
    @Autowired
    private MongoTemplate mongoTemplate;
    private VendorFormDAO vendorFormDAO;
    private WorkflowDAO workflowDAO;

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

    @RequestMapping(method = RequestMethod.POST, value = "")
    @PreAuthorize("hasRole('ADMIN')")
    // getting the the request payload
    public ResponseEntity<Object> addForm(@RequestBody VendorForm vendorForm) {
        // function to iterate through requestbody, based on the inputtype fill in the
        // data
        String checkId = vendorForm.getWorkflowId();
        Workflow checkWorkflow = mongoTemplate.findById(checkId, Workflow.class);
        if (checkWorkflow != null) {
            vendorFormDAO.insertVendorForm(vendorForm);
            checkWorkflow.insertForm(vendorForm.getId());
            workflowDAO.updateWorkflow(checkId, checkWorkflow);
            return new ResponseEntity<Object>("Form added", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("Workflow does not exist", HttpStatus.FORBIDDEN);
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
