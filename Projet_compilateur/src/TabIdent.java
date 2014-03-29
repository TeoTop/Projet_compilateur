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
	 * Table des identificateurs locaux
	 * 
	 * @see TabIdent#TabIdent()
	 */
	private HashMap<String,Ident> locaux;
	
	/**
	 * Table des identificateurs globaux
	 * 
	 * @see TabIdent#TabIdent()
	 */
	HashMap<String,Ident> globaux;
	
	/**
	 * Pile des variables
	 * 
	 * @see TabIdent#TabIdent()
	 */
	public Stack<Integer> var;
	/**
	 * Pile temporaire des paramètres
	 * 
	 * @see TabIdent#TabIdent()
	 * 
	 */
	private HashMap<Integer,HashMap<String,Ident>> param;// a changer en class
	private int rang;
	/**
	 * Pile des arguments
	 * 
	 */
	public Stack<Integer> paramTest;

	/**
	 * Constructeur TabIdent
	 * Création de la table des identificateurs et de la pile des variables
	 * 
	 *  @see TabIdent#locaux
	 *  @see TabIdent#var
	 */
	public TabIdent(){
		this.locaux = new HashMap<String,Ident>();
		this.globaux = new HashMap<String,Ident>();
		this.var = new Stack<Integer>();
		this.param = new HashMap<Integer,HashMap<String,Ident>>();
		this.rang=0;
		this.paramTest= new Stack<Integer>();
	}

	/**
	 * Retourne l'ident dans la table des locaux correspondant à la clef
	 * 
	 * @see TabIdent#locaux
	 * @param clef(
	 * @return ident 
	 */
	public Ident chercheIdent(String clef){
		return this.locaux.get(clef);
	}
	
	/**
	 * Retourne true si la clef existe dans la table des identificateurs locaux, false sinon
	 * 
	 * @see TabIdent#locaux
	 * @param clef
	 * @return true si la clef existe, false sinon
	 */
	public boolean existeIdentG(String clef){
		return this.globaux.get(clef) != null;
		
	}
	/**
	 * Retourne l'ident dans la table des globaux correspondant à la clef
	 * 
	 * @see TabIdent#locaux
	 * @param clef(
	 * @return ident 
	 */
	public Ident chercheIdentG(String clef){
		return this.globaux.get(clef);
	}

	/**
	 * Retourne true si la clef existe dans la table des identificateurs globaux, false sinon
	 * 
	 * @see TabIdent#locaux
	 * @param clef
	 * @return true si la clef existe, false sinon
	 */
	public boolean existeIdent(String clef){
		return this.locaux.get(clef) != null;

	}

	/**
	 * Ajouter les variables et les constantes dans la table des identificateurs locaux
	 * 
	 * @see Ident
	 * @see TabIdent#locaux
	 * @see TabIdent#var
	 * @param clef
	 * @param id
	 */
	public void rangeIdent(String clef, Ident id){
		if (!existeIdent(clef)) { // clef n'existe pas
			if (id.isVar()){// id est une varialble
				int offset = ( -2 * nbVar() ) - 2;
				((IdVar) id).setOffset(offset);;
				this.var.push(-1);// on empile -1 pour dire que la variable n'a pas encore de valeur
			}
			this.locaux.put(clef, id);
		}
		else {// clef existe déjà
			Erreur.message("DÃ©claration double pour : " + clef);
		}
	}
	
	/**
	 * Ajouter les paramètres dans la pile temporaire param
	 * 
	 * @see TabIdent#param
	 * @param ident
	 */
	public void addParam(String ident,Ident id){//appelee a partir de addparam_declaration
		HashMap<String,Ident> tmp = new HashMap<String, Ident>();
		tmp.put(ident, id);
		this.param.put(this.rang,tmp);
		this.rang++;
	}	
	/**
	 * Ajouter les paramètres dans la table des identificateurs locaux
	 * 
	 * @see TabIdent#param
	 */
	public void rangeParam(){
		int nbParam =this.nbParam();
		String clef;
		for (int i = 0; i < nbParam; i++){
			int offset = nbParam*2+4-((i+1)*2);
			clef=(String) this.param.get(i).keySet().toArray()[0];
			IdVar id = (IdVar) this.param.get(i).get(clef);
			id.setOffset(offset);
			this.locaux.put(clef, id);
		}
	}	
	/**
	 * Vider la table des identificateurs locaux
	 * Vider la hashMap param
	 * Vider la pile des variables
	 * Remettre le rang à 0
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
	 * Ajouter une fonction
	 * @param nom
	 */
	public void addFonction(String nom,IdFonc id){
		if (!existeIdentG(nom)) { // fonction n'existe pas
			int nbParam =this.nbParam();
			for (int i = 0; i < nbParam; i++){
				String clef = (String) this.param.get(i).keySet().toArray()[0];
				IdVar var = (IdVar) this.param.get(i).get(clef);
				id.ajoutTypeParam(var.getType());
				id.nbParam=nbParam();
			}
			this.globaux.put(nom, id);
		}
		else {// fonction existe déjà
			Erreur.message("Déclaration double pour la fonction : " + nom);
		}
	}	
	
	public void identIsFunction(String identLu) {
		if (!existeIdentG(identLu))	{// fonction n'existe pas
			Erreur.message("L'identificateur '" + identLu + "' doit être une fonction");
		}
	}
	
	public void testNbParam(String nom) {
		IdFonc func = (IdFonc) chercheIdentG(nom);
		if (func!=null){//fonction existe
			int nbParam = func.nbParam;
			int paramTest = this.paramTest.peek();
			if (nbParam!=paramTest+1){
				Erreur.message("La fonction '" + nom + "' n'est pas appliquée au nombre exact d'arguments");
			}
		}
		else{//fonction n'existe pas
			Erreur.message("La fonction '" + nom + "' n'est pas définie");
		}
	}

	/**
	 * Retourne le nombre de variable
	 * @return nombre de variable
	 */
	public int nbVar() {
		/*int nb = 0;
		Set<String> cles = table.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
			Object cle = it.next();
			Ident valeur = table.get(cle);
			if(valeur.isVar()){
				nb ++;
			}
		}
		return nb;*/
		return (this.var.size());
	}
	
	/**
	 * Retourne le nombre de paramètres
	 * @return nombre de paramètres
	 */
	public int nbParam() {
		return (this.param.size());
	}

	/**
	 * Permet d'afficher les attributs de la classe TabIdent
	 * @return l'affichage du contenue de l'objet TabIdent
	 * 
	 * @see TabIdent#locaux
	 */
	@Override
	public String toString() {
		return "TabIdent [locaux=" + locaux + ", globaux=" + globaux + ", var="
				+ var + ", param=" + param + ", rang=" + rang + "]";
	}

	public void addParamForTest() {
		this.paramTest.push(this.paramTest.pop()+1);
	}
	
	public void newFun() {
		this.paramTest.push(-1);
	}

	public void resetParamTest() {
		this.paramTest.pop();
	}
}
