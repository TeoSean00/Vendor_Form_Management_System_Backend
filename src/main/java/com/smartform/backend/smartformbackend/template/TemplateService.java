package com.smartform.backend.smartformbackend.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TemplateService implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Template> templateList = mongoTemplate.findAll(Template.class);

        // if (templateList.size() == 0) {
        //     ObjectMapper mapper = new ObjectMapper();

        //     mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //     try {
        //         mongoTemplate.insert(mapper.readValue(
        //                 "{" +
        //                         "details:{" +
        //                         "templateInfo: {" +
        //                         "templateName: 'New Vendor Assessment Form'," +
        //                         "assignedTo: 'Vendor'," +
        //                         "templateDesc: 'Assessment for new vendors'" +
        //                         "}," +
        //                         "templateContents: [" +
        //                         "{" +
        //                         "Vendor: [" +
        //                         "{" +
        //                         "type: 'header'," +
        //                         "order: 0," +
        //                         "text: 'NEW VENDOR ASSESSMENT FORM'," +
        //                         "style: 'h1'" +
        //                         "}," +
        //                         "{ type: 'text', order: 1, text: 'Company's Name' }," +
        //                         "{ type: 'number', order: 2, text: 'Company Registration No:' }," +
        //                         "{ type: 'text', order: 3, text: 'Office Address' }," +
        //                         "{ type: 'boolean', order: 4, text: 'GST Registered' }," +
        //                         "{ type: 'number', order: 5, text: 'Tel' }," +
        //                         "{ type: 'text', order: 6, text: 'Fax' }," +
        //                         "{" +
        //                         "type: 'checkbox'," +
        //                         "order: 7," +
        //                         "text: 'Type of business License/Registration'," +
        //                         "options: [" +
        //                         "'Sole Proprietorship'," +
        //                         "'Limited Company'," +
        //                         "'Partnership Agreement'," +
        //                         "'Others'" +
        //                         "]" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'header'," +
        //                         "order: 0," +
        //                         "text: 'Contact Person'," +
        //                         "style: 'h1'" +
        //                         "}," +
        //                         "{ type: 'text', order: 8, text: 'Contact Name' }," +
        //                         "{ type: 'number', order: 9, text: 'Contact Tel' }," +
        //                         "{ type: 'text', order: 10, text: 'Contact Designation' }," +
        //                         "{" +
        //                         "type: 'checkbox'," +
        //                         "order: 11," +
        //                         "text: 'Nature of Business'," +
        //                         "options: [" +
        //                         "'Manufacturing'," +
        //                         "'Agent/dealer'," +
        //                         "'Distributor'," +
        //                         "'Others'" +
        //                         "]" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'text'," +
        //                         "order: 12," +
        //                         "text: 'If you picked Others please specify the nature of your business'" +
        //                         "}," +
        //                         "]" +
        //                         "}," +
        //                         "{" +
        //                         "Admin: [" +
        //                         "{" +
        //                         "type: 'header'," +
        //                         "order: 0," +
        //                         "text: 'NEW VENDOR ASSESSMENT FORM'," +
        //                         "style: 'h1'" +
        //                         "}," +
        //                         "{ type: 'boolean', order: 2, text: 'ISO 9001 Certification' }," +
        //                         "{ type: 'text', order: 3, text: 'Certification Body' }," +
        //                         "{" +
        //                         "type: 'boolean'," +
        //                         "order: 4," +
        //                         "text: 'Accreditation of Laboratory'" +
        //                         "}," +
        //                         "{ type: 'text', order: 5, text: 'Accreditation Body' }," +
        //                         "{ type: 'boolean', order: 6, text: 'Product Certification' }," +
        //                         "{" +
        //                         "type: 'text'," +
        //                         "order: 7," +
        //                         "text: 'Product Markings (e.g. PSB, UL, TUV)'" +
        //                         "}," +
        //                         "{ type: 'boolean', order: 8, text: 'Site Evaluation Results' }," +
        //                         "{" +
        //                         "type: 'checkbox'," +
        //                         "order: 9," +
        //                         "text: 'Site Evaluation Results'," +
        //                         "options: ['Satisfactory', 'Unsatisfactory']" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'boolean'," +
        //                         "order: 10," +
        //                         "text: 'Results of Samples/Product Evaluation'" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'checkbox'," +
        //                         "order: 11," +
        //                         "text: 'Results of Samples/Product Evaluation'," +
        //                         "options: ['Satisfactory', 'Unsatisfactory']" +
        //                         "}," +
        //                         "{ type: 'boolean', order: 12, text: 'Results of First Deal' }," +
        //                         "{" +
        //                         "type: 'checkbox'," +
        //                         "order: 13," +
        //                         "text: 'Results of First Deal'," +
        //                         "options: ['Satisfactory', 'Unsatisfactory']" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'boolean'," +
        //                         "order: 14," +
        //                         "text: 'Track Record Review/ Customer Reference'" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'checkbox'," +
        //                         "order: 15," +
        //                         "text: 'Track Record Review/ Customer Reference'," +
        //                         "options: ['Satisfactory', 'Unsatisfactory']" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'boolean'," +
        //                         "order: 16," +
        //                         "text: 'Other Evaluation'" +
        //                         "}," +
        //                         "{" +
        //                         "type: 'text'," +
        //                         "order: 17," +
        //                         "text: 'Specify: Product Markings (e.g. PSB, UL, TUV)'" +
        //                         "}," +
        //                         "]" +
        //                         "}" +
        //                         "]" +
        //                         "}" +
        //                         "},",
        //                 Template.class));
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
    }
}
