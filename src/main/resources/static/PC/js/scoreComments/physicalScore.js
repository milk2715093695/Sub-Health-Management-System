function showPhysicalScoreComment() {
    let physicalReport = document.querySelector('.physical-report');
    let physicalComment = physicalReport.querySelector('p');
    let score = parseInt(document.querySelector('.physical-score').getAttribute('data'));

    if (score >= 0) {
        if (score <= 20) {
            physicalReport.textContent = '体质情况：急需关注';
            physicalComment.textContent = '在此区间的体质指标，暗示着你的健康状况面临着严重的挑战，甚至可能正处于危机之中。你或许频繁感到极度疲惫，生活质量大幅下降，身体的各项机能并不在最佳状态，比如免疫力下降，容易受到疾病的侵扰。这种情况下，关键的第一步是寻求专业医疗机构的帮助，进行全面而细致的健康检查。与此同时，重视医生的专业建议，适时地调整饮食习惯和生活方式至关重要。更健康的饮食，充足的睡眠，适量的运动，以及学会压力管理等，都是恢复健康的重要组成部分。此外，保持积极乐观的心态，以及与家人和朋友的良好沟通，也将为你的健康恢复提供重要的精神支持。总之，在这一阶段，采取积极措施改善健康状况，防止疾病的产生或发展，是最为紧迫和重要的。';
        } else if (score <= 40) {
            physicalReport.textContent = '体质情况：改善空间大';
            physicalComment.textContent = '这一得分范围意味着你的身体可能正在发出一些警示信号，提醒你的健康状况有待提升。虽然不至于处于紧急状态，但身体的某些不适应该得到适当的关注和处理。这时，建议定期进行健康检查，以便及时发现并应对可能出现的健康问题。饮食方面，注重营养均衡，增加蔬果摄入，减少油脂和高糖食品是提升体质的有效途径。同时，运动是调节身心状态、增强身体机能的重要手段。试着找到自己喜爱的运动方式，将其变成日常的一部分。不要忽视精神健康的重要性，试着通过冥想、瑜伽或是兴趣爱好来减缓生活和工作压力。改善自身健康的同时，也将给你带来更多生活的乐趣和满足感。';
        } else if (score <= 60) {
            physicalReport.textContent = '体质情况：中等水平';
            physicalComment.textContent = '处于这个得分区间的你，体质处于中等水平。你的健康状态总体尚可，但偶尔也会遇到一些小波折，比如不时的健康小毛病或是偶尔的情绪波动。现阶段，最重要的是对已有的健康习惯进行巩固和提升。均衡饮食是基础，应该包含足够的蛋白质、必要的脂肪和丰富的维生素。体育活动不仅能增强体质，还能提高情绪，建议选择自己喜欢的运动项目，持之以恒地进行。除此之外，优化睡眠质量也非常关键，保证充足的睡眠有助于身体和大脑的恢复。此外，学会有效的压力管理技巧，保持心理的平衡和健康，对于维持和提升整体健康同样重要。';
        } else if (score <= 80) {
            physicalReport.textContent = '体质情况：良好状态';
            physicalComment.textContent = '你的得分显示出相对良好的体质状态，说明你的健康管理已经步入了正确的轨道。在这个阶段，关键是通过持续的努力，将良好的健康状态提升到更高水平。保持规律的身体锻炼和均衡的饮食结构是必不可少的，同时，适当增加新的健康习惯，如定期的拉伸运动、冥想或是户外活动，都能给你的健康带来积极的影响。了解并实践科学的健康信息，避免盲目跟风各种健康潮流，基于自身实际情况做出合理的健康选择。记得欣赏自己在健康道路上取得的进步，并为继续保持、甚至提升这一状态设定新的目标。';
        } else if (score <= 100) {
            physicalReport.textContent = '体质情况：极佳体质';
            physicalComment.textContent = '达到这个等级，标志着你在健康管理方面取得了显著的成就，你的体质处于极佳的状态。这说明你已经养成了健康的生活习惯，并能持续地、自觉地维护这些习惯。为了保持这一优异的状态，除了继续坚持已有的健康习惯之外，还可以考虑在现有的基础上探索新的健康领域。例如，尝试不同的运动形式，寻找新的营养食谱，或进一步了解心理健康的保养方法。同时，分享你的健康经验和知识，帮助他人也能改善他们的生活方式，不仅能给予他们极大的帮助，也能为你带来额外的满足感和幸福感。永远保持学习和探索的心态，让健康的生活方式成为持续的生命旅程。进一步强化这一状态，建议定期进行体检，密切关注身体的任何微小变化，并及时调整自己的健康计划。同时，通过参与社区活动、健康工作坊或是在线健康论坛，不仅能增加你的健康知识，也可拓宽社交圈，增进人际交往的同时促进心理健康。在维持身体健康的同时，不要忘记培养正面的心理态度，学习应对生活压力的策略，如正念冥想、情绪管理技巧等。一个全面发展、均衡养护的健康生活态度，将使你在健康的道路上越走越远，享受生活每一个美好瞬间。';
        }
        physicalReport.append(physicalComment);
    }
}