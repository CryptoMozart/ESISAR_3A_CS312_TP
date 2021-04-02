
public class Dessert extends Plat {
	public Dessert(String nom,  int prix, int kcal, float glucide) {
		super(nom, prix,kcal,glucide);
	}
	public Dessert(String nom, int prix){
		this(nom,prix,0,0);
	}
}
