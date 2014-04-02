import java.util.Stack;


public class Fonction implements YakaConstants{
	/**
	 * Pile des fonctions
	 */
	private Stack<String> lastFun;
	


	/**
	 * Flag permettant de savoir si on est dans une fonction lors de son utilisation
	 */
	private Stack<Boolean> inFunTest;
	

	/**
	 * Flag permettant de savoir si on est dans une fonction lors de sa déclaration
	 */
	boolean inFunc;

	public Fonction() {
		this.lastFun = new Stack<String>();
		this.inFunTest = new Stack<Boolean>();
		this.inFunTest.push(false);
	}
	
	/*public Stack<String> getLastFun(){
		return this.lastFun;
	}*/
	
	public Stack<String> getLastFun() {
		return lastFun;
	}
	
	public void newFun(String id) {
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
				Yaka.expression.pushType((Yaka.tabident.chercheIdentG(id)).getType());
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
	
	public void testInFunc() {
		if (!this.inFunc){
			Erreur.message("L'instruction RETOURNE ne s'applique que dans les fonctions");
		}
	}
	
	public void returnFun() {
		int nbParam = Yaka.tabident.nbParam();
		int offset = nbParam*2+4;
		Yaka.yvm.ireturn(offset);
	}
	
	/**
	 * Permet de modifier l'attribut inFunc  
	 * @param b
	 * 
	 * @see Declaration#inFunc
	 */
	public void inFunction(boolean b) {
		this.inFunc=b;
	}
	
	public void fermeBloc(String id) {
		if(Yaka.tabident.existeIdentG(id)){
			IdFonc func=(IdFonc) Yaka.tabident.chercheIdentG(id);
			int nbOctet = func.nbParam*2;
			Yaka.yvm.fermeBloc(nbOctet);
		}
	}
}
