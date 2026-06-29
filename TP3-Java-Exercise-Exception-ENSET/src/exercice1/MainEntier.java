package exercice1;

import java.util.Scanner;

public class MainEntier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntierNaturel entier = null;
        try {
            System.out.print("Initialisez un entier naturel : ");
            int init = sc.nextInt();
            entier = new EntierNaturel(init);
        } catch (NombreNegatifException e) {
            System.err.println("Erreur : " + e.getMessage());
            System.err.println("Valeur erronee : " + e.getValeurErronee());
            return;
        }

        int choix;
        do {
            System.out.println("\n----- Menu EntierNaturel -----");
            System.out.println("1. Afficher la valeur");
            System.out.println("2. Modifier la valeur");
            System.out.println("3. Decrementer");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Valeur actuelle : " + entier.getVal());
                    break;
                case 2:
                    System.out.print("Nouvelle valeur : ");
                    int nv = sc.nextInt();
                    try {
                        entier.setVal(nv);
                        System.out.println("Valeur modifiee !");
                    } catch (NombreNegatifException e) {
                        System.err.println("Erreur : " + e.getMessage());
                        System.err.println("Valeur erronee : " + e.getValeurErronee());
                    }
                    break;
                case 3:
                    try {
                        entier.decrementer();
                        System.out.println("Valeur decrementee : " + entier.getVal());
                    } catch (NombreNegatifException e) {
                        System.err.println("Erreur : " + e.getMessage());
                        System.err.println("Valeur erronee : " + e.getValeurErronee());
                    }
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 4);
        sc.close();
    }
}
