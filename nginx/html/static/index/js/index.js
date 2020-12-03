
		var swiper = new Swiper(".banner", {
			loop: true,
			autoplay: 1000,
			nextButton: ".swiper-button-next",
			prevButton: ".swiper-button-prev",
			pagination: '.swiper-pagination',
			effect: 'fade',
			fade: {
				crossFade: false,
			},
		})
		var swiper1 = new Swiper(".banner1", {
			loop: true,
			nextButton: ".swiper-button-next",
			prevButton: ".swiper-button-prev",
		})
		$(".section_ash_content .section_ash_con_bottom .banner1").mousemove(function() {
			$(this).children(".swiper-button-next , .swiper-button-prev").css({"display": "block"})
		}).mouseleave(function() {
		    $(this).children(".swiper-button-next , .swiper-button-prev").css({"display": "none"})
		})
			$(".section_ash_content .section_ash_con_bottom .banner1 .swiper-button-next , .swiper-button-prev").mousemove(function() {
			$(this).css({"color": "#EC0110"})
		}).mouseleave(function() {
		    $(this).css({"color": "gray"})
		})
		

		$(".section_xpz_content_left img").hover(function() {
			$(this).stop(true).animate({
				"left": "-10px"
			}, 400)
		}, function() {
			$(this).stop(true).animate({
				"left": "10px"
			}, 400)

		})
		$(".xpz_right_bottom .right_bottom_left img").hover(function() {
			$(this).stop(true).animate({
				"left": "-10px"
			}, 400)
		}, function() {
			$(this).stop(true).animate({
				"left": 0
			}, 400)

		})
		$(".section_ash_con_top .con_top_left img").hover(function() {
			$(this).stop(true).animate({
				"left": "-5px"
			}, 400)
		}, function() {
			$(this).stop(true).animate({
				"left": "5px"
			}, 400)

		})
		$(".con_top_right .right_con_img ").hover(function() {
			$(this).stop(true).animate({
				"right": "5px"
			}, 400)
		}, function() {
			$(this).stop(true).animate({
				"right": "-5px"
			}, 400)

		})
		$(".xpz_right_bottom img").hover(function() {
			$(this).stop(true).animate({
				"left": "-5px"
			}, 400)
		}, function() {
			$(this).stop(true).animate({
				"left": "5px"
			}, 400)

		})
		$(".section_ash_center_img img").hover(function() {
			$(this).stop(true).animate({
				"left": "-5px"
			}, 400)
		}, function() {
			$(this).stop(true).animate({
				"left": "5px"
			}, 400)

		})
	

