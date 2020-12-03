//下拉菜单
$(function(){
	$(".hd_wrap_left>.dorpdown").mouseover(function(){
		$(this).children(".dd").show();
		$(this).css("background", "#fff");
		
	}).mouseout(function(){
		$(this).children(".dd").hide();
		
		$(this).css("background", "#E3E4E5")
	    
	});	$(".hd_wrap_right>.hd_dj").mouseover(function(){
		$(this).children(".hd_dj_ol").show();
		$(this).css("background", "#fff");
	}).mouseout(function(){
		$(this).children(".hd_dj_ol").hide();
		$(this).css("background", "#E3E4E5");
	});
	$(".hd_wrap_right>.hd_kh").mouseover(function(){
		$(this).children(".hd_kh_ol").show();
		$(this).css("background", "#fff");
	}).mouseout(function(){
		$(this).children(".hd_kh_ol").hide();
		$(this).css("background", "#E3E4E5");
	});$(".hd_wrap_right>.hd_daohang").mouseover(function(){
		$(this).children(".hd_dh").show();
		$(this).css("background", "#fff");
	}).mouseout(function(){
		$(this).children(".hd_dh").hide();
		$(this).css("background", "#E3E4E5");
	});$(".hd_wrap_right>.hd_jing").mouseover(function(){
		$(this).children(".hd_jing_sj").show()
	}).mouseout(function(){
		$(this).children(".hd_jing_sj").hide()
	});$(".nav_top_three").mouseover(function(){
		$.get("/miniCart",function (data) {
            $(".dorpdown-layer").remove();
			$(".nav_top_three").append(data);

        })

        // $(this).children(".dorpdown-layer").show()
        // $(this).css("background", "#fff");
	}).mouseout(function(){
		$(this).children(".dorpdown-layer").hide()
	});

$(".nav_down_ul_1").mouseover(function(){
	$(this).children(".nav_down_ul_ol").show();
}).mouseout(function(){
	$(this).children(".nav_down_ul_ol").hide();
})
$(".li-1").mouseover(function(){
	$(this).children("#fen").show();
}).mouseout(function(){
	$(this).children("#fen").hide();
})
$(".list_goods>.list_cover>a").mouseover(function(){
	$(this).children(".a_ol").show();
}).mouseout(function(){
	$(this).children(".a_ol").hide();
})
$(".glyphicon-share").mouseover(function(){
	$(this).children(".wang").show();
}).mouseout(function(){
	$(this).children(".wang").hide();
})

});


  window.onload = function(){
	var mySwiper = new Swiper ('.banner1',{
    loop: true, 
    // 如果需要分页器
    pagination: '.swiper-pagination',  //小圆点显示
    paginationType:'custom',
	paginationCustomRender: function(swiper, current, total) {
		var _html = '';
		for(var i = 1; i <= total; i++) {
			if(current == i) {
				_html += '<li class="swiper-pagination-custom active llll">' + i + '</li>';
			} else {
				_html += '<li class="swiper-pagination-custom llll">' + i + '</li>';
			}
		}
		return _html; //返回所有的页码html
	}
  
  }) 
	//给每个页码绑定跳转的事件   
	$('.banner1').on('mouseover', 'li', function() {
		var index = this.innerHTML;
		mySwiper.slideTo(index, 500, false); //切换到第一个slide，速度为1秒
	})
	
	
	var Banner = new Swiper ('.banner',{
    loop: true, 
    // 如果需要分页器
    pagination: '.swiper-pagination',  //小圆点显示
    paginationType:'custom',
	paginationCustomRender: function(swiper, current, total) {
		var _html = '';
		for(var i = 1; i <= total; i++) {
			if(current == i) {
				_html += '<li class="swiper-pagination-custom active la">' + i + '</li>';
			} else {
				_html += '<li class="swiper-pagination-custom la">' + i + '</li>';
			}
		}
		return _html; //返回所有的页码html
	}
  
  }) 
	//给每个页码绑定跳转的事件   
	$('.banner').on('mouseover', 'li', function() {
		var index = this.innerHTML;
		Banner.slideTo(index, 500, false); //切换到第一个slide，速度为1秒
	})

	
}







