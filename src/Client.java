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

        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket(SERVER_IP,PORT);
            ChatThread cht = new ChatThread(socket);
            cht.start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}