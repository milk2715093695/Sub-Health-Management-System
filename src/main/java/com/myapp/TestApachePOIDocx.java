package com.myapp;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

public class TestApachePOIDocx {
    public static void main(String[] args) throws IOException {
        // 获取类路径下的资源文件
        Resource resource = new ClassPathResource("templates/Doc2.docx");

        // 打开存在的 Word 文档
        XWPFDocument document = new XWPFDocument(new FileInputStream(resource.getFile()));

        // 创建一个新的段落
        XWPFParagraph paragraph = document.createParagraph();

        // 获取所有的表格
        for (XWPFTable tbl : document.getTables()) {
            // 遍历表格的所有行
            for (XWPFTableRow row : tbl.getRows()) {
                // 遍历行中的所有单元格
                for (XWPFTableCell cell : row.getTableCells()) {
                    // 打印单元格的内容
                    System.out.println(cell.getText());
                }
            }
        }
        document.close();
    }
}