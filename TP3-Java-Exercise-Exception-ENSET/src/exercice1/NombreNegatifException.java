package exercice1;

public class NombreNegatifException extends Exception {
    private final int valeurFautive;

    public NombreNegatifException(int valeurFautive) {
        super(String.format("Valeur negative refusee : %d", valeurFautive));
        this.valeurFautive = valeurFautive;
    }

    public int valeurFautive() {
        return valeurFautive;
    }
}
