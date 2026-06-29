package Bibliotheque;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MetierProduitImpl metier = new MetierProduitImpl();
        int choix;
        do {
            System.out.println("----- Menu Gestion Produits -----");
            System.out.println("1. Afficher la liste des produits");
            System.out.println("2. Rechercher un produit par id");
            System.out.println("3. Ajouter un nouveau produit");
            System.out.println("4. Supprimer un produit par id");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Liste des produits :");
                    for (Produit p : metier.getAll()) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.print("Entrez l'id du produit : ");
                    long idRecherche = sc.nextLong();
                    Produit p = metier.findById(idRecherche);
                    if (p != null) System.out.println(p);
                    else System.out.println("Produit introuvable !");
                    break;
                case 3:
                    System.out.print("Id : ");
                    long id = sc.nextLong(); sc.nextLine();
                    System.out.print("Nom : ");
                    String nom = sc.nextLine();
                    System.out.print("Marque : ");
                    String marque = sc.nextLine();
                    System.out.print("Prix : ");
                    double prix = sc.nextDouble(); sc.nextLine();
                    System.out.print("Description : ");
                    String desc = sc.nextLine();
                    System.out.print("Stock : ");
                    int stock = sc.nextInt();
                    Produit nouveau = new Produit(id, nom, marque, prix, desc, stock);
                    metier.add(nouveau);
                    System.out.println("Produit ajoute avec succes !");
                    break;
                case 4:
                    System.out.print("Entrez l'id du produit a supprimer : ");
                    long idSup = sc.nextLong();
                    metier.delete(idSup);
                    System.out.println("Produit supprime !");
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 5);
        sc.close();
    }
}
