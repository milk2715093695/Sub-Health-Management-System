document.getElementById('arrow').addEventListener('click', function() {
    let text = document.getElementById('hidden-text');
    let arrow= document.getElementById('arrow');

    arrow.style.display = 'none';
    text.style.display = 'block';

    // 滚动到文本位置
    window.scrollTo({
        top: text.getBoundingClientRect().top + window.scrollY,
        behavior: 'smooth'
    });
});
