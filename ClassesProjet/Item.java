package ClassesProjet;

public class Item {
    String idItem;
    String nom;
    String prix;
    String quantite;

    public Item (String idItem, String nom, String quantite, String prix){
        this.idItem = idItem;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    public String getId (){
        return idItem;
    }

    public String getNom (){
        return nom;
    }

    public Double getPrix (){
        return Double.parseDouble(prix);
    }

    public int getQuantite (){
        return Integer.parseInt(quantite);
    }
}