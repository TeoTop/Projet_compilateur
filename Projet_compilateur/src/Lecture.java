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
 * @see Ecriture
 * 
 * Lecture est une classe utilitaire permet de lire dans un fichier par l'intermédiaire 
 * d'un pointeur de type InputStream..
 */

import java.io.*;

public class Lecture {   
	/**
	 * Cette méthode permet de lire dans un fichier.
	 * @param nomFich
	 * @return Un pointeur de type InputStream pointant sur le fichier passé en paramètre.
	 */
    public static InputStream ouvrir(String nomFich) {
		 //délivre un pointeur sur le fichier de nom nomFich (null si erreur)
		 InputStream f;
		 try {f=new DataInputStream(new FileInputStream(nomFich));}
		 catch (IOException e) {f=null;}
		 return f;
    }

    /**
     * Cette méthode permet de tester si pointeur se trouve à la fin du fichier.
     * @param f
     * @return true si la fin du fichier est atteinte, false sinon.
     */
    public static boolean finFichier(InputStream f) {
		//détermine si la fin de fichier est atteinte
		try {return (f!=System.in && f.available()==0);}
        catch(IOException e) {System.out.println("pb test finFichier");
                       System.exit(1);
		 }
		 return true;
    }

    /**
     * Cette méthode permet de fermer le fichier (supprime le pointeur passé en paramètre). 
     * @param f
     */
    public static void fermer(InputStream f) {
		 //ferme un fichier (affiche un message si probleme)           
		 try {f.close();}
		 catch (IOException e) {System.out.println("pb fermeture fichier");}
    }


    /**
     * Cette méthode permet de lire un caractère depuis le fichier passé en paramètre.
     * @param f
     * @return Le caractère lu
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
     * Cette méthode permet de lire un caractère depuis la console.
     * @return Le caractère lu
     */
    public static char lireChar() {return lireChar(System.in);}
}

