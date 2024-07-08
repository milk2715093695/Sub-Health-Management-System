document.getElementById('arrow').addEventListener('click', function() {
    let text = document.getElementById('report');
    // 滚动到文本位置
    window.scrollTo({
        top: text.getBoundingClientRect().top + window.scrollY,
        behavior: 'smooth'
    });
});
