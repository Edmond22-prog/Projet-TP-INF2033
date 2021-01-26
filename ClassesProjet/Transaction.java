package ClassesProjet; 
import ClassesProjet.*;

public class Transaction {
    String idTrans;
    Itemset produits;
    String idClient;

    public Transaction (String idTrans, Itemset produits, String idClient){
        this.idTrans = idTrans;
        this.produits = produits;
        this.idClient = idClient;
    }

    public String getIdTransaction (){
        return idTrans;
    }

    public String getIdClient (){
        return idClient;
    }

    /* Methode permettant d'ajouter un produit (item) dans le panier de transaction */
    public Transaction ajouterItem (Item item){
        produits = produits.ajouter(item);
        return new Transaction(this.idTrans, produits, this.idClient);
    }

    public void afficheProduits (){
        System.out.println ("Pour la transaction "+this.getIdTransaction());
        produits.affiche();
    }
}
