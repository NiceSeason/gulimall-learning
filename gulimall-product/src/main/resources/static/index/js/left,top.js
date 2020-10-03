$(window).scroll(function(event) {
  var hi= $(document).scrollTop();
   if(hi>825){
     $(".top_find").stop().animate({
       top:0
     },500)
   }else{
     $(".top_find").stop().animate({
       top:"-66px"
     },0)
   }
   if(hi>1850){
     $(".left_floor").stop().animate({
       opacity:1
     },300)
   }else{
     $(".left_floor").stop().animate({
       opacity:0
     },300)
   }
   //楼层滑动选中
   if(hi<2612){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_xiang").addClass('left_floor_active');
   }else if(hi>=2612&&hi<3207){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_fu").addClass('left_floor_active');
   }else if(hi>=3207&&hi<3742){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_jia").addClass('left_floor_active');
   }
   else if(hi>=3742&&hi<4280){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_dian").addClass('left_floor_active');
   }else if(hi>=4280&&hi<4832){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_3C").addClass('left_floor_active');
   }else if(hi>=4832&&hi<5398){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_ai").addClass('left_floor_active');
   }else if(hi>=5398&&hi<5932){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_mu").addClass('left_floor_active');
   }else if(hi>=5932&&hi<6442){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_tu").addClass('left_floor_active');
   }else if(hi>=6442&&hi<6977){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_you").addClass('left_floor_active');
   }else if(hi>=6977&&hi<7910){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_lv").addClass('left_floor_active');
   }else if(hi>7910){
     $(".left_floor li").removeClass("left_floor_active");
     $(".left_floor_hai").addClass('left_floor_active');
   }
})

//楼层点击选中
$(".left_floor li").click(function(){
  $(".left_floor li").removeClass("left_floor_active");
  $(this).addClass('left_floor_active');
  console.log($(this).index());
});
$(".left_floor li").mouseover(function(){
  $(this).addClass('left_floor_active1');
  console.log($(this).index());
}).mouseout(function(){
  $(this).removeClass('left_floor_active1');

});
//楼层点击滑动指定位置
$(".left_floor_xiang").click(function(){
  $("body,html").animate({
    scrollTop:1858,
  },500)

})
$(".left_floor_fu").click(function(){
  $("body,html").animate({
    scrollTop:2612,
  },500)

})
$(".left_floor_jia").click(function(){
  $("body,html").animate({
    scrollTop:3207,
  },500)

})
$(".left_floor_dian").click(function(){
  $("body,html").animate({
    scrollTop:3742,
  },500)

})
$(".left_floor_3C").click(function(){
  $("body,html").animate({
    scrollTop:4280,
  },500)

})
$(".left_floor_ai").click(function(){
  $("body,html").animate({
    scrollTop:4832,
  },500)

})
$(".left_floor_mu").click(function(){
  $("body,html").animate({
    scrollTop:5398,
  },500)

})
$(".left_floor_tu").click(function(){
  $("body,html").animate({
    scrollTop:5932,
  },500)

})
$(".left_floor_you").click(function(){
  $("body,html").animate({
    scrollTop:6442,
  },500)

})
$(".left_floor_lv").click(function(){
  $("body,html").animate({
    scrollTop:6977,
  },500)

})
$(".left_floor_hai").click(function(){
  $("body,html").animate({
    scrollTop:7917,
  },500)

})
$(".left_floor_ding").click(function(){
  $("body,html").animate({
    scrollTop:0,
  },500)

})
