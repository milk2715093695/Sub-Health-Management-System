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



    // 如果类型是 bot，添加头像
    if (type === 'bot') {
        const avatar = document.createElement('img');
        avatar.src = './../images/icons/aidoctor.png'; //
        avatar.alt = 'bot';
        avatar.className = 'bot-avatar'; // 将用于 CSS 样式的类名
        div.prepend(avatar); // 将头像添加到外层div
    }
}

// document.getElementById("clean").addEventListener('click', function() {
//     const chatBody = document.getElementById('chat-body');
//     while (chatBody.firstChild) {
//         chatBody.removeChild(chatBody.firstChild);
//     }
// });

function sendMessage() {
    const inputBox = document.getElementById('input-box');
    if (inputBox.value !== '') {
        addChatHistory('human', inputBox.value);
        const encodedInput = encodeURIComponent(inputBox.value);
        addChatHistory('bot', '');

        const chatBody = document.getElementById('chat-body');
        let chatRecords = chatBody.getElementsByClassName('chat-message');
        const outputBox = chatRecords[chatRecords.length - 1].firstChild.firstChild;

        const eventSource = new EventSource(`/api/chat?encodedInput=${encodedInput}`);
        eventSource.onmessage = function(event) {
            console.log(event.data);
            outputBox.innerText += event.data;
        }

        eventSource.addEventListener('DONE', function(event) {
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