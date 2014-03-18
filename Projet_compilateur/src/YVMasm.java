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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class YVMasm extends YVM {
	/* fichier de sortie */
	private OutputStream fichier;
	/* compteur de nombre de message pour la creation de variable pour la fonction ecrireChaine */
	private int comptString;
	/* liste qui contiendra les fonctions nï¿½cessaires : lirent, ecrent, ecrbool, ecrch, ligsuiv. */
	private ArrayList<String> extern;
	
	/**
	 * 
	 */
	public YVMasm() {
		super();
		this.fichier = Ecriture.ouvrir("yvm.tmp"); /* ecrire dans un fichier tmp et puis le copier dans l'autre fichier ssi il n'y a pas d'erreur */
		this.comptString = 0;
		this.extern = new ArrayList<String>();
	}

	@Override
	/**
	 * Transformation d'un code YAKA de soustraction en code ASM
	 */
	public void isub() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isub");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tsub ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	@Override
	/**
	 * Transformation d'un code YAKA de negation en code ASM
	 */
	public void ineg() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ineg");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tpush dx");
		Ecriture.ecrireStringln(this.fichier,  "\tmov dx,-1");
		Ecriture.ecrireStringln(this.fichier,  "\tmul dx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop dx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	@Override
	/**
	 * Transformation d'un code YAKA conditionnel inférieur en code ASM
	 */
	public void iinf() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iinf");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjge $+6");
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	/**
	 * Transformation d'un code YAKA conditionnel d'égalité en code ASM
	 */
	public void iegal() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iegal");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjne $+6");
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant le chargement d'une variable à l'offset 'offset' en code ASM
	 * @param offset
	 */
	public void iload(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iload " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush word ptr [bp" + offset + "]");
	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant le stockage d'une variable à l'offset 'offset' en code ASM
	 * @param offset
	 */
	public void istore(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;istore " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmov word ptr [bp" + offset + "],ax");
	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant le stockage d'une valeur avec la valeur 'valeur' en code ASM
	 * @param valeur
	 */
	public void iconst(int valeur) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iconst " + valeur + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush " + valeur + "");
	}


	@Override
	/**
	 * Transformation d'un code YAKA permettant de comparer la valeur se trouvant en sommet de pile à 0, si égale
	 * alors go to vers étiquette en code ASM
	 * @param etiq
	 */
	public void ifeq(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ifeq " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);
	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de comparer la valeur se trouvant en sommet de pile à 0, si non égale
	 * alors go to vers étiquette en code ASM
	 * @param etiq
	 */
	public void iffaux(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iffaux " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant d'aller exécuter les instructions se trouvant juste après l'étiquette en code ASM
	 * @param etiq
	 */
	public void Goto(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;goto " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tjmp " + etiq);
	}


	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une addition en code ASM
	 */
	public void iadd() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iadd");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tadd ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une multiplication en code ASM
	 */
	public void imul() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;imul");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmul bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une division en code ASM
	 */
	public void idiv() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;idiv");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier , "\tcwd"); /* dx = 0 ou -1 */
		Ecriture.ecrireStringln(this.fichier,  "\tdiv bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une comparaison supérieur en code ASM
	 */
	public void isup() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isup");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjle $+6");
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une comparaison inférieur ou égale en code ASM
	 */
	public void iinfegal() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iinfegal");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjg $+6");
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");



	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une comparaison supérieur ou égale en code ASM
	 */
	public void isupegal() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isupegal");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjl $+6");
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser une comparaison de différence en code ASM
	 */
	public void idiff() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;idiff");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tje $+6");
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");
	}

	@Override
	/**
	 * Permet d'écrire en ASM l'entete necessaire pour exécuter le code ASM
	 * @ param fichierOut
	 */
	public void recopierEntete(String fichierOut) {
		// TODO Auto-generated method stub
		this.fichier = Ecriture.ouvrir(fichierOut);
		Ecriture.ecrireStringln(this.fichier,  ";entete");
		Ecriture.ecrireString(this.fichier, "extrn ");
		
		Iterator<String> ite = this.extern.iterator();
		while(ite.hasNext()){
			String fonction = ite.next();
			if(ite.hasNext())
				Ecriture.ecrireString(this.fichier, fonction + ":proc, ");
			else
				Ecriture.ecrireStringln(this.fichier, fonction + ":proc");
		}

		Ecriture.ecrireStringln(this.fichier,  ".MODEL SMALL");
		Ecriture.ecrireStringln(this.fichier,  ".586");
		Ecriture.ecrireStringln(this.fichier,  ".CODE");
		Ecriture.ecrireStringln(this.fichier,  "debut:");
		Ecriture.ecrireStringln(this.fichier,  "\tSTARTUPCODE");
	}

	@Override
	/**
	 * Permet d'écrire en ASM la fin du fichier ASM necessaire pour être exécuter
	 */
	public void queue() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;queue");
		Ecriture.ecrireStringln(this.fichier,  "\tnop");
		Ecriture.ecrireStringln(this.fichier,  "\tEXITCODE");
		Ecriture.ecrireStringln(this.fichier,  "End debut");
		Ecriture.fermer(this.fichier);
	}

	@Override
	/**
	 * Permet de reserver nbOctets pour les variables
	 * @param nbOctet
	 */
	public void ouvrePrinc(int nbOctet) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ouvrePrinc " + nbOctet);
		Ecriture.ecrireStringln(this.fichier,  "\tmov bp,sp");
		Ecriture.ecrireStringln(this.fichier , "\tsub sp," + nbOctet);
	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser un OU en code ASM
	 */
	public void ior() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ior");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tor ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser un NON en code ASM
	 */
	public void inot() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;inot");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tnot ax");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	@Override
	/**
	 * Transformation d'un code YAKA permettant de réaliser un ET en code ASM
	 */
	public void iand() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;and");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tand ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	/* entrÃ©e, sortie */
	@Override
	/**
	 * Permet d'écrire un entier depuis le clavier
	 */
	public void ecrireEnt() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireEnt");
		Ecriture.ecrireStringln(this.fichier,"\tcall ecrent");
		if(!extern.contains("ecrent")) this.extern.add("ecrent");
	}

	@Override
	/**
	 * Permet d'afficher une chaine sur la sortie courante
	 * @param x
	 */
	public void ecrireChaine(String x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireChaine " + x);
		Ecriture.ecrireStringln(this.fichier, ".DATA\n\tmess" + this.comptString + " DB \"" + x.substring(1, x.length() - 1) + "$\""); /* enlever le dernier guillemet afin d'ajouter $ */
		Ecriture.ecrireStringln(this.fichier, ".CODE\n\tlea dx,mess" + this.comptString + "\n\tpush dx\n\tcall ecrch");
		this.comptString++;
		if(!extern.contains("ecrch")) this.extern.add("ecrch");
	}

	@Override
	/**
	 * Permet d'écrire un booleen depuis le clavier
	 */
	public void ecrireBool() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireBool");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ecrbool");
		if(!extern.contains("ecrbool")) this.extern.add("ecrbool");
	}

	@Override
	/**
	 * Permet d'afficher un entier sur la sortie courante
	 * @param x
	 */
	public void lireEnt(int x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;lireEnt " + x);
		Ecriture.ecrireStringln(this.fichier,  "\tlea dx,[bp" + x + "]");
		Ecriture.ecrireStringln(this.fichier,  "\tpush dx");
		Ecriture.ecrireStringln(this.fichier,  "\tcall lirent");
		if(!extern.contains("lirent")) this.extern.add("lirent");
	}

	@Override
	/**
	 * Permet de réaliser un retour à la ligne
	 */
	public void aLaLigne() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;aLaLigne");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ligsuiv");
		if(!extern.contains("ligsuiv")) this.extern.add("ligsuiv");
	}
	
	
	@Override
	/**
	 * Permet d'écrire une étiquette 'etiq'
	 * @param etiq
	 */
	public void ecrireEtiqu(String etiq){
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t" + etiq + " :");
		
	}
}
