document.getElementById('password').addEventListener('input', function() {
    let password = document.getElementById('password').value;
    let passwordStrength = document.getElementById('passwordStrength');

    // 检测密码强度的正则表达式
    let strongRegex = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})');
    let mediumRegex = new RegExp('^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})');

    if(password.length===0){
        passwordStrength.innerHTML = '';
    } else if(strongRegex.test(password)) {
        passwordStrength.innerHTML = '密码强度：强';
        passwordStrength.style.color = 'green';
    } else if(mediumRegex.test(password)) {
        passwordStrength.innerHTML = '密码强度：中';
        passwordStrength.style.color = 'orange';
    } else {
        passwordStrength.innerHTML = '密码强度：弱';
        passwordStrength.style.color = 'red';
    }
});