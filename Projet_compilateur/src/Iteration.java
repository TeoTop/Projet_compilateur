import java.util.Stack;


public class Iteration implements YakaConstants{
	/**
	 * La piles des boucles
     * @see Expression#Expression()
     * @see Expression#addTantQue()
     * @see Expression#removeTantQue()
	 */
	private Stack<Integer> pileTQ;
	
	/**
	 * Compteur de boucles
     * @see Expression#Expression()
     * @see Expression#addTantQue()
	 */
	private int comptTQ;

	public Iteration() {
		this.pileTQ = new Stack<Integer>();
		this.comptTQ = 1;
	}
	
	/**
	 * Empiler les itérations et d'incrementer le nombre d'itérations présentes dans le code YAKA 
	 * 
	 * @see Expression#pileTQ
	 * @see Expression#comptTQ
	 */
	public void addTantQue() {
		this.pileTQ.push(this.comptTQ++);
	}
	
	/**
	 * Appel à la méthode ecrireEtiqu de YVMasm pour écrire l'étiquette faire
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFaire() {
		Yaka.yvm.ecrireEtiqu("Faire" + this.pileTQ.peek());
	}
	
	/**
	 * Appel à la méthode iffaux de YVMasm
	 * 
	 * @see YVMasm 
	 */
	public void ecrireIffauxFait() {
		Yaka.yvm.iffaux("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * Appel à la méthode Goto de YVMasm
	 * 
	 * @see YVMasm 
	 */
	public void ecrireGotoFaire() {
		Yaka.yvm.Goto("FAIRE" + this.pileTQ.peek());
	}
	
	/**
	 * Appel à la méthode ecrireEtiqu de YVMasm pour écrire l'étiquette fait
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFait() {
		Yaka.yvm.ecrireEtiqu("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * Dépiler la pile des itérations
	 * 
	 * @see Expression#pileTQ
	 */
	public void removeTantQue() {
		this.pileTQ.pop();
	}
	
	
}
