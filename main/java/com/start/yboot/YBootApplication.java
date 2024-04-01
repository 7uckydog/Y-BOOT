package com.start.yboot;

import com.start.yboot.common.tcp.TcpClient;
import com.start.yboot.notepad.NotePadComponent;
import com.start.yboot.notepad.NotePadResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class YBootApplication {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NotePadComponent component;

    @Autowired
    private TcpClient client;
    public static void main(String[] args) {
        SpringApplication.run(YBootApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        logger.info("onApplicationReadyEvent Run");

        try {
            /* json 데이터를 이용하여 데이터 추출 */
            List<NotePadResultDTO> totalList = component.run();

            /* 추출한 데이터 send */
            for(int i = 0; i < totalList.size(); i++){
                NotePadResultDTO vo = totalList.get(i);
                client.send( "******************************************" + "\n"
                            + "[" + vo.getLocation() + " " + vo.getRank_name() + " 발령] " + "\n"
                            + "- 시간: " + vo.getInfo_date() + "시 ~ " + vo.getAf_info_date() + "시" + "\n"
                            + "- 내용: " + vo.getRank_info() + "\n"
                            + "******************************************"
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

