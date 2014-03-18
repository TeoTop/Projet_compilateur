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
import java.util.Stack;
/**
 * 
 * 
 * 
 * @see Yaka
 */
public class Expression implements YakaConstants{
	
	/**
     * La pile des types
     * @see Expression#Expression()
     * @see Expression#empileTypeAvecIdent(String)
     * @see Expression#empileType(String)
     */
	private Stack<String> type;
	
	/**
     * La pile des op�rateurs
     * @see Expression#Expression()
     * @see Expression#empileOpera(String)
     */
	private Stack<String> opera;
	
	/**
	 * Le dernier operateur de relation de comparaison utilis�
	 * @see Expression#setLastOpRel(int)
	 */
	private int lasOpRel;
	
	/**
	 * Le dernier operateur de multiplication utilis� (multiplication, division
	 * ET)
	 * @see Expression#setLastOpMul(int)
	 */
	private int lasOpMul;
	
	/**
	 * Le dernier operateur d'addition utilis� (addition, soustraction, OR)
	 * @see Expression#setLastOpAdd(int)
	 */
	private int lasOpAdd;
	
	/**
	 * Le dernier operateur de n�gation utilis� (moins unaire, NON)
	 * @see Expression#setLastOpNeg(int)
	 */
	private int lasOpNeg;
	
	/**
	 * La piles des conditionnelles
     * @see Expression#Expression()
     * @see Expression#addSi()
     * @see Expression#removeSi()
	 */
	private Stack<Integer> pileSI;
	
	/**
	 * Compteur de conditionnelles
     * @see Expression#Expression()
     * @see Expression#addSi()
	 */
	private int comptSI;
	
	/**
	 * La piles des boucles
     * @see Expression#Expression()
     * @see Expression#addTantQue()
     * @see Expression#removeTantQue()
	 */
	private Stack<Integer> pileTQ;
	
	/**
	 * Compteur de boucles
     * @see Expression#Expression()
     * @see Expression#addTantQue()
	 */
	private int comptTQ;

	/**
	 * Constructeur Expression
	 * Cr�ation de la pile des types, des op�rateurs ainsi que 
	 * la piles des conditionnalles et des it�rations. La valeur des
	 * compteurs de conditionnelles et de boucles est mise � 1
	 * 
	 * @see Expression#type
	 * @see Expression#opera
	 * @see Expression#pileSI
	 * @see Expression#comptSI
	 * @see Expression#pileTQ
	 * @see Expression#comptTQ
	 */
	public Expression() {
		this.type = new Stack<String>();
		this.opera = new Stack<String>();
		this.pileSI = new Stack<Integer>();
		this.comptSI = 1;
		this.pileTQ = new Stack<Integer>();
		this.comptTQ = 1;
	}
	
	/**
	 * Empile un type gr�ce � son ident
	 * 
	 * @see Expression#type
	 * @see TabIdent
	 * @param id
	 */
	public void empileTypeAvecIdent(String id) {
		/* tester si l'ident existe avant de l'empiler */
		if (identExiste(id)){
			this.type.push((YakaTokenManager.tabident.chercheIdent(id)).getType().toLowerCase());
		}
	}
	
	/**
	 * Empile un type
	 * 
	 * @see Expression#type
	 * @param type
	 */
	public void empileType(String type) {
		this.type.push(type);
	}

	/**
	 * Empiler un op�rateur
	 * 
	 * @see Expression#opera
	 * @param opera
	 */
	public void empileOpera(String opera) {
		this.opera.push(opera);
	}

	/**
	 * Teste si un ident existe dans la table des identificateurs
	 * 
	 * @see TabIdent
	 * @param id
	 * @return true si l'ident existe, false sinon
	 */
	public boolean identExiste(String id){
		if (!YakaTokenManager.tabident.existeIdent(id)) {
			Erreur.message("L'identificateur '" + id + "' n'a pas été déclaré");
			return false;
		}
		return true;
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent � l'op�rande utilis� 
	 * 
	 * @see Expression#lasOpMul
	 */
	public void testMul() {
		String type1,type2,op = "";
		int p = this.lasOpMul;
		switch(p) {
		case MUL : op="multiplication";break;
		case DIV : op="division";break;
		case AND : op="et logique";break;
		}
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			if (type1.equals(type2)) {
				if (type1.equals("entier") && (p == MUL || p == DIV) || type1.equals("booleen") && p == AND) {
					empileType(type1);
				}
				else {
					empileType("erreur");
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre deux variables de type " + type1);
				}
			}
			else {
				if (!(type1.equals("erreur") || type2.equals("erreur"))) {
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre les types " + type1 + " et " + type2);
				}
				empileType("erreur");
			}
			this.opera.pop();
		}
		else {
			Erreur.message("L'opération '" + op + "' ne peut s'effectuer");
			empileType("erreur");
		}
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent � l'op�rande utilis� 
	 * 
	 * @see Expression#lasOpAdd
	 */
	public void testAdd() {
		String type1,type2,op = "";
		int p = this.lasOpAdd;
		switch(p) {
		case ADD : op="addtion";break;
		case SUBNEG : op="soustration";break;
		case OR : op="ou logique";break;
		}
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			if (type1.equals(type2)) {
				if (type1.equals("entier") && (p == ADD || p == SUBNEG) || type1.equals("booleen") && p == OR) {
					empileType(type1);
				}
				else {
					empileType("erreur");
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre deux variables de type " + type1);
				}
			}
			else {
				if (!(type1.equals("erreur") || type2.equals("erreur"))) {
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre les types " + type1 + " et " + type2);
				}
				empileType("erreur");
			}
			this.opera.pop();
		}
		else {
			empileType("erreur");
			Erreur.message("L'opération '" + op + "' ne peut s'effectuer");
		}
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent � l'op�rande utilis� 
	 * 
	 * @see Expression#lasOpRel
	 */
	public void testRel() {
		String op = "";
		int p = this.lasOpRel;
		switch(p) {
		case EGAL : op="égal";break;
		case DIFF : op="différent";break;
		case INF : op="inférieur";break;
		case INFEGAL : op="inférieur ou égal";break;
		case SUP : op="supérieur";break;
		case SUPEGAL : op="supérieur ou égal";break;
		}
		if (this.type.size() >= 2) {
			String type1 = this.type.pop();
			String type2 = this.type.pop();
			if (type1.equals("entier") && type2.equals("entier")) {
				empileType("booleen");
			}
			else {
				if (!(type1.equals("erreur") || type2.equals("erreur"))) {
					Erreur.message("Impossible d'effectuer l'opération de comparaison '" + op + "' entre les types " + type1 + " et " + type2);
					empileType("erreur");
				}
			}
			this.opera.pop();
		}
		else {
			Erreur.message("L'opération de comparaison '" + op + "' ne peut s'effectuer");
		}
	}
	
	/**
	 * Teste si le type en sommet de pile correspond � l'op�rande utilis�
	 * 
	 * @see Expression#lasOpNeg
	 */
	public void testNeg() {
		String op = "";
		int p = this.lasOpNeg;
		switch(p) {
		case NOT : op="non logique";break;
		case SUBNEG : op="moins unaire";break;
		}
		if (this.type.size() >= 1) {
			String type = this.type.peek();
			if (!((type.equals("entier") && p == SUBNEG) || (type.equals("booleen") && p == NOT ))){
				if (!type.equals("erreur")) {
					empileType("erreur");
					Erreur.message("Impossible d'effectuer l'opérateur '" + op +"' sur une variable de type " + type);
				}
			}
		}
		else {
			Erreur.message("L'opérateur '" + op + "' ne peut être effectué");
		}

	}

	/**
	 * Vider la pile type
	 * 
	 * @see Expression#type
	 */
	public void clearType() {
		this.type.clear();
	}

	/**
	 * Vider la pile type
	 * 
	 * @see Expression#opera
	 */
	public void clearOp() {
		this.opera.clear();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Expression [type=" + type + ", opera=" + opera + "]";
	}

	/**
	 * 
	 * Appel � la m�thode iload de YVMasm si l'ident est une variable et iconst si c'est une constante
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 * @see TabIdent
	 * @see Ident
	 * @param id
	 */
	public void loadIdent(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe
			if (ident.isVar()) {// ident est une variable
				int offset =((IdVar) ident).getOffset();
				int index=-1*offset/2 - 1;// index de la variable dans la pile des variables
				if (YakaTokenManager.tabident.var.get(index)!=-1) {// la variable a �t� d�j� d�finie
					YakaTokenManager.yvm.iload(offset);
				}
				else {// variable non d�finie
					Erreur.message("La variable '" + id + "' n'est pas encore d�finie");
				}
			}
			else {// ident est une constante
				YakaTokenManager.yvm.iconst(((IdConst) ident).getValeur());
			}
		}
		else {// l'ident n'existe pas
			Erreur.message("La variable ou la constante '" + id + "' n'existe pas");
			this.type.push("erreur");
		}
	}

	/**
	 * 
	 * Appel � la m�thode istore de YVMasm si l'ident est une variable
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 * @see TabIdent
	 * @see Ident
	 * @param id
	 */
	public void store(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe
			if (ident.isVar()) {// ident est une variable
				String type = this.type.peek();
				if (type.equals(ident.getType().toLowerCase())) {// teste si l'expression a le m�me type que l'ident � affecter
					int offset = ((IdVar) ident).getOffset();
					int index = -1 * offset / 2 - 1;
					YakaTokenManager.yvm.istore(offset);
					YakaTokenManager.tabident.var.set(index, 1);
				}
				else if (!type.equals("erreur")){// si le type en sommet de la pile est erreur, on �crit pas le msg d'erreur
					Erreur.message("La variable '" + id + "' doit être de type " + type);
				}
			}
			else {// ident est une constante
				Erreur.message("L'identificateur '" + id + "' n'est pas une variable");
			}
		}
		else {// l'ident n'existe pas
			Erreur.message("La variable '" + id + "' n'existe pas");
		}
	}

	/**
	 * Appel � une des m�thodes de comparaison de YVMasm
	 * 
	 * @see Expression#lasOpRel
	 * @see YVMasm
	 */
	public void executerOpRel() {
		switch(this.lasOpRel) {
		case EGAL : YakaTokenManager.yvm.iegal();break;
		case DIFF : YakaTokenManager.yvm.idiff();break;
		case INFEGAL : YakaTokenManager.yvm.iinfegal();break;
		case INF : YakaTokenManager.yvm.iinf();break;
		case SUP : YakaTokenManager.yvm.isup();break;
		case SUPEGAL : YakaTokenManager.yvm.isupegal();break;
		}
	}

	/**
	 * Appel � une des m�thodes d'addition de YVMasm
	 * 
	 * @see Expression#lasOpAdd
	 * @see YVMasm
	 */
	public void executerOpAdd() {
		switch(this.lasOpAdd) {
		case ADD : YakaTokenManager.yvm.iadd();break;
		case SUBNEG : YakaTokenManager.yvm.isub();break;
		case OR : YakaTokenManager.yvm.ior();break;
		}

	}
	
	/**
	 * Appel � une des m�thodes de multiplication de YVMasm
	 * 
	 * @see Expression#lasOpMul
	 * @see YVMasm
	 */
	public void executerOpMul() {
		switch(this.lasOpMul) {
		case MUL : YakaTokenManager.yvm.imul();break;
		case DIV : YakaTokenManager.yvm.idiv();break;
		case AND : YakaTokenManager.yvm.iand();break;
		}

	}
	
	/**
	 * Appel � une des m�thodes de n�gation de YVMasm
	 * 
	 * @see Expression#lasOpNeg
	 * @see YVMasm
	 */
	public void executerOpNeg() {
		switch(this.lasOpNeg) {
		case SUBNEG : YakaTokenManager.yvm.ineg();break;
		case NOT : YakaTokenManager.yvm.inot();break;
		}
	}

	/**
	 * Met � jour le dernier op�rateur de multiplication utilis�
	 * 
	 * @see Expression#lasOpMul
	 * @param op
	 */
	public void setLastOpMul(int op) {
		this.lasOpMul = op;
	}
	
	/**
	 * Met � jour le dernier op�rateur d'addition utilis�
	 * 
	 * @see Expression#lasOpAdd
	 * @param op
	 */
	public void setLastOpAdd(int op) {
		this.lasOpAdd = op;
	}
	
	/**
	 * Met � jour le dernier op�rateur de n�gation utilis�
	 * 
	 * @see Expression#lasOpNeg
	 * @param op
	 */
	public void setLastOpNeg(int op) {
		this.lasOpNeg = op;
	}
	
	/**
	 * Met � jour le dernier op�rateur de comparaison utilis�
	 * 
	 * @see Expression#lasOpRel
	 * @param op
	 */
	public void setLastOpRel(int op) {
		this.lasOpRel = op;
	}

	/**
	 * Appel � la m�thode lirEnt de YVMasm ssi la variable est de type entier
	 * 
	 * @see TabIdent
	 * @see YVMasm
	 * @see Ident
	 * @param id
	 */
	public void lire(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe
			if (ident.isVar()) {// ident est une variable
				if (ident.getType().equals("ENTIER")) {// la variable est de type entier
					int offset = ((IdVar) ident).getOffset();
					int index = -1 * offset / 2 - 1;
					YakaTokenManager.yvm.lireEnt(offset);
					YakaTokenManager.tabident.var.set(index, 1);
				}
				else {// la variable n'est pa de type entier
					Erreur.message("La variable '" + id +"' doit être un entier");
				}
			}
			else {// l'ident est une constante
				Erreur.message("L'identificateur '" + id + "' n'est pas une variable");
			}
		}
		else {// l'ident n'existe pas
			Erreur.message("La variable '" + id + "' n'existe pas");
		}
	}

	/**
	 * Appel � la m�thode ecrireEnt si le type en sommet de pile est entier sinon si le type est booleen, on fait
	 * appel � la m�thode ecrireBool de YVMasm
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 */
	public void ecrire() {
		String type = this.type.peek();
		if (type.equals("entier")) {
			YakaTokenManager.yvm.ecrireEnt();
		}
		else if (type.equals("booleen")) {
			YakaTokenManager.yvm.ecrireBool();
		}
	}

	/**
	 * Si le type en sommet de pile n'est pas bool�en, un message d'erreur est affich�
	 * 
	 * @see Expression#type
	 */
	public void testBool() {
		String type = this.type.peek();
		if (!type.equals("booleen")) {
			Erreur.message("Le type de l'expression dans une conditionnelle doit �tre bool�en");
		}
	}
	
	/**
	 * Empiler les conditionnelles et d'incrementer le nombre de conditionnelles pr�sentes dans le code YAKA 
	 * 
	 * @see Expression#pileSI
	 * @see Expression#comptSI
	 */
	public void addSi() {
		this.pileSI.push(this.comptSI++);
	}
	
	/**
	 * Appel � la m�thode iffaux de YVMasm
	 * 
	 * @see YVMasm
	 */
	public void ecrireIffauxSinon() {
		YakaTokenManager.yvm.iffaux("SINON" + this.pileSI.peek());
	}
	
	/**
	 * Appel � la m�thode Goto de YVMasm
	 * 
	 * @see YVMasm
	 */
	public void ecrireGotoFSI() {
		YakaTokenManager.yvm.Goto("FSI" + this.pileSI.peek());
	}
	
	/**
	 * Appel � la m�thode ecrireEtiqu de YVMasm pour �crire l'�tiquette sinon
	 * 
	 * @see YVMasm
	 */
	public void ecrireSinon() {
		YakaTokenManager.yvm.ecrireEtiqu("SINON" + this.pileSI.peek());
	}
	
	/**
	 * Appel � la m�thode ecrireEtiqu de YVMasm pour �crire l'�tiquette fsi
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFsi() {
		YakaTokenManager.yvm.ecrireEtiqu("FSI" + this.pileSI.peek());
	}
	
	/**
	 * D�piler la pile des conditionnelles
	 * 
	 * @see Expression#pileSI
	 */
	public void removeSi() {
		this.pileSI.pop();
	}
	
	/**
	 * Empiler les it�rations et d'incrementer le nombre d'it�rations pr�sentes dans le code YAKA 
	 * 
	 * @see Expression#pileTQ
	 * @see Expression#comptTQ
	 */
	public void addTantQue() {
		this.pileTQ.push(this.comptTQ++);
	}
	
	/**
	 * Appel � la m�thode ecrireEtiqu de YVMasm pour �crire l'�tiquette faire
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFaire() {
		YakaTokenManager.yvm.ecrireEtiqu("Faire" + this.pileTQ.peek());
	}
	
	/**
	 * Appel � la m�thode iffaux de YVMasm
	 * 
	 * @see YVMasm 
	 */
	public void ecrireIffauxFait() {
		YakaTokenManager.yvm.iffaux("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * Appel � la m�thode Goto de YVMasm
	 * 
	 * @see YVMasm 
	 */
	public void ecrireGotoFaire() {
		YakaTokenManager.yvm.Goto("FAIRE" + this.pileTQ.peek());
	}
	
	/**
	 * Appel � la m�thode ecrireEtiqu de YVMasm pour �crire l'�tiquette fait
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFait() {
		YakaTokenManager.yvm.ecrireEtiqu("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * D�piler la pile des it�rations
	 * 
	 * @see Expression#pileTQ
	 */
	public void removeTantQue() {
		this.pileTQ.pop();
	}
}
