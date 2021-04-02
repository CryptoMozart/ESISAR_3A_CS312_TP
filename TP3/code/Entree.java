
public class Entree extends Plat {

	public Entree(String nom, int prix,int kcal, float glucide) {
		super(nom, prix,kcal,glucide);
	}
	public Entree(String nom, int prix){
		this(nom,prix,0,0);
	}
}
