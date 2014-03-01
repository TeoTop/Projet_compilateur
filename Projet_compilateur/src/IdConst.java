
public class IdConst extends Ident {
	private Object valeur;

	public Object getValeur() {
		return valeur;
	}

	public IdConst() {
		super();
	}

	@Override
	public String toString() {
		return "IdConst [valeur=" + valeur + " type="+ this.getType() + "]";
	}

	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}
}
