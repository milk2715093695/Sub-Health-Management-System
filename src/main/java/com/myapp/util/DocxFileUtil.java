package com.myapp.util;


import com.myapp.Model.TableCellCoordinate;

import org.apache.poi.xwpf.usermodel.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * DOCX文件操作工具类，用于对Word文档进行修改和查找操作。
 * @author milk
 */
public class DocxFileUtil {
    private final XWPFDocument doc;
    private final FileInputStream fis;
    private final FileOutputStream fos;

    /**
     * 构造方法，初始化一个DocxFileUtil实例。
     *
     * @param inputFilename  输入文件名
     * @param outputFilename 输出文件名
     * @throws IOException 如果文件操作出现错误
     */
    public DocxFileUtil(String inputFilename, String outputFilename) throws IOException {
        Resource resource = new ClassPathResource("templates/"+inputFilename);
        fis = new FileInputStream(resource.getFile());
        fos = new FileOutputStream("src/main/resources/static/files/" + outputFilename);
        doc = new XWPFDocument(fis);
    }

    /**
     * 关闭文档及流资源。
     *
     * @throws IOException 如果关闭流时出现错误
     */
    public void close() throws IOException {
        if (doc != null) {
            doc.write(fos);
            doc.close();
        }
        if (fis != null) fis.close();
        if (fos != null) fos.close();
    }

    /**
     * 修改表格中指定单元格的文本内容。
     *
     * @param tableIndex   表格索引
     * @param rowIndex     行索引
     * @param columnIndex  列索引
     * @param newText      新的文本内容
     * @return 如果修改成功返回true，否则返回false
     * @throws IOException 如果修改时发生文件操作错误
     */
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

    /**
     * 修改表格中指定单元格的文本内容。
     *
     * @param coordinate 表格单元格坐标对象
     * @param newText    新的文本内容
     * @return 如果修改成功返回true，否则返回false
     * @throws IOException 如果修改时发生文件操作错误
     */
    public boolean modifyCellInTable(TableCellCoordinate coordinate, String newText) throws IOException {
        return modifyCellInTable(coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex(), newText);
    }

    /**
     * 在文档中查找指定文本，并在找到的单元格中插入新的文本内容。
     *
     * @param targetText    目标文本
     * @param newText       新的文本内容
     * @param offsetIndex   表格索引偏移量
     * @param offsetRow     行索引偏移量
     * @param offsetColumn  列索引偏移量
     * @return 如果查找并插入成功返回true，否则返回false
     * @throws IOException 如果操作时发生文件操作错误
     */
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

    /**
     * 在文档中查找指定文本，并在找到的单元格中插入新的文本内容。
     *
     * @param targetText 目标文本
     * @param newText    新的文本内容
     * @return 如果查找并插入成功返回true，否则返回false
     * @throws IOException 如果操作时发生文件操作错误
     */
    public boolean findAndInsert(String targetText, String newText) throws IOException {
        return findAndInsert(targetText, newText, 0, 0, 0);
    }

    /**
     * 在文档中查找指定文本，并在找到的单元格中插入新的文本内容。
     *
     * @param targetText  目标文本
     * @param newText     新的文本内容
     * @param coordinate  表格单元格坐标对象
     * @return 如果查找并插入成功返回true，否则返回false
     * @throws IOException 如果操作时发生文件操作错误
     */
    public boolean findAndInsert(String targetText, String newText, TableCellCoordinate coordinate) throws IOException {
        return findAndInsert(targetText, newText, coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex());
    }

    /**
     * 在文档中查找包含指定字符串的单元格，并返回该单元格的坐标。
     *
     * @param targetString 目标字符串
     * @return 包含目标字符串的单元格坐标，若未找到则返回null
     * @throws IOException 如果操作时发生文件操作错误
     */
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

    /**
     * 获取表格中指定单元格的文本内容。
     *
     * @param tableIndex  表格索引
     * @param rowIndex    行索引
     * @param columnIndex 列索引
     * @return 指定单元格的文本内容，若获取失败则返回null
     * @throws IOException 如果操作时发生文件操作错误
     */
    public XWPFTableCell cellText(int tableIndex, int rowIndex, int columnIndex) throws IOException {
        try {
            XWPFTable table = doc.getTables().get(tableIndex);
            XWPFTableRow row = table.getRows().get(rowIndex);
            return row.getTableCells().get(columnIndex);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * 获取表格中指定单元格的文本内容。
     *
     * @param coordinate 表格单元格坐标对象
     * @return 指定单元格的文本内容，若获取失败则返回null
     * @throws IOException 如果操作时发生文件操作错误
     */
    public XWPFTableCell cellText(TableCellCoordinate coordinate) throws IOException {
        return(coordinate == null) ? null : cellText(coordinate.tableIndex(), coordinate.rowIndex(), coordinate.columnIndex());
    }

    /**
     * 打印文档中的所有段落和表格单元格内容。
     *
     * @throws IOException 如果操作时发生文件操作错误
     */
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