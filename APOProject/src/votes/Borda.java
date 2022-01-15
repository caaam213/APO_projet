package votes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import parametres.Candidat;
import parametres.Electeur;
import Utilites.*;

/**
 * @author nabil
 *
 */
public class Borda extends Scrutin{

	/**
	 * Constructeur
	 * 
	 * @param candidats
	 * @param electeurs
	 */
	public Borda(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Permet de lancer la simulation
	 */
	@Override
	public void simulation() {
		// TODO Auto-generated method stub
		resultatScrutin = scrutinBorda(candidats,electeurs);
	}
	
	/**
	 *@param pourcentElecteurs pourcentage d'électeurs testé dans la population
	 */
	@Override
	public void sondage(double pourcentElecteurs) {
		// TODO Auto-generated method stub
		resultatSondage = scrutinBorda(candidats, CalculVote.recupElecteurAlea(pourcentElecteurs, electeurs));
	}
	
	/**
	 * Fonction de traitement
	 * 
	 * @param candidats
	 * @param electeurs
	 * @return
	 */
	private LinkedHashMap<Candidat,Double> scrutinBorda(Candidat[] candidats, Electeur[] electeurs)
	{
		//int n = electeurs.length;
		LinkedHashMap<Candidat,Double> res = new LinkedHashMap<Candidat,Double>();
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

}
