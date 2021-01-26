import java.io.*;
import ClassesProjet.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader file;
        Transaction [] tabTrans = new Transaction [100];
        Item item;
        Itemset itemset;
        Transaction transaction;
        try {
            file = new BufferedReader(new FileReader("DataBase.csv"));
            String [] oneLine = file.readLine().split(";");
            item = new Item(oneLine[1], oneLine[2], oneLine[3], oneLine[5]);
            itemset = new Itemset(item, null);
            transaction = new Transaction(oneLine[0], itemset, oneLine[6]);
            tabTrans[0] = transaction;
            int k = 0;  // Represente le compteur du nombre de transaction 
            for (int i = 1; i < 999; i++){
                oneLine = file.readLine().split(";"); 
                if (oneLine[0].equals(tabTrans[k].getIdTransaction())){
                    item = new Item(oneLine[1], oneLine[2], oneLine[3], oneLine[5]);
                    transaction = transaction.ajouterItem(item);
                    tabTrans[k] = transaction;
                }else{
                    k = k+1;
                    item = new Item(oneLine[1], oneLine[2], oneLine[3], oneLine[5]);
                    itemset = new Itemset(item, null);
                    transaction = new Transaction(oneLine[0], itemset, oneLine[6]);
                    tabTrans [k] = transaction;
                }
            }
            for (int i = 0; i <= k; i++){
                tabTrans[i].afficheProduits();
            }
            //System.out.println (k);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
