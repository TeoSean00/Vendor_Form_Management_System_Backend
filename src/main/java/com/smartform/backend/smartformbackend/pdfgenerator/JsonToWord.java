package com.smartform.backend.smartformbackend.pdfgenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

//\u2002 is the white space character
public class JsonToWord {
    XWPFDocument doc;
    FileOutputStream fos;

    public JsonToWord() {

    }

    public static void main(String[] args) {
        // Leaving this in for reference, right now this is done inside
        // PDFGeneratorLayer.java
        // JsonToWord json2word = new JsonToWord();
        // String filePath = "generated/test2.docx";
        // json2word.createDocument(filePath);
        // json2word.writeLine("Testing this line!");
        // json2word.createHeader(Object input);
        // json2word.createTextInput();
        // json2word.createBoolean();
        // json2word.createLikertGroup();
        // json2word.createRadioGroup();

        // // Create a new ArrayList to hold the values for the first row of the table
        // ArrayList<String> row1 = new ArrayList<String>();
        // row1.add("1");
        // row1.add("John");
        // row1.add("Doe");

        // // Create a new ArrayList to hold the values for the second row of the table
        // ArrayList<String> row2 = new ArrayList<String>();
        // row2.add("2");
        // row2.add("Jane");
        // row2.add("Smith");

        // ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        // table.add(row1);
        // table.add(row2);
        // // json2word.drawTable(table);
        // json2word.saveDocument();
    }

    public void createDocument(String filePath) {
        // File path has to have .docx
        // Para seems to be a forced newline
        try {
            doc = new XWPFDocument();
            fos = new FileOutputStream(new File(filePath));
            System.out.println("Created successfully");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void saveDocument() {
        try {
            doc.write(fos);
            doc.close();
            System.out.println("Document saved successfully!");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public byte[] saveToPdf(String filePath) {
        try {
            InputStream templateInputStream = new FileInputStream(filePath);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
            // Replace this name later
            String outputfilepath = "test.pdf";
            FileOutputStream os = new FileOutputStream(outputfilepath);
            Docx4J.toPDF(wordMLPackage, os);
            os.flush();
            os.close();

            Path pdfPath = Paths.get("test.pdf");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] data = Files.readAllBytes(pdfPath);

            System.out.println("I AM CHECING THE BYTES ");
            System.out.println(data.length);
            return data;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeLine(String line) {
        // Creates a paragraph
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();
        run.setText(line);
    }

    public void createFormInfo(JSONObject input) {
        // Create a new header and set the text
        // XWPFHeader header = doc.createHeader(HeaderFooterType.DEFAULT);
        XWPFTable table = doc.createTable(3, 3);

        XWPFParagraph para1 = table.getRow(0).getCell(0).addParagraph();
        para1.setAlignment(ParagraphAlignment.CENTER);
        para1.createRun().setText("QUANTUM LEAP INCORPORATION PTE LTD");
        table.getRow(0).getCell(0).setVerticalAlignment(XWPFVertAlign.TOP);
        XWPFParagraph para2 = table.getRow(1).getCell(0).addParagraph();
        para2.createRun().setText(input.get("formName").toString());
        para2.setAlignment(ParagraphAlignment.CENTER);
        table.getRow(1).getCell(0).setVerticalAlignment(XWPFVertAlign.TOP);

        // Getting today date
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        table.getRow(2).getCell(0).setText("FORM ID");
        table.getRow(2).getCell(0).setWidth("5000");
        ;
        table.getRow(2).getCell(0).setVerticalAlignment(XWPFVertAlign.TOP);
        table.getRow(2).getCell(1).setText("Date: " + date);
        table.getRow(2).getCell(1).setWidth("5000");
        ;
        table.getRow(2).getCell(2).setText("Revision: " + "v1.0.1");
        table.getRow(2).getCell(2).setWidth("5000");
        ;

        // Merging headers
        CTHMerge hMerge = CTHMerge.Factory.newInstance();
        CTHMerge hMerge2 = CTHMerge.Factory.newInstance();
        for (int i = 0; i < 3; i++) {
            // Merge cells in row 1
            if (i == 0) {
                hMerge.setVal(STMerge.RESTART);
                hMerge2.setVal(STMerge.RESTART);
            } else {
                hMerge.setVal(STMerge.CONTINUE);
                hMerge2.setVal(STMerge.CONTINUE);
            }
            table.getRow(0).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge);
            table.getRow(1).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge2);
        }

        // Creating footers
        XWPFParagraph paragraph = doc.createParagraph();
        // Create a footer
        XWPFHeaderFooterPolicy footerPolicy = doc.getHeaderFooterPolicy();
        if (footerPolicy == null) {
            footerPolicy = doc.createHeaderFooterPolicy();
        }
        XWPFFooter footer = footerPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
        // Set the center footer
        paragraph = footer.getParagraphArray(0);
        if (paragraph == null) {
            paragraph = footer.createParagraph();
        }
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(input.get("formName").toString()
                + "                       QUANTUM LEAP INCORPORATION                       " + "Version Number");
    }

    /**
     * Takes in a Java object containing key-value pairs related to the type of
     * input to be generated.
     * Currently, the input is hardcoded. Input has to be coded such that the key
     * order can be accessed via
     * .get(key) methods.
     * Create text input can take in and create text, date and numeric inputs.
     */
    public void createTextInput(JSONObject input) {
        // HashMap<String, Object> textInput = new HashMap<String, Object>() {{
        // put("order", 2);
        // put("label", "Please enter your name:");
        // put("input", "Desmond");
        // put("type", "text");
        // }};
        XWPFParagraph para = doc.createParagraph();
        XWPFRun question = para.createRun();
        question.setText(input.get("order") + ".");
        question.addTab();
        question.setText((String) input.get("label"));
        question.addTab();
        XWPFRun answer = para.createRun();
        answer.setUnderline(UnderlinePatterns.SINGLE);
        // We need this for them the form is populated
        if (input.get("type").equals("number")) {
            answer.setText(Integer.toString((int) input.get("input")));
        } else {
            answer.setText((String) input.get("input"));
        }
        // answer.setText((String) input.get("input"));
    }

    /**
     * Takes in a Java object containing key-value pairs related to the type of
     * input to be generated.
     * Currently, the input is hardcoded. Input has to be coded such that the key
     * order can be accessed via
     * .get(key) methods.
     * Creates bold and different size headers based on style.
     */
    public void createHeader(JSONObject input) {
        // HashMap<String, Object> headerInput = new HashMap<String, Object>() {{
        // put("order", 1);
        // put("label", "Header Name Here");
        // put("style", "h1");
        // put("type", "header");
        // }};
        XWPFParagraph para = doc.createParagraph();
        XWPFRun header = para.createRun();
        header.setBold(true);
        header.setFontSize(14);
        header.setText(input.get("order") + ".");
        header.setText((String) input.get("label"));

    }

    /**
     * Takes in a Java object containing key-value pairs related to the type of
     * input to be generated.
     * Currently, the input is hardcoded. Input has to be coded such that the key
     * order can be accessed via
     * .get(key) methods.
     * Creates a boolean question with YES / NO options at the right.
     */
    public void createBoolean(JSONObject input) {
        // HashMap<String, Object> booleanInput = new HashMap<String, Object>() {{
        // List<String> options = new ArrayList<>();
        // options.add("Yes");
        // options.add("No");
        // put("order", 1);
        // put("label", "Boolean Question");
        // put("options", options);
        // put("type", "radio");
        // }};
        XWPFParagraph para = doc.createParagraph();
        XWPFRun booleanText = para.createRun();
        booleanText.setText(input.get("order") + ".");
        booleanText.addTab();
        booleanText.setText((String) input.get("label"));
        drawBoolOption();
    }

    /**
     * Takes in a Java object containing key-value pairs related to the type of
     * input to be generated.
     * Currently, the input is hardcoded. Input has to be coded such that the key
     * order can be accessed via
     * .get(key) methods.
     * Creates a radio group
     */
    public void createRadioGroup(JSONObject input) {
        // HashMap<String, Object> radioInput = new HashMap<String, Object>() {{
        // List<String> options = new ArrayList<>();
        // options.add("a. Sole proprietorship");
        // options.add("b. Limited Company");
        // options.add("d. Others");
        // options.add("c. Partnership Agreement");
        // put("order", 1);
        // put("label", "Type of Business");
        // put("options", options);
        // put("type", "radioGroup");
        // }};

        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak();
        run.setText((String) input.get("label"));
        JSONArray options = (JSONArray) input.get("options");
        for (int i = 0; i < options.length(); i++) {
            XWPFRun optionRun = doc.createParagraph().createRun();
            optionRun.addTab();
            optionRun.setText("[ ] " + options.get(i));
        }
    }

    /**
     * Takes in a Java object containing key-value pairs related to the type of
     * input to be generated.
     * Currently, the input is hardcoded. Input has to be coded such that the key
     * order can be accessed via
     * .get(key) methods.
     * Creates a likert group from 1 to 5.
     */
    public void createLikertGroup(JSONObject input) {
        // HashMap<String, Object> likertInput = new HashMap<String, Object>() {{
        // List<String> options = new ArrayList<>();
        // options.add("Attendance in Safety Meeting");
        // options.add("Tool Box Meeting");
        // put("order", 1);
        // put("label", "Part 1: Participation and Safety");
        // put("options", options);
        // put("type", "likertGroup");
        // }};
        // Tables are always 6 columns, number of options + 3
        JSONArray options = (JSONArray) input.get("options");
        int noOfOptions = options.length();
        doc.createParagraph();
        XWPFTable table = doc.createTable(noOfOptions + 4, 6);
        // Set header
        table.getRow(0).getCell(0).setText((String) input.get("label"));
        table.getRow(1).getCell(1).setText("Grade");
        table.getRow(2).getCell(1).setText("1 (Poor)");
        table.getRow(2).getCell(2).setText("2 (Below Average)");
        table.getRow(2).getCell(3).setText("3 (Average)");
        table.getRow(2).getCell(4).setText("4 (Above Average)");
        table.getRow(2).getCell(5).setText("5 (Good)");
        // Set score at bottom
        table.getRow(noOfOptions + 3).getCell(1).setText("SCORE = ");
        // Merging headers
        CTHMerge hMerge = CTHMerge.Factory.newInstance();
        CTHMerge hMerge2 = CTHMerge.Factory.newInstance();
        CTHMerge hMerge3 = CTHMerge.Factory.newInstance();
        for (int i = 0; i < 6; i++) {
            // Merge cells in row 1
            if (i == 0) {
                hMerge.setVal(STMerge.RESTART);
            } else if (i == 1) {
                hMerge2.setVal(STMerge.RESTART);
                hMerge3.setVal(STMerge.RESTART);
                hMerge.setVal(STMerge.CONTINUE);
            } else {
                hMerge2.setVal(STMerge.CONTINUE);
                hMerge3.setVal(STMerge.CONTINUE);
            }
            table.getRow(0).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge);
            table.getRow(1).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge2);
            table.getRow(noOfOptions + 3).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge2);
        }
        // Filling in options
        for (int i = 0; i < options.length(); i++) {
            table.getRow(3 + i).getCell(0).setText(options.getString(i));
        }
    }

    public void createSubcontractorAcknowledgement() {
        doc.createParagraph().createRun().addBreak(); // Line Break
        XWPFTable table = doc.createTable();
        // Creates "Acknowledgement"
        XWPFParagraph para = table.getRow(0).getCell(0).addParagraph();
        XWPFRun header = para.createRun();
        header.setBold(true);
        header.setText("ACKNOWLEDGEMENT");
        // header.setFontSize(14);
        para.setAlignment(ParagraphAlignment.CENTER);
        // Block of text
        table.createRow();
        XWPFParagraph blockText = table.getRow(1).getCell(0).addParagraph();
        XWPFRun blockTextRun = blockText.createRun();
        blockTextRun.setText(
                "I, representative of the above-named sub-contractor, have understand the various Safety Criteria listed above and hereby acknowledged that the information given above are valid and supporting items/documents are available upon request by the main contractor.");
        // Signature
        table.createRow();
        XWPFParagraph signaturePara = table.getRow(2).getCell(0).addParagraph();
        XWPFRun signatureRun = signaturePara.createRun();
        signatureRun.setText("Date: _____________");
        signatureRun.addBreak();
        signatureRun.setText(
                "Acknowledged by: _________________________________                    Signature: _____________");

        // table.getRow(2).getCell(0)
    }

    public void createApprovalTable(){
        // Creating header
        XWPFParagraph para = doc.createParagraph();
        XWPFRun header = para.createRun();
        header.setBold(true);
        header.setFontSize(14);
        header.setText("RESULT OF EVALUATION:");

        // Creating table
        XWPFTable table = doc.createTable();
        XWPFRun para1 = table.getRow(0).getCell(0).addParagraph().createRun();
        para1.setBold(true);
        para1.setText("Approved");
        
        XWPFRun para2 = table.getRow(0).getCell(2).addParagraph().createRun();
        para2.setBold(true);
        para2.setText("Not Approved");

        XWPFRun para3 = table.getRow(1).getCell(0).addParagraph().createRun();
        para3.setText("Evaluated by: "); 
        XWPFRun para4 = table.getRow(1).getCell(2).addParagraph().createRun();
        para4.setText("Signature: "); 
        
        XWPFRun para5 = table.getRow(2).getCell(0).addParagraph().createRun();
        para5.setText("Approved by Director: "); 
        XWPFRun para6 = table.getRow(2).getCell(2).addParagraph().createRun();
        para6.setText("Signature: "); 
        
        XWPFRun para7 = table.getRow(3).getCell(0).addParagraph().createRun();
        para7.setText("Effective Date: "); 


    }

    public void drawBoolOption() {
        XWPFTable table = doc.createTable();
        table.setTableAlignment(TableRowAlign.RIGHT);
        XWPFTableRow tableRow = table.getRow(0);
        tableRow.getCell(0).setWidth("auto");
        tableRow.getCell(0).setText(" YES / NO * ");
    }

    public void drawTable(ArrayList<ArrayList<String>> tableInfo) {
        XWPFTable table = doc.createTable();
        int rowCounter = 0;
        int cellCounter = 0;
        int numCells = tableInfo.get(0).size();
        int numRows = tableInfo.size();
        for (ArrayList<String> rowInfo : tableInfo) {
            XWPFTableRow tableRow = table.getRow(rowCounter);
            if (rowCounter == 0) {
                // Initialise number of cells / columns
                for (int i = 0; i < numCells - 1; i++) {
                    tableRow.addNewTableCell();
                }
            }
            for (String cellInfo : rowInfo) {
                tableRow.getCell(cellCounter).setText(cellInfo);
                cellCounter += 1;
            }
            cellCounter = 0;
            rowCounter += 1;
            if (rowCounter < numRows) {
                table.createRow();
            }
        }
    }

}
