import java.util.Stack;


public class IdFonc extends Ident {

	static Stack<String> param;
	public int nbParam;
	@Override
	public String toString() {
		return "IdFonc [param=" + param + ", nbParam=" + nbParam + "]";
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
