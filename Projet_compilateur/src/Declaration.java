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
	 * Objet de la classe Ident permettant de d�finir si c'est une constante ou une variable
	 * de type entier ou bool�en.
	 * 
	 * @see Ident
	 * @see TabIdent
	 */
	Ident id;
	
	/**
	 * Correspond au type de la variable ou de la constante dans le code YAKA (soit entier
	 * soit bool�en).
	 */
	String type;
	
	/**
	 * Permet d'ajouter une variable du code � la table des idents en fonction de son nom.
	 * @param ident
	 * 
	 * @see TabIdent#rangeIdent(String, Ident)
	 */
	public void ajoutNomVariable(String ident){
		YakaTokenManager.tabident.rangeIdent(ident,this.id);
	}
	
	/**
	 * Permet de cr�er un nouvel objet de la classe IdVar correspondant � une variable du
	 * code YAKA et d'initialiser le type de la variable.
	 * @param type
	 * 
	 * @see IdVar#setType(String)
	 */
	public void ajoutVariableParTYPE(String type){
		this.id = new IdVar();
		this.type = type;
	  	this.id.setType(type);
	}
	
	/**
	 * Permet de cr�er un nouvel objet de la classe IdVar correspondant � une variable du
	 * code YAKA et d'initialiser le type de la variable puis de la placer dans le tableau
	 * des idents de la classe TabIdent.
	 * @param ident
	 * 
	 * @see IdVar#setType(String)
	 * @see TabIdent#rangeIdent(String, Ident)
	 */
	public void ajoutNomVariableSecondaire(String ident){
		this.id = new IdVar();
		this.id.setType(this.type);
		YakaTokenManager.tabident.rangeIdent(ident,this.id);
	}
	
	/**
	 * Permet d'ajouter une constante du code � la table des idents en fonction de son nom.
	 * @param ident
	 * 
	 * @see TabIdent#rangeIdent(String, Ident)
	 */
	public void ajoutNomConstante(String ident){
		this.id = new IdConst();
		YakaTokenManager.tabident.rangeIdent(ident,this.id);
	}
	
	/**
	 * Permet de cr�er un nouvel objet de la classe IdConst correspondant � partir d'une
	 * constante d�j� d�finie dont le nom est pass� en param�tre. Pour ce faire, la m�thode 
	 * v�rifie dans un premier temps que la constante existe dans la tableau de idents de 
	 * la classe TabIdent.
	 * @param ident
	 * 
	 * @see IdConst#setType(String)
	 * @see IdConst#setValeur(int)
	 * @see TabIdent#chercheIdent(String)
	 */
	public void ajoutConstanteParConstante(String ident){
		/*on v�rifie que la constante est dans le tableau puis on la r�cup�re pour la copier,
		sinon on d�clenche une erreur.*/
	  	IdConst idConst = (IdConst) YakaTokenManager.tabident.chercheIdent(ident);
	  	if (idConst != null){
	  		((IdConst) this.id).setValeur(idConst.getValeur());
	  		this.id.setType(idConst.getType());
	  	}
	  	else {
	  		this.id.setType("erreur");
	  		Erreur.message("La constante `" + ident + "` n'existe pas");
	  	}
	}
	
	/**
	 * Permet de cr�er une constante de type ENTIER dont la valeur est pass� en param�tre.
	 * @param entier
	 * 
	 * @see IdConst#setValeur(int)
	 * @see IdConst#setType(String)
	 */
	public void ajoutConstanteParEntier(int entier){
		((IdConst) this.id).setValeur(entier);
	  	this.id.setType("ENTIER");
	}
	
	/**
	 * Permet de cr�er une constante de type ENTIER dont la valeur est pass� en param�tre.
	 * @param entier
	 * 
	 * @see IdConst#setValeur(int)
	 * @see IdConst#setType(String)
	 */
	public void ajoutConstanteParBooleen(int entier){
		((IdConst) this.id).setValeur(entier);
	  	this.id.setType("BOOLEEN");
	}
	
}
