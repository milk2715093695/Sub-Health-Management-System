package com.myapp.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
    /**
     * 错误处理方法
     *
     * 当出现错误请求时，会转向到"error"页面。
     *
     * @return 返回error视图。
     */
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}