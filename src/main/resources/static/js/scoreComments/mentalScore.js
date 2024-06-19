function showMentalScoreComment() {
    let mentalReport = document.querySelector('.mental-report');
    let mentalComment = mentalReport.querySelector('p');
    let score = parseInt(document.querySelector('.mental-score').getAttribute('data'));

    if (score >= 0) {
        if (score <= 20) {
            mentalReport.textContent = '心理警戒';
            mentalComment.textContent = '处于这一得分范围的你，可能正在经历心理健康的严峻挑战，这可能包括但不限于持续的高压环境、深刻的焦虑感或明显的抑郁情绪。在这个阶段，感到无助、孤独或被忽视是非常普遍的情绪体验。重要的是，你需要认识到，寻求帮助并不意味着你脆弱，而是一种勇气的表现。首先，建议寻找专业的心理咨询服务。许多心理健康专家通过倾听、对话和专业的治疗方法，能够提供必要的支持和帮助。此外，与家人、好友或值得信赖的人分享你的内心世界同样重要，他们的理解和支持会成为你走出困境的重要力量。此时，尝试寻找减压的方法，如参与户外活动、练习冥想或是拥有一个爱好，都是有益的自我照顾方式。记住，你并不孤单，在这个过程中有许多人和资源可以为你提供帮助和支持。';
        } else if (score <= 40) {
            mentalReport.textContent = '需关注';
            mentalComment.textContent = '在这个分数区间，你的心理状况表示你可能面临一定程度的心理压力或情绪波动，尽管这并没有达到极端的情绪困扰。可能的压力源包括工作、人际关系或是个人期望等。此时，识别这些压力源，并且学会有效地管理它们是提高心理健康的关键。你可以通过增加身体运动，比如参加瑜伽、跑步或其他形式的体育活动，来释放压力；确保有充足的睡眠，这对于情绪稳定至关重要；此外，培养一个兴趣爱好，无论是绘画、音乐还是阅读，都能在紧张的生活中为你提供一片宁静之地。这个阶段，也可以考虑通过书籍、工作坊或在线课程学习一些管理情绪和压力的基本技巧。同时，保持开放的心态，与身边的人分享自己的感受，你会发现，沟通和分享是缓解心理压力的有效方式。';
        } else if (score <= 60) {
            mentalReport.textContent = '稳定但可改善';
            mentalComment.textContent = '位于这一分数范围的你，在心理健康方面表现出相对稳定的状态，但仍存在提升空间。这意味着你已经能够较好地应对日常生活中的压力和挑战，但为了达到更高层次的心理健康，还可以进一步加强自我关怀和改善生活习惯。首先，建议制定规律的运动计划，定期的身体活动不仅能增强体质，还能有效地提升心情和精神状态。试着探索不同类型的放松技巧，如深呼吸、正念冥想或是渐进式肌肉放松，这些都是改善心理健康的好方法。此外，保持良好的人际关系对于改善心理健康同样重要，定期花时间与家人和朋友相聚，和他们共享你的经历和感受。记得给予自己充分的休息时间，保持正常的作息习惯，并确保每晚有充足的睡眠。通过这些方法，你不仅能够保持现有的稳定状态，还能进一步提升自身的心理健康水平。';
        } else if (score <= 80) {
            mentalReport.textContent = '良好状态';
            mentalComment.textContent = '恭喜，这一得分显示你的心理健康状况处于比较良好的状态。你能有效地应对生活中的挑战和压力，拥有积极的人际关系，并保持健康的生活方式。为了保持并进一步提升这一良好状态，继续保持和培养积极的社交活动非常重要，良好的社交关系能为你提供情感的支持和积极的反馈。同时，不要忘记定期进行自我反思，意识到什么是让你感到幸福和满足的，保持对生活的热爱和对新事物的探索精神。尝试新的活动或学习新的技能可以为你的生活带来新鲜感，从而增加幸福感。此外，继续维护健康的生活习惯，如合理饮食、适量运动和充足睡眠，这些对维持心理健康至关重要。记住，自我关怀是一种重要的能力，适时地奖励自己，拥抱生活中的每一刻。';
        } else if (score <= 100) {
            mentalReport.textContent = '非常健康';
            mentalComment.textContent = '在这一得分区间内的你，代表着你的心理健康处于一个极佳的状态。这说明你不仅成功地建立了健康的心态，还精通于有效地管理生活中的压力，并且在人际交往中显得游刃有余。这样的心理健康状态，使你能够在面对生活挑战时保持积极乐观，同时也让你在社交场合中充满魅力和能量。为了继续维持并进一步优化这一极佳的状态，建议持续保持自我成长和学习的态度。寻找新的学习机会和挑战，可以是工作相关的，也可以是个人兴趣和爱好，比如学习一门新语言、掌握一项艺术技能或是投身于公益活动。这样不仅能增加你的生活满意度，还能提高你的社会阅历，让你的心理更加健康。';
        }
        mentalReport.append(mentalComment);
    }
}