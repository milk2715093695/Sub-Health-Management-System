// JavaScript
// 对于第一个轮播器
let currentIndex1 = 0;
let timer1;

const items1 = document.querySelectorAll('.slider1 .item');
const totalItems1 = items1.length;
const dots1 = document.querySelectorAll('.slider1 .dot');
const slider1 = document.querySelector('.slider1');

// 对于第二个轮播器
let currentIndex2 = 0;
let timer2;

const items2 = document.querySelectorAll('.slider2 .item');
const totalItems2 = items2.length;
const dots2 = document.querySelectorAll('.slider2 .dot');
const slider2 = document.querySelector('.slider2');

// 针对每个轮播器，你需要创建一个 showSlide 函数，这是第一个：
function showSlide1(index) {
    currentIndex1 = index;
    document.querySelector('.slider1 .slider-items').style.transform = `translateX(-${currentIndex1 * 100}%)`;
    dots1.forEach(dot => dot.classList.remove('active'));
    dots1[index].classList.add('active');
}

// 这是第二个：
function showSlide2(index) {
    currentIndex2 = index;
    document.querySelector('.slider2 .slider-items').style.transform = `translateX(-${currentIndex2 * 100}%)`;
    dots2.forEach(dot => dot.classList.remove('active'));
    dots2[index].classList.add('active');
}

// 并且你需要为每个轮播器创建 startAutoPlay 和 stopAutoPlay 函数。这是第一个轮播器的：
function startAutoPlay1() {
    timer1 = setInterval(() => {
        showSlide1((currentIndex1 + 1) % totalItems1)
    }, 10000)
}

function stopAutoPlay1() {
    clearInterval(timer1);
}

// 这是第二个轮播器的：
function startAutoPlay2() {
    timer2 = setInterval(() => {
        showSlide2((currentIndex2 + 1) % totalItems2)
    }, 12000);
}

function stopAutoPlay2() {
    clearInterval(timer2);
}

// 绑定小点的点击事件，当点击小点时，切换到对应的轮播项，并重新启动自动播放。这是第一 个轮播器的：
dots1.forEach((dot, index) => {
    dot.addEventListener('click', () => {
        stopAutoPlay1();
        showSlide1(index);
        startAutoPlay1();
    });
});

// 这是第二个轮播器的：
dots2.forEach((dot, index) => {
    dot.addEventListener('click', () => {
        stopAutoPlay2();
        showSlide2(index);
        startAutoPlay2();
    });
});

// 鼠标悬停时停止自动播放，鼠标离开后重新启动自动播放。这是第一个轮播器的：
slider1.addEventListener('mouseover', stopAutoPlay1);
slider1.addEventListener('mouseout', startAutoPlay1);

// 这是第二个轮播器的：
slider2.addEventListener('mouseover', stopAutoPlay2);
slider2.addEventListener('mouseout', startAutoPlay2);

// 页面加载完成后就开始自动播放
startAutoPlay1();
startAutoPlay2();