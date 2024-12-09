import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 2545;
    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_IP, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ) {
            System.out.println("Connected to the server.");
            Thread listenerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });

            listenerThread.start();

            Scanner scanner = new Scanner(System.in);
            String message;
            while (!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
                writer.println(message);
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
