// Vertical displacement for maindiv

$(document).ready(function() {
    var height = $(window).height();
    var height_2 = $('.maindiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.maindiv').css('margin-top', margin + "px");
})

$(window).on('resize', function() {
    var height = $(window).height();
    var height_2 = $('.maindiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.maindiv').css('margin-top', margin + "px");
})