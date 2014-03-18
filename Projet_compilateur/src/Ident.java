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
 * Classe permettant de définir le type d'une variable ou d'une constante
 * 
 * @see IdVar
 * @see IdConst
 * @see Declaration
 * @see TabIdent
 */
public abstract class Ident {
	/**
	 * Chaine permettant de renseigner si la variable ou la constante est entier ou booléen.
	 * Elle ne prend que deux valeurs : ENTIER ou BOOLEEN
	 * 
	 * @see Ident#getType()
	 * @see Ident#setType(String)
	 */
	private String type;

	/**
	 * Retourne l'attribut type de la classe Ident
	 * @return Le type est soit ENTIER, soit BOOLEEN
	 * 
	 * @see type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Mettre à jour l'attribut type de la classe Ident
	 * @param type
	 * 
	 * @see type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Méthode permettant de savoir si l'objet Ident est une variable. 
	 * @return true si c'est une variable, false sinon
	 * @see IdVar#isVar()
	 * @see IdConst#isVar()
	 */
	public abstract boolean isVar();
	
	
	/**
	 * Permet d'afficher les attributs de la classe Ident
	 * @return l'affichage du contenue de l'objet Ident
	 * 
	 * @see type
	 */
	@Override
	public String toString() {
		return "Ident [type=" + this.type + "]";
	}



}
