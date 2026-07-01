package exercice2;

public class CompteCourant extends CompteBancaire {
    private final double decouvert;

    public CompteCourant(String numero, String titulaire, double solde, double decouvert) {
        super(numero, titulaire, solde);
        this.decouvert = decouvert;
    }

    @Override
    protected double montantRetirable() {
        return solde + decouvert;
    }
}
