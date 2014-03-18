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
public class Declaration {

	Ident id;
	String type;
	
	/**
	 * 
	 */
	public Declaration() {
		super();
	}
	
	/**
	 * 
	 * @param ident
	 */
	public void ajoutNomVariable(String ident){
		YakaTokenManager.tabident.rangeIdent(ident,id);
	}
	
	/**
	 * 
	 * @param type
	 */
	public void ajoutVariableParTYPE(String type){
		id = new IdVar();
		this.type = type;
	  	id.setType(type);
	}
	
	/**
	 * 
	 * @param ident
	 */
	public void ajoutNomVariableSecondaire(String ident){
		id = new IdVar();
		id.setType(this.type);
		YakaTokenManager.tabident.rangeIdent(ident,id);
	}
	
	/**
	 * 
	 * @param ident
	 */
	public void ajoutNomConstante(String ident){
		id = new IdConst();
		YakaTokenManager.tabident.rangeIdent(ident,id);
	}
	
	/**
	 * 
	 * @param ident
	 */
	public void ajoutConstanteParConstante(String ident){
	  	IdConst idConst = (IdConst) YakaTokenManager.tabident.chercheIdent(ident);
	  	if (idConst != null){
	  		((IdConst) id).setValeur(idConst.getValeur());
	  		id.setType(idConst.getType());
	  	}
	  	else {
	  		id.setType("erreur");
	  		//Erreur.erreurDeclarationConstante(ident);
	  		Erreur.message("La constante `" + ident + "` n'existe pas");
	  	}
	}
	
	/**
	 * 
	 * @param entier
	 */
	public void ajoutConstanteParEntier(int entier){
		((IdConst) id).setValeur(entier);
	  	id.setType("ENTIER");
	}
	
	/**
	 * 
	 * @param entier
	 */
	public void ajoutConstanteParBooleen(int entier){
		((IdConst) id).setValeur(entier);
	  	id.setType("BOOLEEN");
	}
	
}
