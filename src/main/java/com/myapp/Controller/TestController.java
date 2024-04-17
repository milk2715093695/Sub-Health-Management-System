package com.myapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("info", "1");
        return "test";
    }
}
