package ClassesProjet;

public class Item {
    String idItem;
    String nom;
    String prix;

    public Item (String idItem, String nom, String prix){
        this.idItem = idItem;
        this.nom = nom;
        this.prix = prix;
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
}