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
public class Erreur {
	public static boolean err;

	/*public static void erreurDeclarationDouble(String id) {
		System.out.println("Erreur : Déclaration double pour : " + id);
		err = true;
	}

	public static void erreurIdentNonExiste(String id) {
		System.out.println("Erreur : L'identificateur `" + id + "` n'a pas été déclaré");
		err = true;
	}

	public static void erreurType(String type1, String type2, String op) {
		System.out.println("Erreur : Impossible d'effectuer l'opération " + op + " entre les types : " + type1 + " et " + type2);
		err = true;
	}

	public static void erreurDeclarationConstante(String ident) {
		System.out.println("La constante `" + ident + "` n'existe pas");
		err = true;
	}

	public static void erreurRel() {
		System.out.println("Un type autre qu'entier ne peut être comparable");
		err = true;
	}

	public static void erreurOperation(String lastOper, String type) {
		System.out.println("L'opérateur " + lastOper + "exige une expression de type " + type + " pour être évalué");
		err = true;
	}

	public static void errOp(String op) {
		System.out.println("L'opération " + op + " ne peut s'effectuer");
		err = true;
	}*/

	/**
	 * 
	 * @param erreur
	 */
	public static void message(String erreur) {
		System.out.println(erreur);
		err = true;
	}
	
}
