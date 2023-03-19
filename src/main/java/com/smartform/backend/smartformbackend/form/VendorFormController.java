package com.smartform.backend.smartformbackend.form;

import java.util.List;

import org.json.JSONObject;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartform.backend.smartformbackend.pdfgenerator.PDFGeneratorLayer;
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

    // get all the forms for a specific vendor
    @GetMapping("/vendor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<VendorForm> getVendorForms(@PathVariable String id) {
        return vendorFormDAO.findAllVendorForms(id);
    }

    // need to tell spring to send the id to the method
    @RequestMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public VendorForm getForm(@PathVariable String id) {
        // call a method from the business service of the topic that has the id
        return vendorFormDAO.getVendorForm(id);
    }

    // map this method to any request that is a POST at /topics
    @RequestMapping(method = RequestMethod.POST, value = "/")
    @PreAuthorize("hasRole('ADMIN')")
    // getting the the request payload
    public void addForm(@RequestBody VendorForm vendorForm) {
        String checkId = vendorForm.getVendorId();
        System.out.println(checkId + "CHECK ID IS HERE");
        vendorForm.setStatus("vendor_response");
        vendorForm.setRevNumber(1);
        Vendor checkVendor = mongoTemplate.findById(checkId, Vendor.class);
        if (checkVendor != null) {
            vendorFormDAO.insertVendorForm(vendorForm);
            checkVendor.insertForm(vendorForm.getId());
            vendorDAO.updateVendor(checkId, checkVendor);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateForm(@RequestBody VendorForm form, @PathVariable String id) {
        vendorFormDAO.updateVendorForm(id, form);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteForm(@PathVariable String id) {
        vendorFormDAO.deleteWorkflow(id);
    }

    @RequestMapping("generateForm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void generatePdf(@PathVariable String id) {
        PDFGeneratorLayer pdfGenerator = new PDFGeneratorLayer();
        VendorForm form = vendorFormDAO.getVendorForm(id);
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(form.getContent());
            JSONObject jsonObj = new JSONObject(json);
            pdfGenerator.generatePdf(jsonObj);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(content.get("FormInfo"));
        // JSONArray formContent = content.get("FormContent");

        // for (Map.Entry<String, Object> entry : content.entrySet()) {
        // System.out.println(entry.getKey() + "/" + entry.getValue());
        // if (entry.getKey().equals("FormInfo")) {
        // System.out.println("This is form info!");
        // } else if (entry.getKey().equals("FormContent")) {
        // System.out.println("This is FormContent");
        // System.out.println(entry.getValue().getClass());
        // // System.out.println(entry.getValue());
        // String key = entry.getKey();
        // String value = (String) entry.getValue();
        // ObjectMapper objMapper = new ObjectMapper();
        // JSONObject jsonObject = objMapper.readValue(value);
        // System.out.println("Value fking is");
        // System.out.println(value);
        // }
        // String sectionContent = (String) entry.getValue();
        // ArrayList<Map<String, Object>> sectionContent = (ArrayList<Map<String,
        // Object>>) entry.getValue();

        // pdfGenerator.generatePdf(entry.getValue());

        // for (Map<String, Object> item : sectionContent) {
        // System.out.println(item);
        // for (Map.Entry<String, Object> rowContent : item.entrySet()) {
        // System.out.println(rowContent.getKey() + "/" + rowContent.getValue());
        // List<Object> rowContentArray = (List<Object>) rowContent.getValue();
        // for (Object rowItem : rowContentArray) {
        // System.out.println(rowItem);
        // }
        // }
        // }
        // ArrayList<HashMap<String, Object>> rowItems = (ArrayList<HashMap<String,
        // Object>>) entry.getValue();
        // pdfGenerator.generatePdf(rowItems);
        // }
        // }
        // HashMap<String, Object> formContent = content.getFormContent();
        // pdfGenerator.generatePdf(formContent);
    }
}
