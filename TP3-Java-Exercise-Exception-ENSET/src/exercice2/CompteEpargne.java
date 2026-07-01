package exercice2;

public class CompteEpargne extends CompteBancaire {
    private final double taux;

    public CompteEpargne(String numero, String titulaire, double solde, double taux) {
        super(numero, titulaire, solde);
        this.taux = taux;
    }

    public double interetsAnnuels() {
        return solde * taux / 100;
    }

    @Override
    protected double montantRetirable() {
        return solde;
    }
}
