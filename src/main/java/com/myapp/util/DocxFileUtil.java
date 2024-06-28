package com.myapp.util;

import com.myapp.Model.TableCellCoordinate;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocxFileUtil {
    private final XWPFDocument doc;
    private final FileInputStream fis;
    private final FileOutputStream fos;

    public DocxFileUtil(String inputFilename, String outputFilename) throws IOException {
        Resource resource = new ClassPathResource("templates/"+inputFilename);
        fis = new FileInputStream(resource.getFile());
        fos = new FileOutputStream("src/main/resources/static/files/" + outputFilename);
        doc = new XWPFDocument(fis);
    }

    public void close() throws IOException {
        if (doc != null) {
            doc.write(fos);
            doc.close();
        }

        if (fis != null) {
            fis.close();
        }

        if (fos != null) {
            fos.close();
        }
    }

    public boolean modifyCellInTable(int tableIndex, int rowIndex, int columnIndex, String newText) throws IOException {
        try {
            XWPFTable table = doc.getTables().get(tableIndex);
            XWPFTableRow row = table.getRow(rowIndex);
            XWPFTableCell cell = row.getCell(columnIndex);

            // 获取该单元格的第一个段落
            XWPFParagraph paragraph;
            if(!cell.getParagraphs().isEmpty()){
                paragraph = cell.getParagraphs().get(0);
            }else{
                paragraph = cell.addParagraph(); // 如果单元格没有段落，添加一个新的段落
            }

            // 创建一个新的 XWPFRun 对象，然后对它设置文字和字体
            XWPFRun run = paragraph.createRun();
            run.setFontFamily("宋体");
            run.setText(newText);

            // 清空原有的内容
            paragraph.removeRun(0);

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean modifyCellInTable(TableCellCoordinate coordinate, String newText) throws IOException {
        return modifyCellInTable(coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex(), newText);
    }

    public boolean findAndInsert(String targetText, String newText,int offsetIndex, int offsetRow, int offsetColumn) throws IOException {
        try {
            List<XWPFTable> tables = doc.getTables();
            for (int tableIndex = 0; tableIndex < tables.size(); tableIndex++) {
                XWPFTable table = tables.get(tableIndex);
                List<XWPFTableRow> rows = table.getRows();
                for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                    XWPFTableRow row = rows.get(rowIndex);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (int columnIndex = 0; columnIndex < cells.size(); columnIndex++) {
                        if (cells.get(columnIndex).getText().equals(targetText)) {
                            modifyCellInTable(tableIndex + offsetIndex, rowIndex + offsetRow, columnIndex + offsetColumn, newText);
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean findAndInsert(String targetText, String newText) throws IOException {
        return findAndInsert(targetText, newText, 0, 0, 0);
    }

    public boolean findAndInsert(String targetText, String newText, TableCellCoordinate coordinate) throws IOException {
        return findAndInsert(targetText, newText, coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex());
    }

    public TableCellCoordinate findCellInTable(String targetString) throws IOException {
        List<XWPFTable> tables = doc.getTables();
        for (int tableIndex = 0; tableIndex < tables.size(); tableIndex++) {
            XWPFTable table = tables.get(tableIndex);
            List<XWPFTableRow> rows = table.getRows();
            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                XWPFTableRow row = rows.get(rowIndex);
                List<XWPFTableCell> cells = row.getTableCells();
                for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
                    XWPFTableCell cell = cells.get(cellIndex);
                    if (cell.getText().contains(targetString)) {
                        return new TableCellCoordinate(tableIndex, rowIndex, cellIndex);
                    }
                }
            }
        }
        return null;
    }

    public XWPFTableCell cellText(int tableIndex, int rowIndex, int columnIndex) throws IOException {
        try {
            XWPFTable table = doc.getTables().get(tableIndex);
            XWPFTableRow row = table.getRows().get(rowIndex);
            return row.getTableCells().get(columnIndex);
        } catch (Exception exception) {
            return null;
        }
    }

    public XWPFTableCell cellText(TableCellCoordinate coordinate) throws IOException {
        return(coordinate == null) ? null : cellText(coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex());
    }

    public void printDocument() throws IOException {
        for (XWPFParagraph p : doc.getParagraphs()) {
            System.out.println("Paragraph: " + p.getText());
        }

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    System.out.println("Table Cell: " + cell.getText());
                }
            }
        }
    }
}