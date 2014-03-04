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
			Erreur.message("L'identificateur `" + id + "` n'a pas été déclaré");
			return false;
		}
		return true;
	}

	public void testMul() {
		String type1,type2;
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			if (type1.equals(type2) && type1.equals("entier")) {
				empileType(type1);
			}
			else {
				if (!(type1.equals("erreur") || type2.equals("erreur"))) {
					Erreur.message("Impossible d'effectuer l'opération multiplication entre les types : " + type1 + " et " + type2);
					empileType("erreur");
				}
			}
			this.opera.pop();
		}
		else {
			//Erreur.errOp("multiplication");
			Erreur.message("L'opération multiplication ne peut s'effectuer");
		}
	}

	public void testAdd() {
		String type1,type2;
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			if (type1.equals(type2)) {
				empileType(type1);
			}
			else {
				if (!(type1.equals("erreur") || type2.equals("erreur"))) {
					//Erreur.erreurType(type1,type2,"addition");
					Erreur.message("Impossible d'effectuer l'opération addition entre les types : " + type1 + " et " + type2);
					empileType("erreur");
				}
			}
			this.opera.pop();
		}
		else {
			//Erreur.errOp("addition");
			Erreur.message("L'opération addition ne peut s'effectuer");
		}
	}


	public void testRel() {
		String type;
		if (this.type.size() >= 1) {
			type = this.type.pop();
			if (!type.equals("entier")) {
				//Erreur.erreurRel();
				Erreur.message("Un type autre qu'entier ne peut être comparable");
			}
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

	public void testType() {
		String lastOper = this.opera.peek(); /* trouver la tête de la pile ! pk la pile est vide ? */
		if (lastOper.equals("OU") || lastOper.equals("ET") || lastOper.equals("NON")) {
			testBoolean(lastOper);
		}
		else {
			testEntier(lastOper);
		}
	}
	private void testEntier(String lastOper) {
		if (!this.type.lastElement().equals("entier")) {
			//Erreur.erreurOperation(lastOper,"entier");
			Erreur.message("L'opérateur " + lastOper + "exige une expression de type entier pour être évalué");
		}
	}
	private void testBoolean(String lastOper) {
		if (!this.type.lastElement().equals("boolean")) {
			//Erreur.erreurOperation(lastOper,"boolean");
			Erreur.message("L'opérateur " + lastOper + "exige une expression de type boolean pour être évalué");
		}
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
			Erreur.message("La variable ou la constante `" + id + "` n'existe pas");
		}
	}

	public void store(String id) {
		Ident ident = YakaTokenManager.tabident.chercheIdent(id);
		if (ident != null) {
			if (ident instanceof IdVar) {
				int offset = ((IdVar) ident).getOffset();
				YakaTokenManager.yvm.istore(offset);
			}
			else {
				Erreur.message("L'identificateur `" + id + "` n'est pas une variable");
			}
		}
		else {
			Erreur.message("La variable `" + id + "` n'existe pas");
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
}
