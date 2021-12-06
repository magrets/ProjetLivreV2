import java.util.Scanner;

public class CompteEpargne extends Compte{
    private double taux;

    public CompteEpargne(){
        super("Epargne");
        taux = controlTaux();
    }

    private double controlTaux(){
        Scanner sc = new Scanner(System.in);
        double tmpdouble;
    
        do{
            System.out.println("Quel est le taux du compte épargne :");
            tmpdouble = Double.parseDouble(sc.next());
        }while(tmpdouble < 0.0);
    
        return tmpdouble;
    }

    public void afficheCompte(){
        super.afficheCompte();
        System.out.println("Le taux du compte est : " + taux);
    }
}
