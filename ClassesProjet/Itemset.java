package ClassesProjet;
import ClassesProjet.*;

public class Itemset {
    Item info;
    Itemset suivant;

    public Itemset (Item info, Itemset suivant){
        this.info = info;
        this.suivant = suivant;
    }

    public String getPremier (){
        return info.getNom();
    }

    public Itemset getReste (){
        return suivant;
    }

    public Itemset ajouter (Item item){
        return new Itemset(item, this);
    }

    public int taille (){
		int compteur = 0;
		Itemset ref = this;
		while (ref != null){
			compteur++;
			ref = ref.getReste();
		}
		return compteur-1;
    }
    
    public boolean contient (Item item){
		Itemset ref = this;
		while (ref != null){
			if (ref.getPremier() == item.getNom())
				return true;
			else
				ref = ref.getReste();
		}
		return false;
    }
    
    public Itemset supprimerPremier (){
		Itemset ref = this;
		return ref.getReste();
	}

	public void affiche (){
		Itemset ref = this;
		int taille = ref.taille();
		System.out.println ("Affichage des produits :");
		for (int i = 0; i <= taille; i++){
			System.out.println ("- "+ref.getPremier());
			ref = ref.getReste();
		}
		System.out.print ("\n\n");
	}
}
