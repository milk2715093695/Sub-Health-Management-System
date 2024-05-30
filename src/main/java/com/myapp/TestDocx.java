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

            // 第一种，直接通过链式调用进行操作，优点是短小，缺点在可能会越界访问
            {
                doc.getTables().get(0).getRow(0).getCell(0).setText("1");
            }

            // 第二种，通过逐层访问，优点在越界的时候可以作出相应的处理，缺点是麻烦
            {
                XWPFTable table;

                // 如果表不存在，则创建表
                if(doc.getTables().size() < 2) {
                    table = doc.createTable();
                } else {
                    table = doc.getTables().get(1);
                }

                XWPFTableRow row;

                // 如果行不存在，则创建行
                if(table.getRows().size() < 2) {
                    row = table.createRow();
                } else {
                    row = table.getRow(1);
                }

                XWPFTableCell cell;

                // 如果单元格不存在，则创建单元格
                if(row.getTableCells().size() < 2) {
                    cell = row.createCell();
                } else {
                    cell = row.getCell(1);
                }

                cell.setText("success");
            }


            System.out.println(doc.getTables().get(0).getRow(2).getCell(1).getText());
            System.out.println(doc.getTables().get(0).getRow(2).getCell(2).getText());

            doc.write(fos);
        }
    }
}
