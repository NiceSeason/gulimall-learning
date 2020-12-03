


$(".nav_ul .li").removeClass("hover")
$(".nav_ul .li").hover(function(){
  $(this).children("ol").stop().show()
  $(this).css({"color":"black","background":"white","border":"1px solid #ccc"})
  $(this).addClass("hover")
},function(){
  $(this).children("ol").stop().hide()
  $(this).css({"color":"white","background":"#E2231A","border":"none"})
  $(this).removeClass("hover")
})
$(".nav_ul .li ol li").hover(function(){
  $(this).css({"background":"#F3F3F3","color":"#E43953"})
},function(){
  $(this).css({"background":"#fff","color":"#000"})
})



$("#nav .right .shop>li").hover(function(){
  $(this).children("ol").stop().show()
},function(){
  $(this).children("ol").stop().hide()
})

$(".details .detailsLiok").hover(function(){
  $(this).children("ol").stop().show();
  $(this).css({"border":"1px solid #ccc","border-bottom":"none","background":"white"});
  $(this).children("i").addClass("allBig_i1").removeClass("allBig_i")
},function(){
  $(this).children("ol").stop().hide()
  $(this).css({"border":"none","background":"#F3F3F3"})
  $(this).children("i").addClass("allBig_i").removeClass("allBig_i1")
})

$(".details .detailsUl>li ol li").hover(function(){
    $(this).css({"background":"#F3F3F3","color":"#E43953"})

},function(){
    $(this).css({"background":"#fff","color":"#000"})
})

$(".details .detailsUlDiv>li ol li").hover(function(){
    $(this).css({"background":"#F3F3F3","color":"#E43953"})
},function(){
    $(this).css({"background":"#fff","color":"#000"})
})


$(".table").hover(function(){
  $(this).find(".isShow").show()
},function(){
  $(this).find(".isShow").hide()
})

$(".table .isShow").hover(function(){
  $(this).addClass("table_i51").removeClass("table_i5")
},function(){
  $(this).addClass("table_i5").removeClass("table_i51")
})


$(".buy_btm dl .ab_operate ul .guanzhu").hover(function(){
  $(this).children(".buy_btm_i").addClass("buy_btm_i1_1").removeClass("buy_btm_i1")
},function(){
  $(this).children(".buy_btm_i").addClass("buy_btm_i1").removeClass("buy_btm_i1_1")
})

$(".buy_btm dl .ab_operate ul .zhan").hover(function(){
  $(this).children(".buy_btm_i0").addClass("buy_btm_i2_1").removeClass("buy_btm_i2")
},function(){
  $(this).children(".buy_btm_i0").addClass("buy_btm_i2").removeClass("buy_btm_i2_1")
})


$(".mySwiper .mySwiper_like ol .liList").mouseover(function(){
  var i = $(this).index();
  $('.mySwiper .mySwiper_like ol .liList').css({"background":"none","color":"#666"}).eq(i).css({"background":"#E4393C","color":"white"})
  $(".mySwiper ul li").hide().eq(i).stop().show()
})


$(".fixed .dog").hover(function(){
  $(this).children("img").attr("src","img/joygif_hover.gif")
},function(){
  $(this).children("img").attr("src","img/joygif.gif")
})


$(".fixed .teSe .test_li1").hover(function(){
  $(this).children("ol").show()
},function(){
  $(this).children("ol").hide()
})



$(document).bind("scroll",function(){
  var oTop = document.body.scrollTop || document.documentElement.scrollTop;
  // var oScreen=window.screen.width
  if(oTop>50){
    $(".teSe ul .test_li3").css({"display":"block"})
  }else{
    $(".teSe ul .test_li3").css({"display":"none"})
  }
})



$(".hide").hover(function(){
  $(this).children(".hi").stop().show();
},function(){
  $(this).children(".hi").stop().hide();
})
