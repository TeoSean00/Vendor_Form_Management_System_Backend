package com.smartform.backend.smartformbackend.template;

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
@RequestMapping("/api/template")
public class TemplateController {
    @Autowired
    private TemplateDAO templateDAO;

    @GetMapping("/all")
    public List<Template> getallTemplates() {
        return templateDAO.findAll();
    }

    @RequestMapping("/{id}")
    public Template getTemplate(@PathVariable String id) {
        return templateDAO.getTemplate(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    // getting the the request payload
    public void addTemplate(@RequestBody Template template) {
        templateDAO.insertTemplate(template);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateTemplate(@RequestBody Template template, @PathVariable String id) {
        templateDAO.updateTemplate(id, template);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTemplate(@PathVariable String id) {
        templateDAO.deleteTemplate(id);
    }
}
