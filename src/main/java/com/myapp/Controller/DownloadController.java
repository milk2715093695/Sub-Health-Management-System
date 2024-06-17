package com.myapp.Controller;


import com.myapp.Service.DocxToPdfService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class DownloadController {
    final DocxToPdfService docxToPdfService;
    @Autowired
    public DownloadController(DocxToPdfService docxToPdfService) {
        this.docxToPdfService = docxToPdfService;
    }

    @RequestMapping("/download")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> downloadPdf() throws IOException {
        boolean isConverted = docxToPdfService.convertDocxToPdf("filled_report.docx");
        File file = new File("src/main/resources/static/files/filled_report" + (isConverted ? ".pdf" : ".docx"));
        if(file.exists()) {
            byte[] data = IOUtils.toByteArray(new FileInputStream(file));
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_LOCATION, "attachment;filename=" + file.getName())
                    .contentType(isConverted ? MediaType.APPLICATION_PDF : MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                    .contentLength(data.length)
                    .body(resource);
        }
        return ResponseEntity.status(404).body(null);
    }
}