package com.smartform.backend.smartformbackend.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<VendorForm> getAllForms() {
        return vendorFormDAO.findAll();
    }

    // get all the updates
    @GetMapping("/updates/{id}")
    public List<VendorForm> getFormUpdates(@PathVariable String id) {
        var returnList = new ArrayList<VendorForm>();
        var vendorFormList = vendorFormDAO.findAllVendorForms(id);
        LocalDate currentDate = LocalDate.now();
        for (VendorForm form : vendorFormList) {
            LocalDate createDate = form.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (createDate.equals(currentDate)) {
                returnList.add(form);
            } else if (form.getLatestCompletedDate() != null) {
                LocalDate lastCompleted = form.getLatestCompletedDate().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                if (lastCompleted.equals(currentDate)) {
                    returnList.add(form);
                } else {
                    if (form.getLatestRejectionDate() != null) {
                        LocalDate lastRejDate = form.getLatestRejectionDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        if (lastRejDate.equals(currentDate)) {
                            returnList.add(form);
                        }
                    }
                }
            }
        }
        return returnList;
    }

    // get all the forms for a specific vendor
    @GetMapping("/vendor/{id}")
    public List<VendorForm> getVendorForms(@PathVariable String id) {
        return vendorFormDAO.findAllVendorForms(id);
    }

    // need to tell spring to send the id to the method
    @RequestMapping("/{id}")
    public VendorForm getForm(@PathVariable String id) {
        // call a method from the business service of the topic that has the id
        return vendorFormDAO.getVendorForm(id);
    }

    // map this method to any request that is a POST at /topics
    @RequestMapping(method = RequestMethod.POST, value = "/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
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
    public void updateForm(@RequestBody VendorForm form, @PathVariable String id) {
        vendorFormDAO.updateVendorForm(id, form);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void deleteForm(@PathVariable String id) {
        vendorFormDAO.deleteWorkflow(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @RequestMapping("generateForm/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable String id) {
        PDFGeneratorLayer pdfGenerator = new PDFGeneratorLayer();
        VendorForm form = vendorFormDAO.getVendorForm(id);
        String json;
        byte[] bytes;
        try {
            json = new ObjectMapper().writeValueAsString(form.getContent());
            JSONObject jsonObj = new JSONObject(json);
            bytes = pdfGenerator.generatePdf(jsonObj, form.getFinalApprovedDate(), form.getFinalApprover(),
                    form.getRevNumber());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(bytes.length);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            System.out.println("I AM CHECING THE BYTES ");
            System.out.println(bytes.length);
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @RequestMapping("generateWordDocument/{id}")
    public ResponseEntity<byte[]> generateWord(@PathVariable String id) {
        PDFGeneratorLayer pdfGenerator = new PDFGeneratorLayer();
        VendorForm form = vendorFormDAO.getVendorForm(id);
        String json;
        byte[] bytes;
        try {
            json = new ObjectMapper().writeValueAsString(form.getContent());
            JSONObject jsonObj = new JSONObject(json);
            bytes = pdfGenerator.generateWord(jsonObj, form.getFinalApprovedDate(), form.getFinalApprover(),
                    form.getRevNumber());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setContentLength(bytes.length);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            System.out.println("I AM CHECING THE BYTES ");
            System.out.println(bytes.length);
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
