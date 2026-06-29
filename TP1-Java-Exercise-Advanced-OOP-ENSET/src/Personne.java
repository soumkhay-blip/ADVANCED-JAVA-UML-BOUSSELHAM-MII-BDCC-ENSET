import java.util.Date;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private int telefon;
    private Date date;
public Personne () {
    System.out.println("Constructor");
}

    public void afficherInfoPersonnelle (){

        System.out.println("id : "+ id+" nom : " + nom+ " prenom : " + prenom + " telefon : " + telefon + " date : " + date);
    }
}
