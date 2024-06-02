package com.myapp.Controller;


import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class TestShowPdf {
    @RequestMapping("/showPdf")
    public String showPdf(){
        return "TestPdf";
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> downloadPdf(@RequestParam String filename) throws IOException {
        File file = new File("src/main/resources/static/files/" + filename);
        if(file.exists()){
            byte[] data = IOUtils.toByteArray(new FileInputStream(file));
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_LOCATION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(data.length)
                    .body(resource);
        }
        return ResponseEntity.status(404).body(null);
    }
}