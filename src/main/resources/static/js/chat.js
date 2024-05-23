document.getElementById("button").addEventListener('click', function() {
    const inputBox = document.getElementById('inputBox');
    const outputBox = document.getElementById('outputBox');

    // 将URL元素中的inputBox中的内容进行URL编码（否则发送中文时会乱码）
    const encodedInput = encodeURIComponent(inputBox.value);

    // 创建一个新的事件源（EventSource），用来监听服务器发送的SSE事件-->
    const eventSource = new EventSource(`/api/chat?encodedInput=${encodedInput}`);

    // 设置一个事件监听器，用来处理服务器发送的消息-->
    eventSource.onmessage = function(event) {
        outputBox.value += event.data;  // 将新的数据连接到原来的output中
    }

    inputBox.value = '';    // 清空inputBox
});

document.getElementById("reset-button").addEventListener('click', function() {
    document.getElementById("outputBox").value = '';
})