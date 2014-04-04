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
 * Classe permettant de renseigner les erreurs du code YAKA.
 * 
 * @see Expression
 * @see TabIdent
 * @see Declaration
 */
public class Erreur {
	/**
	 * Attribut permettant de savoir si une erreur a été commise dans le code YAKA
	 */
	public static boolean err;
	
	/**
	 * Permet d'afficher le message d'erreur et passe l'attribut err à vrai
	 * @param erreur
	 * 
	 * @see Erreur#err
	 */
	public static void message(String erreur) {
		System.out.println("Ligne/Colonne " + Yaka.token.beginLine + "/" + Yaka.token.beginColumn + " : " + erreur);
		err = true;
	}
	
}
