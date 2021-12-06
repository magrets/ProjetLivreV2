import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FichierCompte {
    private ObjectOutputStream ofW;
    private ObjectInputStream ofR;

    private final String nomFichier = "Compte.dat";
    private char mode;

    public boolean ouvrir(String sMode){
        mode = (sMode.toUpperCase()).charAt(0);
        System.out.println("mode : " + mode);
        try {
            if(mode == 'E' || mode == 'W'){ // ouverture en ecriture
                ofW = new ObjectOutputStream(new FileOutputStream(nomFichier));
            }
            else if(mode == 'L' || mode == 'R'){ // ouverture en lecture
                ofR = new ObjectInputStream( new FileInputStream(nomFichier));
            }
            return true;
        } catch (IOException e) {
            System.out.println(nomFichier + " : Erreur d'ouverture");
            return false;
        }
    }

    public ListeCompte lire(){
        try {
            ListeCompte tmp = (ListeCompte) ofR.readObject();
            return tmp;
        } catch (IOException e) {
            System.out.println(nomFichier + " : Erreur de lecture"); 
        } catch (ClassNotFoundException e) {
            System.out.println(nomFichier + " : Erreur de format"); 
        }
        return null;
    }

    public void ecrire(ListeCompte tmp){
        try {
            if(tmp != null){
                ofW.writeObject(tmp);
            }
        } catch (IOException e) {
            System.out.println(nomFichier + " : Erreur d'ecriture");
            System.out.println(e);       
        }
    }

    public void fermer(){
        try {
            if(mode == 'E' || mode == 'W'){ // fermeture en ecriture
                ofW.close();
            }
            else if(mode == 'L' || mode == 'R'){ // fermeture en lecture
                ofR.close();
            }
        } catch (IOException e) {
            System.out.println(nomFichier + " : Erreur de fermeture");
        }
    }
}
