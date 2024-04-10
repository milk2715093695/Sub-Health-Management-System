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
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;

    if (password !== confirmPassword) {
        event.preventDefault(); // 阻止表单提交
        alert('两次密码输入不一致，请重新输入！');
    }
});