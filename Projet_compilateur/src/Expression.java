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
	 * La pile des op�rateurs
	 * @see Expression#Expression()
	 * @see Expression#empileOpera(int)
	 */
	private Stack<Integer> opera;

	private Stack<String> lastFun;

	private Stack<Boolean> inFunTest;
	/**
	 * Constructeur Expression
	 * Cr�ation de la pile des types, des op�rateurs ainsi que 
	 * la piles des conditionnalles et des it�rations. La valeur des
	 * compteurs de conditionnelles et de boucles est mise � 1
	 * 
	 * @see Expression#type
	 * @see Expression#opera
	 */
	public Expression() {
		this.type = new Stack<Integer>();
		this.opera = new Stack<Integer>();
		this.lastFun = new Stack<String>();
		this.inFunTest = new Stack<Boolean>();
		this.inFunTest.push(false);
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
		if (Yaka.tabident.existeIdent(id)){
			this.type.push((Yaka.tabident.chercheIdent(id)).getType());
		}
		else if (!Yaka.tabident.existeIdentG(id)) {
			Erreur.message("L'identificateur '" + id + "' n'a pas été déclaré");
		}
	}

	public void newFun(String id) {
		System.out.println(id);
		this.lastFun.push(id);
		this.inFunTest.push(true);
	}

	public void resetFun() {
		if (this.inFunTest.peek()) {
			this.lastFun.pop();
			this.inFunTest.pop();
		}
	}



	public void empileTypeFun() {
		if(this.inFunTest.peek()) {
			String id = this.lastFun.peek();
			if (Yaka.tabident.existeIdentG(id)){
				this.type.push((Yaka.tabident.chercheIdentG(id)).getType());
			}		
		}
	}

	public void call() {
		if(this.inFunTest.peek()) {
			String id = this.lastFun.peek();
			if (Yaka.tabident.existeIdentG(id)){
				Yaka.yvm.call(id);
			}	
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
	 * Empiler un op�rateur
	 * 
	 * @see Expression#opera
	 * @param opera
	 */
	public void empileOpera(int opera) {
		this.opera.push(opera);
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent � l'op�rande utilis� pour une
	 * multiplication, une division et un ET bool�en
	 * 
	 * @see Expression#lasOpMul
	 */
	public void testMul() {
		int type1,type2;
		String op = "";
		int p = this.opera.peek();

		//on v�rifie le type d'op�ration � effectuer
		switch(p) {
		case MUL : op="multiplication";break;
		case DIV : op="division";break;
		case AND : op="et logique";break;
		}

		//on v�rifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			//on v�rifie que les deux arguments on le m�me type. Puis on v�rifie que l'op�rateur correspond.
			if (type1 == type2) {
				if ((type1 == YakaConstants.ENTIER && (p == MUL || p == DIV)) || (type1 == YakaConstants.BOOLEEN && p == AND)) {
					empileType(type1);
				}
				else {
					empileType(YakaConstants.ERREUR);
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre deux variables de type " + type1);
				}
			}
			else {
				if (!(type1 == YakaConstants.ERREUR || type2 == YakaConstants.ERREUR)) {
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre les types " + type1 + " et " + type2);
				}
				empileType(YakaConstants.ERREUR);
			}
		}
		else {
			Erreur.message("L'opération '" + op + "' ne peut s'effectuer");
			empileType(YakaConstants.ERREUR);
		}
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent � l'op�rande utilis� pour une
	 * addition, une soustration et un OU bool�en
	 * 
	 * @see Expression#lasOpAdd
	 */
	public void testAdd() {
		int type1,type2;
		String op = "";
		int p = this.opera.peek();

		//on v�rifie le type d'op�ration � effectuer
		switch(p) {
		case ADD : op="addtion";break;
		case SUBNEG : op="soustration";break;
		case OR : op="ou logique";break;
		}

		//on v�rifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 2) {
			type1 = this.type.pop();
			type2 = this.type.pop();
			//on v�rifie que les deux arguments ont le m�me type. Puis on v�rifie que l'op�rateur correspond.
			if (type1 == type2) {
				if (type1 == YakaConstants.ENTIER && (p == ADD || p == SUBNEG) || type1 == YakaConstants.BOOLEEN && p == OR) {
					empileType(type1);
				}
				else {
					empileType(YakaConstants.ERREUR);
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre deux variables de type " + type1);
				}
			}
			else {
				if (!(type1 == YakaConstants.ERREUR || type2 == YakaConstants.ERREUR)) {
					Erreur.message("Impossible d'effectuer l'opération '" + op + "' entre les types " + type1 + " et " + type2);
				}
				empileType(YakaConstants.ERREUR);
			}
		}
		else {
			empileType(YakaConstants.ERREUR);
			Erreur.message("L'opération '" + op + "' ne peut s'effectuer");
		}
	}

	/**
	 * Teste si les deux types en sommet de pile correspondent � l'op�rande utilis� pour une
	 * comparaison entre deux entiers
	 * 
	 * @see Expression#lasOpRel
	 */
	public void testRel() {
		String op = "";
		int p = this.opera.peek();

		//on v�rifie le type d'op�ration � effectuer
		switch(p) {
		case EGAL : op="égal";break;
		case DIFF : op="différent";break;
		case INF : op="inférieur";break;
		case INFEGAL : op="inférieur ou égal";break;
		case SUP : op="supérieur";break;
		case SUPEGAL : op="supérieur ou égal";break;
		}

		//on v�rifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 2) {
			int type1 = this.type.pop();
			int type2 = this.type.pop();
			//on v�rifie que les deux arguments on le m�me type.
			if (type1 == YakaConstants.ENTIER && type2 == YakaConstants.ENTIER) {
				empileType(YakaConstants.BOOLEEN);
			}
			else {
				if (!(type1 == YakaConstants.ERREUR || type2 == YakaConstants.ERREUR)) {
					Erreur.message("Impossible d'effectuer l'opération de comparaison '" + op + "' entre les types " + type1 + " et " + type2);
					empileType(YakaConstants.ERREUR);
				}
			}
		}
		else {
			Erreur.message("L'opération de comparaison '" + op + "' ne peut s'effectuer");
		}
	}

	/**
	 * Teste si le type en sommet de pile correspond � l'op�rande utilis� pour une n�gation
	 * 
	 * @see Expression#lasOpNeg
	 */
	public void testNeg() {
		String op = "";
		int p = this.opera.peek();

		//on v�rifie le type d'op�ration � effectuer
		switch(p) {
		case NOT : op="non logique";break;
		case SUBNEG : op="moins unaire";break;
		}

		//on v�rifie le nombre d'arguments dans la pile des types
		if (this.type.size() >= 1) {
			int type = this.type.peek();
			//on v�rifie que le type de l'argument correspond � l'op�rateur.
			if (!((type == YakaConstants.ENTIER && p == SUBNEG) || (type == YakaConstants.BOOLEEN && p == NOT ))){
				if (type != YakaConstants.ERREUR) {
					empileType(YakaConstants.ERREUR);
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
	 * Appel � la m�thode iload de YVMasm si l'ident est une variable et iconst si c'est une constante
	 * 
	 * @see Expression#type
	 * @see YVMasm
	 * @see TabIdent
	 * @see Ident
	 * @param id
	 */
	public void loadIdent(String id) {
		System.out.println(id + " : " + this.lastFun);
		Ident ident = Yaka.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe var_param_const
			if (ident.isVar()) {// ident est une variableOuParam
				int offset =((IdVar) ident).getOffset();
				if(offset < 0){//ident_var
					int index=-1*offset/2 - 1;// index de la variable dans la pile des variables
					if (Yaka.tabident.var.get(index)!=-1) {// la variable a �t� d�j� d�finie
						Yaka.yvm.iload(offset);
					}
					else {// variable non d�finie
						Erreur.message("La variable '" + id + "' n'est pas encore d�finie");
					}
				}
				else{//ident_param
					Yaka.yvm.iload(offset);
				}
			}
			else {// ident est une constante
				Yaka.yvm.iconst(((IdConst) ident).getValeur());
			}
		}
		else {
			ident = Yaka.tabident.chercheIdentG(id);
			if(ident!=null){// ident existe fonc
				//Yaka.yvm.call(id);
			}
			else {// l'ident n'existe pas
				Erreur.message("La variable ou la constante '" + id + "' n'existe pas");
				this.type.push(YakaConstants.ERREUR);
			}
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
		Ident ident = Yaka.tabident.chercheIdent(id);
		if (ident != null) {// l'ident existe
			if (ident.isVar()) {// ident est une variableOuParam
				int type = this.type.peek();
				if (type == ident.getType()) {// teste si l'expression a le m�me type que l'ident � affecter
					int offset = ((IdVar) ident).getOffset();
					if(offset<0){//ident_var
						int index = -1 * offset / 2 - 1;
						Yaka.yvm.istore(offset);
						Yaka.tabident.var.set(index, 1);
					}
					else{//ident_param
						Erreur.message("L'identificateur '" + id + "' n'est pas une variable");
					}
				}
				else if (type != YakaConstants.ERREUR){// si le type en sommet de la pile est erreur, on �crit pas le msg d'erreur
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
	 * Appel � une des m�thodes d'addition de YVMasm
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
	 * Appel � une des m�thodes de multiplication de YVMasm
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
	 * Appel � une des m�thodes de n�gation de YVMasm
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
	 * Appel � la m�thode lirEnt de YVMasm ssi la variable est de type entier
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
		int type = this.type.peek();
		if (type == YakaConstants.ENTIER) {
			Yaka.yvm.ecrireEnt();
		}
		else if (type == YakaConstants.BOOLEEN) {
			Yaka.yvm.ecrireBool();
		}
	}

	/**
	 * Si le type en sommet de pile n'est pas bool�en, un message d'erreur est affich�
	 * 
	 * @see Expression#type
	 */
	public void testBool() {
		int type = this.type.peek();
		if (type != YakaConstants.BOOLEEN) {
			Erreur.message("Le type de l'expression dans une conditionnelle doit �tre bool�en");
		}
	}


	public void testParamFonc() {
		int typeExpr=this.type.peek();
		int index = Yaka.tabident.paramTest.peek();
		int nbParam = IdFonc.param.size();
		if(index+1<=nbParam){//nbParam est encore >= nb expr entrees en param 
			int typeParam=IdFonc.param.get(index);
			if (typeExpr != typeParam){//types de param et expr sont differents
				Erreur.message("Le type du param�tre " + index + " doit �tre de type " + typeParam);
			}
		}
	}

	public void testInFunc() {
		if (!Yaka.declaration.inFunc){
			Erreur.message("L'instruction RETOURNE ne s'applique que dans les fonctions");
		}
	}

	public void testTypeExprFunc() {
		int typeExpr = this.type.peek();
		int typeFunc = Yaka.declaration.idFonc.getType();
		if(typeExpr != typeFunc){
			Erreur.message("La valeur � retourner doit �tre de type " + typeFunc);
		}
	}

	public void returnFun() {
		int nbParam = Yaka.tabident.nbParam();
		int offset = nbParam*2+4;
		Yaka.yvm.ireturn(offset);
	}
	/*public void returnFun(String id) {
		if(Yaka.tabident.existeIdentG(id)){
			IdFonc func=(IdFonc) Yaka.tabident.chercheIdentG(id);
			int offset = func.nbParam*2+4;
			Yaka.yvm.ireturn(offset);
		}
	}*/

	public void fermeBloc(String id) {
		if(Yaka.tabident.existeIdentG(id)){
			IdFonc func=(IdFonc) Yaka.tabident.chercheIdentG(id);
			int nbOctet = func.nbParam*2;
			Yaka.yvm.fermeBloc(nbOctet);
		}
	}
}
