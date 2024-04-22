document.querySelector('.iBanner-arrow').addEventListener('click', function() {
    let text = document.querySelector('.wrapper p');
    let arrow=document.querySelector('.iBanner-arrow');
    if (text.style.display === 'none') {
        text.style.display = 'block';
        arrow.style.display='none'
    } else {
        text.style.display = 'none';
    }
    // 滚动到文本位置，smooth表示平滑地滚动
    window.scrollTo({
        top: text.getBoundingClientRect().top + window.scrollY,
        behavior: 'smooth'
    });
});
