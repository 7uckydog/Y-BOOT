package com.start.yboot.common.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Component
@EnableAutoConfiguration
public class TcpClient {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${tcp.server.host}")
    private String tcpHost;

    @Value("${tcp.server.port}")
    private Integer tcpPort;

    public void send(String data) throws Exception{
        logger.info("TCP HOST : " + tcpHost);
        logger.info("TCP PORT : " + tcpPort);
        logger.info("SEND DATA : " + data);
        Socket socket = new Socket(tcpHost, tcpPort);
        OutputStream output = socket.getOutputStream();
        output.write(data.getBytes(StandardCharsets.UTF_8));

        socket.close();
    }


}
