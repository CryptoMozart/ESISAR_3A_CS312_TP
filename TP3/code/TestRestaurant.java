
public class TestRestaurant {

  public static void main (String [] args){
      Entree e = new Entree("Salade Grecque",800,10,0);
      PlatPrincipal p = new PlatPrincipal("Moussaka du chef", 1200,100,0);
      Dessert d = new Dessert("Loukoumades", 500,30,0);
      Boisson b = new Boisson("Ouzo (5cL)", 5,800,15,0);

      Menu m = new Menu(1500,e, p, d, b);

      Carte carte = new Carte();
      carte.addEntree(e);
      carte.addPlatPrincipal(p);
      carte.addDessert(d);
      carte.addBoisson(b);

      Entree e2 = new Entree("Oui",1000);

      carte.addMenu(m);

      Commande commande = new Commande();
      commande.addItem(e);
      commande.addItem(p);
      commande.addItem(d);
      commande.addItem(b);
      commande.addItem(e2);

      System.out.println("\n\n------------------TEST COMMANDE ET MENU SIMPLE--------------------\n\n");

      System.out.println("Le prix de la commande est : " + carte.calculerPrixCommande(commande)/100 + " euros");
      System.out.println("");
      carte.proposerMenu(154,1);

      System.out.println("\n\n------------------TEST AFFICHAGE CARTE VIA FICHIER--------------------\n\n");

      Carte carte2 = new Carte("../carte.flst");
      carte2.afficherMenu();
      System.out.println("");
      carte2.proposerMenu(327,15);
    }
}
