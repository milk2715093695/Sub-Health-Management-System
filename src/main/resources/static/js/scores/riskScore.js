function showResult(score) {
    const result = document.getElementById('score');
    if (score >= 90) result.innerText = "你的分数是：" + score + "，你的健康风险指数较低，你的生活方式和习惯可能对你的健康有积极的影响。建议你继续保持!";
    else if (score >= 80) result.innerText =  "你的分数是：" + score + "，你的健康风险指数中等，你可能需要调整生活方式和习惯，降低一些健康风险。建议你寻求专业的建议和帮助!";
    else if (score >= 0) result.innerText =  "你的分数是：" + score + "，你的健康风险指数较高，可能存在较大的健康风险。强烈建议你立即寻求医生和营养师的帮助，了解风险并开始积极改善健康状况。";
    else result.innerText = "抱歉，计算分数时出现了问题，请重新填写问卷";
}

document.getElementById('quiz').onsubmit = function(event) {
    event.preventDefault();
    const score = calculateScore();
    showResult(score);

    fetch('/riskScore', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            score: score
        })
    })
        .then(response => response.json())
        .then(data => {
            if (!data.success) {
                let language = localStorage.getItem('language') || 'zh-CN';
                alert(data.errMessage[language]);
            } else {
                alert("保存成功");
            }
        })
}