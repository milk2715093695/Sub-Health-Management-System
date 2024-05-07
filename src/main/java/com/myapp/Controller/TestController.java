package com.myapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("info", "这是一个测试网页");
        return "test";
    }
}
