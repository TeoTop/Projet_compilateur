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
 * Classe h�rit�e de Ident. Permet de d�finir une variable du code YAKA
 * 
 * @see Ident
 */
public class IdVar extends Ident {
	/**
	 * Permet d'enregistrer le positionnement de la variable dans la pile assembleur
	 * 
	 * @see IdVar#getOffset()
	 * @see IdVar#setOffset(int)
	 */
	private int offset;

	/**
	 * Retourne l'offset de la variable (o� elle se situe dans la pile assembleur)
	 * @return entier de l'attribut offset
	 * 
	 * @see offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Mettre � jour l'attribut offset de la classe IdVar
	 * @param offset
	 * 
	 * @see offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Retourne toujours vrai puisque la classe correspond aux variables
	 * @return true
	 */
	public boolean isVar() {
		return true;
	}

	/**
	 * Permet d'afficher les attributs de la classe IdVar et celui de la classe Ident
	 * @return l'affichage du contenue de l'objet IdVar
	 * 
	 * @see offset
	 */
	@Override
	public String toString() {
		return "IdVar [offset=" + offset + " Type="+ getType() +"]";
	}


}
