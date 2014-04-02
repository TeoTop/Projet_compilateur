import java.util.HashMap;
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
 * @see Expression
 * @see Ident
 */
public class TabIdent {
	
	/**
	 * Table des identificateurs locaux pour les variables et constantes des fonctions et 
	 * du programme principal. Cette table est effac�e � chaque fin de fonction.
	 * 
	 * @see TabIdent#TabIdent()
	 */
	private HashMap<String,Ident> locaux;
	
	/**
	 * Table des identificateurs globaux pour les fonctions (nom, type de retour, param�tre). 
	 * Cette table n'est jamais effac�e lors de l'�x�cution du compilateur.
	 * 
	 * @see TabIdent#TabIdent()
	 */
	HashMap<String,Ident> globaux;
	
	/**
	 * Pile des variables. Permet de savoir si une variable pr�c�demment d�clar�e est
	 * initialis�e. 
	 * 
	 * @see TabIdent#TabIdent()
	 */
	public Stack<Integer> var;
	
	/**
	 * Pile temporaire des param�tres. Permet de renseigner les param�tres de la fonction
	 * en cours. Cette table est effac�e � chaque fin de fonction.
	 * 
	 * @see TabIdent#TabIdent()
	 * 
	 */
	private HashMap<Integer,Parametre> param;
	
	/**
	 * Permet de savoir le nombre de param�tre de la fonction en cours.
	 */
	private int rang;
	
	/**
	 * Pile des arguments. Correspond aux param�tres lors de l'appel � la fonction. Sert
	 * � tester le nombre d'argument d'une fonction. Utile pour les appels de fonctions
	 * imbriqu�s.
	 * 
	 */
	public Stack<Integer> argument;

	/**
	 * 
	 * Constructeur TabIdent
	 * Cr�ation de la table des identificateurs et de la pile des variables
	 * 
	 *  @see TabIdent#locaux
	 *  @see TabIdent#var
	 */
	public TabIdent(){
		this.locaux = new HashMap<String,Ident>();
		this.globaux = new HashMap<String,Ident>();
		this.var = new Stack<Integer>();
		this.param = new HashMap<Integer,Parametre>();
		this.rang=0;
		this.argument= new Stack<Integer>();
	}

	/**
	 * Retourne true si la clef existe dans la table des identificateurs locaux, false sinon
	 * 
	 * @param clef
	 * @return true si la clef existe, false sinon
	 * 
	 * @see TabIdent#locaux
	 */
	public boolean existeIdent(String clef){
		return this.locaux.get(clef) != null;
	}
	
	/**
	 * Retourne l'ident dans la table des locaux correspondant � la clef
	 * 
	 * @param clef
	 * @return ident 
	 * 
	 * @see TabIdent#locaux
	 */
	
	public Ident chercheIdent(String clef){
		return this.locaux.get(clef);
	}
	
	/**
	 * Retourne true si la clef existe dans la table des identificateurs globaux, false sinon
	 * 
	 * @param clef
	 * @return true si la clef existe, false sinon
	 * 
	 * @see TabIdent#globaux
	 */
	public boolean existeIdentG(String clef){
		return this.globaux.get(clef) != null;
		
	}
	
	/**
	 * Retourne l'ident dans la table des globaux correspondant � la clef
	 * 
	 * @param clef
	 * @return ident 
	 * 
	 * @see TabIdent#globaux
	 */
	public Ident chercheIdentG(String clef){
		return this.globaux.get(clef);
	}

	/**
	 * Ajouter les variables et les constantes dans la table des identificateurs locaux
	 * 
	 * @param clef
	 * @param id
	 * 
	 * @see Ident
	 * @see TabIdent#locaux
	 * @see TabIdent#var
	 */
	public void rangeIdent(String clef, Ident id){
		if (!existeIdent(clef)) { // clef n'existe pas
			if (id.isVar()){// id est une variable
				int offset = ( -2 * nbVar() ) - 2;
				((IdVar) id).setOffset(offset);;
				this.var.push(-1);// on empile -1 pour dire que la variable n'a pas encore de valeur
			}
			this.locaux.put(clef, id);
		}
		else {// clef existe d�j�
			Erreur.message("Déclaration double pour : " + clef);
		}
	}
	
	/**
	 * Ajouter les param�tres dans la pile temporaire param
	 * 
	 * @param ident
	 * 
	 * @see TabIdent#param
	 */
	public void addParam(String ident,Ident id){//appelee a partir de addparam_declaration
		Parametre tmp = new Parametre(ident, id);
		this.param.put(this.rang,tmp);
		this.rang++;
	}	
	
	/**
	 * Ajouter les param�tres dans la table des identificateurs locaux
	 * 
	 * @see TabIdent#param
	 */
	public void rangeParam(){
		int nbParam =this.nbParam();
		for (int i = 0; i < nbParam; i++){
			int offset = nbParam*2+4-((i+1)*2);
			IdParam id = (IdParam) this.param.get(i).getId();
			id.setOffset(offset);
			this.locaux.put(this.param.get(i).getNom(), id);
		}
	}	
	
	/**
	 * Vider la table des identificateurs locaux
	 * Vider la hashMap param
	 * Vider la pile des variables
	 * Remettre le rang � 0
	 * 
	 * @see TabIdent#locaux
	 * @see TabIdent#param
	 * @see TabIdent#var
	 * @see TabIdent#rang
	 */
	public void clearFun(){
		this.locaux.clear();
		this.param.clear();
		this.var.clear();
		this.rang=0;
	}
	
	/**
	 * Ajouter une fonction � la table des globaux
	 * 
	 * @param nom
	 * 
	 * @see TabIdent#param
	 * @see TabIdent#nbParam
	 * @see TabIdent#globaux
	 */
	public void addFonction(String nom,IdFonc id){
		if (!existeIdentG(nom)) { // fonction n'existe pas
			int nbParam =this.nbParam();
			for (int i = 0; i < nbParam; i++){
				IdParam param = (IdParam) this.param.get(i).getId();
				id.ajoutTypeParam(param.getType());
				id.nbParam=nbParam();
			}
			this.globaux.put(nom, id);
		}
		else {// fonction existe d�j�
			Erreur.message("D�claration double pour la fonction : " + nom);
		}
	}	
	
	/**
	 * Permet de tester si la fonction appel�e existe. (A DEPLACER DANS FONCTION)
	 * 
	 * @param identLu
	 */
	public void identIsFunction(String identLu) {
		if (!existeIdentG(identLu))	{// fonction n'existe pas
			Erreur.message("L'identificateur '" + identLu + "' doit �tre une fonction");
		}
	}
	
	/**
	 * Permet de tester si la fonction appel�e existe et que le nombre d'argument correspond.
	 * 
	 * @param nom
	 * 
	 * @see TabIdent#nbParam
	 * @see TabIdent#argument
	 */
	public void testNbParam(String nom) {
		IdFonc func = (IdFonc) chercheIdentG(nom);
		if (func!=null){//fonction existe
			int nbParam = func.nbParam;
			int paramTest = this.argument.peek();
			if (nbParam!=paramTest+1){
				Erreur.message("La fonction '" + nom + "' n'est pas appliqu�e au nombre exact d'arguments");
			}
		}
		else{//fonction n'existe pas
			Erreur.message("La fonction '" + nom + "' n'est pas d�finie");
		}
	}

	/**
	 * Retourne le nombre de variable
	 * 
	 * @return nombre de variable
	 * 
	 * @see TabIdent#var
	 */
	public int nbVar() {
		return (this.var.size());
	}
	
	/**
	 * Retourne le nombre de param�tres
	 * 
	 * @return nombre de param�tres
	 * 
	 * @see TabIdent#param
	 */
	public int nbParam() {
		return (this.param.size());
	}

	/**
	 * Permet d'afficher les attributs de la classe TabIdent
	 * 
	 * @return l'affichage du contenue de l'objet TabIdent
	 */
	@Override
	public String toString() {
		return "TabIdent [locaux=" + locaux + ", globaux=" + globaux + ", var="
				+ var + ", param=" + param + ", rang=" + rang + "]";
	}

	/**
	 * Permet de sp�cifier qu'un argument a �t� pass� en param�tre (de 0 � nbparam - 1)
	 * 
	 * @see TabIdent#argument
	 */
	public void addArgument() {
		this.argument.push(this.argument.pop()+1);
	}
	
	/**
	 * Permet de signifier l'appel � une fonction dans la pile des arguments. 
	 * 
	 * @see TabIdent#argument
	 */
	public void newFun() {
		this.argument.push(-1);
	}

	/**
	 * Permet de retirer les arguments de la pile lorsque que l'appel � la fonction est
	 * termin�.
	 * 
	 * @see TabIdent#argument
	 */
	public void resetParamTest() {
		this.argument.pop();
	}
}
