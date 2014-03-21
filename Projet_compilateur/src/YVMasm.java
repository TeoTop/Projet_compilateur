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
 * La classe YVMasm est étendue de la classe YVM et a pour but de traduire en assembleur
 * les méthodes YAKA. 
 * 
 * @see YVM
 * @see Ecriture
 */

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class YVMasm extends YVM {
	
	/**
	 * Fichier de sortie temporaire
	 * 
	 * @see Ecriture
	 */
	private OutputStream fichier;
	
	/**
	 *  Compteur permettant de spécifier le numéro du message affiché dans la focntion 
	 *  ecrireChaine 
	 *  
	 *  @see YVMasm#YVMasm
	 */
	private int comptString;
	
	/** 
	 * Liste qui contiendra les fonctions externes nécessaires : lirent, ecrent, ecrbool, 
	 * ecrch, ligsuiv. 
	 * 
	 * @see YVMasm#YVMasm
	 */
	private ArrayList<String> extern;
	
	/**
	 * Constructeur : permet de créer un fichier temporaire dans lequel sera stocké le code
	 * assembleur avant la recopie dans le fichier final si il n'y a pas d'erreur.
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#comptString
	 * @see YVMasm#extern
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
	 * @see YVMasm#fichier
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
	 * @see YVMasm#fichier
	 */
	@Override
	public void ineg() {
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
	 * Transformation d'un code YAKA conditionnel inférieur en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iinf() {
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
	 * Transformation d'un code YAKA conditionnel d'égalité en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iegal() {
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
	 * Transformation d'un code YAKA permettant le chargement d'une variable à l'offset 
	 * passé en paramètre en code ASM
	 * 
	 * @param offset
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iload(int offset) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iload " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush word ptr [bp" + offset + "]");
	}

	
	/**
	 * Transformation d'un code YAKA permettant le stockage d'une variable à l'offset 
	 * passé en paramètre en code ASM
	 * 
	 * @param offset
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void istore(int offset) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;istore " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmov word ptr [bp" + offset + "],ax");
	}

	
	/**
	 * Transformation d'un code YAKA permettant le stockage d'une constante passé en 
	 * paramètre en code ASM
	 * 
	 * @param valeur
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iconst(int valeur) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iconst " + valeur + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush " + valeur + "");
	}


	
	/**
	 * Transformation d'un code YAKA permettant de comparer la valeur se trouvant en sommet 
	 * de pile à 0, si égale
	 * alors go to vers étiquette en code ASM
	 * 
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void ifeq(String etiq) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ifeq " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);
	}

	
	/**
	 * Transformation d'un code YAKA permettant de comparer la valeur se trouvant en sommet 
	 * de pile à 0, si non égale
	 * alors go to vers étiquette en code ASM
	 * 
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iffaux(String etiq) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iffaux " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);

	}

	
	/**
	 * Transformation d'un code YAKA permettant d'aller exécuter les instructions se trouvant
	 *  juste après l'étiquette en code ASM
	 *  
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void Goto(String etiq) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;goto " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tjmp " + etiq);
	}


	
	/**
	 * Transformation d'un code YAKA permettant de réaliser une addition en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iadd() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iadd");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tadd ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	
	/**
	 * Transformation d'un code YAKA permettant de réaliser une multiplication en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void imul() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;imul");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmul bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	
	/**
	 * Transformation d'un code YAKA permettant de réaliser une division en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void idiv() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;idiv");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier , "\tcwd"); /* dx = 0 ou -1 */
		Ecriture.ecrireStringln(this.fichier,  "\tdiv bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");

	}

	
	/**
	 * Transformation d'un code YAKA permettant de réaliser une comparaison supérieur en 
	 * code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void isup() {
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
	 * Transformation d'un code YAKA permettant de réaliser une comparaison inférieur ou 
	 * égale en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iinfegal() {
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
	 * Transformation d'un code YAKA permettant de réaliser une comparaison supérieur ou 
	 * égale en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void isupegal() {
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
	 * Transformation d'un code YAKA permettant de réaliser une comparaison de différence 
	 * en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void idiff() {
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
	 * Permet d'écrire en ASM l'entete necessaire pour exécuter le code ASM. Cette méthode
	 * compléte l'entête avec les fonctions externes rassemblées dans un tableau de chaines. 
	 * 
	 * @param fichierOut
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see Ecriture#ecrireString(OutputStream, String)
	 * @see Ecriture#ouvrir(String)
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#extern
	 */
	@Override
	public void recopierEntete(String fichierOut) {
		// on crée un nouveau fichier (final)
		this.fichier = Ecriture.ouvrir(fichierOut);
		Ecriture.ecrireStringln(this.fichier,  ";entete");
		
		//on vérifie que le tableau n'est pas vide
		if(!extern.isEmpty()){
			Ecriture.ecrireString(this.fichier, "extrn ");
			
			// on itère sur le tableau pour récupérer tous ses éléments et les écrire
			Iterator<String> ite = this.extern.iterator();
			while(ite.hasNext()){
				String fonction = ite.next();
				if(ite.hasNext())
					Ecriture.ecrireString(this.fichier, fonction + ":proc, ");
				else
					Ecriture.ecrireStringln(this.fichier, fonction + ":proc");
			}
		}

		//on renseigne le début type d'un fichier assembleur
		Ecriture.ecrireStringln(this.fichier,  ".MODEL SMALL");
		Ecriture.ecrireStringln(this.fichier,  ".586");
		Ecriture.ecrireStringln(this.fichier,  ".CODE");
		Ecriture.ecrireStringln(this.fichier,  "debut:");
		Ecriture.ecrireStringln(this.fichier,  "\tSTARTUPCODE");
	}

	
	/**
	 * Permet d'écrire la fin type du fichier ASM necessaire à son exécution
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see Ecriture#fermer(OutputStream)
	 * 
	 * @see YVMasm#fichier
	 */
	@Override
	public void queue() {
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
	 * @see YVMasm#fichier
	 */
	@Override
	public void ouvrePrinc(int nbOctet) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ouvrePrinc " + nbOctet);
		Ecriture.ecrireStringln(this.fichier,  "\tmov bp,sp");
		Ecriture.ecrireStringln(this.fichier , "\tsub sp," + nbOctet);
	}

	
	/**
	 * Transformation d'un code YAKA permettant de réaliser un OU en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void ior() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ior");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tor ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Transformation d'un code YAKA permettant de réaliser un NON en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void inot() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;inot");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tnot ax");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Transformation d'un code YAKA permettant de réaliser un ET en code ASM
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void iand() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;and");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tand ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	
	/**
	 * Permet d'écrire l'entier qui est en sommet de pile
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#extern
	 */
	@Override
	public void ecrireEnt() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireEnt");
		Ecriture.ecrireStringln(this.fichier,"\tcall ecrent");
		if(!this.extern.contains("ecrent")) this.extern.add("ecrent");
		/* on pense a enregistrer la fonction externe dans le tableau afin de la renseigner
		dans l'entête*/
	}

	
	/**
	 * Permet d'afficher la chaine passé en paramètre
	 * 
	 * @param x
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#extern
	 * @see YVMasm#comptString
	 */
	@Override
	public void ecrireChaine(String x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireChaine " + x);
		Ecriture.ecrireStringln(this.fichier, ".DATA\n\tmess" + this.comptString + " DB \"" + x.substring(1, x.length() - 1) + "$\""); /* enlever le dernier guillemet afin d'ajouter $ */
		Ecriture.ecrireStringln(this.fichier, ".CODE\n\tlea dx,mess" + this.comptString + "\n\tpush dx\n\tcall ecrch");
		this.comptString++;					// on incrémente pour les numéros de messages
		if(!this.extern.contains("ecrch")) this.extern.add("ecrch");
	}

	
	/**
	 * Permet d'écrire le booléen qui est en sommet de pile
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#extern
	 */
	@Override
	public void ecrireBool() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireBool");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ecrbool");
		if(!this.extern.contains("ecrbool")) this.extern.add("ecrbool");
	}

	
	/**
	 * Permet de lire un entier placé à l'offset x en faisant appel à la fonction 
	 * lirent (prédéfini en assembleur)
	 * 
	 * @param x
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#extern
	 */
	@Override
	public void lireEnt(int x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;lireEnt " + x);
		Ecriture.ecrireStringln(this.fichier,  "\tlea dx,[bp" + x + "]");
		Ecriture.ecrireStringln(this.fichier,  "\tpush dx");
		Ecriture.ecrireStringln(this.fichier,  "\tcall lirent");
		if(!this.extern.contains("lirent")) this.extern.add("lirent");
	}

	
	/**
	 * Permet de réaliser un retour à la ligne
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * 
	 * @see YVMasm#fichier
	 * @see YVMasm#extern
	 */
	@Override
	public void aLaLigne() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;aLaLigne");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ligsuiv");
		if(!this.extern.contains("ligsuiv")) this.extern.add("ligsuiv");
	}
	
	
	
	/**
	 * Permet d'écrire une étiquette de la chaine passé en paramètre
	 * 
	 * @param etiq
	 * 
	 * @see Ecriture#ecrireStringln(OutputStream, String)
	 * @see YVMasm#fichier
	 */
	@Override
	public void ecrireEtiqu(String etiq){
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t" + etiq + " :");
		
	}
}
