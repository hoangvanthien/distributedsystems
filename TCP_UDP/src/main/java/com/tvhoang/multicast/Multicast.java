package com.tvhoang.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Multicast {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // Define a MulticastSocket
        MulticastSocket ms = null;
        // Define IP group
        String ipGroup = "225.6.7.8";
        int port = 6789;
        InetAddress ia = InetAddress.getByName(ipGroup);
        ms = new MulticastSocket(port);
        // join the group
        ms.joinGroup(ia);
        String msg = "Hello World";
        byte[] buffer = msg.getBytes();
        DatagramPacket msgout = new DatagramPacket(buffer, buffer.length, ia, port);
        ms.send(msgout);

        for (int __ = 0; __ < 3; ++__){
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket msgin = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            ms.receive(msgin);

            System.out.println("Received: " + new String(msg.getBytes()) + " from " + msgin.getAddress() + ":" + msgin.getPort());
        }
        ms.leaveGroup(ia);
    }
}
