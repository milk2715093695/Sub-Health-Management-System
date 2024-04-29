function calculateScore() {
    var elements = document.querySelectorAll('#quiz input[type="radio"]:checked');
    var score = 0;
    for(var i=0; i<elements.length; i++){
        score += parseInt(elements[i].value);
    }
    if(score>=80)
        document.getElementById('score').innerText = "你的分数是：" + score+"。您目前的生活方式和健康状况相当良好，保持现状能够有效降低患病风险。";
    else if(score>=60)
        document.getElementById('score').innerText = "你的分数是：" + score+"。您的健康状况和生活方式整体尚可，但是有些方面可能需要改进，例如增加运动量，改善饮食习惯等。";
    else document.getElementById('score').innerText = "你的分数是：" + score+"。您的生活方式或健康状况可能存在一些问题，建议您寻求医生的帮助，制定和执行适当的健康管理计划。";
    return false;
}
if (score >= 90) {
    feedback = "你的表现非常优秀!";
} else if (score >= 80) {
    feedback = "你的表现很好!";
} else {
    feedback = "请继续加油!";
}

// 在HTML中显示分析语句
document.getElementById("demo").innerHTML = feedback;