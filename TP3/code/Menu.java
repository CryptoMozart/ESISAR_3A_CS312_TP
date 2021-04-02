import java.util.ArrayList;

public class Menu {
	ArrayList<Consommable> items = new ArrayList<Consommable>();
	int prix; // en cents

	public Menu(int prix, Consommable e, Consommable p, Consommable d, Consommable b) {
		this.prix = prix;
		items.add(e);
		items.add(p);
		items.add(d);
		items.add(b);
		if(!verifPrixMenu()){
			System.out.println("Erreur prix menu");
			System.exit(1);
		}
	}

	public ArrayList<Consommable> getItems(){
		return this.items;
	}

	public int getPrix(){
		return this.prix;
	}

	public String toString(){
		String message = "Menu compose de ";

		for (Consommable c : this.items) {

			message += c.getNom() + ", ";
		}

		message += "au prix de " + this.prix/100 + " euros";
		return message;
	}

	private boolean verifPrixMenu(){
		int sommePrix = 0;
		for(Consommable c: this.items){
			sommePrix += c.getPrix();
		}
		return sommePrix >= this.prix && sommePrix > 0;
	}
}
