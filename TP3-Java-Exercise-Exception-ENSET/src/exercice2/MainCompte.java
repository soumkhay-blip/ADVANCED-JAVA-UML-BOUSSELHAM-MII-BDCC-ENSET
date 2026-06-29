package exercice2;

import java.util.ArrayList;
import java.util.Scanner;

public class MainCompte {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CompteBancaire> comptes = new ArrayList<>();
        int choix;
        do {
            System.out.println("\n----- Menu Gestion des Comptes -----");
            System.out.println("1. Ajouter un compte");
            System.out.println("2. Supprimer un compte");
            System.out.println("3. Afficher tous les comptes");
            System.out.println("4. Depot d'argent");
            System.out.println("5. Retrait d'argent");
            System.out.println("6. Transfert d'argent");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.print("Numero du compte : ");
                    String num = sc.nextLine();
                    System.out.print("Titulaire : ");
                    String titulaire = sc.nextLine();
                    System.out.print("Solde initial : ");
                    double solde = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Type de compte (1=Courant, 2=Epargne) : ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    if (type == 1) {
                        System.out.print("Decouvert autorise : ");
                        double decouvert = sc.nextDouble();
                        comptes.add(new CompteCourant(num, titulaire, solde, decouvert));
                    } else {
                        System.out.print("Taux d'interet : ");
                        double taux = sc.nextDouble();
                        comptes.add(new CompteEpargne(num, titulaire, solde, taux));
                    }
                    System.out.println("Compte ajoute avec succes !");
                    break;
                case 2:
                    System.out.print("Numero du compte a supprimer : ");
                    String numSup = sc.nextLine();
                    comptes.removeIf(c -> c.numero.equals(numSup));
                    System.out.println("Compte supprime !");
                    break;
                case 3:
                    System.out.println("Liste des comptes :");
                    for (CompteBancaire c : comptes) {
                        System.out.println(c);
                    }
                    break;
                case 4:
                    System.out.print("Numero du compte : ");
                    String numDepot = sc.nextLine();
                    System.out.print("Montant a deposer : ");
                    double montantDepot = sc.nextDouble();
                    sc.nextLine();
                    CompteBancaire cDepot = trouverCompte(comptes, numDepot);
                    if (cDepot != null) {
                        cDepot.depot(montantDepot);
                        System.out.println("Depot effectue !");
                    } else {
                        System.err.println("Compte inexistant !");
                    }
                    break;
                case 5:
                    System.out.print("Numero du compte : ");
                    String numRetrait = sc.nextLine();
                    System.out.print("Montant a retirer : ");
                    double montantRetrait = sc.nextDouble();
                    sc.nextLine();
                    CompteBancaire cRetrait = trouverCompte(comptes, numRetrait);
                    if (cRetrait != null) {
                        try {
                            cRetrait.retrait(montantRetrait);
                            System.out.println("Retrait effectue !");
                        } catch (FondsInsuffisantsException e) {
                            System.err.println("Erreur : " + e.getMessage());
                        }
                    } else {
                        System.err.println("Compte inexistant !");
                    }
                    break;
                case 6:
                    System.out.print("Numero du compte source : ");
                    String numSource = sc.nextLine();
                    System.out.print("Numero du compte destination : ");
                    String numDest = sc.nextLine();
                    System.out.print("Montant a transferer : ");
                    double montantTransfert = sc.nextDouble();
                    sc.nextLine();
                    CompteBancaire source = trouverCompte(comptes, numSource);
                    CompteBancaire dest = trouverCompte(comptes, numDest);
                    try {
                        if (source == null || dest == null) {
                            throw new CompteInexistantException("Un des comptes n'existe pas !");
                        }
                        source.transfert(dest, montantTransfert);
                        System.out.println("Transfert effectue !");
                    } catch (FondsInsuffisantsException | CompteInexistantException e) {
                        System.err.println("Erreur : " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 7);
        sc.close();
    }

    private static CompteBancaire trouverCompte(ArrayList<CompteBancaire> comptes, String num) {
        for (CompteBancaire c : comptes) {
            if (c.numero.equals(num)) return c;
        }
        return null;
    }
}
