在Java后端，你会使用一种称为Servlet的技术来接收和处理HTTP请求。Servlets是Java的服务器端程序，用于接收客户端请求，处理请求，并返回响应。

下面是一个基本的Java Servlet示例，用于处理登录请求：

```java
@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求中获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证用户名和密码，这可以通过查询数据库完成。
        // 这里我们简化处理，假设只有用户名为 "admin"，密码为 "123" 的用户是合法的
        if ("admin".equals(username) && "123".equals(password)) {
            // 验证通过，返回一个success响应
            response.getWriter().append("{\"success\": true}");
        } else {
            // 验证失败，返回一个failure响应
            response.getWriter().append("{\"success\": false}");
        }
    }
}
```

在JavaScript中，你使用fetch或XMLHttpRequest发出HTTP请求，Java的Servlet接收到请求后进行相应的处理并返回响应，然后在JavaScript中接收和处理响应，实现了JavaScript和Java后端的交互。这就是典型的前后端交互流程。

以上代码示例简化了很多内容，例如输入的验证、错误处理、密码的安全存储等，实际应用中要考虑的因素会更多。

对于通信格式，Servlet返回的是一个JSON格式的字符串，前端JavaScript通过`JSON.parse`解析这个字符串成为一个JavaScript对象，然后就可以使用这个对象进行后续处理。

需要注意的是，从安全角度出发，前端代码是不能够被信任的，所以凡是前端发出的数据，在后端都必须进行必要的验证和检查。因为黑客可以伪造前端请求，如果后端逻辑直接信任前端发来的数据，可能会导致安全问题。


在前端JavaScript部分，你需要监听表单的提交事件，并阻止默认的提交行为（即直接发送get或post请求），然后获取表单输入的数据，用XMLHttpRequest或者Fetch API发送异步请求到服务器。

在HTML中，给表单增加一个事件监听：

```html
<form id="loginForm">
  <!-- 其他内容 -->
</form>
```

在JavaScript中编写事件处理函数：

```javascript
document.getElementById('loginForm').addEventListener('submit', function(event) {
  event.preventDefault(); // 阻止表单默认提交行为

  // 获取用户名和密码输入
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  // 创建一个对象来存储这些数据
  const data = {
    username: username,
    password: password,
  };

  // 使用fetch发送一个post请求到登录接口
  fetch('/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  }).then(response => response.json()).then(data => {
    // 处理响应数据
    if (data.success) {
      // 如果登陆成功，重定向到主页
      window.location.href = '/home.html';
    } else {
      // 如果登录失败，显示错误信息
      alert(data.message);
    }
  }).catch(error => {
    // 处理请求错误
    console.error('Error:', error);
  });
});
```

在这个例子中，无论登录是否成功，服务器都会返回一个JSON对象，包含一个表示是否成功的布尔值和一个消息。这只是一个例子，实际的响应可能因你的后端设计而不同。

在Java后端，你需要创建一个对应的API来处理登录请求。你需要解析请求的数据，检查用户名和密码是否匹配，然后返回相应的结果。例如，你可以使用Spring Boot框架和其内置的`@RestController`注解。你可能需要查阅相关学习资料来更深入的了解Java后端的开发。
