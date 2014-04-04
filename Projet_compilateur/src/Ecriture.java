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
 * @see YVMasm
 * @see Lecture
 * 
 * Ecriture est une classe utilitaire permettant d'ecrire directement dans la console ou 
 * dans un fichier texte par l'intermédiaire d'un pointeur de type OutputStream.
 */

//quelques primitives d'écriture à l'ecran  ou dans un fichier

import java.io.*;

public class Ecriture {   
	/**
	 * Permet de renseigner l'erreur passé en paramètre sur la console 
	 * (le message d'erreur est affiché)
	 * @param e
	 */
	private static void erreur(IOException e) {
		System.out.println(e.getMessage());
		System.out.println("Erreur fatale");
		System.exit(1);
	}

	/**
	 * Permet de creer le lien vers le fichier passé en paramètre. Si le fichier existe,
	 * son contenu est remplacé, sinon le fichier est créé.
	 * @param nomFich
	 * @return Un pointeur de type OutputStream sur le fichier passé en paramètre.
	 */
	public static OutputStream ouvrir(String nomFich) {
		OutputStream f;
		try {f=new DataOutputStream(new FileOutputStream(nomFich));
		}
		catch (IOException e) {f=null;}
		return f;
	}
	
	/**
	 * Permet de creer le lien vers le fichier passé en paramètre. Si le fichier existe,
	 * son contenu n'est pas remplacé et le fichier est complété par le nouveau texte, 
	 * sinon le fichier est créé.
	 * @param nomFich
	 * @return Un pointeur de type OutputStream sur le fichier passé en paramètre.
	 */
	public static OutputStream ouvrirSuite(String nomFich) {
		OutputStream f;
		try {f=new DataOutputStream(new FileOutputStream(nomFich,true));
		}
		catch (IOException e) {f=null;}
		return f;
	}

	/**
	 * Permet de fermer le fichier précédemment ouvert. On supprime le pointeur passé en 
	 * paramètre.
	 * @param f
	 */
	public static void fermer(OutputStream f) {
		//fermeture d'un fichier                                          
		try {f.close();}
		catch (IOException e) {erreur(e);}
	}


	/**
	 * Cette méthode permet d'écrire le caractère dans le fichier passé en paramètre.
	 * @param f
	 * @param c
	 */
	public static void ecrireChar(OutputStream f,char c) {
		try {f.write(c);}
		catch(IOException e) {erreur(e);}
	}

	/**
	 * Cette méthode permet d'écrire le caractère dans la console.
	 * @param c
	 */
	public static void ecrireChar(char c) {ecrireChar(System.out,c);}

	
	/**
	 * Cette méthode permet d'écrire la chaine de caractères dans le fichier passé en 
	 * paramètre.
	 * @param f
	 * @param s
	 */
	public static void ecrireString(OutputStream f,String s) {
		try {for (int i=0;i<s.length();i++) f.write(s.charAt(i));}
		catch(IOException e) {erreur(e);}
	}

	/**
	 * Cette méthode permet d'écrire la chaine de caractères dans la console.
	 * @param s
	 */
	public static void ecrireString(String s) {
		ecrireString(System.out,s);
	}

	/**
	 * Cette méthode permet d'écrire la chaine de caractères dans le fichier passé en 
	 * paramètre suivi d'un retour à la ligne.
	 * @param f
	 * @param s
	 */
	public static void ecrireStringln(OutputStream f,String s) {
		ecrireString(f,s+"\r\n");
	}

	/**
	 * Cette méthode permet d'écrire la chaine de caractères dans console suivi d'un retour
	 * à la ligne.
	 * @param s
	 */
	public static void ecrireStringln(String s) {
		ecrireStringln(System.out,s);
	}

	/**
	 * Cette méthode permet d'écrire un entier dans le fichier passé en paramètre.
	 * @param f
	 * @param x
	 */
	public static void ecrireInt(OutputStream f,int x) {
		ecrireString(f,Integer.toString(x));
	}

	/**
	 * Cette méthode permet d'écrire un entier dans la console.
	 * @param x
	 */
	public static void ecrireInt(int x) {ecrireInt(System.out,x);}

	/**
	 * Cette méthode permet d'écrire un entier précédé d'autant d'espace que la longeur
	 * spécifiée dans le fichier passé en paramètre.
	 * @param f
	 * @param x
	 * @param longueur
	 */
	public static void ecrireInt(OutputStream f,int x,int longueur) {
		String s=Integer.toString(x);
		int k=longueur-s.length();
		for (int i=0;i<k;i++) ecrireChar(f,' ');
		ecrireString(f,s);
	}

	/**
	 * Cette méthode permet d'écrire un entier précédé d'autant d'espace que la longeur
	 * spécifiée dans la console.
	 * @param x
	 * @param longueur
	 */
	public static void ecrireInt(int x,int longueur) {
		ecrireInt(System.out,x,longueur);
	}

	/**
	 * Cette méthode permet d'écrire un décimale dans le fichier passé en paramètre.
	 * @param f
	 * @param d
	 */
	public static void ecrireDouble(OutputStream f,double d) {
		ecrireString(f,Double.toString(d));
	}

	/**
	 * Cette méthode permet d'écrire un décimale dans la console.
	 * @param d
	 */
	public static void ecrireDouble(double d) {ecrireDouble(System.out,d);}

	/**
	 * Cette méthode permet d'écrire un décimale précédé d'autant d'espace que la longeur
	 * spécifiée dans le fichier passé en paramètre.
	 * @param f
	 * @param d
	 * @param longueur
	 */
	public static void ecrireDouble(OutputStream f,double d,int longueur) {
		String s=Double.toString(d);
		int k=longueur-s.length();
		for (int i=0;i<k;i++) ecrireChar(f,' ');
		ecrireString(f,s);
	}

	/**
	 * Cette méthode permet d'écrire un décimale précédé d'autant d'espace que la longeur
	 * spécifiée dans la console.
	 * @param d
	 * @param longueur
	 */
	public static void ecrireDouble(double d,int longueur) {
		ecrireDouble(System.out,d,longueur);
	}
	
	/**
	 * Cette méthode permet de copier le contenue du fichier pointé par le pointeur output 
	 * dans le fichier pointé par le pointeur input.
	 * @param output
	 * @param input
	 */
	public static void ecrireFichier(OutputStream output, InputStream input) {     
		char n = 0;               

		while((n = Lecture.lireChar(input)) != (char) -1)
		{
			ecrireChar(output, n);         
		}               
	}

}

