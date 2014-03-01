
public class Erreur {
	public static boolean err;

	public static void erreurDeclarationDouble(String id) {
		System.out.println("Erreur : Déclaration double pour : " + id);
		err = true;
	}

	public static void erreurIdentNonExiste(String id) {
		System.out.println("Erreur : L'identificateur `" + id + "` n'a pas été déclaré");
		err = true;
	}

	public static void erreurType(String type1, String type2, String op) {
		System.out.println("Erreur : Impossible d'effectuer l'opération "+op+" entre les types : " + type1 + " et " + type2);
		err = true;
	}

	
	
}
