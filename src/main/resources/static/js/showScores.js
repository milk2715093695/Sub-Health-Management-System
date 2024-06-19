function setWidth(element, score) {
    element.style.setProperty('--target-width', `${score}%`);
    element.setAttribute('data', `${score}分`);
}

let physic = document.querySelector('.physical-score');
let mental = document.querySelector('.mental-score');
let ill = document.querySelector('.ill-score');
let total = document.querySelector('.total-score');

fetch('/result')
    .then(response => response.json())
    .then(data => {
        if (!data.success) {
            let language = localStorage.getItem('language') || 'zh-CN';
            alert(data.errMessage[language]);

            setWidth(physic, 0);
            setWidth(mental, 0);
            setWidth(ill, 0);
            setWidth(total, 0);
        } else {
            let scores = data.survey;

            let physicalScore = scores.healthScore;
            let mentalScore = scores.mentalScore;
            let illScore = scores.riskScore;
            let totalScore = Math.round((physicalScore + mentalScore + illScore) / 3);

            setWidth(physic, physicalScore);
            setWidth(mental, mentalScore);
            setWidth(ill, illScore);
            setWidth(total, totalScore);
        }

        showPhysicalScoreComment();
        showMentalScoreComment();
        showIllScoreComment();
        showTotalScoreComment();
    })

