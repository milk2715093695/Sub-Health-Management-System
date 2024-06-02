package com.myapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.util.DocxFileUtil;

import java.io.IOException;

@Controller
public class TestDocsFileUtil {
    @RequestMapping("/docUtil")
    public String testDocUtil() throws IOException {
        DocxFileUtil docxFileUtil = new DocxFileUtil("health_report_template.docx", "filled_report.docx");

        System.out.println(docxFileUtil.modifyCellInTable(0, 0, 0, "Fuc"));
        System.out.println(docxFileUtil.modifyCellInTable(0, 1, 1, "Second"));
        System.out.println(docxFileUtil.modifyCellInTable(0, 2, 0, "Third"));
        docxFileUtil.close();

        return "error";
    }
}
