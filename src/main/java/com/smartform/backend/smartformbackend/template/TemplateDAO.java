package com.smartform.backend.smartformbackend.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TemplateDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Template> findAll() {
        return mongoTemplate.findAll(Template.class);
    }

    public void saveAll(final List<Template> templates) {
        mongoTemplate.insertAll(templates);
    }

    public void updateTemplate(String id, Template template) {
        mongoTemplate.save(template);
    }

    public void insertTemplate(Template template) {
        mongoTemplate.insert(template);
    }

    public void deleteTemplate(String id) {
        Template deleteObject = getTemplate(id);
        mongoTemplate.remove(deleteObject);
    }

    public Template getTemplate(final String templateId) {
        return mongoTemplate.findById(templateId, Template.class);
    }

}
