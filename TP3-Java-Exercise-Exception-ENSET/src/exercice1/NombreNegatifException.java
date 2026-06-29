package exercice1;

public class NombreNegatifException extends Exception {
    private int valeurErronee;

    public NombreNegatifException(int valeur) {
        super("Valeur negative detectee : " + valeur);
        this.valeurErronee = valeur;
    }

    public int getValeurErronee() {
        return valeurErronee;
    }
}
