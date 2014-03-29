import java.util.Stack;


public class IdFonc extends Ident {

	/**
	 * Pile permettant de stocker les types de paramètres.
	 */
	static Stack<Integer> param;
	
	/**
	 * Attribut permttant de connaitre le nombre de paramètre.
	 */
	public int nbParam;
	
	@Override
	public String toString() {
		return "IdFonc [param=" + param + ", nbParam=" + nbParam + "]";
	}

	public IdFonc(){
		param = new Stack<Integer>();
	}
	
	@Override
	public boolean isVar() {
		return false;
	}

	@Override
	public boolean isFonc() {
		return true;
	}

	public Stack<Integer> getParam() {
		return param;
	}

	public void ajoutTypeParam(int param) {
		this.param.push(param);
	}
	

}
