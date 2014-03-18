/**
 * 
 * @author CHAPON Theo
 * @author El Omari Alaoui Hassan
 * @author Marchais Julien
 * @author Mesdouri Marouan 
 * @author Tuekam Sandjon Marlene
 * 
 * @version 1.0
 */
public class IdConst extends Ident {
	private int valeur;

	/**
	 * 
	 * @return
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * 
	 */
	public IdConst() {
		super();
	}

	/**
	 * 
	 * @param valeur
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * 
	 */
	public boolean isVar() {
		return false;
	}

	@Override
	/**
	 * 
	 */
	public String toString() {
		return "IdConst [valeur=" + valeur + " type="+ this.getType() + "]";
	}

}
