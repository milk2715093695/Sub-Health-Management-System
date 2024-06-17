function showResult(score) {
    const result = document.getElementById('score');
    if (score >= 90) result.innerText = "你的分数是：" + score + "\n优秀。这个分数表示你的身体条件非常好，无论是身体素质还是身心健康，你都处于顶级状态。这个时候，你需要维持你的健康状况，避免不良的生活习惯。\n";
    else if (score >= 70) result.innerText = "你的分数是：" + score + "\n良好。你的身体条件良好，但可能有部分小问题，可能需要你略微调整你的生活习惯，在饮食、日常运动等方面做出一些改变。";
    else if (score >= 60) result.innerText = "你的分数是：" + score + "\n一般。你的身体条件一般，可能存在部分健康问题。这个时候，你需要注意身体状况，改善你的生活方式，并定期检查身体。";
    else if (score >= 0) result.innerText = "你的分数是：" + score + "\n较差。这说明你的身体可能存在一些显著的问题，需要予以治疗和改善。你需要进一步重视自身健康，并寻求医生和营养师的帮助。";
    else result.innerText = "抱歉，计算分数时出现了问题，请重新填写问卷";
}

document.getElementById('quiz').onsubmit = function(event) {
    event.preventDefault();
    const score = calculateScore();
    showResult(score);

    fetch('/healthScore', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            gender: document.querySelector('input[name="gender"]:checked').id,
            score: score
        })
    })
            .then(response => response.json())
            .then(data => {
                if (!data.success) {
                    let language = localStorage.getItem('language') || 'zh-CN';
                    alert(data.errMessage[language]);
                } else {
                    
                }
            })
}