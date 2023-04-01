package com.smartform.backend.smartformbackend.pdfgenerator;

import java.util.Iterator;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class PDFGeneratorLayer {

    // public void generatePdf(ArrayList<Map<String, Object>> inputList) {
    public byte[] generatePdf(JSONObject jsonObj, String finalApprovedDate, String finalApprover, int revNumber) {
        String filePath = "form.docx"; // Preset name
        JsonToWord json2word = new JsonToWord();
        json2word.createDocument(filePath);
        // json2word.writeLine("Testing this line!");
        // System.out.println(jsonObj);
        JSONArray formContent = jsonObj.getJSONArray("FormContent");
        JSONObject formInfo = jsonObj.getJSONObject("FormInfo");
        json2word.createFormInfo(formInfo);

        for (int i = 0; i < formContent.length(); i++) {
            JSONObject sectionInfo = formContent.getJSONObject(i);
            Iterator<String> keys = sectionInfo.keys();
            String key = keys.next();
            JSONArray sectionArray = sectionInfo.getJSONArray(key);
            for (int i2 = 0; i2 < sectionArray.length(); i2++) {
                try {
                    JSONObject questionObj = sectionArray.getJSONObject(i2);
                    // System.out.println(questionObj);
                    System.out.println(questionObj.get("type"));
                    // Currently missing header section, signature, radio group

                    if (questionObj.get("type").equals("header")) {
                        json2word.createHeader(questionObj);
                    } else if (questionObj.get("type").equals("text") ||
                            questionObj.get("type").equals("date") ||
                            questionObj.get("type").equals("number")) {
                        json2word.createTextInput(questionObj);
                        // Increment counters by 1
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("boolean")) {
                        json2word.createBoolean(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("checkbox")) {
                        json2word.createCheckboxGroup(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("radio")) {
                        json2word.createRadioGroup(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("likertGroup")) {
                        json2word.createLikertGroup(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("approval")) {
                        json2word.createApprovalTable();
                    } else if (questionObj.get("type").equals("acknowledgement")) {
                        json2word.createSubcontractorAcknowledgement();
                    }
                    System.out.println("Counter is now " + json2word.getCounter());
                } catch (Exception e) {
                    System.out.println("General Exception has occured when building form in Word.");
                    System.out.println(e);
                }
            }
        }
        // json2word.createSubcontractorAcknowledgement();
        // json2word.createApprovalTable();
        json2word.saveDocument(filePath);
        byte[] pdfByteArr = json2word.saveToPdf(filePath);
        return pdfByteArr;
        // System.out.println("I AM CHECING THE BYTES ");
        // System.out.println(pdfByteArr.length);
    }

    public byte[] generateWord(JSONObject jsonObj, String finalApprovedDate, String finalApprover, int revNumber) {
        String filePath = "form.docx"; // Preset name
        JsonToWord json2word = new JsonToWord();
        json2word.createDocument(filePath);
        // json2word.writeLine("Testing this line!");
        // System.out.println(jsonObj);
        JSONArray formContent = jsonObj.getJSONArray("FormContent");
        JSONObject formInfo = jsonObj.getJSONObject("FormInfo");
        json2word.createFormInfo(formInfo);

        for (int i = 0; i < formContent.length(); i++) {
            JSONObject sectionInfo = formContent.getJSONObject(i);
            Iterator<String> keys = sectionInfo.keys();
            String key = keys.next();
            JSONArray sectionArray = sectionInfo.getJSONArray(key);
            for (int i2 = 0; i2 < sectionArray.length(); i2++) {
                try {
                    JSONObject questionObj = sectionArray.getJSONObject(i2);
                    // System.out.println(questionObj);
                    System.out.println(questionObj.get("type"));
                    // Currently missing header section, signature, radio group

                    if (questionObj.get("type").equals("header")) {
                        json2word.createHeader(questionObj);
                    } else if (questionObj.get("type").equals("text") ||
                            questionObj.get("type").equals("date") ||
                            questionObj.get("type").equals("number")) {
                        json2word.createTextInput(questionObj);
                        // Increment counters by 1
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("boolean")) {
                        json2word.createBoolean(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("checkbox")) {
                        json2word.createCheckboxGroup(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("radio")) {
                        json2word.createRadioGroup(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("likertGroup")) {
                        json2word.createLikertGroup(questionObj);
                        json2word.setCounter(json2word.getCounter() + 1);
                    } else if (questionObj.get("type").equals("approval")) {
                        json2word.createApprovalTable();
                    } else if (questionObj.get("type").equals("acknowledgement")) {
                        json2word.createSubcontractorAcknowledgement();
                    }
                    System.out.println("Counter is now " + json2word.getCounter());
                } catch (Exception e) {
                    System.out.println("General Exception has occured when building form in Word.");
                    System.out.println(e);
                }
            }
        }
        // json2word.createSubcontractorAcknowledgement();
        // json2word.createApprovalTable();
        byte[] byteArray = json2word.saveDocument(filePath);

        return byteArray;
        // System.out.println("I AM CHECING THE BYTES ");
        // System.out.println(pdfByteArr.length);
    }

}