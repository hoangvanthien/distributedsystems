import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public void run (int port) {
        Scanner scannerConsole = new Scanner(System.in);
        try (ServerSocket serverSocket = new ServerSocket(port);) {

            while (true) {
                System.out.println("Server on port " + serverSocket.getLocalPort() + " is listening...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to Client " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " from server " + clientSocket.getLocalAddress() + ":" +clientSocket.getLocalPort());
                Scanner request = new Scanner(clientSocket.getInputStream());

                PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());

                String nickname = request.nextLine();

                while (request.hasNextLine()) {
                    String requestData = request.nextLine();

                    if (requestData.equals("bye")) {
                        System.out.println(nickname + ": " + requestData);
                        outputStream.println("bye...");
                        request.close();
                        clientSocket.close();
                        break;

                    } else {
                        System.out.println(nickname + ": " + requestData);
                        System.out.print("Server: ");
                        String sendData = scannerConsole.nextLine();
                        outputStream.println(sendData);
                    }
                }
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }
        scannerConsole.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run(8080);
    }
}
