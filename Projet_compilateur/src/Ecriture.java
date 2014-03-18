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
 * Ecriture est une classe utilitaire permettant d'ecrire directement dans la console ou 
 * dans un fichier texte par l'interm�diaire d'un pointeur de type OutputStream.
 */

//quelques primitives d'�criture � l'ecran  ou dans un fichier

import java.io.*;

public class Ecriture {   
	/**
	 * Permet de renseigner l'erreur pass� en param�tre sur la console 
	 * (le message d'erreur est affich�)
	 * @param e
	 * 
	 * @since 1.0
	 */
	private static void erreur(IOException e) {
		System.out.println(e.getMessage());
		System.out.println("Erreur fatale");
		System.exit(1);
	}

	/**
	 * Permet de creer le lien vers le fichier pass� en param�tre. Si le fichier existe,
	 * son contenu est remplac�, sinon le fichier est cr��.
	 * @param nomFich
	 * @return Un pointeur de type OutputStream sur le fichier pass� en param�tre.
	 * 
	 * @since 1.0
	 */
	public static OutputStream ouvrir(String nomFich) {
		OutputStream f;
		try {f=new DataOutputStream(new FileOutputStream(nomFich));
		}
		catch (IOException e) {f=null;}
		return f;
	}
	
	/**
	 * Permet de creer le lien vers le fichier pass� en param�tre. Si le fichier existe,
	 * son contenu n'est pas remplac� et le fichier est compl�t� par le nouveau texte, 
	 * sinon le fichier est cr��.
	 * @param nomFich
	 * @return Un pointeur de type OutputStream sur le fichier pass� en param�tre.
	 * 
	 * @since 1.0
	 */
	public static OutputStream ouvrirSuite(String nomFich) {
		OutputStream f;
		try {f=new DataOutputStream(new FileOutputStream(nomFich,true));
		}
		catch (IOException e) {f=null;}
		return f;
	}

	/**
	 * Permet de fermer le fichier pr�c�demment ouvert. On supprime le pointeur pass� en 
	 * param�tre.
	 * @param f
	 * 
	 * @since 1.0
	 */
	public static void fermer(OutputStream f) {
		//fermeture d'un fichier                                          
		try {f.close();}
		catch (IOException e) {erreur(e);}
	}


	/**
	 * Cette m�thode permet d'�crire le caract�re dans le fichier pass� en param�tre.
	 * @param f
	 * @param c
	 * 
	 * @since 1.0
	 */
	public static void ecrireChar(OutputStream f,char c) {
		try {f.write(c);}
		catch(IOException e) {erreur(e);}
	}

	/**
	 * Cette m�thode permet d'�crire le caract�re dans la console.
	 * @param c
	 * 
	 * @since 1.0
	 */
	public static void ecrireChar(char c) {ecrireChar(System.out,c);}

	
	/**
	 * Cette m�thode permet d'�crire la chaine de caract�res dans le fichier pass� en 
	 * param�tre.
	 * @param f
	 * @param s
	 * 
	 * @since 1.0
	 */
	public static void ecrireString(OutputStream f,String s) {
		try {for (int i=0;i<s.length();i++) f.write(s.charAt(i));}
		catch(IOException e) {erreur(e);}
	}

	/**
	 * Cette m�thode permet d'�crire la chaine de caract�res dans la console.
	 * @param s
	 * 
	 * @since 1.0
	 */
	public static void ecrireString(String s) {
		ecrireString(System.out,s);
	}

	/**
	 * Cette m�thode permet d'�crire la chaine de caract�res dans le fichier pass� en 
	 * param�tre suivi d'un retour � la ligne.
	 * @param f
	 * @param s
	 * 
	 * @since 1.0
	 */
	public static void ecrireStringln(OutputStream f,String s) {
		ecrireString(f,s+"\r\n");
	}

	/**
	 * Cette m�thode permet d'�crire la chaine de caract�res dans console suivi d'un retour
	 * � la ligne.
	 * @param s
	 * 
	 * @since 1.0
	 */
	public static void ecrireStringln(String s) {
		ecrireStringln(System.out,s);
	}

	/**
	 * Cette m�thode permet d'�crire un entier dans le fichier pass� en param�tre.
	 * @param f
	 * @param x
	 * 
	 * @since 1.0
	 */
	public static void ecrireInt(OutputStream f,int x) {
		ecrireString(f,Integer.toString(x));
	}

	/**
	 * Cette m�thode permet d'�crire un entier dans la console.
	 * @param x
	 * 
	 * @since 1.0
	 */
	public static void ecrireInt(int x) {ecrireInt(System.out,x);}

	/**
	 * Cette m�thode permet d'�crire un entier pr�c�d� d'autant d'espace que la longeur
	 * sp�cifi�e dans le fichier pass� en param�tre.
	 * @param f
	 * @param x
	 * @param longueur
	 * 
	 * @since 1.0
	 */
	public static void ecrireInt(OutputStream f,int x,int longueur) {
		String s=Integer.toString(x);
		int k=longueur-s.length();
		for (int i=0;i<k;i++) ecrireChar(f,' ');
		ecrireString(f,s);
	}

	/**
	 * Cette m�thode permet d'�crire un entier pr�c�d� d'autant d'espace que la longeur
	 * sp�cifi�e dans la console.
	 * @param x
	 * @param longueur
	 * 
	 * @since 1.0
	 */
	public static void ecrireInt(int x,int longueur) {
		ecrireInt(System.out,x,longueur);
	}

	/**
	 * Cette m�thode permet d'�crire un d�cimale dans le fichier pass� en param�tre.
	 * @param f
	 * @param d
	 * 
	 * @since 1.0
	 */
	public static void ecrireDouble(OutputStream f,double d) {
		ecrireString(f,Double.toString(d));
	}

	/**
	 * Cette m�thode permet d'�crire un d�cimale dans la console.
	 * @param d
	 * 
	 * @since 1.0
	 */
	public static void ecrireDouble(double d) {ecrireDouble(System.out,d);}

	/**
	 * Cette m�thode permet d'�crire un d�cimale pr�c�d� d'autant d'espace que la longeur
	 * sp�cifi�e dans le fichier pass� en param�tre.
	 * @param f
	 * @param d
	 * @param longueur
	 * 
	 * @since 1.0
	 */
	public static void ecrireDouble(OutputStream f,double d,int longueur) {
		String s=Double.toString(d);
		int k=longueur-s.length();
		for (int i=0;i<k;i++) ecrireChar(f,' ');
		ecrireString(f,s);
	}

	/**
	 * Cette m�thode permet d'�crire un d�cimale pr�c�d� d'autant d'espace que la longeur
	 * sp�cifi�e dans la console.
	 * @param d
	 * @param longueur
	 * 
	 * @since 1.0
	 */
	public static void ecrireDouble(double d,int longueur) {
		ecrireDouble(System.out,d,longueur);
	}
	
	/**
	 * Cette m�thode permet de copier le contenue du fichier point� par le pointeur output 
	 * dans le fichier point� par le pointeur input.
	 * @param output
	 * @param input
	 * 
	 * @since 1.0
	 */
	public static void ecrireFichier(OutputStream output, InputStream input) {     
		char n = 0;               

		while((n = Lecture.lireChar(input)) != (char) -1)
		{
			ecrireChar(output, n);         
		}               
	}

}

