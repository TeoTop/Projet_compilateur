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
	 * Empiler les it�rations et d'incrementer le nombre d'it�rations pr�sentes dans le code YAKA 
	 * 
	 * @see Expression#pileTQ
	 * @see Expression#comptTQ
	 */
	public void addTantQue() {
		this.pileTQ.push(this.comptTQ++);
	}
	
	/**
	 * Appel � la m�thode ecrireEtiqu de YVMasm pour �crire l'�tiquette faire
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFaire() {
		Yaka.yvm.ecrireEtiqu("Faire" + this.pileTQ.peek());
	}
	
	/**
	 * Appel � la m�thode iffaux de YVMasm
	 * 
	 * @see YVMasm 
	 */
	public void ecrireIffauxFait() {
		Yaka.yvm.iffaux("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * Appel � la m�thode Goto de YVMasm
	 * 
	 * @see YVMasm 
	 */
	public void ecrireGotoFaire() {
		Yaka.yvm.Goto("FAIRE" + this.pileTQ.peek());
	}
	
	/**
	 * Appel � la m�thode ecrireEtiqu de YVMasm pour �crire l'�tiquette fait
	 * 
	 * @see YVMasm 
	 */
	public void ecrireFait() {
		Yaka.yvm.ecrireEtiqu("FAIT" + this.pileTQ.peek());
	}
	
	/**
	 * D�piler la pile des it�rations
	 * 
	 * @see Expression#pileTQ
	 */
	public void removeTantQue() {
		this.pileTQ.pop();
	}
	
	
}
