/*$(document).ready(function() {
    $("#mobile" ).click(function() {
        $(this).toggleClass('active');
        $(".main-menu").toggleClass('show');
    });
});*/

function toggleClass() {
    var element = document.getElementById("mobile");
    element.classList.toggle("active");

    var mainMenu = document.getElementById("main-menu");
    mainMenu.classList.toggle("show");
}

function confirm_delete() {
    return confirm('Are you sure that you want to delete?');
}
