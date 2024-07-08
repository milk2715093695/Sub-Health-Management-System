(function (){
    const inputElements = document.querySelectorAll('input');

    const radioInputs = Array.from(inputElements).filter(input => input.type === 'radio');

    radioInputs.forEach(input => {
        input.classList.add('cursor-pointer');
    })

    const label = document.querySelectorAll('label');
    label.forEach(label => label.classList.add('cursor-pointer'));
})()