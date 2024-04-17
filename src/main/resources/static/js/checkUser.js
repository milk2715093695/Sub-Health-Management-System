document.getElementById("submit").addEventListener('click', function (event) {
    event.preventDefault();

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: document.getElementById('username').value,
            password: document.getElementById('password').value
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.href = "/home";
            } else {
                alert('用户名或密码错误，请重新输入');
                document.getElementById('username').value = '';
                document.getElementById('password').value = '';
            }
        })
})