package exercice2;

public abstract class CompteBancaire {
    private final String numero;
    private final String titulaire;
    protected double solde;

    protected CompteBancaire(String numero, String titulaire, double solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
    }

    public String numero() {
        return numero;
    }

    public double solde() {
        return solde;
    }

    public void deposer(double montant) {
        solde += montant;
    }

    protected abstract double montantRetirable();

    public final void retirer(double montant) throws FondsInsuffisantsException {
        if (montant > montantRetirable()) {
            throw new FondsInsuffisantsException(
                    "Retrait refuse sur " + numero + " : " + montant + " > disponible " + montantRetirable());
        }
        solde -= montant;
    }

    public void virer(CompteBancaire beneficiaire, double montant) throws FondsInsuffisantsException {
        retirer(montant);
        beneficiaire.deposer(montant);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + numero + ", " + titulaire + ", solde=" + solde + "}";
    }
}
