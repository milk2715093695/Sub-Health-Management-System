document.getElementById('confirmPassword').addEventListener('input', function () {
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
})