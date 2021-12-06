import java.io.Serializable;
import java.util.Scanner;

public class Compte implements Serializable{
    

    private LigneComptable [] ligne;
    public static final int NBLigne = 10;
    private int nbLignecreer;
    private String type, num;
    protected double valeur;

    //Constructeur
    public Compte(){
        type = controlType();
        creerCompte();
    }

    //Constructeur surchargé
    public Compte(String typeCompte){
        if(typeCompte.equalsIgnoreCase("Epargne")){
            type = typeCompte;
            creerCompte();
        }
    }

    private void creerCompte(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Numéro de compte :");
        num = sc.next();
        valeur = controlValInit();
        // créer un tableau qui peut contenir 10 lignes comptables
        ligne = new LigneComptable[NBLigne];
        nbLignecreer = -1; //initialise le nombre de ligne a -1 car aucune ligne existe
    }

    public void afficheCompte(){
        //affichage du compte
        System.out.format("|%20s|%26s|%22.2f|\n",num, type, valeur);

        //affichage des lignes
        if(nbLignecreer > -1){
            System.out.println("**************************  LIGNE  COMPTABLE  **************************");
            System.out.println("|Num ligne|    Date    |    Motif    |    Valeur    | Mode de paiement |");
            for(int i = 0; i < ligne.length; i++){
                if(ligne[i] != null){
                    ligne[i].afficheLigneComptable(i);
                }
            }
            System.out.println("+---------+------------+--------------+-------------+------------------+");
        }
    }

    public void creerLigne(){
        nbLignecreer++;

        //le tableau n'est pas plein
        if(nbLignecreer < NBLigne){
            ligne[nbLignecreer] = new LigneComptable();
            //ajoute la ligne à l'emplacement actuelle du tableau
            ligneModifieVal(nbLignecreer);
        }
        else{// le tableau est plein
            decaleLigne();
            ligne[ligne.length-1] = new LigneComptable();
            //ajoute la ligne dans la dernière case du tableau
            System.out.println("longueur du tableau" + ligne.length);
            //ligne.lenght-1 car le tableau commence a 0
            ligneModifieVal(ligne.length-1); 
        }
    }

    private void decaleLigne(){
        for(int i = 1; i < ligne.length ; i++){
            ligne[i-1] = ligne[i];
        }
    }

    private void ligneModifieVal(int i){
        //modifie la valeur du compte en fonction de la ligne
        valeur = valeur + ligne[i].getValeur(); 
    }

    private String controlType(){
        Scanner sc = new Scanner(System.in);
        String tmpString = "";
        char tmpChar;

        do{
            System.out.println("Type du compte [1. courant, 2. joint] :");
            tmpChar = sc.next().charAt(0);
        }while(tmpChar != '1' && tmpChar != '2' && tmpChar != '3');
        switch(tmpChar){
            case '1':
                tmpString = "Courant";
                break;
            case '2':
                tmpString = "Epargne";
                break;
            case '3':
                tmpString = "Joint";
                break;
        }
        return tmpString;
    }

    private double controlValInit(){
        Scanner sc = new Scanner(System.in);
        double tmpDouble;

        do{
            System.out.println("Première valeur du compte :");
            tmpDouble = sc.nextDouble();
        }while(tmpDouble < 0);

        return tmpDouble;
    }

    public String getNum(){
        return num;
    }
}
