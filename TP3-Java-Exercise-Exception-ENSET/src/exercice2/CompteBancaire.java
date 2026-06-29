package exercice2;

public abstract class CompteBancaire {
    protected String numero;
    protected String titulaire;
    protected double solde;

    public CompteBancaire(String numero, String titulaire, double solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
    }

    public void depot(double montant) {
        solde += montant;
    }

    public abstract void retrait(double montant) throws FondsInsuffisantsException;

    public void transfert(CompteBancaire destination, double montant) throws FondsInsuffisantsException {
        this.retrait(montant);
        destination.depot(montant);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [numero=" + numero + ", titulaire=" + titulaire
                + ", solde=" + solde + "]";
    }
}
