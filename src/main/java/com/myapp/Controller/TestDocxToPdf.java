package com.myapp.Controller;

import com.myapp.Service.DocxToPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestDocxToPdf {
    final DocxToPdfService docxToPdfService;
    @Autowired
    public TestDocxToPdf(DocxToPdfService docxToPdfService) {
        this.docxToPdfService = docxToPdfService;
    }

    @RequestMapping("/docxToPdf")
    public String docxToPdf() {
        if (docxToPdfService.convertDocxToPdf("filled_report.docx")) {
            return "test";
        }
        return "error";
    }
}
