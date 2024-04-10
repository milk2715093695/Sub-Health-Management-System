package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @PostMapping("/register")
    public String register() {
        // 在这里处理表单提交的数据
        // ...

        // 重定向到registerSuccess.html页面
        return "redirect:/registerSuccess.html";
    }

}