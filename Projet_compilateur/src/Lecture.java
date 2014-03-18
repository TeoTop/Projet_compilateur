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

import java.io.*;

//quelques primitives de lecture au clavier ou dans un fichier 

public class Lecture {   
	/**
	 * 
	 * @param nomFich
	 * @return
	 */
    public static InputStream ouvrir(String nomFich) {
		 //d�livre un pointeur sur le fichier de nom nomFich (null si erreur)
		 InputStream f;
		 try {f=new DataInputStream(new FileInputStream(nomFich));}
		 catch (IOException e) {f=null;}
		 return f;
    }

    /**
     * 
     * @param f
     * @return
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
     * 
     * @param f
     */
    public static void fermer(InputStream f) {
		 //ferme un fichier (affiche un message si probleme)           
		 try {f.close();}
		 catch (IOException e) {System.out.println("pb fermeture fichier");}
    }


    //lecture d'un octet dans la cha�ne d'entr�e (avec capture de l'exception)
    /**
     * 
     * @param f
     * @return
     */
    public static char lireChar(InputStream f) {
    	char carSuiv=' ';
        try {int x=f.read();
        carSuiv=(char)x;
      /*if (x==-1) {System.out.println("lecture apr�s fin de fichier");
                  System.exit(2);}*/
        }
        catch(IOException e) {
     System.out.println(e.getMessage());
     System.out.println("Erreur fatale");System.exit(3);
        }
        return carSuiv;
    }

    /**
     * 
     * @return
     */
    public static char lireChar() {return lireChar(System.in);}


/*public static void main(String args[]){
    InputStream fichier;
    char carLu;    
    fichier=ouvrir("automate.xml");
    while (!finFichier(fichier)){
      carLu=lireChar(fichier);
      System.out.print(carLu);
        }
    fermer(fichier);

   // lecture au clavier
    carLu= lireChar();
  }*/
}//class Lecture
