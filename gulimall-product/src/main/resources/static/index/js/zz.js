//左右箭头显示、隐藏
  //电脑数码
  $(".section_dnsm_gun").hover(function() {
    $(this).children().children('.section_dnsm_you').stop().show()
    $(this).children().children('.section_dnsm_zuo').stop().show()
  }, function() {
    $(this).children().children('.section_dnsm_you').stop().hide()
    $(this).children().children('.section_dnsm_zuo').stop().hide()
  });
  //玩3c
  $(".section_w3c_gun").hover(function() {
    $(this).children().children('.section_w3c_you').stop().show()
    $(this).children().children('.section_w3c_zuo').stop().show()
  }, function() {
    $(this).children().children('.section_w3c_you').stop().hide()
    $(this).children().children('.section_w3c_zuo').stop().hide()
  });
  //爱运动
  $(".section_ayd_gun").hover(function() {
    $(this).children().children('.section_ayd_you').stop().show()
    $(this).children().children('.section_ayd_zuo').stop().show()
  }, function() {
    $(this).children().children('.section_ayd_you').stop().hide()
    $(this).children().children('.section_ayd_zuo').stop().hide()
  });
  //爱吃
  $(".section_ac_gun").hover(function() {
    $(this).children().children('.section_ac_you').stop().show()
    $(this).children().children('.section_ac_zuo').stop().show()
  }, function() {
    $(this).children().children('.section_ac_you').stop().hide()
    $(this).children().children('.section_ac_zuo').stop().hide()
  });


//左右箭头变色
  $(".section_dnsm_zuo,.section_w3c_zuo,.section_ayd_zuo,.section_ac_zuo").hover(function() {
    $(this).children('img').attr('src', './img/left-active.png');
  }, function() {
    $(this).children('img').attr('src', './img/left.png');
  });
  $(".section_dnsm_you,.section_w3c_you,.section_ayd_you,.section_ac_you").hover(function() {
    $(this).children('img').attr('src', './img/right-active.png');
  }, function() {
    $(this).children('img').attr('src', './img/right.png');
  });



//左右滑动
  //电脑数码
  $(".section_dnsm_zuo").click(function() {
    // $(".section_dnsm_xian ul").animate({"left":"+=1140px"},600);
    $(".section_dnsm_xian ul").animate({"left":"-1140px"},600);
  });
  $(".section_dnsm_you").click(function() {
    $(".section_dnsm_xian ul").animate({"left":"-2280px"},600);
  });
  //爱吃
  $(".section_ac_zuo").click(function() {
        $(".section_ac_xian ul").animate({"left":"-1140px"},600)
  });
  $(".section_ac_you").click(function() {
        $(".section_ac_xian ul").animate({"left":"-2280px"},600);
  });
  //玩3c
  $(".section_w3c_zuo").click(function() {
    $(".section_w3c_xian ul").animate({"left":"-570px"}, 600);
  });
  $(".section_w3c_you").click(function() {
    $(".section_w3c_xian ul").animate({"left":"-1140"}, 600);
  });
  //爱运动
  $(".section_ayd_zuo").click(function() {
    $(".section_ayd_xian ul").animate({"left":"-570px"}, 600);
  });
  $(".section_ayd_you").click(function() {
    $(".section_ayd_xian ul").animate({"left":"-1140px"}, 600);
  });

//图片滑动效果
  //电脑数码
  $(".section_dnsm_left>div:first-child").hover(function(){
    $(".section_dnsm_left>div:first-child>img").stop().animate({
      left:"-10px"
    },300)
  },function(){
    $(".section_dnsm_left>div:first-child>img").stop().animate({
      left:0
    },300)
  })
  $(".section_dnsm_right>div:first-child").hover(function(){
    $(".section_dnsm_right>div:first-child>img").stop().animate({
      left:"-10px"
    },300)
  },function(){
    $(".section_dnsm_right>div:first-child>img").stop().animate({
      left:0
    },300)
  })
  //玩3C
  $(".section_w3c_left>div:first-child").hover(function(){
    $(".section_w3c_left>.section_w3c_er>img").stop().animate({
      left:"-10px"
    },300)
  },function(){
    $(".section_w3c_left>.section_w3c_er>img").stop().animate({
      left:0
    },300)
  })
  //爱运动
  $(".section_ayd_left div:first-child").hover(function(){
    $(".section_ayd_left div:first-child img").stop().animate({
      left:"-10px"
    },300)
  },function(){
    $(".section_ayd_left div:first-child img").stop().animate({
      left:0
    },300)
  })
  //爱吃
  $(".section_ac_left div:first-child").hover(function(){
    $(".section_ac_left div:first-child img").stop().animate({
      left:"-10px"
    },300)
  },function(){
    $(".section_ac_left div:first-child img").stop().animate({
      left:0
    },300)
  })
  $(".section_ac_right div:not(:last-child)").hover(function(){
    $(this).children('img').stop().animate({
      left:"-10px"
    },300)
  },function(){
    $(this).children('img').stop().animate({
      left:0
    },300)
  })


//小图滑动效果
  //电脑数码
  $(".section_dnsm_box li").hover(function() {
    $(this).children().children('.section_dnsm_tu').children('img').stop().animate({
      left:"-5px"
    }, 300)
  }, function() {
    $(this).children().children('.section_dnsm_tu').children('img').stop().animate({
      left:0
    }, 300)
  });
  $(".section_dnsm_di a").hover(function() {
    $(this).children("img").stop().animate({
      left:"-10px"
    }, 300)
  }, function() {
    $(this).children("img").stop().animate({
      left:0
    }, 300)
  });
  //玩3c
  $(".section_w3c_box li").hover(function() {
    $(this).children().children('.section_w3c_tu').children('img').stop().animate({
      left:"-5px"
    }, 300)
  }, function() {
    $(this).children().children('.section_w3c_tu').children('img').stop().animate({
      left:0
    }, 300)
  });
  $(".section_w3c_di a").hover(function() {
    $(this).children("img").stop().animate({
      left:"-10px"
    }, 300)
  }, function() {
    $(this).children("img").stop().animate({
      left:0
    }, 300)
  });
  //爱运动
  $(".section_ayd_box li").hover(function() {
    $(this).children().children('.section_ayd_tu').children('img').stop().animate({
      left:"-5px"
    }, 300)
  }, function() {
    $(this).children().children('.section_ayd_tu').children('img').stop().animate({
      left:0
    }, 300)
  });
  $(".section_ayd_di a").hover(function() {
    $(this).children("img").stop().animate({
      left:"-10px"
    }, 300)
  }, function() {
    $(this).children("img").stop().animate({
      left:0
    }, 300)
  });
  //爱吃
  $(".section_ac_box li").hover(function() {
    $(this).children().children('.section_ac_tu').children('img').stop().animate({
      left:"-5px"
    }, 300)
  }, function() {
    $(this).children().children('.section_ac_tu').children('img').stop().animate({
      left:0
    }, 300)
  });
  $(".section_ac_di a").hover(function() {
    $(this).children("img").stop().animate({
      left:"-10px"
    }, 300)
  }, function() {
    $(this).children("img").stop().animate({
      left:0
    }, 300)
  });
