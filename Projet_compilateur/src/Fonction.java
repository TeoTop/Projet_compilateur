import java.util.Stack;


public class Fonction implements YakaConstants{
	/**
	 * Pile des fonctions
	 */
	private Stack<String> lastFun;
	


	/**
	 * Pile de flag permettant de savoir si on est dans une fonction lors de son utilisation
	 */
	private Stack<Boolean> inFunTest;
	

	/**
	 * Flag permettant de savoir si on est dans une fonction lors de sa déclaration
	 */
	boolean inFunc;

	/**
	 * Initialiser les piles de la fonction et la pile des flag
	 * @see Fonction#lastFun
	 * @see Fonction#inFunTest
	 */
	public Fonction() {
		this.lastFun = new Stack<String>();
		this.inFunTest = new Stack<Boolean>();
		this.inFunTest.push(false);
	}
	
	/*public Stack<String> getLastFun(){
		return this.lastFun;
	}*/
	/**
	 * Retourne la pile des fonctions
	 * @return Stack
	 */
	public Stack<String> getLastFun() {
		return lastFun;
	}
	
	/**
	 * Crée une nouvelle fonction en empilant son id dans la pile
	 * des fonctions et en empilant vrai dans la pile des flags
	 * @param id
	 */
	public void newFun(String id) {
		this.lastFun.push(id);
		this.inFunTest.push(true);
	}
	/**
	 * Si on est dans une fonction, on la dépile de la pile des fonctions ainsi que le flag
	 * 
	 */
	public void resetFun() {
		if (this.inFunTest.peek()) {
			this.lastFun.pop();
			this.inFunTest.pop();
		}
	}


	/**
	 * Empile le type de la dernière fonction  si elle est dans la hashmap des globaux, 
	 * et si on est vraiment dans une fonction
	 */
	public void empileTypeFun() {
		if(this.inFunTest.peek()) {
			String id = this.lastFun.peek();
			if (Yaka.tabident.existeIdentG(id)){
				Yaka.expression.empileType((Yaka.tabident.chercheIdentG(id)).getType());
			}		
		}
	}

	/**
	 * Appel à la methode call de YVMasm ssi on est dans une fonction
	 */
	public void call() {
		if(this.inFunTest.peek()) {
			String id = this.lastFun.peek();
			if (Yaka.tabident.existeIdentG(id)){
				Yaka.yvm.call(id);
			}	
		}
	}
	
	/**
	 * Test si on est dans une fonction lors de la déclaration.
	 */
	public void testInFunc() {
		if (!this.inFunc){
			Erreur.message("L'instruction RETOURNE ne s'applique que dans les fonctions");
		}
	}
	/**
	 * Appel à la methode ireturn de YVMasm
	 */
	public void returnFun() {
		int nbParam = Yaka.tabident.nbParam();
		int offset = nbParam*2+4;
		Yaka.yvm.ireturn(offset);
	}
	
	/**
	 * Permet de modifier l'attribut inFunc  
	 * @param b
	 * 
	 * @see Fonction#inFunc
	 */
	public void inFunction(boolean b) {
		this.inFunc=b;
	}
	/**
	 * Appel à la methode ireturn de YVMasm si elle existe dans la Hashmap des globaux
	 * @param id
	 */
	public void fermeBloc(String id) {
		if(Yaka.tabident.existeIdentG(id)){
			IdFonc func=(IdFonc) Yaka.tabident.chercheIdentG(id);
			int nbOctet = func.nbParam*2;
			Yaka.yvm.fermeBloc(nbOctet);
		}
	}
}
