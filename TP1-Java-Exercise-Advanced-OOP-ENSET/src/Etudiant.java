public class Etudiant extends Personne {
    private String cne;

    public Etudiant() {
        System.out.println("Etudiant constructor");
    }

    public void afficherCNE() {
        System.out.println("CNE :"+cne);
    }
}
