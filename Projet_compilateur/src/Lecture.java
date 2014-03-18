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
 * Lecture est une classe utilitaire permet de lire dans un fichier par l'interm�diaire 
 * d'un pointeur de type InputStream..
 */

import java.io.*;

public class Lecture {   
	/**
	 * Cette m�thode permet de lire dans un fichier.
	 * @param nomFich
	 * @return Un pointeur de type InputStream pointant sur le fichier pass� en param�tre.
	 * 
	 * @since 1.0
	 */
    public static InputStream ouvrir(String nomFich) {
		 //d�livre un pointeur sur le fichier de nom nomFich (null si erreur)
		 InputStream f;
		 try {f=new DataInputStream(new FileInputStream(nomFich));}
		 catch (IOException e) {f=null;}
		 return f;
    }

    /**
     * Cette m�thode permet de tester si pointeur se trouve � la fin du fichier.
     * @param f
     * @return true si la fin du fichier est atteinte, false sinon.
     * 
     * @since 1.0
     */
    public static boolean finFichier(InputStream f) {
		//d�termine si la fin de fichier est atteinte
		try {return (f!=System.in && f.available()==0);}
        catch(IOException e) {System.out.println("pb test finFichier");
                       System.exit(1);
		 }
		 return true;
    }

    /**
     * Cette m�thode permet de fermer le fichier (supprime le pointeur pass� en param�tre). 
     * @param f
     * 
     * @since 1.0
     */
    public static void fermer(InputStream f) {
		 //ferme un fichier (affiche un message si probleme)           
		 try {f.close();}
		 catch (IOException e) {System.out.println("pb fermeture fichier");}
    }


    /**
     * Cette m�thode permet de lire un caract�re depuis le fichier pass� en param�tre.
     * @param f
     * @return Le caract�re lu
     * 
     * @since 1.0
     */
    public static char lireChar(InputStream f) {
    	char carSuiv=' ';
        try {int x=f.read();
        carSuiv=(char)x;
        }
        catch(IOException e) {
     System.out.println(e.getMessage());
     System.out.println("Erreur fatale");System.exit(3);
        }
        return carSuiv;
    }

    /**
     * Cette m�thode permet de lire un caract�re depuis la console.
     * @return Le caract�re lu
     * 
     * @since 1.0
     */
    public static char lireChar() {return lireChar(System.in);}
}

