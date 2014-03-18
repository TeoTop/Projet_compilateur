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

public class Expression implements YakaConstants{
	private Stack<String> type;
	private Stack<String> opera;
	private int lasOpRel; /* dernier operateur Rel utilisé */
	private int lasOpMul;/* dernier operateur Mul utilisé */
	private int lasOpAdd;/* dernier operateur Add utilisé */
	private int lasOpNeg;/* dernier operateur Neg utilisé */
	private Stack<Integer> pileSI;/* empilement des si imbriqués */
	private int comptSI; /* compteur du nombre de conditions*/
	private Stack<Integer> pileTQ;/* empilement des TQ imbriqués */
	private int comptTQ; /* compteur du nombre d'iterations*/

	/**
	 * 
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
	 * 
	 * @param id
	 */
	public void empileTypeAvecIdent(String id) {
		if (identExiste(id)){
			this.type.push((YakaTokenManager.tabident.chercheIdent(id)).getType().toLowerCase());
		}
	}
	
	/**
	 * 
	 * @param type
	 */
	public void empileType(String type) {
		this.type.push(type);
	}

	/**
	 * 
	 * @param opera
	 */
	public void empileOpera(String opera) {
		this.opera.push(opera);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean identExiste(String id){
		if (!YakaTokenManager.tabident.existeIdent(id)) {
			Erreur.message("L'identificateur '" + id + "' n'a pas été déclaré");
			return false;
		}
		return true;
	}

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	public void clearType() {
		this.type.clear();
	}

	/**
	 * 
	 */
	public void clearOp() {
		this.opera.clear();
	}

	@Override
	/**
	 * 
	 */
	public String toString() {
		return "Expression [type=" + type + ", opera=" + opera + "]";
	}

	/**
	 * 
	 * @param id
	 */
	public void loadIdent(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident.isVar()) {
				int offset =((IdVar) ident).getOffset();
				int index=-1*offset/2 - 1;
				if (YakaTokenManager.tabident.var.get(index)!=-1) {
					YakaTokenManager.yvm.iload(offset);
				}
				else {
					Erreur.message("La variable '" + id + "' n'est pas encore d�finie");
				}
			}
			else {
				YakaTokenManager.yvm.iconst(((IdConst) ident).getValeur());
			}
		}
		else {
			Erreur.message("La variable ou la constante '" + id + "' n'existe pas");
			this.type.push("erreur");
		}
	}

	/**
	 * 
	 * @param id
	 */
	public void store(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident.isVar()) {
				String type = this.type.peek();
				if (type.equals(ident.getType().toLowerCase())) {
					int offset = ((IdVar) ident).getOffset();
					int index = -1 * offset / 2 - 1;
					YakaTokenManager.yvm.istore(offset);
					YakaTokenManager.tabident.var.set(index, 1);
				}
				else if (!type.equals("erreur")){
					Erreur.message("La variable '" + id + "' doit être de type " + type);
				}
			}
			else {
				Erreur.message("L'identificateur '" + id + "' n'est pas une variable");
			}
		}
		else {
			Erreur.message("La variable '" + id + "' n'existe pas");
		}
	}

	/**
	 * 
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
	 * 
	 */
	public void executerOpAdd() {
		switch(this.lasOpAdd) {
		case ADD : YakaTokenManager.yvm.iadd();break;
		case SUBNEG : YakaTokenManager.yvm.isub();break;
		case OR : YakaTokenManager.yvm.ior();break;
		}

	}
	
	/**
	 * 
	 */
	public void executerOpMul() {
		switch(this.lasOpMul) {
		case MUL : YakaTokenManager.yvm.imul();break;
		case DIV : YakaTokenManager.yvm.idiv();break;
		case AND : YakaTokenManager.yvm.iand();break;
		}

	}
	
	/**
	 * 
	 */
	public void executerOpNeg() {
		switch(this.lasOpNeg) {
		case SUBNEG : YakaTokenManager.yvm.ineg();break;
		case NOT : YakaTokenManager.yvm.inot();break;
		}
	}

	/**
	 * 
	 * @param op
	 */
	public void setLastOpMul(int op) {
		this.lasOpMul = op;
	}
	
	/**
	 * 
	 * @param op
	 */
	public void setLastOpAdd(int op) {
		this.lasOpAdd = op;
	}
	
	/**
	 * 
	 * @param op
	 */
	public void setLastOpNeg(int op) {
		this.lasOpNeg = op;
	}
	
	/**
	 * 
	 * @param op
	 */
	public void setLastOpRel(int op) {
		this.lasOpRel = op;
	}

	/**
	 * 
	 * @param id
	 */
	public void lire(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident.isVar()) {
				if (ident.getType().equals("ENTIER")) {
					int offset = ((IdVar) ident).getOffset();
					int index = -1 * offset / 2 - 1;
					YakaTokenManager.yvm.lireEnt(offset);
					YakaTokenManager.tabident.var.set(index, 1);
				}
				else {
					Erreur.message("La variable '" + id +"' doit être un entier");
				}
			}
			else {
				Erreur.message("L'identificateur '" + id + "' n'est pas une variable");
			}
		}
		else {
			Erreur.message("La variable '" + id + "' n'existe pas");
		}
	}

	/**
	 * 
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
	 * 
	 */
	public void testBool() {
		String type = this.type.peek();
		if (!type.equals("booleen")) {
			Erreur.message("Le type de l'expression dans une conditionnelle doit �tre bool�en");
		}
	}
	
	/**
	 * 
	 */
	public void addSi() {
		this.pileSI.push(this.comptSI++);
	}
	
	/**
	 * 
	 */
	public void ecrireIffauxSinon() {
		YakaTokenManager.yvm.iffaux("SINON" + this.pileSI.peek());
	}
	
	/**
	 * 
	 */
	public void ecrireGotoFSI() {
		YakaTokenManager.yvm.Goto("FSI" + this.pileSI.peek());
	}
	
	/**
	 * 
	 */
	public void ecrireSinon() {
		YakaTokenManager.yvm.ecrireEtiqu("SINON" + this.pileSI.peek());
	}
	
	/**
	 * 
	 */
	public void ecrireFsi() {
		YakaTokenManager.yvm.ecrireEtiqu("FSI" + this.pileSI.peek());
	}
	
	/**
	 * 
	 */
	public void removeSi() {
		this.pileSI.pop();
	}
	
	/* iteration */
	/**
	 * 
	 */
	public void addTantQue() {
		this.pileTQ.push(this.comptTQ++);
	}
	
	/**
	 * 
	 */
	public void ecrireFaire() {
		YakaTokenManager.yvm.ecrireEtiqu("Faire" + this.pileTQ.peek());
	}
	
	/**
	 * 
	 */
	public void ecrireIffauxFait() {
		YakaTokenManager.yvm.iffaux("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * 
	 */
	public void ecrireGotoFaire() {
		YakaTokenManager.yvm.Goto("FAIRE" + this.pileTQ.peek());
	}
	
	/**
	 * 
	 */
	public void ecrireFait() {
		YakaTokenManager.yvm.ecrireEtiqu("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * 
	 */
	public void removeTantQue() {
		this.pileTQ.pop();
	}
}
