import java.io.*;
import java.net.Socket;

public class ChatThread extends Thread {
    private final Socket socket;
    private final MainServerThread server;
    private PrintWriter writer;

    public ChatThread(Socket socket, MainServerThread server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            writer = new PrintWriter(socket.getOutputStream(), true);
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                server.broadcastMessage(message, this);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected ");
        } finally {
            server.removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }
}
