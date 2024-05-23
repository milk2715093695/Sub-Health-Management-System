function calculateScore() {
    var elements = document.querySelectorAll('#quiz input[type="radio"]:checked');
    var score = 0;
    for(var i=0; i<elements.length; i++){
        score += parseInt(elements[i].value);
    }
    if(score>=80)
        document.getElementById('score').innerText = "你的分数是：" + score+"\n你的心理健康状况可能处于良好状态。你的心理压力与困扰较低，生活态度乐观，面对压力和困难有健康、积极的应对方式。建议保持良好的生活习惯，如健康饮食、适当运动和保证足够的休息。";
    else if(score>=60)
        document.getElementById('score').innerText = "你的分数是：" + score+"\n可能存在一些轻度的心理压力或者困难。这可能是由于工作压力、生活变故或其他的压力源所导致的。这个时候，建议你尝试一些自我疏导的方式，比如瑜伽、冥想或者兴趣爱好。如有需要，你也可以寻求心理咨询师的帮助。";
    else if(score<60)
        document.getElementById('score').innerText = "你的分数是：" + score+"\n你正在经历一些严重的心理压力或者困扰，对此，你可能需要专业的心理支持和援助。建议尽早与心理咨询师或者医生接洽，以便得到及时、专业的辅导。";

    return false;
}