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
 * Classe permettant de définir un paramètre d'une fonction
 * 
 * @see IdVar
 * @see IdConst
 * @see Declaration
 * @see TabIdent
 */
public class Parametre {
	private String nom;
	private Ident id;
	/**
	 * Constructeur pour initialiser le parametre avec les deux paramètre de la fonction
	 * @param nom de la fonction
	 * @param id de ma fonction
	 */
	public Parametre(String nom, Ident id) {
		this.nom = nom;
		this.id = id;
	}
	/**
	 * Recupere le nom de la fonction
	 * @return Retourne le nom du paramètre
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Recupere l'id de la fonction
	 * @return Retourne l'id du paramètre
	 */
	public Ident getId() {
		return id;
	}
	
	
	
	
	
	
}
