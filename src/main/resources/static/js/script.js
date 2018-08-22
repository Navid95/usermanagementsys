$(document).ready(function () {
    function menuscroll() {
        let navmenu = $('.nav-menu');
        if ($(window).scrollTop() > 50){
            navmenu.addClass('is-scrolling');
        }else {
            navmenu.removeClass('is-scrolling');
        }
    }
    menuscroll();
    $(window).on('scroll' , menuscroll);

    let sidenav = $('#mynavbar');
    sidenav.on('show.bs.collapse' , function () {
        $(this).parents('.nav-menu').addClass('menu-is-open');
    });

    sidenav.on('hid.bs.collapse' , function () {
        $(this).parents('.nav-menu').removeClass('menu-is-open');
    })

    $('#mynavbar .navbar-nav a').on('click' , function(e) {
        let target = $(this.hash);
        if(target.length) {
            e.preventDefault();
            $('html,body').animate({
                scrollTop : target.offset().top
            },1000)
        }
    })

});