function setWidth(element, score) {
    element.style.setProperty('--target-width', `${score}%`);
    element.setAttribute('data-percentage', `${score}分`);
}

let physicalScore = document.querySelector('.physical-score');
let mentalScore = document.querySelector('.mental-score');
let illScore = document.querySelector('.ill-score');
let totalScore = document.querySelector('.total-score');

setWidth(physicalScore, 70);
setWidth(mentalScore, 85);
setWidth(illScore, 40);
setWidth(totalScore, 40);