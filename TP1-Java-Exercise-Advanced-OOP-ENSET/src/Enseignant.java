public class Enseignant extends Personne {

    public String matricule;
    public Enseignant() {
        System.out.println("Enseignant constructor");
    }

    public void afficherMatricule() {
        System.out.println("matricule : " + matricule);
    }
}
