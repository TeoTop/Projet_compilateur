import java.util.Stack;


public class Expression {
	private Stack<String> type;
	private Stack<String> opera;
	
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
			Erreur.erreurIdentNonExiste(id);
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
					Erreur.erreurType(type1,type2,"multiplication");
					empileType("erreur");
				}
			}
			this.opera.pop();
		}/* ajouter un else */
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
					Erreur.erreurType(type1,type2,"addition");
					empileType("erreur");
				}
			}
			this.opera.pop();
		}/* ajouter un else */
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
	
}
