// Vertical displacement for passenger_login_page
$(document).ready(function() {
    var height = $(window).height();
    var height_2 = $('.logdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.logdiv').css('margin-top', margin + "px");
})

$(window).on('resize', function() {
    var height = $(window).height();
    var height_2 = $('.logdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.logdiv').css('margin-top', margin + "px");
})

// Vertical displacement for passenger_register_page
$(document).ready(function() {
    var height = $(window).height();
    var height_2 = $('.regdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.regdiv').css('margin-top', margin + "px");
})

$(window).on('resize', function() {
    var height = $(window).height();
    var height_2 = $('.regdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.regdiv').css('margin-top', margin + "px");
})

// Vertical displacement for passenger_incorrect_login_page
$(document).ready(function() {
    var height = $(window).height();
    var height_2 = $('.incorrectlogdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.incorrectlogdiv').css('margin-top', margin + "px");
})

$(window).on('resize', function() {
    var height = $(window).height();
    var height_2 = $('.incorrectlogdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.incorrectlogdiv').css('margin-top', margin + "px");
})

// Vertical displacement for passenger_account_page
$(document).ready(function() {
    var height = $(window).height();
    var height_2 = $('.accountdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.accountdiv').css('margin-top', margin + "px");
})

$(window).on('resize', function() {
    var height = $(window).height();
    var height_2 = $('.accountdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.accountdiv').css('margin-top', margin + "px");
})

// Vertical displacement for admin_account_page
$(document).ready(function() {
    var height = $(window).height();
    var height_2 = $('.adminaccountdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.adminaccountdiv').css('margin-top', margin + "px");
})

$(window).on('resize', function() {
    var height = $(window).height();
    var height_2 = $('.adminaccountdiv').outerHeight();
    var margin = (height - height_2)/"2";
    $('.adminaccountdiv').css('margin-top', margin + "px");
})