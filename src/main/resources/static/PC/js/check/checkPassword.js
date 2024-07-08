function checkPassword() {
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;

    let checkPassword = document.getElementById('checkPassword');

    let language =  localStorage.getItem('language') || 'zh-CN';

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

document.getElementById('register').addEventListener('click', function (event) {
    event.preventDefault(); // 阻止表单提交

    let language = localStorage.getItem('language') || 'zh-CN';
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;

    if (password !== confirmPassword) {
        alert(language === 'zh-CN' ? "密码不一致，请重新输入" : "Passwords do not match, please try again.");
    } else {
        fetch('/user/register', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    location.href = './../html/register-success.html'
                } else {
                    alert(data.errMessage[language]);
                    document.getElementById('username').value = '';
                    document.getElementById('password').value = '';
                    document.getElementById('confirmPassword').value = '';
                }
            })
    }
});