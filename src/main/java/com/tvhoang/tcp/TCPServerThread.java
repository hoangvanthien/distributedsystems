package com.tvhoang.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TCPServerThread extends Thread {
    public TCPServerThread(Socket client) {
        this.client = client;
    }
    @Override
    public void run() {
        try (
            Scanner scanner = new Scanner(client.getInputStream());
            PrintStream p = new PrintStream(client.getOutputStream());)
        {
            System.out.println("Thread ID " + getId() + " started.");
            TimeUnit.SECONDS.sleep(5);
            long startTime = System.nanoTime();
            while (scanner.hasNextLine()) {
                String query = scanner.nextLine();
                System.out.println(query);
                if (query.equals("Day")) {
                    p.println(LocalDate.now());
                } else if (query.equals("Time")) {
                    p.println(LocalTime.now());
                } else if (query.equals("Exit")) {
                    p.println("OK Exit");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread ID " + getId() + " ended.");
        }
    }
    private Socket client;
}
