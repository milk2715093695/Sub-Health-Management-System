package com.myapp;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDocx {
    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("templates/Doc2.docx");

        try (FileInputStream fis = new FileInputStream(resource.getFile());
             XWPFDocument doc = new XWPFDocument(fis);
             FileOutputStream fos = new FileOutputStream("src/main/resources/templates/newDoc.docx")) {

            XWPFTable table = doc.getTables().get(0);

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);

            cell.setText("success");
            System.out.println(doc.getTables().get(0).getRow(2).getCell(1).getText());
            System.out.println(doc.getTables().get(0).getRow(2).getCell(2).getText());

            doc.write(fos);
        }
    }
}
