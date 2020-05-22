package com.tvhoang.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    static int serverPort = 5000;
    static ServerSocket socket;

    public static void main(String[] args) throws IOException {
        socket = new ServerSocket(serverPort);
        System.out.println("Server is listening...");
        while (true) {
            Socket client = socket.accept();
            (new TCPServerThread(client)).start();
        }
    }
}
