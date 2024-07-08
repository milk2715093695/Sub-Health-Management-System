(function () {
    let contents = ["恭喜你发现隐藏彩蛋！(≧∇≦)ﾉ", "才怪嘞ヽ(ﾟД ﾟ)ﾉ", "其实是页面错误啦(´ｰ∀ｰ`)", "不用看了，没有彩蛋", "快走吧", "你怎么还在щ(ºДºщ)", "还不回去看看URL输错了没"];

    (function changeContent() {
        if (contents.length === 0) {
            return;
        }

        let content = contents.shift();
        let targetElement = document.getElementById("text");

        targetElement.innerText = content;
        setTimeout(changeContent, 5000);
    })();
}());