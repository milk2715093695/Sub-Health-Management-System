package Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) {

        // 这里仅进行了基本的验证，实际应用中，你可能需要跟数据库中的记录进行比对
        if ("admin".equals(username) && "123456".equals(password)) {
            System.out.println("success");
            return "success"; // 登录成功，返回"success"
        } else {
            System.out.println("fail");
            return "fail"; // 用户名或者密码不正确，登录失败，返回"fail"
        }
    }
}