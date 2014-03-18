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
 * La classe YVMasm est �tendue de la classe YVM et a pour but de traduire en assembleur
 * les m�thodes YAKA. 
 * 
 * @see YVM
 */

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class YVMasm extends YVM {
	
	/**
	 * Fichier de sortie temporaire
	 * 
	 * @see Ecriture#YVMasm
	 */
	private OutputStream fichier;
	
	/**
	 *  Compteur permettant de sp�cifier le num�ro du message affich� dans la focntion 
	 *  ecrireChaine 
	 *  
	 *  @see YVMasm#YVMasm
	 */
	private int comptString;
	
	/** 
	 * Liste qui contiendra les fonctions externes n�cessaires : lirent, ecrent, ecrbool, 
	 * ecrch, ligsuiv. 
	 * 
	 * @see YVMasm#YVMasm
	 */
	private ArrayList<String> extern;
	
	/**
	 * Constructeur : permet de cr�er un fichier temporaire dans lequel sera stock� le code
	 * assembleur avant la recopie dans le fichier final si il n'y a pas d'erreur.
	 * 
	 * @see fichier
	 * @see comptString
	 * @see extern
	 * 
	 * @since 1.0
	 */
	public YVMasm() {
		super();
		this.fichier = Ecriture.ouvrir("yvm.tmp");
		this.comptString = 0;
		this.extern = new ArrayList<String>();
	}

	
	/**
	 * Transformation d'un code YAKA de soustraction en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void isub() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isub");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tsub ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Transformation d'un code YAKA de negation en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	/**
	 * Transformation d'un code YAKA conditionnel inf�rieur en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Transformation d'un code YAKA conditionnel d'�galit� en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Transformation d'un code YAKA permettant le chargement d'une variable � l'offset 
	 * pass� en param�tre en code ASM
	 * 
	 * @param offset
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void iload(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iload " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush word ptr [bp" + offset + "]");
	}

	
	/**
	 * Transformation d'un code YAKA permettant le stockage d'une variable � l'offset 
	 * pass� en param�tre en code ASM
	 * 
	 * @param offset
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void istore(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;istore " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmov word ptr [bp" + offset + "],ax");
	}

	
	/**
	 * Transformation d'un code YAKA permettant le stockage d'une constante pass� en 
	 * param�tre en code ASM
	 * 
	 * @param valeur
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void iconst(int valeur) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iconst " + valeur + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush " + valeur + "");
	}


	
	/**
	 * Transformation d'un code YAKA permettant de comparer la valeur se trouvant en sommet 
	 * de pile � 0, si �gale
	 * alors go to vers �tiquette en code ASM
	 * 
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void ifeq(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ifeq " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);
	}

	
	/**
	 * Transformation d'un code YAKA permettant de comparer la valeur se trouvant en sommet 
	 * de pile � 0, si non �gale
	 * alors go to vers �tiquette en code ASM
	 * 
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void iffaux(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iffaux " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);

	}

	
	/**
	 * Transformation d'un code YAKA permettant d'aller ex�cuter les instructions se trouvant
	 *  juste apr�s l'�tiquette en code ASM
	 *  
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void Goto(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;goto " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tjmp " + etiq);
	}


	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une addition en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void iadd() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iadd");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tadd ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une multiplication en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void imul() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;imul");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmul bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une division en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une comparaison sup�rieur en 
	 * code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une comparaison inf�rieur ou 
	 * �gale en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une comparaison sup�rieur ou 
	 * �gale en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser une comparaison de diff�rence 
	 * en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Permet d'�crire en ASM l'entete necessaire pour ex�cuter le code ASM. Cette m�thode
	 * compl�te l'ent�te avec les fonctions externes rassembl�es dans un tableau de chaines. 
	 * 
	 * @param fichierOut
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see Ecriture#ecrireString(OutputStream, String)
	 * @see Ecriture#ouvrir(String)
	 * 
	 * @see fichier
	 * @see extern
	 * 
	 * @since 1.0
	 */
	@Override
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

	
	/**
	 * Permet d'�crire la fin type du fichier ASM necessaire � son ex�cution
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see Ecriture#fermer(OutputStream)
	 * 
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void queue() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;queue");
		Ecriture.ecrireStringln(this.fichier,  "\tnop");
		Ecriture.ecrireStringln(this.fichier,  "\tEXITCODE");
		Ecriture.ecrireStringln(this.fichier,  "End debut");
		Ecriture.fermer(this.fichier);
	}

	
	/**
	 * Permet de reserver nbOctets pour les variables dans la pile (assembleur)
	 * 
	 * @param nbOctet
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void ouvrePrinc(int nbOctet) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ouvrePrinc " + nbOctet);
		Ecriture.ecrireStringln(this.fichier,  "\tmov bp,sp");
		Ecriture.ecrireStringln(this.fichier , "\tsub sp," + nbOctet);
	}

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser un OU en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void ior() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ior");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tor ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser un NON en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void inot() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;inot");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tnot ax");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Transformation d'un code YAKA permettant de r�aliser un ET en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void iand() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;and");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tand ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Permet d'�crire l'entier qui est en sommet de pile
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see fichier
	 * @see extern
	 * 
	 * @since 1.0
	 */
	@Override
	public void ecrireEnt() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireEnt");
		Ecriture.ecrireStringln(this.fichier,"\tcall ecrent");
		if(!extern.contains("ecrent")) this.extern.add("ecrent");
	}

	
	/**
	 * Permet d'afficher la chaine pass� en param�tre
	 * 
	 * @param x
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see fichier
	 * @see extern
	 * @see comptString
	 * 
	 * @since 1.0
	 */
	@Override
	public void ecrireChaine(String x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireChaine " + x);
		Ecriture.ecrireStringln(this.fichier, ".DATA\n\tmess" + this.comptString + " DB \"" + x.substring(1, x.length() - 1) + "$\""); /* enlever le dernier guillemet afin d'ajouter $ */
		Ecriture.ecrireStringln(this.fichier, ".CODE\n\tlea dx,mess" + this.comptString + "\n\tpush dx\n\tcall ecrch");
		this.comptString++;
		if(!extern.contains("ecrch")) this.extern.add("ecrch");
	}

	
	/**
	 * Permet d'�crire le bool�en qui est en sommet de pile
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see fichier
	 * @see extern
	 * 
	 * @since 1.0
	 */
	@Override
	public void ecrireBool() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireBool");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ecrbool");
		if(!extern.contains("ecrbool")) this.extern.add("ecrbool");
	}

	
	/**
	 * Permet de lire un entier plac� � l'offset x en faisant appel � la fonction 
	 * lirent (pr�d�fini en assembleur)
	 * 
	 * @param x
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see fichier
	 * @see extern
	 * 
	 * @since 1.0
	 */
	@Override
	public void lireEnt(int x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;lireEnt " + x);
		Ecriture.ecrireStringln(this.fichier,  "\tlea dx,[bp" + x + "]");
		Ecriture.ecrireStringln(this.fichier,  "\tpush dx");
		Ecriture.ecrireStringln(this.fichier,  "\tcall lirent");
		if(!extern.contains("lirent")) this.extern.add("lirent");
	}

	
	/**
	 * Permet de r�aliser un retour � la ligne
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see fichier
	 * @see extern
	 * 
	 * @since 1.0
	 */
	@Override
	public void aLaLigne() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;aLaLigne");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ligsuiv");
		if(!extern.contains("ligsuiv")) this.extern.add("ligsuiv");
	}
	
	
	
	/**
	 * Permet d'�crire une �tiquette de la chaine pass� en param�tre
	 * 
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see fichier
	 * 
	 * @since 1.0
	 */
	@Override
	public void ecrireEtiqu(String etiq){
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t" + etiq + " :");
		
	}
}
