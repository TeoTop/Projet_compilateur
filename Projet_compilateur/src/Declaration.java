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
 * Classe permettant de d�clarer et d'ajouter les variables ou les constantes du code YAKA. 
 * 
 * @see Ident
 */
public class Declaration {
	/**
	 * Objet de la classe Ident permettant de d�finir si c'est une constante ou une variable ou un param�tre 
	 * de type entier ou bool�en.
	 * 
	 * @see Ident
	 * @see TabIdent
	 */
	Ident id;
	
	/**
	 * Objet de la classe IdFonc permettant de d�finir une fonction
	 * 
	 * @see IdFonc
	 * @see TabIdent
	 */
	IdFonc idFonc;
	
	/**
	 * Correspond au type de la variable ou de la constante dans le code YAKA (soit entier
	 * soit bool�en).
	 */
	int type;

	
	/**
	 * Permet d'ajouter une variable du code � la table des idents en fonction de son nom.
	 * @param ident
	 * 
	 * @see TabIdent#rangeIdent(String, Ident)
	 */
	public void ajoutNomVariable(String ident){
		Yaka.tabident.rangeIdent(ident,this.id);
	}
	/**
	 * Permet d'ajouter une fonction � la table des idents globaux.
	 * @param ident
	 * 
	 * @see TabIdent#addFonction(String nom)
	 */
	public void ajoutNomFonction(String ident){
		Yaka.tabident.addFonction(ident,this.idFonc);
	}
	/**
	 * Permet d'ajouter un param�tre � la table des param�tres temporaires
	 * @param ident
	 * 
	 * @see TabIdent#addParam(String, Ident)
	 */
	public void addParam(String ident){
		Yaka.tabident.addParam(ident,this.id);
	}
	/**
	 * Permet de cr�er un nouvel objet de la classe IdParam correspondant � une variable du
	 * code YAKA et d'initialiser le type de la variable.
	 * @param type
	 * 
	 * @see Declaration#id
	 * @see Declaration#type
	 * @see IdParam#setType(String)
	 */
	public void ajoutParam(int type){
		this.id = new IdParam();
		this.type = type;
		this.id.setType(type);
	}
	
	/**
	 * Permet de cr�er un nouvel objet de la classe IdVar correspondant � une variable du
	 * code YAKA et d'initialiser le type de la variable.
	 * @param type
	 * 
	 * @see Declaration#id
	 * @see Declaration#type
	 * @see IdVar#setType(String)
	 */
	public void ajoutVariableParTYPE(int type){
		this.id = new IdVar();
		this.type = type;
		this.id.setType(type);
	}
	/**
	 * Permet de cr�er un nouvel objet de la classe IdFonc correspondant � une fonction du
	 * code YAKA et d'initialiser le type de la fonction.
	 * @param type
	 * 
	 * @see Declaration#id
	 * @see Declaration#type
	 * @see IdFonc#setType(String)
	 */
	public void ajoutFonction(int type){
		this.idFonc = new IdFonc();
		this.type = type;
	  	this.idFonc.setType(type);
	}
	
	/**
	 * Permet de cr�er un nouvel objet de la classe IdVar correspondant � une variable du
	 * code YAKA et d'initialiser le type de la variable puis de la placer dans le tableau
	 * des idents de la classe TabIdent.
	 * @param ident
	 * 
	 * @see Declaration#id
	 * @see Declaration#type
	 * @see IdVar#setType(String)
	 * @see TabIdent#rangeIdent(String, Ident)
	 */
	public void ajoutNomVariableSecondaire(String ident){
		this.id = new IdVar();
		this.id.setType(this.type);
		Yaka.tabident.rangeIdent(ident,this.id);
	}
	
	/**
	 * Permet d'ajouter une constante du code � la table des idents en fonction de son nom.
	 * @param ident
	 * 
	 * @see Declaration#id
	 * @see TabIdent#rangeIdent(String, Ident)
	 */
	public void ajoutNomConstante(String ident){
		this.id = new IdConst();
		Yaka.tabident.rangeIdent(ident,this.id);
	}
	
	/**
	 * Permet de cr�er un nouvel objet de la classe IdConst correspondant � partir d'une
	 * constante d�j� d�finie dont le nom est pass� en param�tre. Pour ce faire, la m�thode 
	 * v�rifie dans un premier temps que la constante existe dans la tableau de idents de 
	 * la classe TabIdent.
	 * @param ident
	 * 
	 * @see Declaration#id
	 * @see IdConst#setType(String)
	 * @see IdConst#setValeur(int)
	 * @see TabIdent#chercheIdent(String)
	 */
	public void ajoutConstanteParConstante(String ident){
		/*on v�rifie que la constante est dans le tableau puis on la r�cup�re pour la copier,
		sinon on d�clenche une erreur.*/
	  	IdConst idConst = (IdConst) Yaka.tabident.chercheIdent(ident);
	  	if (idConst != null){
	  		((IdConst) this.id).setValeur(idConst.getValeur());
	  		this.id.setType(idConst.getType());
	  	}
	  	else {
	  		this.id.setType(YakaConstants.ERREUR);
	  		Erreur.message("La constante `" + ident + "` n'existe pas");
	  	}
	}
	
	/**
	 * Permet de cr�er une constante de type ENTIER dont la valeur est pass� en param�tre.
	 * @param entier
	 * 
	 * @see Declaration#id
	 * @see IdConst#setValeur(int)
	 * @see IdConst#setType(String)
	 */
	public void ajoutConstanteParEntier(int entier){
		((IdConst) this.id).setValeur(entier);
	  	this.id.setType(YakaConstants.ENTIER);
	}
	
	/**
	 * Permet de cr�er une constante de type ENTIER dont la valeur est pass� en param�tre.
	 * @param entier
	 * 
	 * @see Declaration#id
	 * @see IdConst#setValeur(int)
	 * @see IdConst#setType(String)
	 */
	public void ajoutConstanteParBooleen(int entier){
		((IdConst) this.id).setValeur(entier);
	  	this.id.setType(YakaConstants.BOOLEEN);
	}
	

	
}
