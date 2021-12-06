import java.util.Scanner;

public class ProjetLivreV2
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ListeCompte c = new ListeCompte();
        FichierCompte fichierSav = new FichierCompte();
        byte choix = 0;

        if(fichierSav.ouvrir("Lecture")) //test ouverture du fichier sav
        {
            c = fichierSav.lire(); //recupère la sauvegarde
            fichierSav.fermer();
        }

        do{
            switch(menuPrincipal(sc))
            {
                case 1: // créer un compte
                    System.out.println("Compte épargne [O / N] :");
                    if(sc.next().charAt(0) == 'O'){
                        c.ajouteUnCompte("epargne"); // création d'un compte épargne grace au constructeur
                    }else{
                        c.ajouteUnCompte("courant");// création d'un compte normal avec le constructeur normal
                    }
                    break;
                case 2: //affiche un compte
                    System.out.println("Quel compte voulez vous voir :");
                    c.afficheUnCompte(sc.next());
                    break;
                case 3: // créer un ligne
                System.out.println("A quel compte voulez ajouter une ligne :");
                    c.ajouteUneLigne(sc.next());
                    break;
                case 4: // fermeture du programme
                    sc.close();
                    if(fichierSav.ouvrir("write")){
                        fichierSav.ecrire(c);
                        fichierSav.fermer();
                    }
                    System.out.println("Fin du programme...");
                    sortir();
                    break;
                case 5: //supprimer un compte
                    System.out.println("Quel compte voulez vous supprimer :");
                    c.supprimeUnCompte(sc.next());
                    break;
                case 6: //affiche tous les comptes
                    c.afficheLesComptes();
                    break;
                default:
                    System.out.println("Cette option n'existe pas...");
            }
        }while(choix != 4);
    }

    public static byte menuPrincipal(Scanner sc){
        byte tmp = 0;

        System.out.println("1. Créer un compte");
        System.out.println("2. Afficher un compte");
        System.out.println("3. Créer une ligne comptable");
        System.out.println("4. Sortir");
        System.out.println("5. Supprimer un compte");
        System.out.println("6. Afficher tous les comptes");
        System.out.println("Votre choix : ");
        tmp = sc.nextByte();
        return tmp;
    }

    public static void sortir(){
        System.exit(0); // ferme le programme
    }
}