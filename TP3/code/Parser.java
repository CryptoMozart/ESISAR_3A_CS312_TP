import java.util.*;
import java.io.*;

// Format d'enregistrement de la carte
// ATTENTION : Une il ne doit pas avoir autre choses que des menus apres "Menu"
//--------------------------------
//Lignes de commentaires possibles
//Lignes de commentaires possibles
//.....
//Lignes de commentaires possibles
//Lignes de commentaires possibles
//Consommable
// type consommables (entree / plat / dessert / boisson)
//  nom
//  prix
//  volume ("null" si ce n'est pas une boisson)
//  kcal
//  glucide
// type consommables
//  ...
//Menu
// prix
//  nom entree
//  nom plat
//  nom dessert
//  nom boisson
// prix
//  ...
//-------------------------------

public class Parser {

  private BufferedReader buffer;

  private ArrayList<ArrayList<String>> parsed_struct = new ArrayList<ArrayList<String>>();

  public Parser(BufferedReader buffer){

    this.buffer = buffer;
  }

  public ArrayList<ArrayList<String>> parse(){

    String line = new String(); //Objet contenant la ligne courante

    ArrayList<String> parse_to_add = new ArrayList<String>(); //Array temporaire pour la construction de l'array final (parsed_struct)

    boolean need_to_write = false; //Variable indiquant si l'array temporaire est pret pour etre enregistré dans l'array final (parsed_struct)

    int state = 0; //Initialisation de la variable d'etat pour la machine à état
		//permettant de se situer dans la lecture du fichier

    int index_in_data = 0; //Variable stockant le nombre d'attribut stocké dans l'array temporaire

    try { //lecture d'une nouvelle ligne du fichier
      line = buffer.readLine();
    } catch(Exception e) {
      System.out.println("erreur lecture fichier : " + e);
    }

    while(line != null ){ //Tant que nous ne sommes pas à la fin du fichier
      if(line.contentEquals("Consommable")){ //Detection du mot clé commencant le parsing du fichier
        state = 1; //la ligne suivante sera forcement le mot clé Menu ou les mots clé de type de consommables
        parse_to_add.add(line);
        need_to_write = true;
      }
      else if(state == 1){
        if(!line.contentEquals("Menu")){
          line = line.trim(); //Le mot clé est celui d'un consommable
          parse_to_add.add(line);
          state = 2; //les prochaines lignes seront donc ces attributs
          need_to_write = true;
        }
        else{
          parse_to_add.add(line);
          state = 3; //Le mot clé est "Menu", on passe donc en mode de lecture de menu
          need_to_write = true;
        }
      }
      else if(state == 2){

        line = line.trim(); //on supprime les espaces qui precede et qui succedent
        if(index_in_data != 2 || !line.contentEquals("null")){ //la ligne est celle de l'attribut des boissons
          parse_to_add.add(line); //si l'attribut n'est pas "null" alors on l'ajoute à l'array temporaire
        }
        index_in_data++; //on a lu un nouvel attribut
        if(index_in_data > 4){ // si on à lu les quatres attributs d'un consommable
          index_in_data = 0;
          state = 1; // on remet l'etat sur la Detection du type
          need_to_write = true; //et on les ajoutent à l'Array final
        }
      }
      else if (state == 3) {

        line = line.trim(); //Meme chose pour les menus, ici on lit le prix
        parse_to_add.add(line);
        state = 4;
      }
      else if (state == 4) { //et ici on lit les noms des plats qui compose le menu

        line = line.trim();
        parse_to_add.add(line);
        index_in_data++;
        if(index_in_data > 3){
          index_in_data = 0;
          state = 3;
          need_to_write = true;
        }
      }

      if(need_to_write){ //si l'array temporaire est pret pour etre enregistré
        parsed_struct.add(parse_to_add); // On l'enregistre et on le reinitialise
        need_to_write = false;
        parse_to_add = new ArrayList<String>();
      }

      try {  //lecture d'une nouvelle ligne du fichier
        line = buffer.readLine();
      } catch(Exception e) {
        System.out.println("erreur lecture fichier");
      }
    }

    return(parsed_struct);
  }
}
