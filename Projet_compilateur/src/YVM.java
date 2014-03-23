/**
 * 
 * @author CHAPON Theo
 * @author El Omari Alaoui Hassan
 * @author Marchais Julien
 * @author Mesdouri Marouan 
 * @author Tuekam Sandjon Marlene
 * 
 * @version 1.0
 * 
 * Classe permettant de regrouper les fonctions contenue dans le code YAKA
 * 
 * @see YVMasm
 */
public abstract class YVM {

	public abstract void isub();

	public abstract void iadd();

	public abstract void imul();

	public abstract void idiv();

	public abstract void ineg();

	public abstract void iinf();

	public abstract void iegal();

	public abstract void ior();

	public abstract void iand();

	public abstract void inot();

	public abstract void iload(int offset);

	public abstract void istore(int offset);

	public abstract void iconst(int valeur);

	public abstract void ifeq(String etiq);

	public abstract void isup();

	public abstract void iinfegal();

	public abstract void isupegal();

	public abstract void idiff();

	public abstract void iffaux(String etiq);

	public abstract void Goto(String etiq);
	
	public abstract void recopierEntete(String fichierOut);

	public abstract void queue();

	public abstract void ecrireEnt();

	public abstract void ecrireChaine(String x);

	public abstract void ecrireBool();

	public abstract void lireEnt(int x);
	
	public abstract void aLaLigne();

	public abstract void ecrireEtiqu(String string);
	
	public abstract void ouvbloc(int nbOctet);

	public abstract void fermeBloc(int nbOctet);

	public abstract void call(String id);

	public abstract void reserveRetour();
	
	public abstract void ireturn(int offset);

	public abstract void debut();
	
}
