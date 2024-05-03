(function () {
    function Slider(sliderClass) {
        this.currentIndex = 0;
        this.timer = null;
        this.items = document.querySelectorAll(`.${sliderClass} .item`);
        this.totalItems = this.items.length;
        this.dots = document.querySelectorAll(`.${sliderClass} .dot`);
        this.slider = document.querySelector(`.${sliderClass}`);

        this.showSlide = function (index) {
            console.log("Function showSlide called, index = " + index);
            this.currentIndex = index;
            this.slider.querySelector('.transition-time').style.transform = `translateX(-${this.currentIndex * 100}%)`;
            this.dots.forEach(dot => dot.classList.remove('active'));
            this.dots[index].classList.add('active');
        };

        this.startAutoPlay = function () {
            console.log("Function startAutoPlay called");
            this.timer = setInterval(() => {
                this.showSlide((this.currentIndex + 1) % this.totalItems)
            }, 10000);
            console.log(this.timer);
        };

        this.stopAutoPlay = function () {
            console.log("Function stopAutoPlay called");
            clearInterval(this.timer);
            console.log(this.timer);
        };

        this.bindEvents = function () {
            console.log("Function bindEvents called");
            this.dots.forEach((dot, index) => {
                dot.addEventListener('click', () => {
                    this.showSlide(index);
                });
            });

            this.slider.addEventListener('mouseover', () => this.stopAutoPlay());
            this.slider.addEventListener('mouseout', () => this.startAutoPlay());

            this.showSlide(0);
        }
    }

    let slider1 = new Slider('slider1');
    let slider2 = new Slider('slider2');

    slider1.bindEvents();
    slider2.bindEvents();
    slider1.startAutoPlay();
    slider2.startAutoPlay();
}());