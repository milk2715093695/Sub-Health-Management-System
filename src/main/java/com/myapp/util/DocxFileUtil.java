package com.myapp.util;

import com.myapp.model.TableCellCoordinate;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

// 如果不知道怎么写可以去参考我写的src/main/java/TestDocx.java和src/main/java/TestApachePOIDocx.java文件
public class DocxFileUtil {
    private final XWPFDocument doc;
    private final FileInputStream fis;
    private final FileOutputStream fos;

    public DocxFileUtil(String inputFilename, String outputFilename) throws IOException {
        Resource resource = new ClassPathResource("templates/"+inputFilename);
        fis = new FileInputStream(resource.getFile());
        fos = new FileOutputStream("src/main/resources/templates" + outputFilename);
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

    // 第一个需要补全的内容，如果不知道怎么写可以去参考我写的src/main/java/TestDocx.java和src/main/java/TestApachePOIDocx.java文件
    /**
     * 修改指定表格中单元格的文本。
     *
     * @param tableIndex  表格索引
     * @param rowIndex    行索引
     * @param columnIndex 列索引（单元格位置）
     * @param newText     需要设置的新文本
     * @return 如果操作成功返回true，否则返回false。
     * @throws IOException 如果在修改文本时出现I/O错误
     */

    public boolean modifyCellInTable(int tableIndex, int rowIndex, int columnIndex, String newText) throws IOException {
        try {
            XWPFTable table = doc.getTables().get(tableIndex);
            XWPFTableRow row = table.getRow(rowIndex);
            XWPFTableCell cell = row.getCell(columnIndex);
            for (XWPFParagraph p : cell.getParagraphs()) {
                for (XWPFRun r : p.getRuns()) {
                    r.setText(newText, 0);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifyCellInTable(TableCellCoordinate coordinate, String newText) throws IOException {
        return modifyCellInTable(coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex(), newText);
    }


    // 第二个需要补全的内容，如果不知道怎么写可以去参考我写的src/main/java/TestDocx.java和src/main/java/TestApachePOIDocx.java文件
    /**
     * 查找目标文本，并在目标文本的指定偏移位置修改文本。
     *
     * @param targetText  要查找的目标文本
     * @param newText     要插入的新文本
     * @param offsetIndex 指定的偏移量，文本将会插入到目标文本之后的这个位置
     * @param offsetRow   行偏移量
     * @param offsetColumn 列偏移量
     * @return 如果操作成功返回true，否则返回false。
     * @throws IOException 如果在查找和插入文本时出现I/O错误
     */
    public boolean findAndInsert(String targetText, String newText,int offsetIndex, int offsetRow, int offsetColumn) throws IOException {
        try {
            XWPFTable table = doc.getTableArray(offsetIndex);
            XWPFTableRow row = table.getRow(offsetRow);
            XWPFTableCell cell = row.getCell(offsetColumn);
            String cellText = cell.getText();
            if(cellText.contains(targetText)){
                String replacedText = cellText.replace(targetText, targetText + newText);
                cell.removeParagraph(0);
                cell.setText(replacedText);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findAndInsert(String targetText, String newText) throws IOException {
        return findAndInsert(targetText, newText, 0, 0, 0);
    }

    public boolean findAndInsert(String targetText, String newText, TableCellCoordinate coordinate) throws IOException {
        return findAndInsert(targetText, newText, coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex());
    }

    // 第三个需要补全的内容，如果不知道怎么写可以去参考我写的src/main/java/TestDocx.java和src/main/java/TestApachePOIDocx.java文件
    /**
     * 查找包含目标字符串的单元格。
     *
     * @param targetString  要查找的目标文本
     * @return  返回找到的单元格的位置，坐标用TableCellCoordinate记录类记录，定义参照/com/myapp/model/TableCellCoordinate.java文件
     * 关于记录类的使用方法可能需要自行查询
     * @throws IOException  如果在查找文本时出现I/O错误
     */
    // 第三个需要补全的内容
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

    // 第四个需要补全的内容，如果不知道怎么写可以去参考我写的src/main/java/TestDocx.java和src/main/java/TestApachePOIDocx.java文件
    /**
     * 获取指定表格、行、列中单元格。
     *
     * @param tableIndex 表格索引
     * @param rowIndex 行索引
     * @param columnIndex 列索引（单元格位置）
     * @return 对应位置的单元格。
     * @throws IOException 如果在获取单元格时出现I/O错误
     */
    public XWPFTableCell cellText(int tableIndex, int rowIndex, int columnIndex) throws IOException {
        XWPFTable table = doc.getTables().get(tableIndex);
        XWPFTableRow row = table.getRows().get(rowIndex);
        XWPFTableCell cell = row.getTableCells().get(columnIndex);
        return cell;
    }

    public XWPFTableCell cellText(TableCellCoordinate coordinate) throws IOException {
        return cellText(coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex());
    }


    // 第五个需要补全的内容，如果不知道怎么写可以去参考我写的src/main/java/TestDocx.java和src/main/java/TestApachePOIDocx.java文件
    /**
     * 打印整个文档的内容。
     *
     * @throws IOException 如果在打印文档时出现I/O错误
     */
    // 第五个需要补全的内容
    public void printDocument() throws IOException {
        // 打印段落内容
        for (XWPFParagraph p : doc.getParagraphs()) {
            System.out.println("Paragraph: " + p.getText());
        }
        // 打印表格内容
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    System.out.println("Table Cell: " + cell.getText());
                }
            }
        }
    }
}