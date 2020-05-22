package com.tvhoang.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TCPClient2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Client started.");
        Socket conn_socket = new Socket("localhost", 5000);
        PrintStream p = new PrintStream(conn_socket.getOutputStream());
        p.println("Time");

        Scanner in = new Scanner(conn_socket.getInputStream());
        System.out.println("From server: " + (in.hasNextLine() ? in.nextLine() : "404"));
        p.close();
        in.close();
        conn_socket.close();
    }
}
