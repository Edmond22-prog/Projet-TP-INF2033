package ClassesProjet; 
import ClassesProjet.Item;

public class Transaction {
    String idTrans;
    Item [] produits;
    String idClient;
    int compt;  // Compteur du nombre d'items

    public Transaction (String idTrans, String idClient){
        this.idTrans = idTrans;
        this.idClient = idClient;
        produits = new Item [100];
        compt = 0;
    }

    public String getIdTransaction (){
        return idTrans;
    }

    public String getIdClient (){
        return idClient;
    }

    /* Methode qui renvoi un tableau d'items, soit un tableau de produit */
    public Item [] getProduits (){
        return produits;
    }

    /* Methode permettant d'ajouter un produit (item) dans le panier de transaction */
    public void ajouterItem (Item item){
        produits [compt] = item;
        compt++;   
    }

    public void afficheProduits (){
        System.out.println ("Pour la transaction "+this.getIdTransaction());
        for (int i = 0; i < compt; i++){
            System.out.println ("- "+produits[i].getNom());
        }
        System.out.print ("\n\n");
    }

    public int getVolumeTransaction (){
        return compt;
    }

    public boolean contient (String idItem){
        for (int i = 0; i < compt; i++){
            if (produits[i].getId().equals(idItem))
                return true;
        }
        return false;
    }
}
