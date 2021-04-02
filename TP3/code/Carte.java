
import java.util.*;
import java.io.*;

public class Carte {
	private ArrayList<Consommable> entrees;
	private ArrayList<Consommable> platsPrincipaux;
	private ArrayList<Consommable> desserts;
	private ArrayList<Consommable> boissons;

	private ArrayList<Menu> menus;

	public Carte() {
		entrees = new ArrayList<Consommable>();
		platsPrincipaux = new ArrayList<Consommable>();
		desserts = new ArrayList<Consommable>();
		boissons = new ArrayList<Consommable>();
		menus = new ArrayList<Menu>();
	}

	public Carte(String filename){

		entrees = new ArrayList<Consommable>();
		platsPrincipaux = new ArrayList<Consommable>();
		desserts = new ArrayList<Consommable>();
		boissons = new ArrayList<Consommable>();
		menus = new ArrayList<Menu>();

		//on declare les lecteurs de fichiers et on les initialise
		BufferedReader buffer = null;
    FileReader filereader = null;

		try {
			filereader = new FileReader(filename);
		} catch(Exception e) {
			System.out.println("Ereur ouverture fichier : " + e);
		}
		buffer = new BufferedReader(filereader);

		//On declare une instance du parseur avec le buffer
		Parser parser = new Parser(buffer);

		//On recupere l'ArrayList des instructions parsés
		ArrayList<ArrayList<String>> parsed_menu = parser.parse();

		//Initialisation de la variable d'etat pour la machine à état
		//permettant de se situer sur la lecture des instructions parsées
		int etat = 0;

		for (ArrayList<String> str_arr : parsed_menu) {

			if(str_arr.get(0).contentEquals("Consommable")){
				etat = 1; //On passe à la lecture des types de consommables
			}

			else if (etat == 1) {
				if(str_arr.get(0).contentEquals("entree")){
          etat = 2; //etat pour la lecture d'entree
        }
				else if(str_arr.get(0).contentEquals("plat")) {
					etat = 3; //etat pour la lecture de plat
				}
				else if(str_arr.get(0).contentEquals("dessert")) {
					etat = 4; //etat pour la lecture de dessert
				}
				else if(str_arr.get(0).contentEquals("boisson")) {
					etat = 5; //etat pour la lecture de boisson
				}
        else if(str_arr.get(0).contentEquals("Menu")){
          etat = 6; //etat pour la lecture de menus
        }
			}

			else if (etat == 2) { //on crée les consommables et on les ajoutes
				Entree e = new Entree(str_arr.get(0),Integer.parseInt(str_arr.get(1)),Integer.parseInt(str_arr.get(2)),Integer.parseInt(str_arr.get(3)));
				addEntree(e);
				etat = 1;
			}
			else if (etat == 3) {
				PlatPrincipal p = new PlatPrincipal(str_arr.get(0),Integer.parseInt(str_arr.get(1)),Integer.parseInt(str_arr.get(2)),Integer.parseInt(str_arr.get(3)));
				addPlatPrincipal(p);
				etat = 1;
			}
			else if (etat == 4) {
				Dessert d = new Dessert(str_arr.get(0),Integer.parseInt(str_arr.get(1)),Integer.parseInt(str_arr.get(2)),Integer.parseInt(str_arr.get(3)));
				addDessert(d);
				etat = 1;
			}
			else if (etat == 5) {
				Boisson b = new Boisson(str_arr.get(0),Integer.parseInt(str_arr.get(1)),Integer.parseInt(str_arr.get(2)),Integer.parseInt(str_arr.get(3)),Integer.parseInt(str_arr.get(4)));
				addBoisson(b);
				etat = 1;
			}
			else if (etat == 6){ //l'array decrit un menu, on cherche les objets associés aux noms des consommables donnés
				Consommable e = find_consommable(str_arr.get(1), entrees);
				Consommable p = find_consommable(str_arr.get(2), platsPrincipaux);
				Consommable d = find_consommable(str_arr.get(3), desserts);
				Consommable b = find_consommable(str_arr.get(4), boissons);

				if(e != null && p != null && d != null && b != null){ //si tous les plats ont été trouvé dans la carte
					Menu m = new Menu(Integer.parseInt(str_arr.get(0)),e, p, d, b); //on crée le menu et on l'ajoute
					addMenu(m);
				}
			}

		}
	}

	private Consommable find_consommable(String str, ArrayList<Consommable> consommables){

		for (Consommable c : consommables) {
			if(str.contentEquals(c.getNom())) return c;
		}

		return null;
	}

	public void addEntree(Entree e){
		if (verifCarte(e)) this.entrees.add(e);
	}

	public void addPlatPrincipal(PlatPrincipal p){
		if (verifCarte(p)) this.platsPrincipaux.add(p);
	}

	public void addDessert(Dessert d){
		if (verifCarte(d)) this.desserts.add(d);
	}

	public void addBoisson(Boisson b){
		if (verifCarte(b)) this.boissons.add(b);
	}

	public void addMenu(Menu m){
		if (verifMenu(m)){
			this.menus.add(m);
		}
	}

	public ArrayList<Consommable> getEntrees(){
		return this.entrees;
	}

	public ArrayList<Consommable> getPlatsPrincipaux(){
		return this.platsPrincipaux;
	}

	public ArrayList<Consommable> getDesserts(){
		return this.desserts;
	}

	public ArrayList<Consommable> getBoissons(){
		return this.boissons;
	}

	// Verifie que les plats et boissons du menu sont bien dans la carte
	private boolean verifMenu(Menu m){
		//on créer 4 variables boolenne pour vérifier l'existence des plats dans le menu
		boolean verif_entree = false;
		boolean verif_plat = false;
		boolean verif_dessert = false;
		boolean verif_boisson = false;

//on parcourt les consommables du menus et on fait une disjontion de cas en fonction du type de consommable
		for(Consommable c : m.getItems()){
			for(Consommable e: entrees){
				if(e.getNom() == c.getNom())verif_entree=true;
			}
			for(Consommable p: platsPrincipaux){
				if(p.getNom() == c.getNom())verif_plat=true;
			}
			for(Consommable d: desserts){
				if(d.getNom() == c.getNom())verif_dessert=true;
			}
			for(Consommable b: boissons){
				if(b.getNom() == c.getNom())verif_boisson=true;
			}
		}
		return (verif_plat && verif_boisson && verif_entree && verif_dessert); //si tous les plats sont des menus
	}

	// Verifie qu'il n'y a pas d'homonymes dans la carte
	private boolean verifCarte(Consommable c){

		boolean return_value = true;
/*on parcourt notre carte et on compare avec le nom du consommable
on fait une disjonction de cas en fonction du consommable */
		for(Consommable b : this.boissons ){
			if (b.getNom() == c.getNom()) return_value = false;
		}
		for(Consommable e : this.entrees ){
			if (e.getNom() == c.getNom()) return_value = false;
		}
		for(Consommable p : this.platsPrincipaux ){
			if (p.getNom() == c.getNom()) return_value = false;
		}
		for(Consommable d : this.desserts ){
			if (d.getNom() == c.getNom()) return_value = false;
		}

		return return_value;
	}

	/* Calcule le prix de la commande. A priori, ce prix est la somme des prix des items
	 * SAUF si une partie de ces items constituent un menu; dans ce cas, le tarif menu s'applique pour ces items.
	 */
	public int calculerPrixCommande(Commande c){

		//crée copie de la commande pour l'édité
		Commande local_commande = new Commande();
		local_commande.copyItems(c);

		int prix_commande = 0;

		for(Menu m : this.menus){ //foreach menu dans menus
			boolean menu_fait = true;

			while((local_commande.getItemsCommandes()).size() > 4 && menu_fait == true){ //tant que ce menu est fait et qu'il y a plus de 4 consommables dans la commande

				Consommable[] elemnts_a_suppr = {null,null,null,null}; //on initialise le tableau contenant les items qui pourraient composer le menu
				int indice = 0;

				for(Consommable conso: m.getItems()){ //pour chaque item qui compose le menu
					for (int i = 0; i < local_commande.getItemsCommandes().size() ; i++) { //pour chaque item qui compose la commande
						if( conso.getNom() == local_commande.getItemsCommandes().get(i).getNom()){ //si l'item du menu est dans la commande
							 elemnts_a_suppr[indice] = local_commande.getItemsCommandes().get(i); //on ajoute l'item au tableau qui composera le menus
						}
					}
					indice++;
				}

				for(int j = 0; j<4;j++){
					if(elemnts_a_suppr[j] == null){
						menu_fait = false; //si un des items du menu n'a pas été attribué, le menu n'a pas pu etre composé
					}
				}
				if(menu_fait){ //si un menu à été composé
					prix_commande += m.getPrix(); //on ajoute le prix du menu au total de la commande
					for(int j = 0; j<4;j++){
						local_commande.getItemsCommandes().remove(elemnts_a_suppr[j]); //on supprime les items de la commande composant ce menu
					}
				}
			}
		}

		//sommation du prix des elemnts restant dans la commande
		for (Consommable reste_conso : local_commande.getItemsCommandes()) {
			prix_commande += reste_conso.getPrix();
		}

		return prix_commande;
	}

	public void afficherMenu(){
		System.out.println("Liste des entrees:" + entrees + "\n");
		System.out.println("Liste des plats principaux:" + platsPrincipaux + "\n");
		System.out.println("Liste des desserts:" + desserts + "\n");
		System.out.println("Liste des boissons:" + boissons + "\n");

	}

//proposer un menu en fonction du nombre de kcal et d'une erreur epsilon
	public void proposerMenu(int Kc, int epsilon){

		int totale_kc=0; //pour calculer le nombre de kcal

		for(Menu m : menus){ //on parcourt les menus

			for(Consommable c: m.getItems()){ //on parcourt les consommables du menu
				totale_kc +=c.getKcal(); //on incrémente le compteur du nombre de kcal
			}
			if(totale_kc >= Kc - epsilon && totale_kc <= Kc + epsilon){ //on vérifié si le module (Kc - totale_kc) est inférieur à epsilon
				System.out.println(m);
			}
		}
	}

}
