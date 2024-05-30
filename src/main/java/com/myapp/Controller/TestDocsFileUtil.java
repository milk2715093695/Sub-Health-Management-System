package com.myapp.Controller;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.util.DocxFileUtil;
import com.myapp.model.TableCellCoordinate;

import java.io.IOException;

@Controller
public class TestDocsFileUtil {
    @RequestMapping("/docUtil")
    public String testDocUtil() throws IOException {
        DocxFileUtil docxFileUtil = new DocxFileUtil("Doc2.docx", "newDoc.docx");

        System.out.println(docxFileUtil.modifyCellInTable(0, 0, 0, "test"));
        System.out.println(docxFileUtil.findAndInsert("test", "Test", 0, 1, 1));
        TableCellCoordinate coordinate = docxFileUtil.findCellInTable("Test");
        XWPFTableCell cell = docxFileUtil.cellText(coordinate);
        System.out.println(cell.getText());
        docxFileUtil.printDocument();

        return "error";
    }
}
