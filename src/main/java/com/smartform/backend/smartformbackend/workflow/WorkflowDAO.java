package com.smartform.backend.smartformbackend.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkflowDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    // get all topics
    public List<Workflow> findAll() {
        return mongoTemplate.findAll(Workflow.class);
    }

    public void saveAll(final List<Workflow> topics) {
        mongoTemplate.insertAll(topics);
    }

    // update function
    public void updateWorkflow(String id, Workflow workflow) {
        mongoTemplate.save(workflow);

    }

    // insert topic
    public void insertWorkflow(Workflow workflow) {
        mongoTemplate.insert(workflow);
    }

    // delete topic
    public void deleteWorkflow(String id) {
        Workflow deleteObject = getWorkflow(id);
        mongoTemplate.remove(deleteObject);
    }

    public Workflow getWorkflow(final String topicId) {
        return mongoTemplate.findById(topicId, Workflow.class);
    }
}
