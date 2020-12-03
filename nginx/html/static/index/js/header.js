$(".header_banner1").hover(function() {
	$(".header_banner1_div").stop(true).animate({
		width:"990px"
	},500)
}, function() {
	$(".header_banner1_div").stop(true).animate({
		width:"0"
	},300)
})
$(".head p").on("click", function() {
	$(".head").fadeOut(500)
})
$(".header_banner1_div p").on("click", function() {
	$(".header_banner1_div").stop(true).animate({
		width:"0"
	},200)
})
$(".header_ol a").hover(function() {
	$(this).css({
		color: "#c81623"
	})
}, function() {
	$(this).css({
		color: "#999"
	})
	$(".aaa").css({
		color: "#111"
	})
})
//轮播图
var swiper1 = new Swiper(".swiper1", {
	loop: true,
	autoplay: 2000,
	effect: 'fade',
	fade: {
		crossFade: false,
	},
	pagination: ".swiper-pagination",
	paginationClickable: true,
	prevButton: '.swiper-button-prev',
	nextButton: '.swiper-button-next',
	autoplayDisableOnInteraction: false,
})

//货品分类
$('.header_main_left>ul>li').hover(function() {
	$(this).css({
		background: "#989898"
	}).find('.header_main_left_main').stop(true).fadeIn(300)
}, function() {
	$(this).css({
		background: "#6e6568"
	}).find(".header_main_left_a").css({
		color: "#fff"
	})
	$(this).find('.header_main_left_main').stop(true).fadeOut(100)
})
$(".header_sj a").hover(function() {
	$(this).css({
		background: "#444"
	})
}, function() {
	$(this).css({
		background: "#6e6568"
	})
})
//购物车下拉
$('.header_gw').hover(function() {
	$(this).next('.header_ko').stop(true).fadeIn(100)
}, function() {
	$(this).next('.header_ko').stop(true).fadeOut(100)
})
//我的京东下拉
$(".header_wdjd").hover(function() {
	$(this).children(".header_wdjd_txt").stop(true).show(100)
	$(this).css({
		background: "#fff"
	})
}, function() {
	$(this).css({
		background: "#E3E4E5"
	})
	$(this).children(".header_wdjd_txt").stop(true).hide(100)
})
//地理位置下拉
$(".header_head_p").hover(function() {
	$(this).children(".header_head_p_cs").stop(true).show(100)
	$(this).css({
		background: "#fff"
	})
}, function() {
	$(this).css({
		background: "#E3E4E5"
	})
	$(this).children(".header_head_p_cs").stop(true).hide(100)
})
$(".header_head_p_cs a").hover(function(){
	$(this).css({background:"#f0f0f0"})
	$(".header_head_p_cs a:nth-child(1)").css({background:"#c81623"})
},function(){
	$(this).css({background:"#fff"})
	$(".header_head_p_cs a:nth-child(1)").css({background:"#c81623"})
})
//客户服务下拉
$(".header_wdjd1").hover(function() {
	$(this).children(".header_wdjd_txt").stop(true).show(100)
	$(this).css({
		background: "#fff"
	})
}, function() {
	$(this).css({
		background: "#E3E4E5"
	})
	$(this).children(".header_wdjd_txt").stop(true).hide(100)
})
//网站导航下拉
$(".header_wzdh").hover(function() {
	$(this).children(".header_wzdh_txt").stop(true).show(100)
	$(this).css({
		background: "#fff"
	})
}, function() {
	$(this).css({
		background: "#E3E4E5"
	})
	$(this).children(".header_wzdh_txt").stop(true).hide(100)
})
//促销公告选项卡
$(".header_new_t p").hover(function() {
	var i = $(this).index()
	$(".header_new_t p").removeClass("active").eq(i).addClass("active")
	$(".header_new_connter_1").hide().eq(i).show()
})
//话费机票
$(".ser_box_aaa_nav li").hover(function() {
	var i = $(this).index()
	$(".ser_box_aaa_nav li").removeClass("active").eq(i).addClass("active")
	$(".ser_ol_li").hide().eq(i).show()
})
$(".guanbi").on("click", function() {
	$(".ser_box_aaa .ser_box_aaa_one").stop(true).animate({
		top: "210px"
	},600)
})
$(".ser_box_item span").hover(function() {
	$(".ser_box_aaa .ser_box_aaa_one").css("display", "block")
	$(".ser_box_aaa .ser_box_aaa_one").stop(true).animate({
		top: "0"
	},600)
}, function() {

})
//右侧侧边栏
$(".header_bar_box ul li").hover(function() {
	$(this).css({
		background: "#7A6E6E",
		borderRadius: 0
	}).children(".div").css({
		display: "block"
	}).stop(true).animate({
		left: "-60px"
	}, 300)
}, function() {
	$(this).css({
		background: "#7A6E6E",
		borderRadius: 5
	}).children(".div").css({
		display: "none"
	}).stop(true).animate({
		left: "0"
	}, 300)
})
