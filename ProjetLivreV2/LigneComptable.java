import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LigneComptable implements Serializable{
    private String date, motif, modePaie;
    private double valeur;

    public LigneComptable(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quelle est la valeur de l'opération [positive = crédit ; négative = débit] :");
        valeur = sc.nextDouble();
        date = controlDate();
        
        motif = controlmotif();
        modePaie = controlModePaie();
    }

    public void afficheLigneComptable(int nLigne){
        System.out.format("|%9s|%12s|%13s|%14.2f|%18s| \n",nLigne, date, motif, valeur, modePaie);
    }

    public double getValeur(){
        return valeur;
    }

    private String controlmotif(){
        Scanner sc = new Scanner(System.in);
        String tmpString = "";
        char tmpChar;

        do{
            System.out.println("Quel est le motif de l'opération [1.Salaire, 2.Loyer, 3.Alimentation, 4.Divers]:");
            tmpChar = sc.next().charAt(0);
        }while(tmpChar != '1' && tmpChar != '2' && tmpChar != '3' && tmpChar != '4');
        switch(tmpChar){
            case '1':
                tmpString = "Salaire";
                break;
            case '2':
                tmpString = "Loyer";
                break;
            case '3':
                tmpString = "Alimentation";
                break;
            case '4':
                tmpString = "Divers";
                break;
        }
        return tmpString;
    }

    private String controlModePaie(){
        Scanner sc = new Scanner(System.in);
        String tmpString = "";
        char tmpChar;

        do{
            System.out.println("Quel est le mode de paiement utilisé [1.CB, 2.Chèque, 3.Virement]:");
            tmpChar = sc.next().charAt(0);
        }while(tmpChar != '1' && tmpChar != '2' && tmpChar != '3');
        switch(tmpChar){
            case '1':
                tmpString = "CB";
                break;
            case '2':
                tmpString = "Chèque";
                break;
            case '3':
                tmpString = "Virement";
                break;
        }
        return tmpString;
    }

    private String controlDate(){
        Scanner sc = new Scanner(System.in);
        String str = "";
        int nbrEssaie = 0;
        Date d;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        while(nbrEssaie < 3)
        {
            try {
                System.out.println("Quelle est la date de l'opération format (JJ/MM/AAAA) :");
                String date = sc.next();
                d = format.parse(date);
                str = format.format(d);
                return str;
            } catch (ParseException e) {
                System.out.println("Problème de parsing de la date");
                d = null;
                nbrEssaie++;
            }
        }
        d = new Date();
        str = format.format(d);
        return str;
    }
}
