package com.myapp;

import com.myapp.Service.DocxToPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
    final DocxToPdfService docxToPdfService;
    @Autowired
    public Test(DocxToPdfService docxToPdfService) {
        this.docxToPdfService = docxToPdfService;
    }

    @RequestMapping("/docxToPdf")
    public String docxToPdf() {
        docxToPdfService.convertDocxToPdf("Doc2.docx");
        return "error";
    }
}
