package com.myapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestShowPdf {
    @RequestMapping("/showPdf")
    public String showPdf(){
        return "TestPdf";
    }
}