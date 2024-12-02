import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static final int PORT = 2545;
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        // server
        // 1- Define Server socket - listen for connections
        // 2 - Accept connection request - new client socket
        // 3 - use client socket for communication

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println(" server running waiting for connections");
            MainServerThread MST = new MainServerThread(serverSocket);
            MST.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}