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
            label:
            while (scanner.hasNextLine()) {
                String query = scanner.nextLine();
                System.out.println(query);
                switch (query) {
                    case "Day":
                        p.println(LocalDate.now());
                        break;
                    case "Time":
                        p.println(LocalTime.now());
                        break;
                    case "Exit":
                        p.println("OK Exit");
                        break label;
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
