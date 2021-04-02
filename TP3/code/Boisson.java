
public class Boisson implements Consommable, Nutrition{

/*
La class Boissons devient une sous classe de l'interface Consommable. On la défini avec le mot clé implements.
On doit donc créer les fonctions getNom() et getPrix() pour cette sous-classe.
On rajoute une variable volume dans le constructeur.
*/
	private int volume; // en centilitres
	private int prix;
	private String nom;
	private int kcal;
	private float glucide;

	public Boisson(String nom, int prix, int volume,int kcal, float glucide){
			this.nom = nom;
			this.prix = prix;
			this.volume = volume;
			this.kcal = kcal;
			this.glucide = glucide;
		}

	public Boisson(String nom, int volume){
		this(nom, 0, volume, 0, 0);
	}

	public int getVolume(){
		return volume;
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
		String message = "\nBoisson: ";
		message = message + nom + ", " + "Au prix de : " + (prix/100) + "e, " + "(" + volume + "cl" + ") "+ "( " + "Kal: " + kcal + " Glucide: " + glucide + " )";
		return (message);
	}
}
