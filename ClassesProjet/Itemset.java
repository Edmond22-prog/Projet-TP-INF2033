package ClassesProjet;
import ClassesProjet.Item;

public class Itemset {
    Item [] info;
	Itemset suivant;
	public int support = 0;

    public Itemset (Item [] info, Itemset suivant){
        this.info = info;
		this.suivant = suivant;
    }

    public Item [] getPremier (){
        return info;
    }

    public Itemset getReste (){
        return suivant;
	}
	
	public Itemset ajouter (Item [] item){
		return new Itemset (item, this);
	}

    public int taille (){
		int compteur = 0;
		Itemset ref = this;
		while (ref != null){
			compteur++;
			ref = ref.getReste();
		}
		return compteur;
	}
	
	/* Renvoi la taille du tableau de la variable info */
	public int taille_Info (){
		return info.length;
	}
	
	/* Verifie si un itemset appartient a la liste en verifiant
	les identifiants de ses produits (de l'itemset) */
	public boolean contient (String idItem){
		Itemset ref = this;
		while (ref != null){
			for (int i = 0; i < ref.taille_Info(); i++){
				if (ref.getPremier()[i].getId().equals(idItem))
					return true;
			}
			ref = ref.getReste();
		}
		return false;
	}

	public void affiche (){
		Itemset ref = this;
		while (ref != null && ref.getPremier() != null){
			System.out.print ("- {");
			for (int j = 0; j < ref.taille_Info(); j++){
				System.out.print (ref.getPremier()[j].getNom()+"; ");
			}
			System.out.println ("}");
			ref = ref.getReste();
		}
		System.out.print ("\n\n");
	}

	public int getSupport (){
		return support;
	}
}
