import java.util.*;

public class Creneau {
	private int annee;
	private int mois; // 1 e 12
	private int jour; // 1 e 31
	private int heure; // 0 e 23
	private int minute; // 0 e 59
	private int duree; // en minutes, maximum 210
	
	private Salle salle;
	private Activite activite;
	
	public Creneau(int a, int m, int j, int h, int min, int d, Salle s, Activite ac) {
		
		annee = a; mois = m; jour = j;
		heure = h; minute = min; duree = d;
		salle = s;
		activite = ac;
		
		if(!verifCapacite()){
			System.exit(1);
		}
		if(!verifDuree()){
			System.exit(1);
		}
		if(!verifSalle()){
			System.exit(1);
		}
	}
	
	// Verifie l'adequation de la salle : la salle affectee doit etre une des salles appropriees de l'activite
	private boolean verifSalle(){
		boolean return_value = false;

		for( Salle s : activite.getSalles()){

			if(s.getNom() == salle.getNom()) return_value = true;
//on parcourt les salles de l'activité. si le nome de la salle donnée correspond à une salle de l'activité on retourne true. 
		}

		return return_value;
	}
	
	// Verifie que la taille de la salle convient e la promo
	private boolean verifCapacite(){
		
		int nb_eleves = 0;

		for( Groupe grp : this.activite.getGroupes()){

			nb_eleves += grp.getEffectif();
//on parcourt les groupes de l'activité avec la cariable grp et on somme l'effectif de chaque groupe assoié à la salle pour vérifier la capacité

		}
		return (nb_eleves <= salle.getCapacite());
	}
	
	// Verifie que le debut et la fin du creneau sont dans la meme journee, entre 8h et 19h
	private boolean verifDuree(){
		return (heure+(minute+duree)/60 <= 19 && heure >= 8);
// on regarde si le créneau est entre 8h et 19h. On vérifie que le début de l'activité + la durée ne dépasse pas 19h.
	}
	
	public Salle getSalle(){
		return salle;
	}
	
	public Activite  getActivite(){
		return activite;
	}
	
	public int  getDuree(){
		return duree;
	}
	
	public String toString(){
		return jour + "/" + mois + "/" + annee + " " + heure + ":" + minute +" (" + duree +") : " + 
				activite + " " + salle;
	}
	
	
	public boolean _intersection(int _annee, int _mois, int _jour, int _heure, int _minute, int _duree){

		boolean return_value = false;

		if(_annee == this.annee && _mois == this.mois && _jour == this.jour){ //si on est dans la meme journée

			if (_heure*60+_minute >= this.heure*60+this.minute && _heure*60+_minute <= this.heure*60+this.minute+this.duree){ // si le debut du creneau est compris dans notre creneau

				return_value = true;
			}
			else if (_heure*60+_minute+_duree >= this.heure*60+this.minute && _heure*60+_minute+_duree <= this.heure*60+this.minute+this.duree){ //si la fin du creneau dans no

				return_value = true;
			}
		}

		return return_value;
	}

	public boolean intersection(Creneau c){
		 
		boolean return_value = false;

		if (c._intersection(this.annee, this.mois, this.jour, this.heure, this.minute, this.duree)){
//on utilise la fonction _intersection pour comparer des variables données en argument pour ne pas implémenter les getter.
			if( this.salle.getNom() != c.getSalle().getNom()){

				for(Groupe grp1 : this.activite.getGroupes()){
 //on parcourt les groupes de cette classe dans les activités avec la variable grp1
					for(Groupe grp2 : c.getActivite().getGroupes()){
//on parcourt les groupes du créneau donné en argument avec la variable grp2

						if(grp1.getNom() == grp2.getNom()) return true;
//si les noms des groupes sont les egaux alors il y a une intersection, on renvoie alors true.
					}
				}
			}
		}

		return return_value;
	}
	
}
