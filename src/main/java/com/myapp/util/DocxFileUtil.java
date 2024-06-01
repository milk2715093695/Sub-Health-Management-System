package com.myapp.util;

import com.myapp.model.TableCellCoordinate;
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
        fos = new FileOutputStream("src/main/resources/templates/" + outputFilename);
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
            cell.setText(newText);
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