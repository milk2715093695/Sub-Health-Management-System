package com.myapp.Service;


import com.myapp.Entity.Survey;
import com.myapp.util.DocxFileUtil;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 提供填写模板pdf的服务
 * @author milk
 */
@Service
public class FillDocxService {
    /**
     * 填写 Docx 模板文档
     *
     * @param survey 健康调查表对象，包含调查信息
     * @return 填写是否成功的布尔值，成功为 true，失败为 false
     * @throws IOException 如果操作 Docx 文件时发生 IO 异常
     */
    public Boolean fillDocx(Survey survey) throws IOException {
        DocxFileUtil docxFileUtil = new DocxFileUtil("health_report_template.docx", "filled_report.docx");

        if (survey == null || survey.getUsername() == null || survey.getUsername().isEmpty() || survey.getGender() == null || !(survey.getGender().equals("male") || survey.getGender().equals("female"))) return false;
        if (!docxFileUtil.modifyCellInTable(0, 3, 0, survey.getUsername())) return false;
        if (!docxFileUtil.modifyCellInTable(0, 3, 1, (survey.getGender().equals("male")) ? "男" : "女")) return false;

        Integer physicalScore = survey.getHealthScore();
        String physicalComment = getPhysicalComment(physicalScore);
        if (!docxFileUtil.modifyCellInTable(0, 3, 2, physicalScore.toString())) return false;
        if (!docxFileUtil.modifyCellInTable(0, 3, 3, physicalComment)) return false;

        Integer mentalScore = survey.getMentalScore();
        String mentalComment = getMentalComment(mentalScore);
        if (!docxFileUtil.modifyCellInTable(0, 3, 4, mentalScore.toString())) return false;
        if (!docxFileUtil.modifyCellInTable(0, 3, 5, mentalComment)) return false;

        Integer riskScore = survey.getRiskScore();
        String riskComment = getRiskComment(riskScore);
        if (!docxFileUtil.modifyCellInTable(0, 3, 6, riskScore.toString())) return false;
        if (!docxFileUtil.modifyCellInTable(0, 3, 7, riskComment)) return false;

        String totalComment = getTotalComment(physicalScore, mentalScore, riskScore);
        if (!docxFileUtil.modifyCellInTable(0, 4, 1, totalComment)) return false;

        docxFileUtil.close();
        return true;
    }

    /**
     * 根据身体健康评分获取评价内容
     *
     * @param physicalScore 身体健康评分
     * @return 身体健康评价内容
     */
    private static String getPhysicalComment(Integer physicalScore) {
        String physicalComment = "分数计算错误";
        if (physicalScore >= 0) {
            if (physicalScore <= 20) {
                physicalComment = "急需关注";
            } else if (physicalScore <= 40) {
                physicalComment = "改善空间大";
            } else if (physicalScore <= 60) {
                physicalComment = "中等水平";
            } else if (physicalScore <= 80) {
                physicalComment = "良好状态";
            } else if (physicalScore <= 100) {
                physicalComment = "极佳体质";
            }
        }
        return physicalComment;
    }

    /**
     * 根据心理健康评分获取评价内容
     *
     * @param mentalScore 心理健康评分
     * @return 心理健康评价内容
     */
    private static String getMentalComment(Integer mentalScore) {
        String mentalComment = "分数计算错误";
        if (mentalScore >= 0) {
            if (mentalScore <= 20) {
                mentalComment = "心理警戒";
            } else if (mentalScore <= 40) {
                mentalComment = "需关注";
            } else if (mentalScore <= 60) {
                mentalComment = "稳定但可改善";
            } else if (mentalScore <= 80) {
                mentalComment = "良好状态";
            } else if (mentalScore <= 100) {
                mentalComment = "非常健康";
            }
        }
        return mentalComment;
    }

    /**
     * 根据风险评估评分获取评价内容
     *
     * @param riskScore 风险评估评分
     * @return 风险评估评价内容
     */
    private static String getRiskComment(Integer riskScore) {
        String riskComment = "分数计算错误";
        if (riskScore >= 0) {
            if (riskScore <= 20) {
                riskComment = "高风险状态";
            } else if (riskScore <= 40) {
                riskComment = "中高风险";
            } else if (riskScore <= 60) {
                riskComment = "中等风险";
            } else if (riskScore <= 80) {
                riskComment = "低风险";
            } else if (riskScore <= 100) {
                riskComment = "极低风险";
            }
        }
        return riskComment;
    }

    /**
     * 根据身体健康、心理健康和风险评估评分计算总体评价内容
     *
     * @param physicalScore 身体健康评分
     * @param mentalScore   心理健康评分
     * @param riskScore     风险评估评分
     * @return 总体评价内容
     */
    private static String getTotalComment(Integer physicalScore, Integer mentalScore, Integer riskScore) {
        int totalScore = (physicalScore + mentalScore + riskScore) / 3;
        String totalComment = "评价生成失败";
        if (totalScore >= 0) {
            if (totalScore <= 20) {
                totalComment = "你的整体健康状况可能极度堪忧，迫切需要关注和干预。可能存在严重的生活方式问题、高度紧张的心理状态、或身体健康上的重大隐患。首先，强烈建议尽快寻求医疗专业人士的帮助，进行全面的健康评估，并根据医生的建议制定改善计划。此外，试图识别和改变可能的不健康习惯，如不良饮食、缺乏运动、过度工作或忽视心理健康。建立良好的生活习惯，包括均衡饮食、适量运动、充足睡眠和有效压力管理，是逐步改善健康的关键。心理支持同样重要，可以考虑加入支持小组或进行心理咨询，以帮助应对压力和焦虑。";
            } else if (totalScore <= 40) {
                totalComment = "你的整体健康状况存在明显的改善空间。可能面临一定程度的生活方式相关问题，如不健康饮食、缺乏足够运动或长时间精神压力。立即采取措施调整生活习惯至关重要。考虑改变饮食习惯，增加新鲜水果和蔬菜的摄入，减少加工食品和高糖食品的消费。定期进行体力活动，如快走、跑步或游泳，不仅有助于提高身体健康，还能有效减轻心理压力。确保每晚获得足够的睡眠，以及找到有效的方式管理日常压力，比如练习冥想、瑜伽或深呼吸。此外，保持积极的社会联系，并寻求必要时的专业健康指导。";
            } else if (totalScore <= 60) {
                totalComment = "你的整体健康处于一种基本水平，但仍有提升空间。你的日常生活可能已经包含了一些健康习惯，但也可能存在某些不利于健康的行为。此时，关键在于识别那些需要改进的领域，并采取适当措施。比如，如果你的饮食还不够健康，尝试进一步增加全谷物、蔬菜和优质蛋白质的摄入量；如果你不经常运动，那么开始规划每周固定的锻炼时间，逐渐将其融入生活。同时，找到应对压力的有效方式变得尤为重要，持续的心理压力可能损害身体健康。保持乐观的心态，积极面对生活中的挑战，对提升整体健康至关重要。";
            } else if (totalScore <= 80) {
                totalComment = "此分数显示出你的整体健康状况较好，你很可能已经建立起一套较为健康的生活方式和习惯。为进一步保持良好的健康状态，继续遵循健康的饮食原则，如食用多样化、营养丰富的食物，限制高糖、高脂肪食品的摄入。保持定期和多样化的运动习惯，每周至少150分钟的中等强度运动或75分钟的高强度运动可以带来显著的健康益处。不要忽视心理健康，保持良好的社交关系，学习压力管理技巧，以识别和缓解日常生活中的压力源。最后，定期进行健康检查，及时发现并解决潜在的健康问题。";
            } else if (totalScore <= 100) {
                totalComment = """
                        恭喜，你的分数代表着极佳的整体健康状况。这说明你不仅已经形成了良好的健康习惯，而且在长期的实践中成功地保持了这些习惯。为了维系这一高水准的健康状态，请继续坚持平衡饮食，确保摄入各种营养元素，包括丰富的蔬菜、水果、全谷物、瘦肉和健康脂肪。同时，保持规律的身体活动至关重要，每周至少进行150分钟的中等强度运动或75分钟的高强度运动，并结合肌肉增强活动。保证充足的高质量睡眠，为身体和心灵提供必要的休息和恢复时间。
                        心理健康是整体健康的关键组成部分。积极面对生活压力，培养抗压能力，乐观地看待生活中的挑战。通过有效的压力管理技巧，如冥想、深呼吸和放松训练，帮助自己保持平和的心态。同时，培养和维护良好的社交关系，与家人、朋友和社区保持密切联系，可以给你的心理健康带来额外的支持。
                        此外，建立定期健康检查的习惯，及时发现并管理任何潜在的健康问题，这样可以在健康隐患初期就进行干预。不断探索和尝试新的健康活动或饮食，保持好奇心和积极的生活态度，将有助于持续提升你的生活质量。在维持极佳健康状态的道路上，记得赞赏自己过去的努力，并为未来的目标制定计划。
                        """;
            }
        }
        return totalComment;
    }
}
