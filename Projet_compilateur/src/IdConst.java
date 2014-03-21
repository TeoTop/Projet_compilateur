/**
 * 
 * @author CHAPON Theo
 * @author El Omari Alaoui Hassan
 * @author Marchais Julien
 * @author Mesdouri Marouan 
 * @author Tuekam Sandjon Marlene
 * 
 * @version 1.0
 * 
 * Classe héritée de Ident. Permet de définir une constante du code YAKA
 * 
 * @see Ident
 */
public class IdConst extends Ident {
	/**
	 * Permet d'enregistrer la valeur de la constante reconnu dans le code YAKA. Elle sera
	 * ensuite recopier en brut dans le code assembleur
	 * 
	 * @see IdConst#getValeur()
	 * @see IdConst#setValeur(int)
	 */
	private int valeur;

	/**
	 * Retourne l'offset de la variable (où elle se situe dans la pile assembleur)
	 * @return entier de l'attribut valeur
	 * 
	 * @see IdConst#valeur
	 */
	public int getValeur() {
		return this.valeur;
	}

	/**
	 * Mettre  à jour l'attribut valeur de la classe IdConst
	 * @param valeur
	 * 
	 * @see IdConst#valeur
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Retourne toujours faux puisque la classe correspond aux constantes
	 * @return false
	 */
	public boolean isVar() {
		return false;
	}

	/**
	 * Permet d'afficher les attributs de la classe IdConst et celui de la classe Ident
	 * @return l'affichage du contenue de l'objet IdConst
	 * 
	 * @see IdConst#valeur
	 */
	@Override
	public String toString() {
		return "IdConst [valeur=" + this.valeur + " type="+ this.getType() + "]";
	}

}
