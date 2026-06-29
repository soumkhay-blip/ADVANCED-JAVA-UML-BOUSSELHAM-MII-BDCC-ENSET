package exercice2;

public class CompteCourant extends CompteBancaire {
    private double decouvert;

    public CompteCourant(String numero, String titulaire, double solde, double decouvert) {
        super(numero, titulaire, solde);
        this.decouvert = decouvert;
    }

    @Override
    public void retrait(double montant) throws FondsInsuffisantsException {
        if (montant > solde + decouvert) {
            throw new FondsInsuffisantsException(
                    "Fonds insuffisants (decouvert depasse) pour le compte " + numero);
        }
        solde -= montant;
    }
}
