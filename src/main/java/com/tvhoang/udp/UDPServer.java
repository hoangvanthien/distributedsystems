package com.tvhoang.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalTime;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        DatagramSocket socket = new DatagramSocket(9876);

        while (true) {
            byte[] receiveData = new byte[1024], sendData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String query = new String (receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            if (query.equals("Day")) {
                sendData = LocalDate.now().toString().getBytes();
            } else if (query.equals("Time")) {
                sendData = LocalTime.now().toString().getBytes();
            } else {
                sendData = "403".getBytes();
            }
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            socket.send(sendPacket);
        }
    }
}
