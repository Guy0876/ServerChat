import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServerThread extends Thread {
    private final ServerSocket serverSocket;
    private final ArrayList<ChatThread> clients = new ArrayList<>();

    public MainServerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        System.out.println("Server is running...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected ");
                ChatThread cth = new ChatThread(clientSocket, this);
                clients.add(cth);
                cth.start();
            }
        } catch (IOException e) {
            System.out.println("Error in server: " + e.getMessage());
        }
    }

    public synchronized void broadcastMessage(String message, ChatThread sender) {
        for (ChatThread client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public synchronized void removeClient(ChatThread cth) {
        clients.remove(cth);
    }
}
