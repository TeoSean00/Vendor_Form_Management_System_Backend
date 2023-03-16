package com.smartform.backend.smartformbackend.pdfgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

//\u2002 is the white space character
public class JsonToWord {
    XWPFDocument doc;
    FileOutputStream fos;

    public JsonToWord(){

    }
    public static void main(String[] args) {
        //Leaving this in for reference, right now this is done inside PDFGeneratorLayer.java
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

    public void createDocument(String filePath){
        //File path has to have .docx
        //Para seems to be a forced newline
        try {
            doc = new XWPFDocument();
            fos = new FileOutputStream(new File(filePath));
            System.out.println("Created successfully");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void saveDocument(){
        try{
            doc.write(fos);
            doc.close();
            System.out.println("Document saved successfully!");
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }

    public void saveToPdf(String filePath){
        try {
            InputStream templateInputStream = new FileInputStream(filePath);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
            //Replace this name later
            String outputfilepath = "generated/test.pdf";
            FileOutputStream os = new FileOutputStream(outputfilepath);
            Docx4J.toPDF(wordMLPackage,os);
            os.flush();
            os.close();
        } catch (Throwable e) {
            e.printStackTrace();
        } 
    }

    public void writeLine(String line){
        //Creates a paragraph
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();
        run.setText(line);
    }
    
    /**
    * Takes in a Java object containing key-value pairs related to the type of input to be generated.
    * Currently, the input is hardcoded. Input has to be coded such that the key order can be accessed via
    * .get(key) methods.
    * Create text input can take in and create text, date and numeric inputs.
    */
    public void createTextInput(HashMap<String,Object> input){
        // HashMap<String, Object> textInput = new HashMap<String, Object>() {{
        //     put("order", 2);
        //     put("label", "Please enter your name:");
        //     put("input", "Desmond");
        //     put("type", "text");
        // }};
        XWPFParagraph para = doc.createParagraph();
        XWPFRun question = para.createRun();
        question.setText(input.get("order") + ".");
        question.addTab();
        question.setText((String) input.get("label"));
        question.addTab();
        XWPFRun answer = para.createRun();
        answer.setUnderline(UnderlinePatterns.SINGLE);
        answer.setText((String) input.get("input"));
    }
    
    /**
    * Takes in a Java object containing key-value pairs related to the type of input to be generated.
    * Currently, the input is hardcoded. Input has to be coded such that the key order can be accessed via
    * .get(key) methods.
    * Creates bold and different size headers based on style.
    */
    public void createHeader(HashMap<String,Object> input){
        // HashMap<String, Object> headerInput = new HashMap<String, Object>() {{
        //     put("order", 1);
        //     put("label", "Header Name Here");
        //     put("style", "h1");
        //     put("type", "header");
        // }};
        XWPFParagraph para = doc.createParagraph();
        XWPFRun header = para.createRun();
        header.setBold(true);
        header.setFontSize(14);
        header.setText(input.get("order") + ".");
        header.setText((String) input.get("label"));

    }

    /**
    * Takes in a Java object containing key-value pairs related to the type of input to be generated.
    * Currently, the input is hardcoded. Input has to be coded such that the key order can be accessed via
    * .get(key) methods.
    * Creates a boolean question with YES / NO options at the right.
    */
    public void createBoolean(HashMap<String,Object> input){
        // HashMap<String, Object> booleanInput = new HashMap<String, Object>() {{
        //     List<String> options = new ArrayList<>();
        //     options.add("Yes");
        //     options.add("No");
        //     put("order", 1);
        //     put("label", "Boolean Question");
        //     put("options", options);
        //     put("type", "radio");
        // }};
        XWPFParagraph para = doc.createParagraph();
        XWPFRun booleanText = para.createRun();
        booleanText.setText(input.get("order") + ".");
        booleanText.addTab();
        booleanText.setText((String) input.get("label"));
        drawBoolOption();
    }

    /**
    * Takes in a Java object containing key-value pairs related to the type of input to be generated.
    * Currently, the input is hardcoded. Input has to be coded such that the key order can be accessed via
    * .get(key) methods.
    * Creates a radio group 
    */
    public void createRadioGroup(HashMap<String,Object> input){
        // HashMap<String, Object> radioInput = new HashMap<String, Object>() {{
        //     List<String> options = new ArrayList<>();
        //     options.add("a. Sole proprietorship");
        //     options.add("b. Limited Company");
        //     options.add("d. Others");
        //     options.add("c. Partnership Agreement");
        //     put("order", 1);
        //     put("label", "Type of Business");
        //     put("options", options);
        //     put("type", "radioGroup");
        // }};

        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak();
        run.setText((String) input.get("label"));
        ArrayList<String> options = (ArrayList<String>) input.get("options");
        for (int i=0;i<options.size();i++){
            XWPFRun optionRun = doc.createParagraph().createRun();
            optionRun.addTab();
            optionRun.setText(options.get(i) + " [ ]");
        }
    }

    /**
    * Takes in a Java object containing key-value pairs related to the type of input to be generated.
    * Currently, the input is hardcoded. Input has to be coded such that the key order can be accessed via
    * .get(key) methods.
    * Creates a likert group from 1 to 5.
    */
    public void createLikertGroup(HashMap<String,Object> input){
        // HashMap<String, Object> likertInput = new HashMap<String, Object>() {{
        //     List<String> options = new ArrayList<>();
        //     options.add("Attendance in Safety Meeting");
        //     options.add("Tool Box Meeting");
        //     put("order", 1);
        //     put("label", "Part 1: Participation and Safety");
        //     put("options", options);
        //     put("type", "likertGroup");
        // }};
        //Tables are always 6 columns, number of options + 3
        ArrayList<String> options = (ArrayList<String>) input.get("options");
        int noOfOptions = options.size();
        doc.createParagraph();
        XWPFTable table = doc.createTable(noOfOptions+4,6);
        //Set header
        table.getRow(0).getCell(0).setText((String) input.get("label"));
        table.getRow(1).getCell(1).setText("Grade");
        table.getRow(2).getCell(1).setText("1 (Poor)");
        table.getRow(2).getCell(2).setText("2 (Below Average)");
        table.getRow(2).getCell(3).setText("3 (Average)");
        table.getRow(2).getCell(4).setText("4 (Above Average)");
        table.getRow(2).getCell(5).setText("5 (Good)");
        //Set score at bottom
        table.getRow(noOfOptions+3).getCell(1).setText("SCORE = ");
        //Merging headers
        CTHMerge hMerge = CTHMerge.Factory.newInstance();
        CTHMerge hMerge2 = CTHMerge.Factory.newInstance();
        CTHMerge hMerge3 = CTHMerge.Factory.newInstance();
        for (int i = 0; i < 6; i++) {
            //Merge cells in row 1
            if (i == 0) {
                hMerge.setVal(STMerge.RESTART);
            } else if (i == 1){
                hMerge2.setVal(STMerge.RESTART);
                hMerge3.setVal(STMerge.RESTART);
                hMerge.setVal(STMerge.CONTINUE);
            } 
            else {
                hMerge2.setVal(STMerge.CONTINUE);
                hMerge3.setVal(STMerge.CONTINUE);
            }
            table.getRow(0).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge);
            table.getRow(1).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge2);
            table.getRow(noOfOptions+3).getCell(i).getCTTc().addNewTcPr().setHMerge(hMerge2);
        }
        //Filling in options
        for (int i=0;i<options.size();i++) {
            table.getRow(3+i).getCell(0).setText(options.get(i));
        }
    }

    public void drawBoolOption(){
        XWPFTable table = doc.createTable();
        table.setTableAlignment(TableRowAlign.RIGHT);
        XWPFTableRow tableRow = table.getRow(0); 
        tableRow.getCell(0).setWidth("auto");
        tableRow.getCell(0).setText(" YES / NO * ");
    }
    
    public void drawTable(ArrayList<ArrayList<String>> tableInfo){
        XWPFTable table = doc.createTable();
        int rowCounter = 0;
        int cellCounter = 0;
        int numCells = tableInfo.get(0).size();
        int numRows = tableInfo.size();
        for (ArrayList<String> rowInfo : tableInfo){
            XWPFTableRow tableRow = table.getRow(rowCounter); 
            if (rowCounter == 0){
                //Initialise number of cells / columns
                for (int i=0;i<numCells-1;i++){
                    tableRow.addNewTableCell();
                }
            }
            for (String cellInfo : rowInfo) {
                tableRow.getCell(cellCounter).setText(cellInfo);
                cellCounter += 1;
            }
            cellCounter = 0;
            rowCounter += 1;
            if (rowCounter < numRows  ){
                table.createRow();
            }
        }
    }



}
