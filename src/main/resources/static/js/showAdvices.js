fetch('/result')
    .then(response => response.json())
    .then(data => {
        if (!data.success) {
            let language = localStorage.getItem('language') || 'zh-CN';
            alert(data.errMessage[language]);
        } else {
            let scores = data.survey;
            let physicalScore = scores.healthScore;
            let mentalScore = scores.mentalScore;
            let riskScore = scores.riskScore;

            let dietDiv = document.querySelector('.diet-platform');
            dietDiv.innerHTML = '';
            if (physicalScore >= 0) {
                let dietHead = document.createElement('h2');
                let dietParagraphOne = document.createElement('p');
                let dietParagraphTwo = document.createElement('p');
                let dietParagraphThree = document.createElement('p');
                let dietParagraphFour = document.createElement('p');
                if (physicalScore <= 20) {
                    dietHead.innerText = '急需改善';
                    dietParagraphOne.innerText = '1.增加营养摄入：专注于整食，包括新鲜蔬菜、水果、全谷物和脂肪含量低的蛋白质来源，如鱼类、豆类和坚果。避免加工食品和高糖饮食。';
                    dietParagraphTwo.innerText = '2.定期进餐：保持规律的饮食习惯，避免长时间不吃导致的饥饿感。小而频繁的饮食有助于维持能量水平和血糖稳定。';
                    dietParagraphThree.innerText = '3.水分补充：保持良好的水分状态，每天至少饮用8杯（大约2升）水分，避免含糖饮料和过量的咖啡因。';
                    dietParagraphFour.innerText = '4.限制不健康食物：减少摄入快餐、油炸食物和高脂肪、高盐分食品，选择烹饪方式更健康的食物，如清蒸、煮和烤代替油炸。';
                } else if (physicalScore <= 40) {
                    dietHead.innerText = '有待提升';
                    dietParagraphOne.innerText = '1.均衡饮食：确保每餐都有蛋白质、碳水化合物和健康脂肪的平衡，避免单一饮食，增加食物多样性。';
                    dietParagraphTwo.innerText = '2.适量食用肉类：适量食用瘦肉，如鸡肉和火鸡肉，并增加鱼类的摄入，富含Omega-3的鱼类对心脏健康尤其有益。';
                    dietParagraphThree.innerText = '3.健康零食选择：选择健康的零食，如鲜果、未加糖的干果或少许的坚果，以替代垃圾食品和高糖零食。';
                    dietParagraphFour.innerText = '4.注意饮食纤维：提高膳食纤维的摄入量，包括全谷物、蔬菜和水果，以帮助消化和防止便秘。';
                } else if (physicalScore <= 60) {
                    dietHead.innerText = '中等水平';
                    dietParagraphOne.innerText = '1.精致糖分提醒：减少白糖和精制糖的摄入，也要注意隐藏在加工食品中的糖分。';
                    dietParagraphTwo.innerText = '2.健康烹饪法：避免使用大量的油、盐和糖来调味，尝试使用草药和香料来增添食物风味。';
                    dietParagraphThree.innerText = '3.五颜六色的蔬菜和水果：吃足够的蔬菜和水果，以保证充分摄入各种维生素、矿物质和抗氧化剂。'
                    dietParagraphFour.innerText = '4.关注脂肪质量：选择健康的脂肪来源，如橄榄油和其他植物油，以及鳄梨和坚果，而不是饱和脂肪和反式脂肪。'
                } else if (physicalScore <= 80) {
                    dietHead.innerText = '良好健康';
                    dietParagraphOne.innerText = '1.保持水果和蔬菜的新鲜度：尽可能选择时令和当地的水果与蔬菜，以获得最佳的营养价值。';
                    dietParagraphTwo.innerText = '2.膳食多样化：尝试新的食材和食谱，提高饮食的多样性，同时也可以探索不同文化的饮食方式。'
                    dietParagraphThree.innerText = '3.适度饮酒：如果饮酒，应该适度。过度饮酒会对健康产生不利影响。';
                    dietParagraphFour.innerText = '4.保持餐饮习惯的一致性：尽量在每天相同的时间进食，帮助身体建立一致的消化节奏。';
                } else if (physicalScore <= 100) {
                    dietHead.innerText = '极佳体质';
                    dietParagraphOne.innerText = '1.自制饮食：尽可能自己烹饪，控制食物的质量和成分，避免外出就餐时可能的过量油脂和添加剂。';
                    dietParagraphTwo.innerText = '2.植物基食物：增加植物基食品的比例，如豆类、全谷物和坚果，即使不是全素也好，植物性食物富含纤维和营养素。';
                    dietParagraphThree.innerText = '3.合理规划饮食：每周定期规划饮食，确保营养均衡且充满变化，避免忙碌时候的不健康选择。';
                    dietParagraphFour.innerText = '4.持续学习最新饮食信息：关注饮食和营养领域的最新研究和建议，根据科学指导调整饮食，不断优化自己的饮食计划。';
                }
                dietDiv.appendChild(dietHead);
                dietDiv.appendChild(dietParagraphOne);
                dietDiv.appendChild(dietParagraphTwo);
                dietDiv.appendChild(dietParagraphThree);
                dietDiv.appendChild(dietParagraphFour);
            }

            let exerciseDiv = document.querySelector('.exercise-platform');
            exerciseDiv.innerHTML = '';
            if (physicalScore >= 0) {
                let exerciseHead = document.createElement('h2');
                let exerciseParagraphOne = document.createElement('p');
                let exerciseParagraphTwo = document.createElement('p');
                let exerciseParagraphThree = document.createElement('p');
                if (physicalScore <= 20) {
                    exerciseHead.innerText = '急需激活';
                    exerciseParagraphOne.innerText = '1.缓慢起步：健康状况较差可能意味着不能马上进行高强度的锻炼。开始以低强度活动，如散步或温和的水中活动，每天累积至少30分钟。';
                    exerciseParagraphTwo.innerText = '2.日常活动增加：寻找增加身体活动的机会，如使用楼梯而非电梯，短途出行选择步行或骑自行车。';
                    exerciseParagraphThree.innerText = '3.一对一辅导：考虑聘请专业的健身教练或咨询物理治疗师，为你量身定制适宜的锻炼计划，并确保运动方式安全、正确。';
                } else if (physicalScore <= 40) {
                    exerciseHead.innerText = '渐进提升';
                    exerciseParagraphOne.innerText = '1.结合有氧和力量训练：在你的日常锻炼中加入有氧运动（如快速步行、慢跑、骑自行车）和基本的力量训练（如使用哑铃的简单举重）来提高心肺功能和增强肌肉。';
                    exerciseParagraphTwo.innerText = '2.固定运动计划：每周至少150分钟的中等强度运动或75分钟的高强度运动，力量训练每周至少2天。';
                    exerciseParagraphThree.innerText = '3.	伸展和柔韧性训练：在运动前后进行伸展，增强柔韧性，减少运动伤害的风险，如瑜伽和普拉提可以是很好的选择。';
                } else if (physicalScore <= 60) {
                    exerciseHead.innerText = '稳健发展';
                    exerciseParagraphOne.innerText = '1.多样化运动项目：尝试不同类型的运动以增加锻炼的趣味性，比如游泳、登山或团体运动，以发展不同的身体素质。';
                    exerciseParagraphTwo.innerText = '2.提高运动强度：渐进增加运动的强度和持续时间，尝试间歇训练或循环训练为锻炼增加挑战。';
                    exerciseParagraphThree.innerText = '3.参与社区运动：加入当地的运动俱乐部或参加运动活动，与他人一起运动可以增加锻炼的动力和持续性。';
                } else if (physicalScore <= 80) {
                    exerciseHead.innerText = '积极优化';
                    exerciseParagraphOne.innerText = '1.目标设定：根据当前的健康和体能状态设定具体可达成的运动目标，如跑步5公里或完成一次半程马拉松。';
                    exerciseParagraphTwo.innerText = '2.高强度间歇训练：将高强度间歇训练（HIIT）纳入你的锻炼计划中，以提高代谢率和身体适应性。';
                    exerciseParagraphThree.innerText = '3.持续监测与调整：利用健身手环或智能手表监测运动强度和身体反应，根据进展适时调整运动计划。';
                } else if (physicalScore <= 100) {
                    exerciseHead.innerText = '维持最佳状态';
                    exerciseParagraphOne.innerText = '1.技术提升：专注于提升运动技能和效率，比如通过专业教练改进游泳或跑步的技术。';
                    exerciseParagraphTwo.innerText = '2.挑战新极限：考虑尝试新的和更具挑战性的运动形式，如铁人三项或攀岩，以激发新的运动兴趣和潜能。';
                    exerciseParagraphThree.innerText = '3.运动恢复：给予身体充分的恢复时间，采取积极的恢复措施，如深层组织按摩、冷热交替浴或专业的体能恢复课程，以保持最佳的运动状态和预防过度训练综合症。';
                }
                exerciseDiv.appendChild(exerciseHead);
                exerciseDiv.appendChild(exerciseParagraphOne);
                exerciseDiv.appendChild(exerciseParagraphTwo);
                exerciseDiv.appendChild(exerciseParagraphThree);
            }

            let restDiv = document.querySelector('.rest-platform');
            restDiv.innerHTML = '';
            if (physicalScore >= 0) {
                let restHead = document.createElement('h2');
                let restParagraphOne = document.createElement('p');
                let restParagraphTwo = document.createElement('p');
                let restParagraphThree = document.createElement('p');
                if (physicalScore <= 20) {
                    restHead.innerText = '紧急调整';
                    restParagraphOne.innerText = '1.建立规律作息：制定严格的入睡和起床时间，即使在周末或假期也不做改变，帮助调节身体的生物钟。';
                    restParagraphTwo.innerText = '2.创建优良睡眠环境：确保卧室安静、黑暗、温度适宜，使用舒适的床垫和枕头。考虑使用白噪音机或耳塞来屏蔽干扰噪音。';
                    restParagraphThree.innerText = '3.夜间限制电子设备：至少在睡前一小时停止使用所有带屏幕的电子设备，减少蓝光对睡眠质量的负面影响。';
                } else if (physicalScore <= 40) {
                    restHead.innerText = '需要改善';
                    restParagraphOne.innerText = '1.优化傍晚活动：避免在睡前进行剧烈运动或消耗精力的智力活动，选择放松的活动，如阅读、瑜伽或轻松散步。';
                    restParagraphTwo.innerText = '2.管理晚餐和饮料：晚餐避免过饱或过度油腻食物，限制晚间咖啡因和酒精的摄入，避免影响睡眠质量。';
                    restParagraphThree.innerText = '3.建立睡前仪式：发展一套睡前仪式，如泡个热水澡、听轻柔音乐或冥想，帮助身心放松，准备进入睡眠状态。';
                } else if (physicalScore <= 60) {
                    restHead.innerText = '稳定中可优化';
                    restParagraphOne.innerText = '1.白天活动量：确保白天有足够的身体活动，如中等强度运动，以提高夜间睡眠质量。';
                    restParagraphTwo.innerText = '2.午睡管理：如果需要午睡，确保不超过20分钟，避免傍晚后的小憩，以免影响夜间睡眠。';
                    restParagraphThree.innerText = '3.学会压力管理：采取积极的压力管理方法，如日志记录、交流分享和时间管理，减轻压力，改善睡眠。';
                } else if (physicalScore <= 80) {
                    restHead.innerText = '持续优化';
                    restParagraphOne.innerText = '1.深化睡眠质量：尝试使用睡眠监测工具监测睡眠模式，深入了解REM睡眠和深睡期，进一步优化睡眠质量。';
                    restParagraphTwo.innerText = '2.环境微调：不断优化睡眠环境，考虑使用更适合个人的床上用品，如适宜的被褥和枕头材质等。';
                    restParagraphThree.innerText = '3.精细化作息计划：针对生物钟和能量高峰期，调整日间工作和休息的安排，提升日间效率，晚间保证充足休息。';
                } else if (physicalScore <= 100) {
                    restHead.innerText = '维持极佳状态';
                    restParagraphOne.innerText = '1.例行健康检查：定期进行健康检查，确保无潜在的生理问题影响睡眠，如睡眠呼吸暂停症或其他睡眠障碍。';
                    restParagraphTwo.innerText = '2.持续知识更新：关注睡眠科学的最新研究和建议，持续学习和实践，以维持或进一步提高睡眠质量。';
                    restParagraphThree.innerText = '3.传递良好习惯：分享自己维持良好作息和高质量睡眠的经验和方法，帮助周围的人也能改善他们的睡眠和生活质量。';
                }
                restDiv.appendChild(restHead);
                restDiv.appendChild(restParagraphOne);
                restDiv.appendChild(restParagraphTwo);
                restDiv.appendChild(restParagraphThree);
            }

            let mentalDiv = document.querySelector('.mental-platform');
            mentalDiv.innerHTML = '';
            if (mentalScore >= 0) {
                let mentalHead = document.createElement('h2');
                let mentalParagraphOne = document.createElement('p');
                let mentalParagraphTwo = document.createElement('p');
                let mentalParagraphThree = document.createElement('p');
                if (mentalScore <= 20) {
                    mentalHead.innerText = '深度关怀';
                    mentalParagraphOne.innerText = '1.专业支持：务必寻求心理健康专家的帮助，如心理医生或心理咨询师，进行专业咨询和/或治疗。及时的专业干预对心理复原与恢复至关重要。';
                    mentalParagraphTwo.innerText = '2.情感表达：找到安全可靠的方式和环境表达自己的情感，无论是通过写作、艺术创作或与信任的家人及朋友谈话，被倾听和理解是极其重要的心理需求。';
                    mentalParagraphThree.innerText = '3.日常放松：练习放松技术，如深呼吸、正念冥想和渐进性肌肉放松，帮助缓解心理压力，提高情绪管理能力。';
                } else if (mentalScore <= 40) {
                    mentalHead.innerText = '基础关注';
                    mentalParagraphOne.innerText = '1.建立日常结构：保持有规律的日常生活习惯，如定时就寝和醒来，健康饮食，定期锻炼，这有助于保持心理的平衡和稳定。';
                    mentalParagraphTwo.innerText = '2.社交参与：积极参与社交活动，与朋友和家人保持良好的互动，建立支持性的人际关系网，这对缓解孤独感、被排斥感有积极作用。';
                    mentalParagraphThree.innerText = '3.技能提升：学习和实践新技能或爱好，如摄影、绘画、乐器等，不仅能给生活带来乐趣，还能提升自我价值感和成就感。';
                } else if (mentalScore <= 60) {
                    mentalHead.innerText = '持续增进';
                    mentalParagraphOne.innerText = '1.时间管理：学会有效的时间管理技巧，合理安排工作和休息时间，避免过度劳累，确保有足够的自我放松和休息时间。';
                    mentalParagraphTwo.innerText = '2.积极心态：培养积极乐观的生活态度，面对困难时尝试看到挑战群后的机会，逐步学会从逆境中成长。';
                    mentalParagraphThree.innerText = '3.情绪记录：借助日记或情绪记录APP跟踪日常的情绪波动，分析情绪变化的原因，有助于更好地理解和管理自己的情绪。';
                } else if (mentalScore <= 80) {
                    mentalHead.innerText = '良好维持';
                    mentalParagraphOne.innerText = '1.感恩练习：定期进行感恩的练习，如每天列出三件当天感到感激的事情，帮助提升个人幸福感和满意度。';
                    mentalParagraphTwo.innerText = '2.正念实践：通过正念冥想等方式，提高当下意识，减少对过去或未来的消极忧虑，提升心理的宁静和满足感。';
                    mentalParagraphThree.innerText = '3.反思与成长：定期自我反思，识别个人成长的机会和领域，设定个人发展目标，并采取行动实现这些目标。';
                } else if (mentalScore <= 100) {
                    mentalHead.innerText = '极佳状态';
                    mentalParagraphOne.innerText = '1.心理健康倡导：利用自身积极的心理状态，为周围的人提供支持和鼓励，倡导健康的心理态度和生活方式。';
                    mentalParagraphTwo.innerText = '2.持续学习：不断学习新的心理健康知识和技能，保持好奇心，通过不断学习来丰富自己的生活经验和心理素养。';
                    mentalParagraphThree.innerText = '3.心理健康维护：即使在心理状态极佳的情况下，也要继续定期进行自我检查，保持实践良好的心理健康习惯，如持续的自我关怀、情绪管理和积极的人际交往。';
                }
                mentalDiv.appendChild(mentalHead);
                mentalDiv.appendChild(mentalParagraphOne);
                mentalDiv.appendChild(mentalParagraphTwo);
                mentalDiv.appendChild(mentalParagraphThree);
            }

            let doctorDiv = document.querySelector('.doctor-platform');
            doctorDiv.innerHTML = '';
            if (riskScore >= 0) {
                let doctorHead = document.createElement('h2');
                let doctorParagraphOne = document.createElement('p');
                let doctorParagraphTwo = document.createElement('p');
                let doctorParagraphThree = document.createElement('p');
                if (riskScore <= 20) {
                    doctorHead.innerText = '紧急行动';
                    doctorParagraphOne.innerText = '1.即刻就医：面对极高的健康风险，应立即寻求医疗帮助。不要犹豫，尽快预约见医生或直接前往医院的急诊部门。';
                    doctorParagraphTwo.innerText = '2.全面健康评估：要求进行全面的健康检查，包括血液检验、心脏功能测试和其他相关健康筛查，确保能及时发现并处理所有潜在的健康问题。';
                    doctorParagraphThree.innerText = '3.专业治疗计划：与医疗专业人员密切合作，制定一个全面的治疗和管理计划，包括必要的药物治疗、生活方式调整以及可能的康复计划。';
                } else if (riskScore <= 40) {
                    doctorHead.innerText = '重点关注';
                    doctorParagraphOne.innerText = '1.定期体检：确保定期进行体检，特别是针对检测出的风险因素进行深入筛查，如高血压、高血糖等慢性病的风险因子。';
                    doctorParagraphTwo.innerText = '2.健康咨询：寻求与医疗保健专家（如家庭医生、营养师和心理咨询师）的咨询，针对个人健康状况获取专业建议和指导。';
                    doctorParagraphThree.innerText = '3.预防重于治疗：主动采取预防措施，比如通过改善饮食习惯、增加身体活动和管理压力来减少健康风险。';
                } else if (riskScore <= 60) {
                    doctorHead.innerText = '稳健管理';
                    doctorParagraphOne.innerText = '1.生活方式改进：继续关注和改进生活方式，如均衡饮食、适度运动和充足睡眠，减少不良习惯，如吸烟和过度饮酒。';
                    doctorParagraphTwo.innerText = '2.自我监测：自我监测健康状况，如血压和血糖水平，特别是对于那些有慢性病风险的人群。';
                    doctorParagraphThree.innerText = '3.定期跟进：与医生定期跟进，即使没有明显的健康问题，也要确保定期检查和评估，以便及时调整健康管理方案。';
                } else if (riskScore <= 80) {
                    doctorHead.innerText = '持续优化';
                    doctorParagraphOne.innerText = '1.健康维护：即使处于较低的风险水平，也仍需持续关注自己的健康，保持良好的生活习惯，定期进行健康检查。';
                    doctorParagraphTwo.innerText = '2.学习新知：了解最新的健康信息和预防策略，适时调整个人的健康行为和策略以适应新的医学研究发现。';
                    doctorParagraphThree.innerText = '3.心理健康：重视心理健康的维护，了解如何管理压力，必要时寻求心理健康支持和咨询。';
                } else if (riskScore <= 100) {
                    doctorHead.innerText = '健康典范';
                    doctorParagraphOne.innerText = '1.例行检查：保持进行例行的健康检查，尽管风险低但定期检查有助于早期识别任何潜在的健康问题。';
                    doctorParagraphTwo.innerText = '2.健康倡导：作为低风险群体的一员，可以通过分享个人的健康经验和积极的生活方式来激励和教育他人。';
                    doctorParagraphThree.innerText = '3.持续进步：在维持当前健康状况的基础上，继续寻求提升，比如通过增加运动量、尝试新的健康食谱或加强心理健康的练习，不断优化生活质量。';
                }
                doctorDiv.appendChild(doctorHead);
                doctorDiv.appendChild(doctorParagraphOne);
                doctorDiv.appendChild(doctorParagraphTwo);
                doctorDiv.appendChild(doctorParagraphThree);
            }
        }
    })
