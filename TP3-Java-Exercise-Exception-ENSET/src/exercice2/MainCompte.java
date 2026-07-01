package exercice2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MainCompte {
    private static final Scanner ENTREE = new Scanner(System.in);
    private static final Map<String, CompteBancaire> BANQUE = new LinkedHashMap<>();

    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = ENTREE.nextInt();
            ENTREE.nextLine();
            switch (choix) {
                case 1 -> ajouter();
                case 2 -> supprimer();
                case 3 -> afficherTout();
                case 4 -> operer(true);
                case 5 -> operer(false);
                case 6 -> virer();
                case 7 -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println("Au revoir !");
    }

    private static void ajouter() {
        System.out.print("Numero : ");
        String numero = ENTREE.nextLine();
        System.out.print("Titulaire : ");
        String titulaire = ENTREE.nextLine();
        System.out.print("Solde initial : ");
        double solde = ENTREE.nextDouble();
        System.out.print("Type (1=Courant, 2=Epargne) : ");
        int type = ENTREE.nextInt();
        System.out.print(type == 1 ? "Decouvert : " : "Taux : ");
        double parametre = ENTREE.nextDouble();
        CompteBancaire compte = (type == 1)
                ? new CompteCourant(numero, titulaire, solde, parametre)
                : new CompteEpargne(numero, titulaire, solde, parametre);
        BANQUE.put(numero, compte);
        System.out.println("Compte enregistre.");
    }

    private static void supprimer() {
        System.out.print("Numero a supprimer : ");
        String numero = ENTREE.nextLine();
        System.out.println(BANQUE.remove(numero) != null ? "Supprime." : "Numero inconnu.");
    }

    private static void afficherTout() {
        if (BANQUE.isEmpty()) {
            System.out.println("Aucun compte.");
            return;
        }
        BANQUE.values().forEach(System.out::println);
    }

    private static void operer(boolean depot) {
        System.out.print("Numero : ");
        String numero = ENTREE.nextLine();
        System.out.print("Montant : ");
        double montant = ENTREE.nextDouble();
        CompteBancaire compte = BANQUE.get(numero);
        if (compte == null) {
            System.err.println("Numero inconnu.");
            return;
        }
        if (depot) {
            compte.deposer(montant);
            System.out.println("Depot effectue. Solde = " + compte.solde());
        } else {
            try {
                compte.retirer(montant);
                System.out.println("Retrait effectue. Solde = " + compte.solde());
            } catch (FondsInsuffisantsException erreur) {
                System.err.println(erreur.getMessage());
            }
        }
    }

    private static void virer() {
        System.out.print("Compte source : ");
        String source = ENTREE.nextLine();
        System.out.print("Compte destination : ");
        String destination = ENTREE.nextLine();
        System.out.print("Montant : ");
        double montant = ENTREE.nextDouble();
        try {
            CompteBancaire ca = BANQUE.get(source);
            CompteBancaire cb = BANQUE.get(destination);
            if (ca == null || cb == null) {
                throw new CompteInexistantException("Un des comptes n'existe pas.");
            }
            ca.virer(cb, montant);
            System.out.println("Virement effectue.");
        } catch (FondsInsuffisantsException | CompteInexistantException erreur) {
            System.err.println(erreur.getMessage());
        }
    }

    private static void afficherMenu() {
        System.out.println("1-Ajouter 2-Supprimer 3-Lister 4-Depot 5-Retrait 6-Virement 7-Quitter");
        System.out.print("Votre choix : ");
    }
}
