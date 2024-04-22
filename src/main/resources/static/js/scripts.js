let navItems = document.querySelectorAll('.navList li');

for (let i = 0; i < navItems.length; i++) {
    navItems[i].addEventListener('mouseover', function() {
        this.classList.add('active');
    });
    navItems[i].addEventListener('mouseout', function() {
        this.classList.remove('active');
    });
}