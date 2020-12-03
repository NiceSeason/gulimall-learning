
$(".aibaobao_pt .pt_loge").hover(function(){
    $(this).children(".jiant").show()
},function(){
$(this).children(".jiant").hide()
})
    $(".left").click(function() {
      $(this).css('color', 'red');
      $(this).parent().prev("ul").animate({
        left: "0px"
      }, 600)
    })
    $(".ringth").click(function() {
      $(this).parent().prev("ul").animate({
        left: "-570px"
      }, 600)
    })
    $(".left").hover(function(){
        $(this).css('color', '#c81623');
    },function(){
    $(this).css('color', '#736B6E');
    })
    $(".ringth").hover(function(){
        $(this).css('color', '#c81623');
    },function(){
    $(this).css('color', '#736B6E');
    })
