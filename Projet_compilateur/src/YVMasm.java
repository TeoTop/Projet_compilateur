import java.io.OutputStream;


public class YVMasm extends YVM {
	private OutputStream fichier;
	
	
	public YVMasm(String nom) {
		super();
		this.fichier = Ecriture.ouvrir(nom);;
	}

	@Override
	public void isub() {
		 Ecriture.ecrireString(this.fichier , "; isub \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "sub ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "push ax \n");
	}

	@Override
	public void ineg() {
		// TODO Auto-generated method stub
		Ecriture.ecrireString(this.fichier , "pop ax \n");
		
	}

	@Override
	public void iinf() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , "; iinf \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cmp ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "jl $+6 \n");
		 Ecriture.ecrireString(this.fichier , "push -1 \n");
		 Ecriture.ecrireString(this.fichier , "jmp $+4 \n");
		 Ecriture.ecrireString(this.fichier , "push 0 \n");

	}

	@Override
	public void iegal() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , "; iegal \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cmp ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "jle $+6 \n");
		 Ecriture.ecrireString(this.fichier , "push -1 \n");
		 Ecriture.ecrireString(this.fichier , "jmp $+4 \n");
		 Ecriture.ecrireString(this.fichier , "push 0 \n");

	}

	@Override
	public void iload(int offset) {
		// TODO Auto-generated method stub
		Ecriture.ecrireString(this.fichier , "push word ptr [bp-");
		Ecriture.ecrireInt(this.fichier,offset);
		Ecriture.ecrireString(this.fichier , "] \n");

	}

	@Override
	public void istore(int offset) {
		// TODO Auto-generated method stub
		
		Ecriture.ecrireString(this.fichier , "pop ax \n");
		Ecriture.ecrireString(this.fichier , "push word ptr [bp-");
		Ecriture.ecrireInt(this.fichier,offset);
		Ecriture.ecrireString(this.fichier , "] \n");
	}

	@Override
	public void iconst(int valeur) {
		// TODO Auto-generated method stub
		
		Ecriture.ecrireString(this.fichier , "push ");
		Ecriture.ecrireInt(this.fichier,valeur);
		Ecriture.ecrireString(this.fichier , "\n");
	}

	
	@Override
	public void ifeq() {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void iffaux() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Goto() {
		// TODO Auto-generated method stub

	}


	@Override
	public void iadd() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";iadd \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "add ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "push ax \n");
		
	}

	@Override
	public void imul() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";imul \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "imul bx\n");
		 Ecriture.ecrireString(this.fichier , "push ax \n");
		
	}

	@Override
	public void idiv() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";idiv \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cwd \n");
		 Ecriture.ecrireString(this.fichier , "idiv bx \n");
		 Ecriture.ecrireString(this.fichier , "push ax \n");
		
	}

	@Override
	public void isup() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";isup \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cmp ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "jg $+6 \n");
		 Ecriture.ecrireString(this.fichier , "push -1 \n");
		 Ecriture.ecrireString(this.fichier , "jmp $+4 \n");
		 Ecriture.ecrireString(this.fichier , "push 0 \n");
		
	}

	@Override
	public void iinfegal() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";iinfegal \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cmp ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "jg $+6 \n");
		 Ecriture.ecrireString(this.fichier , "push -1 \n");
		 Ecriture.ecrireString(this.fichier , "jmp $+4 \n");
		 Ecriture.ecrireString(this.fichier , "push 0 \n");
		
		
		
	}

	@Override
	public void isupegal() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";isupegal \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cmp ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "jge $+6 \n");
		 Ecriture.ecrireString(this.fichier , "push -1 \n");
		 Ecriture.ecrireString(this.fichier , "jmp $+4 \n");
		 Ecriture.ecrireString(this.fichier , "push 0 \n");
		
	}

	@Override
	public void idiff() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";idiff \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "cmp ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "jne $+6 \n");
		 Ecriture.ecrireString(this.fichier , "push -1 \n");
		 Ecriture.ecrireString(this.fichier , "jmp $+4 \n");
		 Ecriture.ecrireString(this.fichier , "push 0 \n");
	}

	@Override
	public void entete() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";entete \n");
		 Ecriture.ecrireString(this.fichier , ".model SMALL \n");
		 Ecriture.ecrireString(this.fichier , ".586 \n");
		 Ecriture.ecrireString(this.fichier , ". CODE \n");
		 Ecriture.ecrireString(this.fichier , "debut : \n");
		 Ecriture.ecrireString(this.fichier , "STARTUPCODE \n");
	}

	@Override
	public void queue() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";queue \n");
		 Ecriture.ecrireString(this.fichier , "nope \n");
		 Ecriture.ecrireString(this.fichier , "exitcode \n");
		 Ecriture.ecrireString(this.fichier , "end debut \n");

	}

	@Override
	public void ouvrePrinc(int offset) {
		// TODO Auto-generated method stub
	   	    Ecriture.ecrireString(this.fichier , ";ouvrirPrinc \n");
			Ecriture.ecrireString(this.fichier , "mov bp,sp \n");
			Ecriture.ecrireString(this.fichier , "sub sp,");
			Ecriture.ecrireString(this.fichier , "push word ptr [bp-");
			Ecriture.ecrireInt(this.fichier,offset);
			Ecriture.ecrireString(this.fichier , "] \n");
		}

	@Override
	public void ior() {
		// TODO Auto-generated method stub
		 Ecriture.ecrireString(this.fichier , ";ior \n");
		 Ecriture.ecrireString(this.fichier , "pop bx \n");
		 Ecriture.ecrireString(this.fichier , "pop ax \n");
		 Ecriture.ecrireString(this.fichier , "or ax,bx \n");
		 Ecriture.ecrireString(this.fichier , "push ax \n");
	}
		
}
