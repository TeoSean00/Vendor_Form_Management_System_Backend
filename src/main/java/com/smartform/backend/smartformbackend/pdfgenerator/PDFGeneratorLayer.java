package com.smartform.backend.smartformbackend.pdfgenerator;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class PDFGeneratorLayer {
    
    // public void generatePdf(ArrayList<Map<String, Object>> inputList) {
    public void generatePdf(JSONObject jsonObj) {
        String filePath = "form.docx"; // Preset name
        JsonToWord json2word = new JsonToWord();
        json2word.createDocument(filePath);
        json2word.writeLine("Testing this line!");
        // System.out.println(jsonObj);
        JSONArray formContent = jsonObj.getJSONArray("FormContent");
        JSONObject formInfo = jsonObj.getJSONObject("FormInfo");
        for (int i=0; i < formContent.length(); i++){
            JSONObject sectionInfo = formContent.getJSONObject(i);
            Iterator<String> keys = sectionInfo.keys();
            String key = keys.next();
            JSONArray sectionArray = sectionInfo.getJSONArray(key);
            for (int i2=0; i2 <sectionArray.length(); i2++){
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
                } else if (questionObj.get("type").equals("boolean")) {
                    json2word.createBoolean(questionObj);
                } else if (questionObj.get("type").equals("checkbox")) {
                    json2word.createRadioGroup(questionObj);
                } else if (questionObj.get("type").equals("likertGroup")) {
                    json2word.createLikertGroup(questionObj);
                }

            }

        }
        
        //Convert JSON String to JSon
        // ObjectMapper mapper = new ObjectMapper();
        // Map<String, Object> map;
        // try {
        //     map = mapper.readValue(inputJsonString, Map.class);
        //     // System.out.println(map.get("FormContent"));
        //     // System.out.println(map.get("FormContent").getClass()); //This has a Vendor and Admin
            
        //     for (Map.Entry<String,Object> entry: map.entrySet()){
        //         // System.out.println(entry.getValue());
        //         // System.out.println(entry.getValue().getClass());
        //         LinkedHashMap<String,Object> sectionContentList = (LinkedHashMap) entry.getValue();
                
        //         for (Map.Entry<String,Object> entryContent: sectionContentList.entrySet()){
        //             System.out.println(entryContent.getValue());
        //             System.out.println(entryContent.getValue().getClass());
        //         }
 
        //     }

            
        // } catch (JsonMappingException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (JsonProcessingException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        //Access Form Info map.get("FormInfo")

        // System.out.println("CONTENT RECEIVED IS"+ inputList);
        // Expect to take in a big json object
        // for (Map<String, Object> section : inputList) {
        //         // System.out.println(input.keySet()); //This gives you vendor, admin which is kinda weird
        //         for (Map.Entry<String,Object> input:section.entrySet()){
        //             JSONArray jsonArray = new JSONArray(input.getValue());
        //             for (int i = 0; i < jsonArray.length(); i++) {
        //                 JSONObject jsonObject = jsonArray.getJSONObject(i);
        //                 System.out.println(jsonObject);
        //                 // perform the necessary operations with the values obtained
        //             }
                 
                    // System.out.println(input.getKey() + "/" + input.getValue());
                    // System.out.println(input.getValue().getClass());
                    // System.out.println("This input is " + input);
                    // Checks object type
                    // Currently missing header section, signature, radio group
                    // if (input.get("type") == "header") {
                    //     json2word.createHeader(input);
                    // } else if (input.get("type") == "text" ||
                    //         input.get("type") == "date" ||
                    //         input.get("type") == "number") {
                    //     json2word.createTextInput(input);
                    // } else if (input.get("type") == "boolean") {
                    //     json2word.createBoolean(input);
                    // } else if (input.get("type") == "checkbox") {
                    //     json2word.createRadioGroup(input);
                    // } else if (input.get("type") == "likertGroup") {
                    //     json2word.createLikertGroup(input);
                    // }

        //         }
            
        // }
        json2word.saveDocument();
        json2word.saveToPdf(filePath);
    }

}