import java.util.Stack;


public class Expression implements YakaConstants{
	private Stack<String> type;
	private Stack<String> opera;
	private String lasOpRel; /* dernier operateur Rel utilisé */
	private String lasOpMul;/* dernier operateur Mul utilisé */
	//private Stack<Integer> valeur;
	private String lasOpAdd;/* dernier operateur Add utilisé */
	private String lasOpNeg;/* dernier operateur Neg utilisé */

	public Expression() {
		this.type = new Stack<String>();
		this.opera = new Stack<String>();
	}
	public void empileTypeAvecIdent(String id) {
		if (identExiste(id)){
			this.type.push((YakaTokenManager.tabident.chercheIdent(id)).getType().toLowerCase());
		}
	}
	public void empileType(String type) {
		this.type.push(type);
	}


	public void empileOpera(String opera) {
		this.opera.push(opera);
	}


	public boolean identExiste(String id){
		if (!YakaTokenManager.tabident.existeIdent(id)) {
			Erreur.message("L'identificateur '" + id + "' n'a pas été déclaré");
			return false;
		}
		return true;
	}

	public void testMul() {
		String type1,type2,op = "";
		int p = position(YakaConstants.tokenImage,this.lasOpMul);
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
			//Erreur.errOp("multiplication");
			Erreur.message("L'opération '" + op + "' ne peut s'effectuer");
			empileType("erreur");
		}
	}

	public void testAdd() {
		String type1,type2,op = "";
		int p = position(YakaConstants.tokenImage,this.lasOpAdd);
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
					//Erreur.erreurType(type1,type2,"addition");
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre les types " + type1 + " et " + type2);
				}
				empileType("erreur");
			}
			this.opera.pop();
		}
		else {
			//Erreur.errOp("addition");
			empileType("erreur");
			Erreur.message("L'opération '" + op + "' ne peut s'effectuer");
		}
	}


	public void testRel() {
		String op = "";
		int p = position(YakaConstants.tokenImage,this.lasOpRel);
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
					//Erreur.erreurType(type1,type2,"addition");
					Erreur.message("Impossible d'effectuer l'opération de comparaison '" + op + "' entre les types " + type1 + " et " + type2);
					empileType("erreur");
				}
			}
			this.opera.pop();
		}
		else {
			//Erreur.erreurRel();
			Erreur.message("L'opération de comparaison '" + op + "' ne peut s'effectuer");
		}
	}

	public void testNeg() {
		String op = "";
		int p = position(YakaConstants.tokenImage,this.lasOpNeg);
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

	public void clearType() {
		this.type.clear();
	}

	public void clearOp() {
		this.opera.clear();
	}

	@Override
	public String toString() {
		return "Expression [type=" + type + ", opera=" + opera + "]";
	}

	public void loadIdent(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident instanceof IdVar) {
				YakaTokenManager.yvm.iload(((IdVar) ident).getOffset());
			}
			else {
				YakaTokenManager.yvm.iconst(((IdConst) ident).getValeur());
			}
		}
		else {
			Erreur.message("La variable ou la constante '" + id + "' n'existe pas");
		}
	}

	public void store(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident instanceof IdVar) {
				String type = this.type.peek();
				if (type.equals(ident.getType().toLowerCase())) {
					int offset = ((IdVar) ident).getOffset();
					YakaTokenManager.yvm.istore(offset);
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

	public void executerOpRel() {
		int p = position(YakaConstants.tokenImage,this.lasOpRel);
		switch(p) {
		case EGAL : YakaTokenManager.yvm.iegal();break;
		case DIFF : YakaTokenManager.yvm.idiff();break;
		case INFEGAL : YakaTokenManager.yvm.iinfegal();break;
		case INF : YakaTokenManager.yvm.iinf();break;
		case SUP : YakaTokenManager.yvm.isup();break;
		case SUPEGAL : YakaTokenManager.yvm.isupegal();break;
		}
	}

	public void executerOpAdd() {
		int p = position(YakaConstants.tokenImage,this.lasOpAdd);
		switch(p) {
		case ADD : YakaTokenManager.yvm.iadd();break;
		case SUBNEG : YakaTokenManager.yvm.isub();break;
		case OR : YakaTokenManager.yvm.ior();break;
		}

	}
	public void executerOpMul() {
		int p = position(YakaConstants.tokenImage,this.lasOpMul);
		switch(p) {
		case MUL : YakaTokenManager.yvm.imul();break;
		case DIV : YakaTokenManager.yvm.idiv();break;
		case AND : YakaTokenManager.yvm.iand();break;
		}

	}
	public void executerOpNeg() {
		int p = position(YakaConstants.tokenImage,this.lasOpNeg);
		switch(p) {
		case SUBNEG : YakaTokenManager.yvm.ineg();break;
		case NOT : YakaTokenManager.yvm.inot();break;
		}

	}

	public void setLastOpMul(String op) {
		this.lasOpMul = op;
	}
	public void setLastOpAdd(String op) {
		this.lasOpAdd = op;
	}
	public void setLastOpNeg(String op) {
		this.lasOpNeg = op;
	}
	public void setLastOpRel(String op) {
		this.lasOpRel = op;
	}

	/* fonction qui cherche la position de op dans tokenimage; changer de class */
	private int position(String[] tokenimage, String op) {
		for (int i = 0;i < tokenimage.length;i++) {
			//			System.out.println(tokenimage[i]);
			//			System.out.println(op);
			if (tokenimage[i].equals("\"" + op + "\"")) {
				return i;
			}
		}
		return -1;
	}

	public void lire(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident instanceof IdVar) {
				if (ident.getType().equals("ENTIER")) {
					YakaTokenManager.yvm.lireEnt(((IdVar) ident).getOffset());
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

	public void ecrire() {
		String type = this.type.peek();
		if (type.equals("entier")) {
			YakaTokenManager.yvm.ecrireEnt();
		}
		else if (type.equals("booleen")) {
			YakaTokenManager.yvm.ecrireBool();
		}
	}

}
