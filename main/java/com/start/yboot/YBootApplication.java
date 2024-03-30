package com.start.yboot;

import com.start.yboot.notepad.NotePadComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class YBootApplication {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NotePadComponent component;

    // TODO : Tcp Server & Client 생성

    public static void main(String[] args) {
        SpringApplication.run(YBootApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        logger.info("onApplicationReadyEvent Run");
        try {
            component.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO : 데이터 정리 및 발령 정보 조건 확인 후 TCP 통신

    }
}

