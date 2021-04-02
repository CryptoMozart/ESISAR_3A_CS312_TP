
import java.util.ArrayList;

public class Commande {
	private ArrayList<Consommable> itemsCommandes;

	public Commande() {
		this.itemsCommandes = new ArrayList<Consommable>();
	}

	public void addItem(Consommable c){
		this.itemsCommandes.add(c);
	}

	public void copyItems(Commande c){

		for (Consommable e : c.getItemsCommandes()) {
			this.itemsCommandes.add(e);
		}
	}

	public ArrayList<Consommable> getItemsCommandes(){
		return this.itemsCommandes;
	}

}
