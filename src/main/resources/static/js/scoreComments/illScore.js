function showIllScoreComment() {
    let illReport = document.querySelector('.ill-report');
    let illComment = illReport.querySelector('p');
    let score = parseInt(document.querySelector('.ill-score').getAttribute('data'));

    if (score >= 0) {
        if (score <= 20) {
            illReport.textContent = '风险情况：高风险状态';
            illComment.textContent = '处于这一得分区间意味着你的健康状况可能正处在一个高风险的状态。这可能是由于一系列不良的生活习惯所造成，如不合理的饮食、缺乏足够的身体活动、过度的压力以及可能的不良嗜好（如吸烟和饮酒）。这个分数提示着你可能正在面对较高的慢性病风险，如心血管疾病、糖尿病、高血压等。为了改善你的健康状况，强烈建议立即采取积极的预防措施。首先，进行一次全面的体检，以准确了解当前的健康状况，并与医疗专业人士讨论可能的健康改善方案。此外，制定一个合理的饮食计划，优化你的营养摄入，同时确保每天有足够的身体活动。这些改变虽然可能在初始阶段感觉艰难，但持之以恒，必将为你的健康带来正面的影响。';
        } else if (score <= 40) {
            illReport.textContent = '风险情况：中高风险';
            illComment.textContent = '这一得分范围显示，你的健康状况可能处在中到高级的风险状态之中。你可能已经感受到了一些与健康相关的警示信号，如体重变化、间歇性的身体不适等。这个阶段，关注并及时应对潜在的健康问题变得尤为重要。定期的健康筛查能帮助你及早发现任何可能的健康风险，并采取适当的措施。同时，重新审视你的生活习惯：营养是否均衡？每周是否有规律的身体锻炼？压力是否得到了有效管理？通过对不健康习惯的改善，你不仅能够降低患病风险，同时还能提高生活的质量。始终记得，健康的生活方式对于预防疾病具有不可替代的重要性。';
        } else if (score <= 60) {
            illReport.textContent = '风险情况：中等风险';
            illComment.textContent = '在这个分数区间意味着你的疾病风险处于中等水平。虽然你的一些生活习惯可能已经相对健康，但仍有改善空间以进一步降低健康风险。在此基础上，持续关注并优化你的饮食结构，确保获得充足而均衡的营养摄入，特别是增加蔬菜和水果的比例，减少过多的糖分和脂肪摄入。同时，保持定期而多样化的身体活动，如结合有氧运动和力量训练。此外，保持良好的心理状态，有效管理压力，保证足够的休息和睡眠，对于维持健康状态同样重要。这些看似简单的习惯，若能长期坚持，将对你的整体健康产生深远的积极影响';
        } else if (score <= 80) {
            illReport.textContent = '风险情况：低风险';
            illComment.textContent = '拥有这一得分区间的你，已立足于较为健康的生活基础之上，处在相对低的患病风险状态。这表明你在生活中已经注重健康，比如规律的饮食和锻炼，合理的作息，以及有效的压力管理等。但是，健康的维持与提升是一个持续的过程。进一步增强健康意识，比如尝试更多样化的身体活动，加入瑜伽或泳池等新的健身活动，可以为你的身体带来新的刺激和改善。在饮食方面，尝试新的健康食谱，增加全谷类、蔬菜、水果和脂肪含量低的蛋白质来源。此外，良好的心理健康也是整体健康不可或缺的一部分，保持积极乐观的心态，定期进行心理健康的自我检查，必要时寻求专业心理辅导。保持开放的心态，愿意尝试、改变，将有助于你在保持健康方面迈向更高的层次。';
        } else if (score <= 100) {
            illReport.textContent = '风险情况：极低风险';
            illComment.textContent = '达到这一得分区间，恭喜你，你已经展现了极强的健康管理能力，处在极低的患病风险水平。这代表着你不仅已经建立和维护了良好的健康习惯，而且你对待健康的态度是积极而主动的。为了维持这一优秀的状态，持续的努力和创新是关键。考虑进一步深化你的饮食知识，了解和尝试基于证据的新营养趋势，如适时进食的好处。持续探索不同的运动项目，以找到适合自己的最佳组合，同时也确保身心的全面发展。别忘了，偶尔跳出常规，尝试新的体验，不仅对身体有益，也能带来心理上的愉悦和满足。继续保持并加强与医疗保健提供者的沟通，定期进行全面的体检，确保及时掌握自己的健康状况并对任何潜在的健康问题迅速反应。在享受你的健康生活的同时，也可以向他人分享你的经验和知识，成为周围人健康生活的榜样和启发者。';
        }
        illReport.append(illComment);
    }
}