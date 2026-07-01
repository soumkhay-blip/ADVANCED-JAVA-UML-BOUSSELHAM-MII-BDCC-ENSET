package exercice1;

import java.util.Scanner;

public class MainEntier {
    private static final Scanner ENTREE = new Scanner(System.in);

    public static void main(String[] args) {
        EntierNaturel entier = initialiser();
        if (entier == null) {
            return;
        }
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = ENTREE.nextInt();
            switch (choix) {
                case 1 -> System.out.println("Valeur actuelle : " + entier.valeur());
                case 2 -> modifier(entier);
                case 3 -> decrementer(entier);
                case 4 -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println("Fin du programme.");
    }

    private static EntierNaturel initialiser() {
        System.out.print("Initialisez un entier naturel : ");
        try {
            return new EntierNaturel(ENTREE.nextInt());
        } catch (NombreNegatifException erreur) {
            signaler(erreur);
            return null;
        }
    }

    private static void modifier(EntierNaturel entier) {
        System.out.print("Nouvelle valeur : ");
        try {
            entier.affecter(ENTREE.nextInt());
            System.out.println("Valeur mise a jour : " + entier.valeur());
        } catch (NombreNegatifException erreur) {
            signaler(erreur);
        }
    }

    private static void decrementer(EntierNaturel entier) {
        try {
            entier.decrementer();
            System.out.println("Valeur decrementee : " + entier.valeur());
        } catch (NombreNegatifException erreur) {
            signaler(erreur);
        }
    }

    private static void afficherMenu() {
        System.out.println("1-Afficher  2-Modifier  3-Decrementer  4-Quitter");
        System.out.print("Votre choix : ");
    }

    private static void signaler(NombreNegatifException erreur) {
        System.err.println(erreur.getMessage() + " (valeur fautive = " + erreur.valeurFautive() + ")");
    }
}
