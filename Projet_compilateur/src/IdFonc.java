import java.util.Stack;


public class IdFonc extends Ident {

	/**
	 * Pile permettant de stocker les paramètres.
	 */
	static Stack<String> param;
	
	/**
	 * Attribut permttant de connaitre le nombre de paramètre.
	 */
	public int nbParam;
	
	@Override
	public String toString() {
		return "IdFonc [param=" + param + "]";
	}

	public IdFonc(){
		param = new Stack<String>();
	}
	
	@Override
	public boolean isVar() {
		return false;
	}

	@Override
	public boolean isFonc() {
		return true;
	}

	public Stack<String> getParam() {
		return param;
	}

	public void ajoutTypeParam(String param) {
		this.param.push(param);
	}
	

}
