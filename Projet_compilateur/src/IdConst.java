
public class IdConst extends Ident {
	private int valeur;

	public int getValeur() {
		return valeur;
	}

	public IdConst() {
		super();
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public boolean isVar() {
		return false;
	}

	@Override
	public String toString() {
		return "IdConst [valeur=" + valeur + " type="+ this.getType() + "]";
	}

}
