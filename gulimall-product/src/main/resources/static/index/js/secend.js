$(window).scroll(function(event) {
  var hi = $(document).scrollTop();
  console.log(hi)
})
var myswiper = new Swiper(".swiper_section_second_list_left", {
  prevButton: '.swiper-button-prev',
  nextButton: '.swiper-button-next',
  loop: true
})
var myswiper2 = new Swiper(".swiper_section_find_center_list", {
  prevButton: '.swiper-button-prev',
  nextButton: '.swiper-button-next',
  loop: true,
  autoplay: 3000,
  pagination: ".swiper-pagination",
  effect: 'fade'
})
var myswiper3 = new Swiper(".swiper_section_ling_right_list", {
  prevButton: '.swiper-button-prev',
  nextButton: '.swiper-button-next',
  loop: true,
  autoplay: 3000,
  pagination: ".swiper-pagination",
  effect: 'fade'

})
// 倒计时
setTimeout(function() {
  var cha = 4 * 1000 * 3600;

  function time() {
    cha = cha - 1000;
    var hours = parseInt(cha / 1000 / 3600) % 24;
    var minutes = parseInt(cha / 1000 / 60) % 60;
    var seconds = parseInt(cha / 1000) % 60;
    if(hours < 10) {
      hours = "0" + hours
    }
    if(minutes < 10) {
      minutes = "0" + minutes
    }
    if(seconds < 10) {
      seconds = "0" + seconds
    }
    $(".section_second_header_right_hours").html(hours);
    $(".section_second_header_right_minutes").html(minutes);
    $(".section_second_header_right_second").html(seconds);
  }
  setInterval(time, 1000)
}, 1)
//秒杀图片上移 文字变红
$(".swiper_section_second_list_left li p").mouseover(function() {
  $(this).css("color", "#F90013");

  $(this).prev().stop().animate({
    marginTop: "0px",
    marginBottom: "8px",

  }, 500)
}).mouseout(function() {
  $(this).css("color", "#999")
  $(this).prev().stop().animate({
    marginTop: "8px",
    marginBottom: "0px",

  }, 500)
})
$(".swiper_section_second_list_left li img").mouseover(function() {
  $(this).next().css("color", "#F90013");

  $(this).stop().animate({
    marginTop: "0px",
    marginBottom: "8px",

  }, 500)
}).mouseout(function() {
  $(this).next().css("color", "#999")
  $(this).stop().animate({
    marginTop: "8px",
    marginBottom: "0px",

  }, 500)
})
//秒杀左侧轮播按钮
$(".swiper_section_second_list_left").mouseover(function() {
  $(".second_list").css("display", "block")
}).mouseout(function() {
  $(".second_list").css("display", "none")
})
//发现center轮播按钮
$(".swiper_section_find_center_list").mouseover(function() {
  $(".center_list").css("display", "block")
  console.log("aaa")
}).mouseout(function() {
  $(".center_list").css("display", "none")
  console.log("bbb")

})
//觅me轮播按钮

$(".swiper_section_ling_right_list").mouseover(function() {
  $(".right_list1").css("display", "block")
  $(".right_list2").css("display", "block")
  console.log("aaa")
}).mouseout(function() {
  $(".right_list1").css("display", "none")
  $(".right_list2").css("display", "none")
  console.log("bbb")

})
//秒杀右侧图片小轮播
$(".section_second_list_right_button p").mouseover(function() {
  $(".section_second_list_right_button p").removeClass('section_second_list_right_button_active')
  $(this).addClass("section_second_list_right_button_active")
  console.log($(this).index());
  var other = $(this).siblings().index()
  $(".section_second_list_right li").eq(other).animate({
    opacity: 0
  }, 1)
  $(".section_second_list_right li").eq($(this).index()).animate({
    opacity: 1
  }, 200)
})
//寻找图片左移
$(".section_find_left_list ul li").mouseover(function() {
  $(this).children('img').stop().animate({
    right: "20px"

  }, 300)
}).mouseout(function() {
  $(this).children('img').stop().animate({
    right: "10px"
  }, 300)
})
//领券中心图片右移
$(".section_ling_left_list ul li").mouseover(function() {
  $(this).children('img').stop().animate({
    left: "55px"
  }, 400)

}).mouseout(function() {
  $(this).children('img').stop().animate({
    left: "40px"
  }, 400)
})
//排行榜选项卡
$(".section_find_right_list_ul li").mouseover(function() {
  var a = ($(this).index() - 1) * 78 + 10

  $(".section_find_right_list_ul li").children('ol').removeClass("active")
  $(this).children('ol').addClass("active")
  $(".xiahua").stop().animate({
    left: a + "px"
  }, 300)
})
