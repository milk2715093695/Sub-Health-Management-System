function calculateScore() {
    const elements = document.querySelectorAll('#quiz input[type="radio"]:checked');
    let score = 0;
    elements.forEach(element => {
        score += parseInt(element.value);
    })
    console.log(score);
    return score;
}