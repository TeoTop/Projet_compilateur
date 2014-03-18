/**
 * 
 * @author CHAPON Theo
 * @author El Omari Alaoui Hassan
 * @author Marchais Julien
 * @author Mesdouri Marouan 
 * @author Tuekam Sandjon Marlene
 * 
 * @version 1.0
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * 
 * 
 *
 */
public class TabIdent {
	
	/**
	 * Table des identificateurs
	 * 
	 * @see TabIdent#TabIdent()
	 */
	private HashMap<String,Ident> table;
	
	/**
	 * Pile des variables
	 * 
	 * @see TabIdent#TabIdent()
	 */
	public Stack<Integer> var;

	/**
	 * Constructeur TabIdent
	 * Création de la table des identificateurs et de la pile des variables
	 * 
	 *  @see TabIdent#table
	 *  @see TabIdent#var
	 */
	public TabIdent(){
		this.table = new HashMap<String,Ident>();
		this.var = new Stack<Integer>();
	}

	/**
	 * Retourne l'ident correspondant à la clef
	 * 
	 * @see TabIdent#table
	 * @param clef
	 * @return ident 
	 */
	public Ident chercheIdent(String clef){
		return this.table.get(clef);
	}

	/**
	 * Retourne true si la clef existe dans la table des identificateurs, false sinon
	 * 
	 * @see TabIdent#table
	 * @param clef
	 * @return true si la clef existe, false sinon
	 */
	public boolean existeIdent(String clef){
		return this.table.get(clef) != null;

	}

	/**
	 * Ajouter les variables et les constantes dans la table des identificateurs
	 * 
	 * @see Ident
	 * @see TabIdent#table
	 * @see TabIdent#var
	 * @param clef
	 * @param id
	 */
	public void rangeIdent(String clef, Ident id){
		if (!existeIdent(clef)) { // clef n'existe pas
			if (id.isVar()){// id est une varialble
				int offset = ( -2 * nbVar() ) - 2;
				((IdVar) id).setOffset(offset);;
				this.var.push(-1);// on empile -1 pour dire que la variable n'a pas encore de valeur
			}
			this.table.put(clef, id);
		}
		else {// clef existe déjà
			Erreur.message("DÃ©claration double pour : " + clef);
		}


	}
	
	/**
	 * Retourne le nombre de variable
	 * @return nombre de variable
	 */
	public int nbVar() {
		/*int nb = 0;
		Set<String> cles = table.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
			Object cle = it.next();
			Ident valeur = table.get(cle);
			if(valeur.isVar()){
				nb ++;
			}
		}
		return nb;*/
		return (this.var.size());
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "TabIdent [table=" + table + "]";
	}
}
