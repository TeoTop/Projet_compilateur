import java.io.OutputStream;


public class YVMasm extends YVM {
	/* fichier de sortie */
	private OutputStream fichier;
	/* compteur de nombre de message pour la creation de variable pour la fonction ecrireChaine */
	private int comptString;

	public YVMasm() {
		super();
		this.fichier = Ecriture.ouvrir("yvm.tmp");; /* ecrire dans un fichier tmp et puis le copier dans l'autre fichier ssi il n'y a pas d'erreur */
		this.comptString = 0;
	}

	@Override
	public void isub() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isub");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tsub ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	@Override
	public void ineg() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ineg");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmul -1");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	@Override
	public void iinf() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iinf");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjge $+6"); /* jge */
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	public void iegal() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iegal");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjne $+6");  /* jne */
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	public void iload(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iload " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush word ptr [bp" + offset + "]");
		/*Ecriture.ecrireInt(this.fichier,offset);
		Ecriture.ecrireString(this.fichier , "] \n");*/

	}

	@Override
	public void istore(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;istore " + offset + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tmov word ptr [bp" + offset + "],ax");
		/*Ecriture.ecrireInt(this.fichier,offset);
		Ecriture.ecrireString(this.fichier , "] \n");*/
	}

	@Override
	public void iconst(int valeur) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;iconst " + valeur + "");
		Ecriture.ecrireStringln(this.fichier,  "\tpush " + valeur + "");
		/*Ecriture.ecrireInt(this.fichier,valeur);
		Ecriture.ecrireString(this.fichier , "\n");*/
	}


	@Override
	public void ifeq(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ifeq " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,0");
		Ecriture.ecrireStringln(this.fichier,  "\tje " + etiq);
	}

	@Override
	public void iffaux(String etiq) {
		// TODO Auto-generated method stub
		ifeq(etiq);

	}

	@Override
	public void Goto(String etiq) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;goto " + etiq);
		Ecriture.ecrireStringln(this.fichier,  "\tjmp " + etiq);
	}


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

	@Override
	public void isup() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isup");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjle $+6"); /* jle */
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

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

	@Override
	public void isupegal() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;isupegal");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tjl $+6"); /* jl */
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");

	}

	@Override
	public void idiff() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;idiff");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tcmp ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tje $+6"); /* je */
		Ecriture.ecrireStringln(this.fichier,  "\tpush -1");
		Ecriture.ecrireStringln(this.fichier,  "\tjmp $+4");
		Ecriture.ecrireStringln(this.fichier,  "\tpush 0");
	}

	@Override
	public void entete() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  ";entete");
		/* il faut ajouter les fonctions externes => biblio.obj */
		Ecriture.ecrireStringln(this.fichier,  ".MODEL SMALL");
		Ecriture.ecrireStringln(this.fichier,  ".586");
		Ecriture.ecrireStringln(this.fichier,  ".CODE");
		Ecriture.ecrireStringln(this.fichier,  "debut:");
		Ecriture.ecrireStringln(this.fichier,  "\tSTARTUPCODE");
	}

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

	@Override
	public void ouvrePrinc(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ouvrePrinc");
		Ecriture.ecrireStringln(this.fichier,  "\tmov bp,sp");
		Ecriture.ecrireStringln(this.fichier , "\tsub sp," + offset);
		/*Ecriture.ecrireInt(this.fichier,offset);
		Ecriture.ecrireString(this.fichier , "] \n");*/
	}

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

	public void inot() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;inot");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tnot ax");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	public void iand() {
		// TODO Auto-generated method stub
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;and");
		Ecriture.ecrireStringln(this.fichier,  "\tpop bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpop ax");
		Ecriture.ecrireStringln(this.fichier,  "\tand ax,bx");
		Ecriture.ecrireStringln(this.fichier,  "\tpush ax");
	}

	/* entrée, sortie */

	public void ecrireEnt() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireEnt");
		Ecriture.ecrireString(this.fichier,"\tcall ecrent");
	}

	public void ecrireChaine(String x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  ";ecrireChaine");
		Ecriture.ecrireStringln(this.fichier, ".DATA\n\tmess" + this.comptString + " DB " + x + "$");
		Ecriture.ecrireStringln(this.fichier, ".CODE\n\tlea dx,mess" + this.comptString + "\n\tpush dx\n\tcall ecrch");
		this.comptString++;
	}

	public void ecrireBool() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;ecrireBool");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ecrbool");
	}

	public void lireEnt(int x) {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;lireEnt");
		Ecriture.ecrireStringln(this.fichier,  "\tlea dx,[bp" + x + "]");
		Ecriture.ecrireStringln(this.fichier,  "\tpush dx");
		Ecriture.ecrireStringln(this.fichier,  "\tcall lirent");
	}

	public void aLaLigne() {
		Ecriture.ecrireStringln(this.fichier,  "");
		Ecriture.ecrireStringln(this.fichier,  "\t;aLaLigne");
		Ecriture.ecrireStringln(this.fichier,  "\tcall ligsuiv");
	}


}
