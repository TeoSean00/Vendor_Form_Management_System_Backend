package com.smartform.backend.smartformbackend.workflow;

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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {
    @Autowired
    private WorkflowDAO workflowDAO;

    // anything you return is automatically coverted to JS
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Workflow> getAllWorkflows() {
        return workflowDAO.findAll();
    }

    // need to tell spring to send the id to the method
    @RequestMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Workflow getTopic(@PathVariable String id) {
        // call a method from the business service of the topic that has the id
        return workflowDAO.getWorkflow(id);
    }

    // map this method to any request that is a POST at /topics
    @RequestMapping(method = RequestMethod.POST, value = "")
    // @PreAuthorize("hasRole('ADMIN')")
    // getting the the request payload
    public void addWorkflow(@RequestBody Workflow workflow) {
        workflowDAO.insertWorkflow(workflow);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateTopic(@RequestBody Workflow workflow, @PathVariable String id) {
        workflowDAO.updateWorkflow(id, workflow);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTopic(@PathVariable String id) {
        workflowDAO.deleteWorkflow(id);
    }
}
