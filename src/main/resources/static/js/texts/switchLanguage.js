// 语言更改的函数
function showLanguage(language, texts) {
    localStorage.setItem('language', language);

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

document.addEventListener('DOMContentLoaded', (event) => {
    let language = localStorage.getItem('language') || 'zh-CN'; // 默认语言为中文
    showLanguage(language, texts);

    // switchLanguage event listener
    document.getElementById('switchLanguage').addEventListener('click', function() {
        language = (language === 'zh-CN') ? 'en' : 'zh-CN';
        showLanguage(language, texts);
    });
});