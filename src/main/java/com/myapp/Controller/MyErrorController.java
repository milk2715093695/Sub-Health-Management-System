package com.myapp.Controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误路由处理的控制器
 * @author milk
 */
@Controller
public class MyErrorController implements ErrorController {
    /**
     * 错误处理方法
     * <p>当出现错误请求时，会转向到"error"页面。</p>
     *
     * @return 返回error视图。
     */
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}