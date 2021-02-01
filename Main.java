import java.io.*;
import ClassesProjet.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader file;

        /* Ensemble de toute les transactions */
        Transaction [] tabTrans = new Transaction [100];

        Item item;
        Transaction transaction;
        try {
            // Ouverture en mode lecture du fichier
            file = new BufferedReader(new FileReader("DataBase.csv"));  

            /* Lecture d'une ligne et stockage des donnees separees par un point virgule
            dans un tableau de chaine de caractere */
            String [] oneLine = file.readLine().split(";");  

            item = new Item(oneLine[1], oneLine[2], oneLine[5]);
            transaction = new Transaction(oneLine[0], oneLine[6]);
            transaction.ajouterItem(item);
            tabTrans[0] = transaction;
            
            // Represente le compteur du nombre de transaction 
            int k = 0;  
            for (int i = 1; i < 999; i++){
                oneLine = file.readLine().split(";"); 
                if (oneLine[0].equals(tabTrans[k].getIdTransaction())){
                    item = new Item(oneLine[1], oneLine[2], oneLine[5]);
                    transaction.ajouterItem(item);
                    tabTrans[k] = transaction;
                }else{
                    k = k+1;
                    item = new Item(oneLine[1], oneLine[2], oneLine[5]);
                    transaction = new Transaction(oneLine[0], oneLine[6]);
                    transaction.ajouterItem(item);
                    tabTrans [k] = transaction;
                }
            }
            /*for (int i = 0; i <= k; i++){
                //System.out.println ("Volume de la transaction "+tabTrans[i].getIdTransaction()+" : "+tabTrans[i].getVolumeTransaction());
                //tabTrans[i].afficheProduits();
            }*/
            //System.out.println (k);

            // Definition de l'ensemble de tous les items des transactions
            Item [] ensemble_Items = new Item [600];
            int comp = 0;   // Compteur du nombre d'items

            for (int i = 0; i <= k; i++){
                Transaction ref = tabTrans[i];
                for (int j = 0; j < ref.getVolumeTransaction(); j++){
                    if (contient(ensemble_Items, ref.getProduits()[j].getId()) == false){
                        ensemble_Items [comp] = ref.getProduits()[j];
                        comp++;
                    }
                }
            }
            /*System.out.println ("Liste d'items :");
            for (int i = 0; i < comp; i++){
                System.out.println ("- "+ensemble[i].getNom());
            }
            System.out.println ("Nombre d'items : "+comp);*/

            /* Generations de l'ensemble des itemsets de taille 1 */
            Item [] frequent = {ensemble_Items[0]};
            Itemset itemset1 = new Itemset(frequent, null);
            for (int i = 1; i < comp; i++){
                Item [] tab = {ensemble_Items[i]};
                itemset1 = itemset1.ajouter(tab);
            }
            //itemset1.affiche();
            Itemset frequent1 = apriori(itemset1, tabTrans, 6);
            frequent1.affiche();
            Itemset itemset2 = apriori_Gen(frequent1, 2);
            //itemset2.affiche();
            Itemset frequent2 = apriori(itemset2, tabTrans, 4);
            frequent2.affiche();
            Itemset itemset3 = apriori_Gen(frequent2, 3);
            Itemset frequent3 = apriori(itemset3, tabTrans, 2);
            frequent3.affiche();
            /*Itemset refe = itemset1;
            refe = refe.getReste(); // Pour eliminer la premiere accolade avec le vide
            while (refe != null){
                for (int i = 0; i <= k; i++){
                    if (tabTrans[i].contient(refe.getPremier()[0].getId()))
                        refe.support++;
                }
                refe = refe.getReste();
            }
            itemset1 = itemset1.getReste(); // Pour eliminer la premiere accolade avec le vide
            while (itemset1 != null){
                System.out.println (itemset1.getPremier()[0].getNom()+" avec un support de "+itemset1.getSupport());
                itemset1 = itemset1.getReste();
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Fonction qui verifie si un item appartient a un tableau d'items */
    public static boolean contient (Item [] item, String idItem){
        for (int i = 0; i < item.length; i++){
            if (item[i] == null)
                break;
            else if (item [i].getId().equals(idItem))
                return true;
        }
        return false;
    }

    public static Itemset apriori (Itemset itemset, Transaction [] ens, int minsup){
        /* itemset represente l'itemset sur lequel on doit traiter l'apriori
           ens en le tableau de transaction dans lequel on doit effectuer la recherche
           minsup est le support minimum */
        Itemset frequent = new Itemset (null, null);
        while (itemset != null && itemset.getPremier() != null){
            for (int i = 0; i < ens.length; i++){
                if (ens[i] == null)
                    break;
                int situation = 0;  // Variable pour verifier que tous les elements de l'itemset sont dans la transaction
                for (int j = 0; j < itemset.getPremier().length; j++){
                    if (ens[i].contient(itemset.getPremier()[j].getId()))
                        situation++;
                }
                if (situation == itemset.getPremier().length)
                    itemset.support++;
            }
            if (itemset.support >= minsup)
                frequent = frequent.ajouter(itemset.getPremier());
            itemset = itemset.getReste();
        }
        return frequent;    
    }

    public static Itemset apriori_Gen (Itemset frequent, int n){
        /* frequent represente l'itemset a partir du quel on doit creer celui de taille superieur
           n est la taille superieure */
        int m = frequent.taille()*frequent.taille_Info();
        Item [] tab = new Item [m];
        int l = 0;  // Represente le nombre reel d'element dans le tableau d'items
        while (frequent != null && frequent.getPremier() != null){
            for (int j = 0; j < frequent.getPremier().length; j++){
                if (contient(tab, frequent.getPremier()[j].getId()) == false){
                    tab [l] = frequent.getPremier()[j];
                    l++;
                }
            }
            frequent = frequent.getReste();
        }
        Itemset result = new Itemset(null, null);
        for (int i = 0; i <= l-n; i++){
            Item [] info = new Item [n-1];
            int k = 0;
            for (int j = i; j < i+n-1; j++){
                info [k] = tab [j];
                k++;
            }
            for (int e = i+n-1; e < l; e++){
                Item [] last = new Item [1];
                last [0] = tab [e];
                result = result.ajouter (fusion(info, last));
            }
        }
        return result;
    }

    /* Fonction qui fusionne 2 tableaux d'items */
    public static Item [] fusion (Item [] a, Item [] b){
        Item [] c = new Item [a.length+b.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i];
        c [c.length-1] = b[0];
        return c;
    }
}
