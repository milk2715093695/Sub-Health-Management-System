要实现这种情况，你需要在前端JavaScript部分加入相应的处理函数，来根据服务器返回的结果做出反馈。具体可以通过 AJAX 来进行异步请求和处理。我给你提供一种可能的思路：

第一步，需要修改一下你的后端代码，使得它能返回一个 JSON 格式的相应告知前端用户名是否被占用。

这里是 `RegisterController` 的修改版：

```java
@Controller
public class RegisterController {
    @Autowired 
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        HashMap<String, String> map = new HashMap<>();
        boolean success = userService.register(username, password);

        if (!success) {
            map.put("message", "用户名被占用，请重新注册！");
        } else {
            map.put("message", "注册成功");
        }
        
        return map;
    } 
}
```
这里使用了 `@ResponseBody` 标签和 `Map` 类型的返回值，使得我们的注册函数能返回一个 JSON 格式的响应体。

第二步，修改HTML中的form，使其使用AJAX提交，而不是直接提交：

```html
<form id="register-form">
    <!-- 其他内容省略 -->
    <input id="register" type="button" value="注册" class="submit-button cursor-pointer button-hover" onclick="submitForm()">
</form>
```

第三步，在你的JavaScript文件中添加 `submitForm` 函数，该函数会在点击注册按钮时调用：

```javascript
function submitForm() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/register", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            if (response.message == "用户名被占用，请重新注册！") {
                alert("用户名被占用，请重新注册！");
            } else {
                alert("注册成功");
                window.location.href = "/html/registerSuccess.html";
            }
        }
    };

    xhr.send("username=" + username + "&password=" + password);
}
```

这样，当你点击注册按钮时，页面会发送一个 AJAX POST 请求到后端的 `/register` 路由，后端处理完注册请求后返回一个 JSON 格式的响应，前端接收到响应后根据其中的 message 决定是弹出 "用户名被占用，请重新注册" 的警告框，还是 "注册成功" 的警告框，并重定向到成功页面。








你可以在处理用户注册的后端代码里，当发现重复的用户名时，返回一个特定的错误信息。然后在你的前端JavaScript代码里，针对这个错误信息弹出警告。这就需要前后端进行协作。

首先，在你的后端Java代码中，当检测到用户名已被使用时，不返回重定向，而是返回一个错误信息。可以通过一个 Map 或者一个自定义的 Result 类实现。示例如下：

```java
@PostMapping("/register")
@ResponseBody
public Map<String, Object> register(@RequestParam String username,
                                    @RequestParam String password) {
    Map<String, Object> result = new HashMap<>();
    User existedUser = userService.findByUsername(username);

    if (existedUser != null) {
        result.put("error", "该用户名已被占用，请重新输入");
    } else {
        User newUser = userService.register(username, password);
        result.put("user", newUser);
    }

    return result;
}
```

然后，在你的前端JavaScript代码中，处理这个注册请求的响应，如果返回的JSON对象中含有 "error" 字段，那么就弹出警告：

```javascript
axios.post('/register', {
    username: inputUsername,
    password: inputPassword,
}).then(function (response) {
    if (response.data.error) {
        alert(response.data.error);
    } else {
        window.location.href = '/html/loginSuccess.html';
    }
}).catch(function (error) {
    console.log(error);
});
```

注意：你需要替换 `inputUsername` 和 `inputPassword` 为实际获取表单输入的代码。同样地，你的前端应该使用一个可处理Ajax请求的库，如Axios，jQuery等，这里的示例中使用的是Axios。使用这种方式，你就可以在用户名被占用时弹出警告信息啦。



当然，这是你的代码，我将验证密码匹配和登录请求的功能整合到一起，如下所示：

```javascript
function checkPassword() {
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;
    let checkPassword = document.getElementById('checkPassword');
    let language = localStorage.getItem('language') || 'zh-CN';

    if (confirmPassword !== password) {
        checkPassword.innerText = (language === 'zh-CN') ? '两次密码输入不一致' : 'The passwords entered do not match';
        checkPassword.style.color = 'red';
    } else {
        checkPassword.innerText = (language === 'zh-CN') ? '密码匹配' : 'Passwords match';
        checkPassword.style.color = 'green';
    }
}

document.getElementById('confirmPassword').addEventListener('input', checkPassword);
document.getElementById('password').addEventListener('input', checkPassword);

var form = document.getElementById('login-form');
form.addEventListener('submit', function(e) {
    e.preventDefault();

    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;

    if (password !== confirmPassword) {
        alert('两次密码输入不一致，请重新输入！');
        return;
    }

    // 获取用户名
    var username = document.getElementById('username').value;

    // 发起登录请求
    fetch('http://youapiaddress/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        }),
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Login failed');
        }
    })
    .then(data => {
        console.log(data);
        // 登录成功，重定向到 loginSuccess 页面
        window.location.href = "/html/loginSuccess.html";
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Login failed: ' + error.message);
    });
});
```

这段代码首先会在用户键入密码或确认密码时验证它们是否匹配。然后，在用户提交表单时，如果密码和确认密码不匹配，那么它将弹出一个警告并阻止表单的提交。否则，它将使用Fetch API发送登录请求到你的API。

你需要确保你的HTML包含了函数中用到的所有元素，例如使用`getElementById()`函数获取的元素。在JavaScript中的字符串`'http://youapiaddress/login'`应该被替换为你的实际API地址。


