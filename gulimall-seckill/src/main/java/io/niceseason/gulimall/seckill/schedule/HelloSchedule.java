package io.niceseason.gulimall.seckill.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
//@EnableAsync
//@EnableScheduling
@Component
public class HelloSchedule {

    @Scheduled(cron = "0 0 3 * * ?")
    @Async
//    @Scheduled(cron = "* * * * * ?")
    public void hello() throws InterruptedException {
        Thread.sleep(3000);
        log.info("hello...");
    }
}
