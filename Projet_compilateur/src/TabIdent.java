import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;


public class TabIdent {
	private HashMap<String,Ident> table;
	public Stack<Integer> var;/*pile des variables */

	public TabIdent(){
		table = new HashMap<String,Ident>();
		var = new Stack<Integer>();
	}

	public Ident chercheIdent(String clef){
		return table.get(clef);
	}

	public boolean existeIdent(String clef){
		return table.get(clef) != null;

	}

	public void rangeIdent(String clef, Ident id){
		if (!existeIdent(YakaTokenManager.identLu)) { /* identLu = id ? */
			if (id.isVar()){
				int offset = ( -2 * nbVar() ) - 2;
				((IdVar) id).setOffset(offset);;
			}
			table.put(clef, id);
			var.push(-1);
		}
		else {
			Erreur.message("Déclaration double pour : " + clef);
		}


	}
	public int nbVar() {
		int nb = 0;
		Set<String> cles = table.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
			Object cle = it.next();
			Ident valeur = table.get(cle);
			if(valeur.isVar()){
				nb ++;
			}
		}
		return nb;
	}

	@Override
	public String toString() {
		return "TabIdent [table=" + table + "]";
	}
}
