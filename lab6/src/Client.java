import java.net.*;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start("localhost", 6666);
        System.out.println("Клиент запущен!\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите ваше сообщение:");
        String word = reader.readLine();

        String resp = client.sendMessage(word);
        System.out.println("\nОтвет сервера:");
        System.out.println(resp);
        client.stop();
    }
}