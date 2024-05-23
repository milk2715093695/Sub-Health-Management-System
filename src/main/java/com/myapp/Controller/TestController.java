package com.myapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TestController类.
 * <p>此控制器仅用于测试 Spring MVC 视图渲染。</p>
 * <p>请注意，这个控制器是与项目无关的测试代码。</p>
 *
 * @author <a href="https://github.com/milk2715093695">milk</a>
 */
@Controller
public class TestController {
    /**
     * 测试方法，用于处理 '/test' 路径的 GET 请求.
     * <p>此方法将 "这是一个测试网页" 字符串添加到模型中，并返回视图名称 "test"。
     * 用于测试 Spring MVC 视图渲染。</p>
     *
     * @param model Spring MVC Model 对象，用于向视图传递属性
     * @return "test" 视图名称
     */
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("info", "这是一个测试网页");
        return "test";
    }
}
