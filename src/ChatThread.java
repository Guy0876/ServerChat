import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;
public class ChatThread extends Thread{

    private Socket clientSocket;

    public ChatThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        super.run();
        Scanner sc = new Scanner(System.in);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            String input;
            while ((input = reader.readLine()) != null) {
                if ("EXIT".equalsIgnoreCase(input)) {
                    System.out.println("EXITING");
                    break;
                }
                System.out.println("Received: " + input);
                writer.println("Echo: " + input);
            }
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sc.close();
    }
}
