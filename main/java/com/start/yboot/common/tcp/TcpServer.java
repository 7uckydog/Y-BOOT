package com.start.yboot.common.tcp;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class TcpServer {
    private static final Integer TCP_PORT = 8810;

    public static void main(String[] args){
        try {
            runServer();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void runServer() throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        // serReuseAddress 설정을 해줌 (TCP 접속을 닫은 후 일정시간동안 타임아웃 상태일 시 동일 포트를 바인딩 못하는데, 이를 가능하게 하는 옵션)
        serverSocket.setReuseAddress(true);

        // 소켓에 포트 바인딩
        serverSocket.bind(new InetSocketAddress(TCP_PORT));
        System.out.println("starting tcp server : "+ TCP_PORT);

        while(true) {
            // 서버에서 요청 accept 대기
            Socket socket = serverSocket.accept();
            receive(socket);
        }
    }

    private static void receive(Socket socket) throws Exception {
        byte[] recv = new byte[1024];

        InputStream in = socket.getInputStream();

        int readCnt = in.read(recv);
        System.out.println("read Length : " + readCnt);

        byte[] conv = Arrays.copyOf(recv,readCnt);

        System.out.println(new String(conv, StandardCharsets.UTF_8));
    }
}
