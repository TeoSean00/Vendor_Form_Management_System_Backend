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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.json.JSONArray;
import org.json.JSONObject;

//\u2002 is the white space character
public class JsonToWord {
    private XWPFDocument doc;
    private FileOutputStream fos;
    private int Counter = 1;
    private Date finalApprovedDate; 
    private String finalApprover;
    private int revNumber;
    private String vendorName;

    public int getCounter() {
        return Counter;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }

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

        // // Create a new ArrayLcreatedist to hold the values for the second row of the table
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
            System.out.println("date is " + this.finalApprovedDate + this.finalApprover + this.revNumber + this.vendorName);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public byte[] saveDocument(String filePath) {
        try {
            doc.write(fos);
            doc.close();
            System.out.println("Document saved successfully!");
            Path wordPath = Paths.get(filePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] data = Files.readAllBytes(wordPath);
            return data;
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return null;
    }
    

    public byte[] saveToPdf(String filePath) {
        try {
            InputStream templateInputStream = new FileInputStream(filePath);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            // Replace this name later
            String outputfilepath = "test.pdf";
            FileOutputStream os = new FileOutputStream(outputfilepath);
            Docx4J.toPDF(wordMLPackage, os);
            os.flush();
            os.close();
            Path pdfPath = Paths.get("test.pdf");
            byte[] data = Files.readAllBytes(pdfPath);

            System.out.println("I AM CHECING THE BYTES ");
            System.out.println(data.length);
            return data;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public void receiveAdditionalInfo(Date finalApprovedDate, String finalApprover, int revNumber, String vendorName){
        this.finalApprovedDate = finalApprovedDate;
        this.finalApprover = finalApprover;
        this.revNumber = revNumber;
        this.vendorName = vendorName;
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
        table.setTableAlignment(TableRowAlign.CENTER);
        // para1.setAlignment(ParagraphAlignment.CENTER);
        table.getRow(0).getCell(0).setText("Form Title");
        table.getRow(0).getCell(1).setText("Form Code");
        table.getRow(0).getCell(2).setText("Vendor");
        table.getRow(1).getCell(0).setText(input.get("formName").toString());
        table.getRow(1).getCell(1).setText(input.get("formCode").toString());
        table.getRow(1).getCell(2).setText(this.vendorName);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        String strDate = dateFormat.format(this.finalApprovedDate);  
        table.getRow(2).getCell(0).setText("Revision: " + Integer.toString(this.revNumber));
        table.getRow(2).getCell(1).setText("Approver: " +this.finalApprover );
        table.getRow(2).getCell(2).setText("Date Approved: " + strDate);
       

        //Creating header

        XWPFHeaderFooterPolicy headerPolicy = doc.getHeaderFooterPolicy();
        if (headerPolicy == null) {
            headerPolicy = doc.createHeaderFooterPolicy();
        }
        XWPFHeader header = headerPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
        // Set the center header
        XWPFParagraph paragraph1 = header.getParagraphArray(0);
        if (paragraph1 == null) {
            paragraph1 = header.createParagraph();
        }
        paragraph1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run1 = paragraph1.createRun();
        run1.setFontSize(10);
        run1.setText(input.get("formName").toString()
                + "          QUANTUM LEAP INCORPORATION       " + this.vendorName);

        XWPFParagraph paragraph2 = doc.createParagraph();
        // Create a footer
        XWPFHeaderFooterPolicy footerPolicy = doc.getHeaderFooterPolicy();
        if (footerPolicy == null) {
            footerPolicy = doc.createHeaderFooterPolicy();
        }
        XWPFFooter footer = footerPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
        // Set the center footer
        paragraph2 = footer.getParagraphArray(0);
        if (paragraph2 == null) {
            paragraph2 = footer.createParagraph();
        }
        paragraph2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run2 = paragraph2.createRun();
        run2.setFontSize(10);
        run2.setText(input.get("formName").toString()
                + "         QUANTUM LEAP INCORPORATION       " + this.vendorName);
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
        XWPFParagraph para = doc.createParagraph();
        XWPFRun question = para.createRun();
        // question.setText(input.get("order") + ".");
        // question.addTab();
        question.setText(Counter + ".");
        question.addTab();
        question.setText((String) input.get("label"));
        question.addTab();
        XWPFRun answer = para.createRun();
        answer.setUnderline(UnderlinePatterns.SINGLE);
        // We need this for them the form is populated
        if (input.get("type").equals("number")) {
            try {
                answer.setText(Integer.toString((int) input.get("input")));
            } catch (Exception e ){
                answer.setText("unfilled");
            }
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
        XWPFParagraph para = doc.createParagraph();
        XWPFRun header = para.createRun();
        header.setBold(true);
        header.setFontSize(14);
        // header.setText(input.get("order") + ".");
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
        String selected = input.getString("input");
        XWPFParagraph para = doc.createParagraph();
        XWPFRun booleanText = para.createRun();
        booleanText.setText(Counter + ".");
        // booleanText.setText(input.get("order") + ".");
        booleanText.addTab();
        booleanText.setText((String) input.get("label"));
        booleanText.addTab();
        XWPFRun answer = para.createRun();
        if (selected .equals("Yes")) {
            answer.setText("[X] Yes | [ ] No");
        } else {
            answer.setText("[ ] Yes | [X] No");

        }
        // drawBoolOption();
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
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak();
        run.setText(Counter +". ");
        run.addTab();
        run.setText((String) input.get("label"));
        // Getting checked indexes and putting them into a map to iterate easier
        // boolean shortAnswer = input.getBoolean("shortAnswer");
        JSONArray options = (JSONArray) input.get("options");
        int checked = input.getInt("input");
        for (int i = 0; i < options.length(); i++) {
            XWPFParagraph para = doc.createParagraph();
            XWPFRun optionRun = para.createRun();
            optionRun.addTab();
            optionRun.addTab();
            if (checked == i){
                optionRun.setText("[X] " + options.get(i));
            } else {
                optionRun.setText("[ ] " + options.get(i));
            }
        }
    }
    /**
     * Takes in a Java object containing key-value pairs related to the type of
     * input to be generated.
     * Currently, the input is hardcoded. Input has to be coded such that the key
     * order can be accessed via
     * .get(key) methods.
     * Creates a radio group
     */
    public void createCheckboxGroup(JSONObject input) {
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak();
        run.setText(Counter +". ");
        run.addTab();
        run.setText((String) input.get("label"));
        // Getting checked indexes and putting them into a map to iterate easier
        JSONArray checked = input.getJSONArray("input");
        HashMap<Integer, Integer> answerMap = new HashMap<>();
        for (int i=0;i<checked.length();i++){
            answerMap.put(checked.getInt(i),checked.getInt(i));
        }
        System.out.println(answerMap);

        boolean shortAnswer = input.getBoolean("shortAnswer");
        JSONArray options = (JSONArray) input.get("options");
        for (int i = 0; i < options.length(); i++) {
            XWPFParagraph para = doc.createParagraph();
            XWPFRun optionRun = para.createRun();
            optionRun.addTab();
            optionRun.addTab();
            if (answerMap.containsKey(i)){
                optionRun.setText("[X] " + options.get(i));
            } else {
                optionRun.setText("[ ] " + options.get(i));
            }
            if (shortAnswer){
                // Means need to append whatever short answer it is at the back 
                String shortAnswerString = input.getJSONArray("shortAnswerArr").getString(i);
                XWPFRun answerRun = para.createRun();
                if (shortAnswerString.length() != 0) {
                    answerRun.setText(": " + shortAnswerString);
                } else {
                    answerRun.setText(": ___________________________");
                }
            }
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
        JSONArray options = (JSONArray) input.get("options");
        int noOfOptions = options.length();
        XWPFRun run = doc.createParagraph().createRun();
        run.setText(Counter + ". ");
        run.addTab();
        run.setText((String) input.get("label"));
        XWPFTable table = doc.createTable(noOfOptions + 3, 6);
        // Setting width
        table.setWidth("100%");
        table.setTableAlignment(TableRowAlign.CENTER);

        // Set header
        table.getRow(0).getCell(0).setText("Grade");
        table.getRow(1).getCell(1).setText("1 (Poor)");
        table.getRow(1).getCell(2).setText("2 (Below Average)");
        table.getRow(1).getCell(3).setText("3 (Average)");
        table.getRow(1).getCell(4).setText("4 (Above Average)");
        table.getRow(1).getCell(5).setText("5 (Good)");

        table.getRow(0).getCell(0).setWidth("auto");
        table.getRow(1).getCell(1).setWidth("10%");
        table.getRow(1).getCell(2).setWidth("10%");
        table.getRow(1).getCell(3).setWidth("10%");
        table.getRow(1).getCell(4).setWidth("10%");
        table.getRow(1).getCell(5).setWidth("10%");

        // Center align all cells except first column
        for (XWPFTableRow row : table.getRows()) {
            for (int i = 1; i < row.getTableCells().size(); i++) {
                XWPFTableCell cell = row.getCell(i);
                // Set horizontal alignment
                cell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                // Set vertical alignment
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            }
        }
        // Filling in options
        for (int i = 0; i < options.length(); i++) {
            table.getRow(2 + i).getCell(0).setText(options.getString(i));
        }

        //Filling in checked options and getting score.
        int likertScore = 0;
        JSONArray scoreList = input.getJSONArray("input");
        for (int i=0;i<scoreList.length();i++){
            likertScore += scoreList.getInt(i);
            table.getRow(2+i).getCell(2+i).addParagraph();
            table.getRow(2+i).getCell(2+i).setText("X");
        }
        table.getRow(noOfOptions + 2).getCell(0).setText("SCORE= ");
        table.getRow(noOfOptions + 2).getCell(1).setText(Integer.toString(likertScore));
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
        XWPFTable table = doc.createTable(4,4);
        table.setWidth("100%");
        table.setTableAlignment(TableRowAlign.CENTER);

        XWPFRun para1 = table.getRow(0).getCell(0).addParagraph().createRun();
        table.getRow(0).getCell(0).setWidth("25%");
        para1.setBold(true);
        para1.setText("Approved");

        table.getRow(0).getCell(1).setWidth("25%");
        table.getRow(0).getCell(3).setWidth("25%");

        
        XWPFRun para2 = table.getRow(0).getCell(2).addParagraph().createRun();
        table.getRow(0).getCell(2).setWidth("25%");
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

    private void drawBoolOption() {
        XWPFTable table = doc.createTable();
        table.setTableAlignment(TableRowAlign.RIGHT);
        XWPFTableRow tableRow = table.getRow(0);
        tableRow.getCell(0).setWidth("auto");
        tableRow.getCell(0).setText(" YES / NO * ");
    }

    private void drawTable(ArrayList<ArrayList<String>> tableInfo) {
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
