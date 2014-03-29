import java.util.Stack;


public class Conditionnelle implements YakaConstants{
	/**
	 * La piles des conditionnelles
     * @see Expression#Expression()
     * @see Expression#addSi()
     * @see Expression#removeSi()
	 */
	private Stack<Integer> pileSI;
	
	/**
	 * Compteur de conditionnelles
     * @see Expression#Expression()
     * @see Expression#addSi()
	 */
	private int comptSI;

	public Conditionnelle() {
		this.pileSI = new Stack<Integer>();
		this.comptSI = 1;
	}
	
	/**
	 * Empiler les conditionnelles et d'incrementer le nombre de conditionnelles présentes dans le code YAKA 
	 * 
	 * @see Expression#pileSI
	 * @see Expression#comptSI
	 */
	public void addSi() {
		this.pileSI.push(this.comptSI++);
	}
	
	/**
	 * Appel à la méthode iffaux de YVMasm
	 * 
	 * @see YVMasm
	 */
	public void ecrireIffauxSinon() {
		Yaka.yvm.iffaux("SINON" + this.pileSI.peek());
	}
	
	/**
	 * Appel à la méthode Goto de YVMasm
	 * 
	 * @see YVMasm
	 */
	public void ecrireGotoFSI() {
		Yaka.yvm.Goto("FSI" + this.pileSI.peek());
	}
	
	/**
	 * Appel à la méthode ecrireEtiqu de YVMasm pour écrire l'étiquette sinon
	 * 
	 * @see YVMasm
	 */
	public void ecrireSinon() {
		Yaka.yvm.ecrireEtiqu("SINON" + this.pileSI.peek());
	}
	
	/**
	 * Appel à la méthode ecrireEtiqu de YVMasm pour écrire l'étiquette fsi
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFsi() {
		Yaka.yvm.ecrireEtiqu("FSI" + this.pileSI.peek());
	}
	
	/**
	 * Dépiler la pile des conditionnelles
	 * 
	 * @see Expression#pileSI
	 */
	public void removeSi() {
		this.pileSI.pop();
	}
	
}
