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
 * @see Yaka
 * @see TabIdent
 * @see Ident
 */
public class Expression implements YakaConstants{

	/**
	 * La pile des types
	 * @see Expression#Expression()
	 * @see Expression#empileTypeAvecIdent(String)
	 * @see Expression#empileType(int)
	 */
	private Stack<Integer> type;

	/**
	 * La pile des opérateurs
	 * @see Expression#Expression()
	 * @see Expression#empileOpera(int)
	 */
	private Stack<Integer> opera;

	
	/**
	 * Constructeur Expression
	 * Création de la pile des types et des opérateurs.
	 * 
	 * @see Expression#type
	 * @see Expression#opera
	 */
	public Expression() {
		this.type = new Stack<Integer>();
		this.opera = new Stack<Integer>();
	}
	
	
	/**
	 * Empile un type grâce à son ident : variable ou constante 
	 * 
	 * @see Expression#type	
	 * @see TabIdent
	 * @param id
	 */
	public void empileTypeAvecIdent(String id) {
		/* tester si l'ident existe avant de l'empiler */
		if (Yaka.tabident.existeIdent(id)){
			this.type.push((Yaka.tabident.chercheIdent(id)).getType());
		}
		else if (!Yaka.tabident.existeIdentG(id)) {
			Erreur.message("L'identificateur '" + id + "' n'a pas Ã©tÃ© dÃ©clarÃ©");
		}
	}

	/**
	 * Empile un type dans la pile des types
	 * 
	 * @see Expression#type
	 * @param type
	 */
	public void empileType(int type) {
		this.type.push(type);
	}

	/**
	 * Empiler un opérateur dans la pile des opérateurs
	 * 
	 * @see Expression#opera
	 * @param opera
	 */
	public void empileOpera(int opera) {
		this.opera.push(opera);
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent à l'opérande utilisé pour une
	 * multiplication, une division ou un ET booléen
	 * 
	 */
	public void testMul() {
		int type1,type2;
		String t1,t2;
		String op = "";
		int p = this.opera.peek();

		//on vérifie le type d'opération à  effectuer
		switch(p) {
		case MUL : op="multiplication";break;
		case DIV : op="division";break;
		case AND : op="et logique";break;
		}

		//on vérifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			t1 = this.typeToString(type1);
			t2 = this.typeToString(type2);
			//on vérifie que les deux arguments on le même type. Puis on vérifie que l'opérateur correspond.
			if (type1 == type2) {
				if ((type1 == YakaConstants.ENTIER && (p == MUL || p == DIV)) || (type1 == YakaConstants.BOOLEEN && p == AND)) {
					empileType(type1);
				}
				else {
					empileType(YakaConstants.ERREUR);
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre deux variables de type " + t1);
				}
			}
			else {
				if (!(type1 == YakaConstants.ERREUR || type2 == YakaConstants.ERREUR)) {
					Erreur.message("Impossible d'effectuer l'opÃ©ration '" + op + "' entre les types " + t1 + " et " + t2);
				}
				empileType(YakaConstants.ERREUR);
			}
		}
		else {
			Erreur.message("L'opÃ©ration '" + op + "' ne peut s'effectuer");
			empileType(YakaConstants.ERREUR);
		}
	}

	/**
	 * Conversion de type en String grâce à son code en int 
	 * 
	 * @param type
	 * @return string
	 */
	public String typeToString(int type){
		String t = "";
		switch(type){
		case YakaConstants.ENTIER : t = "entier";break;
		case YakaConstants.BOOLEEN : t = "booleen";break;
		}
		return t;
	}
	
	/**
	 * Teste si les deux types en sommet de pile correspondent à l'opérande utilisé pour une
	 * addition, une soustration et un OU booléen
	 * 
	 */
	public void testAdd() {
		int type1,type2;
		String t1,t2;
		String op = "";
		int p = this.opera.peek();

		//on vérifie le type d'opération à effectuer
		switch(p) {
		case ADD : op="addtion";break;
		case SUBNEG : op="soustration";break;
		case OR : op="ou logique";break;
		}

		//on vérifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			t1 = this.typeToString(type1);
			t2 = this.typeToString(type2);
			//on vérifie que les deux arguments ont le même type. Puis on vérifie que l'opérateur correspond.
			if (type1 == type2) {
				if (type1 == YakaConstants.ENTIER && (p == ADD || p == SUBNEG) || type1 == YakaConstants.BOOLEEN && p == OR) {
					empileType(type1);
				}
				else {
					empileType(YakaConstants.ERREUR);
					Erreur.message("Impossible d'effectuer l'opÃ©ration '" + op + "' entre deux variables de type " + t1);
				}
			}
			else {
				if (!(type1 == YakaConstants.ERREUR || type2 == YakaConstants.ERREUR)) {
					Erreur.message("Impossible d'effectuer l'opÃ©ration '" + op + "' entre les types " + t1 + " et " + t2);
				}
				empileType(YakaConstants.ERREUR);
			}
		}
		else {
			empileType(YakaConstants.ERREUR);
			Erreur.message("L'opÃ©ration '" + op + "' ne peut s'effectuer");
		}
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent à l'opérande utilisé pour une
	 * comparaison entre deux entiers
	 * 
	 */
	public void testRel() {
		String op = "";
		String t1,t2;
		int p = this.opera.peek();

		//on vérifie le type d'opération à effectuer
		switch(p) {
		case EGAL : op="égal";break;
		case DIFF : op="différent";break;
		case INF : op="inférieur";break;
		case INFEGAL : op="inférieur ou égal";break;
		case SUP : op="supérieur";break;
		case SUPEGAL : op="supérieur ou égal";break;
		}

		//on vérifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 2) {
			int type1 = this.type.pop();
			int type2 = this.type.pop();
			t1 = this.typeToString(type1);
			t2 = this.typeToString(type2);
			//on vérifie que les deux arguments on le même type.
			if (type1 == YakaConstants.ENTIER && type2 == YakaConstants.ENTIER) {
				empileType(YakaConstants.BOOLEEN);
			}
			else {
				if (!(type1 == YakaConstants.ERREUR || type2 == YakaConstants.ERREUR)) {
					Erreur.message("Impossible d'effectuer l'opération de comparaison '" + op + "' entre les types " + t1 + " et " + t2);
					empileType(YakaConstants.ERREUR);
				}
			}
		}
		else {
			Erreur.message("L'opération de comparaison '" + op + "' ne peut s'effectuer");
		}
	}

	/**
	 * Teste si le type en sommet de pile correspond à l'opérande utilisé pour une négation
	 * 
	 * @see Expression#lasOpNeg
	 */
	public void testNeg() {
		String op = "";
		int p = this.opera.peek();

		//on vérifie le type d'opération à effectuer
		switch(p) {
		case NOT : op="non logique";break;
		case SUBNEG : op="moins unaire";break;
		}

		//on vérifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 1) {
			int type = this.type.peek();
			String t;
			t = this.typeToString(type);
			//on vérifie que le type de l'argument correspond à l'opérateur.
			if (!((type == YakaConstants.ENTIER && p == SUBNEG) || (type == YakaConstants.BOOLEEN && p == NOT ))){
				if (type != YakaConstants.ERREUR) {
					empileType(YakaConstants.ERREUR);
					Erreur.message("Impossible d'effectuer l'opérateur '" + op +"' sur une variable de type " + t);
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
	 * Vider la pile opérateur
	 * 
	 * @see Expression#opera
	 */
	public void clearOp() {
		this.opera.clear();
	}

	/**
	 * Permet d'afficher les attributs de la classe Expression
	 * @return l'affichage du contenue de l'objet Ident
	 * 
	 * @see Expression#type
	 * @see Expression#opera
	 */
	@Override
	public String toString() {
		return "Expression [type=" + type + ", opera=" + opera + "]";
	}

	/**
	 * 
	 * Appel à la méthode iload de YVMasm si l'ident est une variable ou un paramètre et iconst si c'est une constante
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 * @see TabIdent
	 * @see Ident
	 * @param id
	 */
	public void loadIdent(String id) {
		Ident ident = Yaka.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe var_param_const
			if (ident.isVar()) {// ident est une variable
				int offset =((IdVar) ident).getOffset();
				int index=-1*offset/2 - 1;// index de la variable dans la pile des variables
				if (Yaka.tabident.var.get(index)!=-1) {// la variable a été définie
					Yaka.yvm.iload(offset);
				}
				else {// variable non définie
					Erreur.message("La variable '" + id + "' n'est pas encore dï¿½finie");
				}
			}
			else if (ident.isParam()){//ident_param
				int offset =((IdParam) ident).getOffset();
				Yaka.yvm.iload(offset);
			}
			else {// ident est une constante
				Yaka.yvm.iconst(((IdConst) ident).getValeur());
			}
		}
		else {
			ident = Yaka.tabident.chercheIdentG(id);
			if(ident!=null){// ident existe fonc
			}
			else {// l'ident n'existe pas
				Erreur.message("La variable ou la constante '" + id + "' n'existe pas");
				this.type.push(YakaConstants.ERREUR);
			}
		}
	}

	/**
	 * 
	 * Appel à la méthode istore de YVMasm si l'ident est une variable
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 * @see TabIdent
	 * @see Ident
	 * @param id
	 */
	public void store(String id) {
		Ident ident = Yaka.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe var_param_const
			if (ident.isVar()) {// ident est une variable
				int type = this.type.peek();
				String t;
				t = this.typeToString(type);
				if (type == ident.getType()) {// teste si l'expression a le même type que l'ident à affecter
					int offset = ((IdVar) ident).getOffset();
					int index = -1 * offset / 2 - 1;
					Yaka.yvm.istore(offset);
					Yaka.tabident.var.set(index, 1);
				}
				else if (ident.isParam()){//ident_param
					Erreur.message("L'identificateur '" + id + "' n'est pas une variable");
				}
				else if (type != YakaConstants.ERREUR){// si le type en sommet de la pile est erreur, on écrit pas le msg d'erreur
					Erreur.message("La variable '" + id + "' doit être de type " + t);
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
	 * Appel à une des méthodes de comparaison de YVMasm
	 * @see Expression#opera
	 * @see YVMasm
	 */
	public void executerOpRel() {
		switch(this.opera.peek()) {
		case EGAL : Yaka.yvm.iegal();this.opera.pop();break;
		case DIFF : Yaka.yvm.idiff();this.opera.pop();break;
		case INFEGAL : Yaka.yvm.iinfegal();this.opera.pop();break;
		case INF : Yaka.yvm.iinf();this.opera.pop();break;
		case SUP : Yaka.yvm.isup();this.opera.pop();break;
		case SUPEGAL : Yaka.yvm.isupegal();this.opera.pop();break;
		}
	}

	/**
	 * Appel à une des méthodes d'addition de YVMasm
	 * @see Expression#opera
	 * @see YVMasm
	 */
	public void executerOpAdd() {
		switch(this.opera.peek()) {
		case ADD : Yaka.yvm.iadd();this.opera.pop();break;
		case SUBNEG : Yaka.yvm.isub();this.opera.pop();break;
		case OR : Yaka.yvm.ior();this.opera.pop();break;
		}

	}

	/**
	 * Appel à une des méthodes de multiplication de YVMasm
	 * @see Expression#opera
	 * @see YVMasm
	 */
	public void executerOpMul() {
		switch(this.opera.peek()) {
		case MUL : Yaka.yvm.imul();this.opera.pop();break;
		case DIV : Yaka.yvm.idiv();this.opera.pop();break;
		case AND : Yaka.yvm.iand();this.opera.pop();break;
		}

	}

	/**
	 * Appel à une des méthodes de négation de YVMasm
	 * @see Expression#opera
	 * @see YVMasm
	 */
	public void executerOpNeg() {
		switch(this.opera.peek()) {
		case SUBNEG : Yaka.yvm.ineg();this.opera.pop();break;
		case NOT : Yaka.yvm.inot();this.opera.pop();break;
		}
	}

	/**
	 * Appel à la méthode lirEnt de YVMasm ssi la variable est de type entier
	 * 
	 * @see TabIdent
	 * @see YVMasm
	 * @see Ident
	 * @param id
	 */
	public void lire(String id) {
		Ident ident = Yaka.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe
			if (ident.isVar()) {// ident est une variable
				if (ident.getType() == YakaConstants.ENTIER) {// la variable est de type entier
					int offset = ((IdVar) ident).getOffset();
					int index = -1 * offset / 2 - 1;
					Yaka.yvm.lireEnt(offset);
					Yaka.tabident.var.set(index, 1);
				}
				else {// la variable n'est pas de type entier
					Erreur.message("La variable '" + id +"' doit Ãªtre un entier");
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
	 * Appel à la méthode ecrireEnt si le type en sommet de pile est entier sinon si le type est booleen, on fait
	 * appel à la méthode ecrireBool de YVMasm
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 */
	public void ecrire() {
		int type = this.type.peek();
		if (type == YakaConstants.ENTIER) {
			Yaka.yvm.ecrireEnt();
		}
		else if (type == YakaConstants.BOOLEEN) {
			Yaka.yvm.ecrireBool();
		}
	}

	/**
	 * Si le type en sommet de pile n'est pas booléen, un message d'erreur est affiché
	 * 
	 * @see Expression#type
	 */
	public void testBool() {
		int type = this.type.peek();
		if (type != YakaConstants.BOOLEEN) {
			Erreur.message("Le type de l'expression dans une conditionnelle doit ï¿½tre boolï¿½en");
		}
	}

	/**
	 * Test si l'expression à affecter a le même type que la fonction appelée
	 * @see Expression#type
	 */
	public void testParamFonc() {
		int typeExpr=this.type.peek();
		int index = Yaka.tabident.argument.peek();
		int nbParam = IdFonc.param.size();
		if(index+1<=nbParam){//nbParam est encore >= nb expr entrees en param 
			int typeParam=IdFonc.param.get(index);
			String t = this.typeToString(typeParam);
			if (typeExpr != typeParam){//types de param et expr sont differents
				Erreur.message("Dans la fonction '" + Yaka.fonction.getLastFun().peek() + "' le type du paramètre " + (index+1) + " doit être un " + t);
			}
		}
	}


	/**
	 * Test si l'expression à retourner a le même type que la fonction déclarée
	 */
	public void testTypeExprFunc() {
		int typeExpr = this.type.peek();
		int typeFunc = Yaka.declaration.idFonc.getType();
		String t = this.typeToString(typeFunc);
		if(typeExpr != typeFunc){
			Erreur.message("La valeur à retourner doit être de type " + t);
		}
	}

}
