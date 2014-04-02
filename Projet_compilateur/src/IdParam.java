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
 * Classe héritée de Ident. Permet de définir un paramètre du code YAKA
 * 
 * @see Ident
 */
public class IdParam extends Ident {
	/**
	 * Permet d'enregistrer le positionnement du paramètre dans la pile assembleur
	 * 
	 * @see IdParam#getOffset()
	 * @see IdParam#setOffset(int)
	 */
	private int offset;

	/**
	 * Retourne l'offset du paramètre (où elle se situe dans la pile assembleur)
	 * @return entier de l'attribut offset
	 * 
	 * @see IdParam#offset
	 */
	public int getOffset() {
		return this.offset;
	}

	/**
	 * Mettre à jour l'attribut offset de la classe IdParam
	 * @param offset
	 * 
	 * @see IdParam#offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Retourne toujours vrai puisque la classe correspond aux variables
	 * @return true
	 */
	public boolean isVar() {
		return false;
	}

	/**
	 * Permet d'afficher les attributs de la classe IdVar et celui de la classe Ident
	 * @return l'affichage du contenue de l'objet IdParam
	 * 
	 * @see IdParam#offset
	 */
	@Override
	public String toString() {
		return "IdVar [offset=" + this.offset + " Type="+ getType() +"]";
	}

	@Override
	public boolean isFonc() {
		return false;
	}
	
	@Override
	public boolean isParam() {
		return true;
	}


}
