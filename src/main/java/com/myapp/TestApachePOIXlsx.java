package com.myapp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.io.InputStream;

public class TestApachePOIXlsx {
    public static void main(String[] args) {
        try {
            Resource resource = new ClassPathResource("static/model/test.xlsx");
            InputStream inputStream = resource.getInputStream();

            Workbook workbook = new XSSFWorkbook(inputStream);
            inputStream.close();

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        String cellValue = cell.getStringCellValue();

                        // 如果单元格内容是 "test"
                        if (cellValue != null && cellValue.trim().equals("Test")) {
                            // 在该单元格的下一个单元格写入 "success"
                            Row nextRow = sheet.getRow(cell.getRowIndex() + 1);
                            if(nextRow == null) {
                                // 如果下一行不存在，就创建它
                                nextRow = sheet.createRow(cell.getRowIndex() + 1);
                            }
                            Cell nextCell = nextRow.getCell(cell.getColumnIndex());
                            if(nextCell == null) {
                                // 如果单元格不存在，就创建它
                                nextCell = nextRow.createCell(cell.getColumnIndex());
                            }
                            nextCell.setCellValue("success");
                        }
                    }
                }
            }

            // 在特定的单元格(第3行第4列)写入 "data"
            {
                Row row = sheet.getRow(2);
                if(row == null){
                    row = sheet.createRow(2);
                }
                Cell cell = row.getCell(3);
                if(cell == null){
                    cell = row.createCell(3);
                }
                cell.setCellValue("data");
            }

            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.print("");
                    }
                }
                System.out.println();  // 新行
            }

            // 写入文件
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/templates/newTest.xlsx");
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
        } catch (Exception exception) {
            String errorInfo = exception.getMessage();
            System.out.println(errorInfo);
        }
    }
}