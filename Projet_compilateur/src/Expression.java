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
	 * La pile des opï¿½rateurs
	 * @see Expression#Expression()
	 * @see Expression#empileOpera(int)
	 */
	private Stack<Integer> opera;

	
	/**
	 * Constructeur Expression
	 * Crï¿½ation de la pile des types, des opï¿½rateurs ainsi que 
	 * la piles des conditionnalles et des itï¿½rations. La valeur des
	 * compteurs de conditionnelles et de boucles est mise ï¿½ 1
	 * 
	 * @see Expression#type
	 * @see Expression#opera
	 */
	public Expression() {
		this.type = new Stack<Integer>();
		this.opera = new Stack<Integer>();
	}
	
	
	/**
	 * 
	 * @return
	 * 
	 * @see Fonction#empileTypeFun
	 */
	public void pushType(int type){
		this.type.push(type);
	}

	/**
	 * Empile un type grï¿½ce ï¿½ son ident
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
	 * Empile un type
	 * 
	 * @see Expression#type
	 * @param type
	 */
	public void empileType(int type) {
		this.type.push(type);
	}

	/**
	 * Empiler un opï¿½rateur
	 * 
	 * @see Expression#opera
	 * @param opera
	 */
	public void empileOpera(int opera) {
		this.opera.push(opera);
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent ï¿½ l'opï¿½rande utilisï¿½ pour une
	 * multiplication, une division et un ET boolï¿½en
	 * 
	 * @see Expression#lasOpMul
	 */
	public void testMul() {
		int type1,type2;
		String t1,t2;
		String op = "";
		int p = this.opera.peek();

		//on vï¿½rifie le type d'opÃ©ration Ã  effectuer
		switch(p) {
		case MUL : op="multiplication";break;
		case DIV : op="division";break;
		case AND : op="et logique";break;
		}

		//on vï¿½rifie le nombre d'arguments dans la pile des types
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
			Erreur.message("L'opÃ©ration '" + op + "' ne peut s'effectuer");
			empileType(YakaConstants.ERREUR);
		}
	}

	
	public String typeToString(int type){
		String t = "";
		switch(type){
		case YakaConstants.ENTIER : t = "entier";break;
		case YakaConstants.BOOLEEN : t = "booleen";break;
		}
		return t;
	}
	
	/**
	 * Teste si les deux types en sommet de pile correspondent ï¿½ l'opï¿½rande utilisï¿½ pour une
	 * addition, une soustration et un OU boolï¿½en
	 * 
	 * @see Expression#lasOpAdd
	 */
	public void testAdd() {
		int type1,type2;
		String t1,t2;
		String op = "";
		int p = this.opera.peek();

		//on vï¿½rifie le type d'opï¿½ration ï¿½ effectuer
		switch(p) {
		case ADD : op="addtion";break;
		case SUBNEG : op="soustration";break;
		case OR : op="ou logique";break;
		}

		//on vï¿½rifie le nombre d'arguments dans la pile des types
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
	 * Teste si les deux types en sommet de pile correspondent ï¿½ l'opï¿½rande utilisï¿½ pour une
	 * comparaison entre deux entiers
	 * 
	 * @see Expression#lasOpRel
	 */
	public void testRel() {
		String op = "";
		String t1,t2;
		int p = this.opera.peek();

		//on vï¿½rifie le type d'opï¿½ration ï¿½ effectuer
		switch(p) {
		case EGAL : op="Ã©gal";break;
		case DIFF : op="diffÃ©rent";break;
		case INF : op="infÃ©rieur";break;
		case INFEGAL : op="infÃ©rieur ou Ã©gal";break;
		case SUP : op="supÃ©rieur";break;
		case SUPEGAL : op="supÃ©rieur ou Ã©gal";break;
		}

		//on vï¿½rifie le nombre d'arguments dans la pile des types
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
					Erreur.message("Impossible d'effectuer l'opÃ©ration de comparaison '" + op + "' entre les types " + t1 + " et " + t2);
					empileType(YakaConstants.ERREUR);
				}
			}
		}
		else {
			Erreur.message("L'opÃ©ration de comparaison '" + op + "' ne peut s'effectuer");
		}
	}

	/**
	 * Teste si le type en sommet de pile correspond ï¿½ l'opï¿½rande utilisï¿½ pour une nï¿½gation
	 * 
	 * @see Expression#lasOpNeg
	 */
	public void testNeg() {
		String op = "";
		int p = this.opera.peek();

		//on vï¿½rifie le type d'opï¿½ration ï¿½ effectuer
		switch(p) {
		case NOT : op="non logique";break;
		case SUBNEG : op="moins unaire";break;
		}

		//on vï¿½rifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 1) {
			int type = this.type.peek();
			String t;
			t = this.typeToString(type);
			//on vérifie que le type de l'argument correspond à l'opérateur.
			if (!((type == YakaConstants.ENTIER && p == SUBNEG) || (type == YakaConstants.BOOLEEN && p == NOT ))){
				if (type != YakaConstants.ERREUR) {
					empileType(YakaConstants.ERREUR);
					Erreur.message("Impossible d'effectuer l'opÃ©rateur '" + op +"' sur une variable de type " + t);
				}
			}
		}
		else {
			Erreur.message("L'opÃ©rateur '" + op + "' ne peut Ãªtre effectuÃ©");
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
	 * Appel ï¿½ la mï¿½thode iload de YVMasm si l'ident est une variable et iconst si c'est une constante
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
				if (Yaka.tabident.var.get(index)!=-1) {// la variable a ï¿½tï¿½ dï¿½jï¿½ dï¿½finie
					Yaka.yvm.iload(offset);
				}
				else {// variable non dï¿½finie
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
	 * Appel ï¿½ la mï¿½thode istore de YVMasm si l'ident est une variable
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
					Erreur.message("La variable '" + id + "' doit Ãªtre de type " + t);
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
	 * Appel ï¿½ une des mï¿½thodes de comparaison de YVMasm
	 * 
	 * @see Expression#lasOpRel
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
	 * Appel ï¿½ une des mï¿½thodes d'addition de YVMasm
	 * 
	 * @see Expression#lasOpAdd
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
	 * Appel ï¿½ une des mï¿½thodes de multiplication de YVMasm
	 * 
	 * @see Expression#lasOpMul
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
	 * Appel ï¿½ une des mï¿½thodes de nï¿½gation de YVMasm
	 * 
	 * @see Expression#lasOpNeg
	 * @see YVMasm
	 */
	public void executerOpNeg() {
		switch(this.opera.peek()) {
		case SUBNEG : Yaka.yvm.ineg();this.opera.pop();break;
		case NOT : Yaka.yvm.inot();this.opera.pop();break;
		}
	}

	/**
	 * Appel ï¿½ la mï¿½thode lirEnt de YVMasm ssi la variable est de type entier
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
	 * Appel ï¿½ la mï¿½thode ecrireEnt si le type en sommet de pile est entier sinon si le type est booleen, on fait
	 * appel ï¿½ la mï¿½thode ecrireBool de YVMasm
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
	 * Si le type en sommet de pile n'est pas boolï¿½en, un message d'erreur est affichï¿½
	 * 
	 * @see Expression#type
	 */
	public void testBool() {
		int type = this.type.peek();
		if (type != YakaConstants.BOOLEEN) {
			Erreur.message("Le type de l'expression dans une conditionnelle doit ï¿½tre boolï¿½en");
		}
	}


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



	public void testTypeExprFunc() {
		int typeExpr = this.type.peek();
		int typeFunc = Yaka.declaration.idFonc.getType();
		String t = this.typeToString(typeFunc);
		if(typeExpr != typeFunc){
			Erreur.message("La valeur à retourner doit être de type " + t);
		}
	}

}
