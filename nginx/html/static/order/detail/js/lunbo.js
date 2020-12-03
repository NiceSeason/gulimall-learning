var swiper1 = new Swiper(".swiper1", {
	loop: true,
	pagination: '.swiper-pagination', //分页容器的css选择器
	paginationType: 'custom', //自定义-分页器样式类型（前提）
	//设置自定义分页器的内容
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
$('.swiper1').on('mouseover', 'li', function() {
	var index = this.innerHTML;
	swiper1.slideTo(index, 500, false); //切换到第一个slide，速度为1秒
})
var swiper2 = new Swiper(".swiper2", {
	loop: true,
	pagination: '.swiper-pagination', //分页容器的css选择器
	paginationType: 'custom', //自定义-分页器样式类型（前提）
	//设置自定义分页器的内容
	paginationCustomRender: function(swiper2, current, total) {
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
$('.swiper2').on('mouseover', 'li', function() {
	var index = this.innerHTML;
	swiper2.slideTo(index, 500, false); //切换到第一个slide，速度为1秒
})
var data=new Date();
$('#time').text(data.toLocaleDateString())
