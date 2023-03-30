package com.smartform.backend.smartformbackend.template;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

// @Component
// public class TemplateService implements CommandLineRunner {

//     @Autowired
//     private MongoTemplate mongoTemplate;

//     @Override
//     public void run(String... args) throws Exception {
//         List<Template> templateList = mongoTemplate.findAll(Template.class);
//         // Map<String, Object> details = new HashMap<String,Object>();
        
//         var vendorJson = "{\"details\":{\"templateInfo\":{\"templateName\":\"NewVendorAssessmentForm\",\"assignedTo\":\"Vendor\",\"templateDesc\":\"Assessmentfornewvendors\"},\"templateContents\":[{\"vendor\":[{\"type\":\"header\",\"order\":0,\"text\":\"NEWVENDORASSESSMENTFORM\",\"style\":\"h1\"},{\"type\":\"text\",\"order\":1,\"text\":\"Company'sName\"},{\"type\":\"number\",\"order\":2,\"text\":\"CompanyRegistrationNo:\"},{\"type\":\"text\",\"order\":3,\"text\":\"OfficeAddress\"},{\"type\":\"boolean\",\"order\":4,\"text\":\"GSTRegistered\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"number\",\"order\":5,\"text\":\"Tel\"},{\"type\":\"text\",\"order\":6,\"text\":\"Fax\"},{\"type\":\"checkbox\",\"order\":7,\"text\":\"TypeofbusinessLicense/Registration\",\"options\":[\"SoleProprietorship\",\"LimitedCompany\",\"PartnershipAgreement\",\"Others\"]},{\"type\":\"header\",\"order\":0,\"text\":\"ContactPerson\",\"style\":\"h1\"},{\"type\":\"text\",\"order\":8,\"text\":\"ContactName\"},{\"type\":\"number\",\"order\":9,\"text\":\"ContactTel\"},{\"type\":\"text\",\"order\":10,\"text\":\"ContactDesignation\"},{\"type\":\"checkbox\",\"order\":11,\"text\":\"NatureofBusiness\",\"options\":[\"Manufacturing\",\"Agent/dealer\",\"Distributor\",\"Others\"]},{\"type\":\"text\",\"order\":12,\"text\":\"IfyoupickedOtherspleasespecifythenatureofyourbusiness\"}]},{\"admin\":[{\"type\":\"header\",\"order\":0,\"text\":\"NEWVENDORASSESSMENTFORM\",\"style\":\"h1\"},{\"type\":\"boolean\",\"order\":2,\"text\":\"ISO9001Certification\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":3,\"text\":\"CertificationBody\"},{\"type\":\"boolean\",\"order\":4,\"text\":\"AccreditationofLaboratory\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":5,\"text\":\"AccreditationBody\"},{\"type\":\"boolean\",\"order\":6,\"text\":\"ProductCertification\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":7,\"text\":\"ProductMarkings(e.g.PSB,UL,TUV)\"},{\"type\":\"boolean\",\"order\":8,\"text\":\"SiteEvaluationResults\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":9,\"text\":\"SiteEvaluationResults\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":10,\"text\":\"ResultsofSamples/ProductEvaluation\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":11,\"text\":\"ResultsofSamples/ProductEvaluation\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":12,\"text\":\"ResultsofFirstDeal\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":13,\"text\":\"ResultsofFirstDeal\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":14,\"text\":\"TrackRecordReview/CustomerReference\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":15,\"text\":\"TrackRecordReview/CustomerReference\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":16,\"text\":\"OtherEvaluation\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":17,\"text\":\"Specify:ProductMarkings(e.g.PSB,UL,TUV)\"}]}]}}";

//         // JSONObject jObject = new JSONObject(vendorJson);
//         // Iterator<?> keys = jObject.keys();
//         // while (keys.hasNext()){
//         //     String key = (String)keys.next();
//         //     String value = jObject.getString(key);
//         //     details.put(key,value);
//         // }
//         JSONParser parser = new JSONParser(vendorJson);  
//         JSONObject json = (JSONObject) parser.parse();

//         if (templateList.size() == 0) {
//             templateList.add(new Template(json));
//         }
//     }
// }
