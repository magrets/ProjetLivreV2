import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class ListeCompte implements Serializable{
    private HashMap<String, Compte> listeCompte;

    public ListeCompte(){
        listeCompte = new HashMap<String, Compte>();
    }

    public void ajouteUnCompte(String type){
        Compte tmpCompte;

        type = type.toUpperCase();
        if(type.charAt(0) == 'E'){
            tmpCompte = new CompteEpargne();
        }
        else{
            tmpCompte = new Compte();
        }
        //ajoute un compte au dictionnaire si la clé n'est pas déja utilisé
        listeCompte.putIfAbsent(tmpCompte.getNum(), tmpCompte);
    }

    public void ajouteUneLigne(String numCompte){
        Compte tmpCompte;

        // récupère le compte assiocié au numéro donné
        tmpCompte  = listeCompte.get(numCompte);
        if(tmpCompte != null){
            tmpCompte.creerLigne(); // créer la ligne
            //remplace l'ancien compte par le nouveau avec la nouvelle ligne
            listeCompte.put(tmpCompte.getNum(), tmpCompte);
        }else{
            System.out.println("Le compte numéro : " + numCompte + " n'existe pas...");
        }
    }

    public void afficheUnCompte(String numCompte){
        Compte tmpCompte;

        tmpCompte = listeCompte.get(numCompte);
        if(tmpCompte != null){ // si Le compte existe
            miseEnPage();
            tmpCompte.afficheCompte();
        }else
        {
            System.out.println("Le Compte " + numCompte + " n'existe pas...");
        }
    }

    public void supprimeUnCompte(String numCompte){
        Compte tmpCompte;

        tmpCompte = listeCompte.get(numCompte);
        if(tmpCompte != null){ // si Le compte existe
            listeCompte.remove(numCompte);
            System.out.println("Le Compte " + numCompte + " est supprimé");
        }else
        {
            System.out.println("Le Compte " + numCompte + " n'existe pas...");
        }
    }

    public void afficheLesComptes(){
        if(listeCompte.size() > 0){// Si des comptes existent
            Collection<Compte> c = listeCompte.values();
            for(Compte compte : c){
                miseEnPage();
                compte.afficheCompte();
            }
        }else
        {
            System.out.println("Aucun compte n'a été créé...");
        }
    }

    private void miseEnPage(){
        System.out.println("+--------------------+--------------------------+----------------------+");
        System.out.println("|     Num compte     |      Type de compte      |        Valeur        |");
        System.out.println("+--------------------+--------------------------+----------------------+");
    }
}
