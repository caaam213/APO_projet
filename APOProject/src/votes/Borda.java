package votes;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
		//int n = electeurs.length;
		HashMap<Candidat,Double> res = new HashMap<Candidat,Double>();
		HashMap<Double,Candidat> NormeCandidats_Electeur = new HashMap<Double,Candidat>();
		ArrayList<Double> list = new ArrayList<Double>();
		Electeur[] unelecteur = new Electeur[1];
		double[] diffaxes;
		double diffnormes;
		
		
		//Initialisation des candidats
		for(Candidat candidat: candidats)
		{
			res.put(candidat, (double) 0);
		}
		//Traitement des électeurs un par un
		for(Electeur electeur: electeurs)
		{
			//Récupération des points par candidats
			for(Candidat candidat: candidats)
			{
				unelecteur[0] = electeur;
				//Calcul des différence
				diffaxes = CalculVote.CalculDifferenceAxes(candidat, electeur);
				//Calcul de la norme
				diffnormes = CalculVote.getNormes(diffaxes);
				NormeCandidats_Electeur.put(diffnormes, candidat);
				list.add(diffnormes);
			}
			
			//Classement des candidats
			Collections.sort(list);
			
			//Ajout des points par candidats
			for(int i=0;i<list.size();i++)
			{
				res.put(NormeCandidats_Electeur.get(list.get(i)), res.get(NormeCandidats_Electeur.get(list.get(i))) + list.size() - i );
			}
			
			// Effacer pour éventuellement recommencer
			list.clear();
		}
		
		return res;
	}
	
	@Override
	public void sondage(double pourcentpop) {
		// TODO Auto-generated method stub
		
	}


	

}
