
public class Declaration {

	Ident id;
	String type;
	
	public Declaration() {
		super();
	}
	
	//d�claration des variables
	public void ajoutNomVariable(String ident){
		YakaTokenManager.tabident.rangeIdent(ident,id);
	}
		
	public void ajoutVariableParTYPE(String type){
		id = new IdVar();
		this.type = type;
	  	id.setType(type);
	}
	public void ajoutNomVariableSecondaire(String ident){
		id = new IdVar();
		id.setType(this.type);
		YakaTokenManager.tabident.rangeIdent(ident,id);
	}
	//d�claration des constantes
	public void ajoutNomConstante(String ident){
		id = new IdConst();
		YakaTokenManager.tabident.rangeIdent(ident,id);
	}
	
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
	
	public void ajoutConstanteParEntier(int entier){
		((IdConst) id).setValeur(entier);
	  	id.setType("ENTIER");
	}
	
	public void ajoutConstanteParBooleen(int entier){
		((IdConst) id).setValeur(entier);
	  	id.setType("BOOLEEN");
	}
	
}
