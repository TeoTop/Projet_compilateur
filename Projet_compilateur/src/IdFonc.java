
import java.util.Stack;

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
 * Classe permettant de d�finir le type d'une fonction
 * 
 * @see IdVar
 * @see IdConst
 * @see Declaration
 * @see TabIdent
 */
public class IdFonc extends Ident {

	/**
	 * Pile permettant de stocker les types de param�tres.
	 */
	static Stack<Integer> param;
	
	/**
	 * Attribut permttant de connaitre le nombre de param�tre.
	 */
	public int nbParam;
	
	@Override
	/**
	 * Affiche l'�l�ment
	 */
	public String toString() {
		return "IdFonc [param=" + param + ", nbParam=" + nbParam + "]";
	}
	/**
	 * Initialise la IdFonc en cr�ant une nouvelle pile
	 */
	public IdFonc(){
		param = new Stack<Integer>();
	}
	
	@Override
	/**
	 * Retourne toujours faux puisque la classe correspond aux Fonctions
	 * @return false
	 */
	public boolean isVar() {
		return false;
	}

	@Override
	/**
	 * Retourne toujours vrai puisque la classe correspond aux Fonctions
	 * @return true
	 */
	public boolean isFonc() {
		return true;
	}
	/**
	 * Retourne la pile des param�tres
	 * @return pile
	 */
	public Stack<Integer> getParam() {
		return param;
	}
	/**
	 * ajoute un parametre � la pile des parametres de la fonction
	 * @param param
	 */
	public void ajoutTypeParam(int param) {
		this.param.push(param);
	}

	@Override
	/**
	 * Retourne toujours faux puisque la classe correspond aux Fonctions
	 * @return false
	 */
	public boolean isParam() {
		return false;
	}
	

}
