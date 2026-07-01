import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String nomThread = Thread.currentThread().getName();
        try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter ecrivain = new PrintWriter(socket.getOutputStream(), true)) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String commande = ligne.trim().toLowerCase();
                System.out.println("[" + nomThread + "] commande recue : " + commande);
                ecrivain.println(reponsePour(commande));
                if (commande.equals("bye")) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.err.println("Communication interrompue : " + ex.getMessage());
        }
    }

    private String reponsePour(String commande) {
        return switch (commande) {
            case "hello" -> "Bonjour client !";
            case "time" -> "Date et heure : " + LocalDateTime.now().format(FORMAT);
            case "bye" -> "Connexion fermee";
            default -> "Echo : " + commande;
        };
    }
}
