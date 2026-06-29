import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + " traite le client " +
                clientSocket.getInetAddress().getHostAddress());
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message recu [" + threadName + "] : " + message);
                switch (message.toLowerCase()) {
                    case "hello":
                        out.println("Bonjour client !");
                        break;
                    case "time":
                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        out.println("Date et heure actuelles : " + date);
                        break;
                    case "bye":
                        out.println("Connexion fermee");
                        clientSocket.close();
                        return;
                    default:
                        out.println("Message recu : " + message);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur avec le client : " + e.getMessage());
        }
    }
}
