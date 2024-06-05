function addChatHistory(type, message) {
    const chatBody = document.getElementById('chat-body');

    let chatRecords = chatBody.getElementsByClassName('chat-message');
    if (chatRecords.length >= 20) chatBody.removeChild(chatRecords[0]);

    const div = document.createElement('div');
    div.className = 'chat-message';

    const innerDiv = document.createElement('div');
    innerDiv.className = `inline-block-display chat-history ${type}`;

    const text = document.createElement('p');
    text.className = 'chat-content';
    text.innerText = message;

    innerDiv.appendChild(text);
    div.appendChild(innerDiv);
    chatBody.appendChild(div);
    chatBody.scrollTop = chatBody.scrollHeight; // 自动滚动到最底部
}

document.getElementById("clean").addEventListener('click', function() {
    const chatBody = document.getElementById('chat-body');
    while (chatBody.firstChild) {
        chatBody.removeChild(chatBody.firstChild);
    }
});

function sendMessage() {
    const inputBox = document.getElementById('input-box');
    if (inputBox.value !== '') {
        addChatHistory('human', inputBox.value);
        const encodedInput = encodeURIComponent(inputBox.value);
        addChatHistory('bot', '');

        const chatBody = document.getElementById('chat-body');
        let chatRecords = chatBody.getElementsByClassName('chat-message');
        const outputBox = chatRecords[chatRecords.length - 1].firstChild.firstChild;
        console.log("调用了函数");

        const eventSource = new EventSource(`/api/chat?encodedInput=${encodedInput}`);
        eventSource.onmessage = function(event) {
            outputBox.innerText += event.data;
        }

        eventSource.addEventListener('DONE', function(event) {
            console.log("DONE");
            eventSource.close();
        })

        inputBox.value = '';
    }
}

document.getElementById("send").addEventListener("click", sendMessage);

document.getElementById("input-box").addEventListener('keydown', function(event) {
    if (event.key === 'Enter' && !event.shiftKey) {
        event.preventDefault();
        sendMessage();
    }
});