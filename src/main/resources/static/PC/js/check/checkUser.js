document.getElementById("submit").addEventListener('click', function (event) {
    event.preventDefault();

    fetch('/user/login', {
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
                location.href = "/index/home-page.html";
            } else {
                let language = localStorage.getItem('language') || 'zh-CN';
                alert(data.errMessage[language]);
                document.getElementById('username').value = '';
                document.getElementById('password').value = '';
            }
        })
})