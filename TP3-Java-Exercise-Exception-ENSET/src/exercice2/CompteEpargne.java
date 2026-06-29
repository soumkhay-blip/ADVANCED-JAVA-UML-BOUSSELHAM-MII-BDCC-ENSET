package exercice2;

public class CompteEpargne extends CompteBancaire {
    private double taux;

    public CompteEpargne(String numero, String titulaire, double solde, double taux) {
        super(numero, titulaire, solde);
        this.taux = taux;
    }

    @Override
    public void retrait(double montant) throws FondsInsuffisantsException {
        if (montant > solde) {
            throw new FondsInsuffisantsException(
                    "Fonds insuffisants pour le compte epargne " + numero);
        }
        solde -= montant;
    }
}
