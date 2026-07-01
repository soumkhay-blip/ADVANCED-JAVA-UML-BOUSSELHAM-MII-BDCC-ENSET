package exercice1;

public class EntierNaturel {
    private int valeur;

    public EntierNaturel(int valeur) throws NombreNegatifException {
        this.valeur = verifier(valeur);
    }

    public int valeur() {
        return valeur;
    }

    public void affecter(int nouvelle) throws NombreNegatifException {
        this.valeur = verifier(nouvelle);
    }

    public void decrementer() throws NombreNegatifException {
        this.valeur = verifier(valeur - 1);
    }

    private static int verifier(int candidat) throws NombreNegatifException {
        if (candidat < 0) {
            throw new NombreNegatifException(candidat);
        }
        return candidat;
    }

    @Override
    public String toString() {
        return "EntierNaturel(" + valeur + ")";
    }
}
