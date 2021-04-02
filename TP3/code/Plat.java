
public class Plat implements Consommable, Nutrition{

//on fait la même chose que pour la classe Boisson

	private int prix;
	private String nom;
	private int kcal;
	private float glucide;

	public Plat(String nom, int prix, int kcal, float glucide){
			this.nom = nom;
			this.prix = prix;
			this.kcal = kcal;
			this.glucide = glucide;
		}
	public Plat(String nom, int prix){
			this(nom,prix,0,0);
		}

	public int getPrix(){
		return prix;
	}

	public String getNom(){
		return nom;
	}
	public int getKcal(){
			return kcal;
		}

  public float getGlucides(){
			return glucide;
		}

	public String toString(){
		String message = "\nMets: ";
	  message = message + nom + ", " + "Au prix de : " + (prix/100) + "e, " + "(" + "Kal: " + kcal + " Glucide: " + glucide + ")";
		return(message);
	}
}
