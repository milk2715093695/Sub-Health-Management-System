package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        // 这里仅进行了基本的验证，实际应用中，你可能需要跟数据库中的记录进行比对
        if ("admin".equals(username) && "123456".equals(password)) {
            model.addAttribute("username", username);
            return "success"; // 登录成功，跳转到success页面
        } else {
            // 登录失败，重新跳转到login页面
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }
}
