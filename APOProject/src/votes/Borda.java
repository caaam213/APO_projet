package votes;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import parametres.Candidat;
import parametres.Electeur;
import Utilites.*;

public class Borda extends Scrutin{

	public Borda(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void simulation() {
		// TODO Auto-generated method stub
		
	}
	
	public HashMap<Candidat,Double> scrutinBorda(Candidat[] candidats, Electeur[] electeurs)
	{
		int n = electeurs.length;
		HashMap<Candidat,Double> res = new HashMap<Candidat,Double>();
		ArrayList<Double> list = new ArrayList<Double>();
		Electeur[] unelecteur = new Electeur[1];
		
		for(Electeur electeur: electeurs)
		{
			//Récupération des points par candidats
			for(Candidat candidat: candidats)
			{
				unelecteur[0] = electeur;
				//Calcul des différence
				//double[][] diffaxes = CalculVote.CalculDifferenceAxes(candidat, electeur);
				//Calcul de la norme
				//double[] diffnormes = CalculVote.getNormes(diffaxes);
				
				
				
			}
			//list.sort() // classement des candidats
			
			//Ajout des points par candidats
			for(Candidat candidat: candidats)
			{
				
			}
			list.clear();// effacer pour éventuellement recommencer
		}
			//res.put(...)
		
		
		return null;
	}
	@Override
	public void sondage(double pourcentpop) {
		// TODO Auto-generated method stub
		
	}


	

}
