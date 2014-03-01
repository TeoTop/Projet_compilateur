
public abstract class YVM {
	
	public abstract void entete();
	
	public abstract void ouvrePrinc(int offset);
	
	public abstract void isub();
	
	public abstract void iadd();
	
	public abstract void imul();
	
	public abstract void idiv();
	
	public abstract void ineg();
	
	public abstract void iinf();
	
	public abstract void iegal();
	
	public abstract void ior();
	
	public abstract void iload(int offset);
	
	public abstract void istore(int offset);
	
	public abstract void iconst(int valeur);
	
	public abstract void ifeq();
	
	public abstract void isup();
	
	public abstract void iinfegal();
	
	public abstract void isupegal();
	
	public abstract void idiff();
	
	public abstract void iffaux();
	
	public abstract void Goto();
	
	public abstract void queue();
	
	public abstract void ecrireEnt();

	public abstract void ecrireChaine(String x);

	public abstract void ecrireBool();

	public abstract void lireEnt(int x);
}
