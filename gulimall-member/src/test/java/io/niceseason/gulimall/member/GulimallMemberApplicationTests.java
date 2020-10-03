package io.niceseason.gulimall.member;

import io.niceseason.gulimall.member.entity.MemberEntity;
import io.niceseason.gulimall.member.service.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallMemberApplicationTests {

    @Autowired
    private MemberService memberService;

    @Test
    void contextLoads() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail("test@gmail.com");
        memberEntity.setUsername("小胜");
        memberService.save(memberEntity);
    }

}
