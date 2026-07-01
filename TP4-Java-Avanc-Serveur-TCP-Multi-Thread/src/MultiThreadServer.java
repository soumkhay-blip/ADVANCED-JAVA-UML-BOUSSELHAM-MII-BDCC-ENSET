import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    private static final int PORT = 5000;
    private static final int TAILLE_POOL = 5;

    public static void main(String[] args) {
        ExecutorService executeur = Executors.newFixedThreadPool(TAILLE_POOL);
        try (ServerSocket socketServeur = new ServerSocket(PORT)) {
            System.out.println("Serveur en ecoute sur le port " + PORT);
            boolean actif = true;
            while (actif) {
                Socket socketClient = socketServeur.accept();
                String adresse = socketClient.getInetAddress().getHostAddress();
                System.out.println("Nouvelle connexion depuis " + adresse);
                executeur.execute(new ClientHandler(socketClient));
            }
        } catch (IOException ex) {
            System.err.println("Arret du serveur : " + ex.getMessage());
        } finally {
            executeur.shutdown();
        }
    }
}
