'use strict';

(function() {
    let language = 'zh-CN';     // 默认语言为中文

    const texts = {
        'zh-CN': {
            'totalTitle': '亚健康管理系统',
            'title': '亚健康管理系统',
            'containerTitle': '用户登录',
            'username': '请输入您的用户名',
            'password': '请输入您的密码',
            'showPassword': '显示密码',
            'submit': '登录',
            'createAccountText': '还没有账号？创建一个',
            'footerContent': '你的健康，我们关心！（制作者：******************）',
            'switchLanguage': 'EN/中'
        },
        'en': {
            'totalTitle': 'Sub-Health Management System',
            'title': 'Sub-Health Management System',
            'containerTitle': 'User Login',
            'username': 'Please input your username',
            'password': 'Please input your password',
            'showPassword': 'Show password',
            'submit': 'Log in',
            'createAccountText': 'Don\'t have an account yet? Create one',
            'footerContent': 'Your health is what we care!(Creator:******************)',
            'switchLanguage': '中/EN'
        }
    };

    function toggleLanguage() {
        language = (language === 'zh-CN') ? 'en' : 'zh-CN'; // 切换语言

        // 更新页面上的文本内容
        Object.keys(texts[language]).forEach(function(key) {
            const element = document.getElementById(key);
            if (element) {
                if (element.tagName === 'INPUT') {
                    if (element.type === 'submit') {
                        element.value = texts[language][key];
                    } else {
                        element.placeholder = texts[language][key];
                    }
                } else {
                    element.textContent = texts[language][key];
                }
            }
        });
    }

    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('switchLanguage').addEventListener('click', toggleLanguage);
    });
})();