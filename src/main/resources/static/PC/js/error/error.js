(function () {
    let images = ["/images/error/1.jpg", "/images/error/2.jpg", "/images/error/3.jpg", "/images/error/4.jpg", "/images/error/5.jpg", "/images/error/6.jpg", "/images/error/7.jpg", "/images/error/8.jpg", "/images/error/9.jpg", "/images/error/10.jpg", "/images/error/11.jpg", "/images/error/12.jpg", "/images/error/13.jpg", "/images/error/14.jpg", "/images/error/15.jpg"];
    let index = Math.floor(Math.random() * images.length);
    document.body.style.backgroundImage = "url('" + images[index] + "')";
})()