package com.smartform.backend.smartformbackend.pdfgenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class PDFGeneratorLayer {
    public void generatePdf(ArrayList<HashMap<String, Object>> inputList) {
        String filePath = "generated/form.docx"; // Preset name
        JsonToWord json2word = new JsonToWord();
        json2word.createDocument(filePath);
        // Expect to take in a big json object
        for (HashMap<String, Object> input : inputList) {
            // Checks object type
            // Currently missing header section, signature, radio group
            if (input.get("type") == "header") {
                json2word.createHeader(input);
            } else if (input.get("type") == "text" ||
                    input.get("type") == "date" ||
                    input.get("type") == "number") {
                json2word.createTextInput(input);
            } else if (input.get("type") == "boolean") {
                json2word.createBoolean(input);
            } else if (input.get("type") == "checkbox") {
                json2word.createRadioGroup(input);
            } else if (input.get("type") == "likertGroup") {
                json2word.createLikertGroup(input);
            }
        }
        json2word.saveDocument();
        json2word.saveToPdf(filePath);
    }

}