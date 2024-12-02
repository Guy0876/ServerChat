import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class MainServerThread extends Thread{

    private ServerSocket socket;

    public MainServerThread(ServerSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        super.run();
        try {
            while(true) {
                Socket clientSocket = socket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String input = reader.readLine();
                if (input.equals("EXIT")) {
                    System.out.println("Exiting");
                    break;
                }
                System.out.println("received from client " + input);
                writer.println("From Server: " + input);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
