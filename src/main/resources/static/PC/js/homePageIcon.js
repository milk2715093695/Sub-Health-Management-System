let items1 = document.getElementsByClassName('ik-item');
for (let i = 0; i < items1.length; i++){
    items1[i].addEventListener('mouseover',function (){
        let items2 = document.getElementsByClassName('ik-bg');
        let items3 = document.getElementsByClassName('btn1 btn-link');

        items2[i].style.opacity = '1';
        items3[i].style.opacity = '1';

    });
    items1[i].addEventListener('mouseout',function (){
        let items2 = document.getElementsByClassName('ik-bg');
        let items3 = document.getElementsByClassName('btn1 btn-link');

        items2[i].style.opacity = '0';
        items3[i].style.opacity = '0';

    });
}
