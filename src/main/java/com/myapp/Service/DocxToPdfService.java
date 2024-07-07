package com.myapp.Service;

import org.springframework.stereotype.Service;

/**
 * 用于通过命令行命令调用本地的LibreOffice进行Docx与Pdf的转换
 * @author milk
 */
@Service
public class DocxToPdfService {
    public boolean convertDocxToPdf(String fileName) {
        final String LibreOffice_Home = System.getenv("LIBREOFFICE_HOME");
        String command = LibreOffice_Home + " --headless --convert-to pdf src/main/resources/static/files/" + fileName + " --outdir src/main/resources/static/files";

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            return true;
        } catch(Exception exception) {
            return false;
        }
    }
}
