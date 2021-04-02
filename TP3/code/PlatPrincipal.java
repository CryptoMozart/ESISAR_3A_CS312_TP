
public class PlatPrincipal extends Plat {
	public PlatPrincipal(String nom, int prix,int kcal, float glucide) {
		super(nom, prix,kcal,glucide);
	}
	public PlatPrincipal(String nom, int prix){
		this(nom,prix,0,0);
	}
}
