
public class Declaration {

	private String nom;
	private Object valeur;
	private boolean constante;
	private String type;
	
	//création des variables
	public Declaration(String nom, String type) {
		super();
		this.nom = nom;
		this.constante = false;
		this.type = type;
	}
	
	//déclaration des constantes
	public Declaration(String nom) {
		super();
		this.nom = nom;
		this.constante = true;
	}
	
	
	public void valeurConstante(Object valeur, String type){
		this.valeur = valeur;
		this.type = type;
	}
}
