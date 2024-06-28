package com.myapp.Controller;


import com.myapp.Service.DocxToPdfService;
import com.myapp.Service.FillDocxService;
import com.myapp.Service.SurveyService;
import com.myapp.Entity.Survey;
import com.myapp.Entity.User;

import jakarta.servlet.http.HttpSession;

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

/**
 * 处理下载报表请求的控制器
 * @author milk
 */
@Controller
public class DownloadController {
    private final DocxToPdfService docxToPdfService;
    private final SurveyService surveyService;
    private final FillDocxService fillDocxService;

    /**
     * 自动注入服务依赖。
     *
     * @param docxToPdfService 文档转换服务
     * @param surveyService 问卷服务
     * @param fillDocxService 填充文档服务
     */
    @Autowired
    public DownloadController(DocxToPdfService docxToPdfService, SurveyService surveyService, FillDocxService fillDocxService) {
        this.docxToPdfService = docxToPdfService;
        this.surveyService = surveyService;
        this.fillDocxService = fillDocxService;
    }

    /**
     * 处理PDF下载请求。
     *
     * @param session 用户会话，用于验证用户身份
     * @return 响应实体，包含生成的PDF文件或错误状态
     * @throws IOException 文件操作时可能产生的异常
     */
    @RequestMapping("/download")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> downloadPdf(HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) return ResponseEntity.status(404).body(null);

        Survey survey = surveyService.getSurvey(user.getUsername());
        if (!fillDocxService.fillDocx(survey)) return ResponseEntity.status(404).body(null);

        boolean isConverted = docxToPdfService.convertDocxToPdf("filled_report.docx");
        File file = new File("src/main/resources/static/files/filled_report" + (isConverted ? ".pdf" : ".docx"));
        if (file.exists()) {
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