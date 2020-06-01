import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private int port;
    private String host;
    private String nickname;
    public void run () {
        try (Scanner scannerConsole = new Scanner(System.in);) {
            System.out.println("Enter your nickname:");
            nickname = scannerConsole.nextLine();
            System.out.println("Enter the address of server.");
            host = scannerConsole.nextLine();
            System.out.println("Enter the port");
            port = Integer.parseInt(scannerConsole.nextLine());
            System.out.println("Starting the chat... Enter bye to terminate the connection.");

            Socket clientSocket = new Socket(host, port);
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            Scanner response = new Scanner(clientSocket.getInputStream());

            System.out.println("Your nickname: " + nickname);
            outputStream.println(nickname);

            System.out.println("Connected to Server " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " from client " + clientSocket.getLocalAddress() + ":" +clientSocket.getLocalPort());

            while (true) {
                System.out.print(nickname + ": ");
                String sendData = scannerConsole.nextLine();
                outputStream.println(sendData);

                String responseData = response.nextLine();
                System.out.println("Server: " + responseData);

                if (sendData.equals("bye")) {
                    scannerConsole.close();
                    response.close();
                    clientSocket.close();
                    break;
                }
            }
        } catch (IOException exception) {
               exception.printStackTrace();
        } finally {
            System.out.println("Connection closed.");
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
