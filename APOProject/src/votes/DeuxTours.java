package votes;

import Utilites.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import parametres.Candidat;
import parametres.Electeur;

/**
 * @author nabil
 *
 */
public class DeuxTours extends Scrutin{

	/**
	 * Constructeur
	 * 
	 * @param candidats
	 * @param electeurs
	 */
	public DeuxTours(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
	}

	/**
	 *	Simulation du scrutin avec 2 candidats au second tour par défaut
	 */
	@Override
	public void simulation(Candidat[] candidats) {
		resultatScrutin = scrutinDeuxTours(candidats, electeurs, 2);
	}
	/**
	 * @param n nombre de Candidat voulus au second tour
	 */
	public void simulation(int n)
	{
		resultatSondage = scrutinDeuxTours(candidats, electeurs, n);
	}
	/**
	 * @param pourcentElecteurs pourcentage d'électeurs testé dans la population
	 */
	@Override
	public void sondage(double pourcentElecteurs) {
		resultatSondage = scrutinDeuxTours(candidats, CalculVote.recupElecteurAlea(pourcentElecteurs, electeurs), 2);
	}
	/**
	 * @param pourcentElecteurs pourcentage d'électeurs testé dans la population
	 * @param n nombre de Candidat voulus au second tour
	 */
	public void sondage(double pourcentElecteurs, int n) {
		resultatSondage = scrutinDeuxTours(candidats, CalculVote.recupElecteurAlea(pourcentElecteurs, electeurs), n);
	}
	
	/**
	 * Fonction de traitement
	 * 
	 * @param candidats
	 * @param electeurs
	 * @param nb_sectour
	 * @return
	 */
	private LinkedHashMap<Candidat,Double> scrutinDeuxTours(Candidat[] candidats, Electeur[] electeurs, int nb_sectour)
	{
		//-------------1er Tour------------
		UnTour tour_un = new UnTour(candidats, electeurs);
		System.out.println("---1er tour:---");
		LinkedHashMap<Candidat,Double> res_tourun = tour_un.scrutinUnTour(candidats, electeurs);
		
		//Selection des n premiers candidats
		ArrayList<Integer> id_secondtour = trier_ncandidats( nb_sectour , res_tourun );
		
		//-------------2e Tour-------------
		Candidat[] candidats_2 = new Candidat[id_secondtour.size()];
		for(int i=0;i<id_secondtour.size();i++)
		{
			candidats_2[i] = candidats[id_secondtour.get(i)];
		}
		UnTour tour_deux = new UnTour(candidats_2, electeurs);
		System.out.println("---2eme tour:---");
		
		
		return tour_deux.scrutinUnTour(candidats_2, electeurs);
	}
	
	private ArrayList<Integer> trier_ncandidats( int n , HashMap<Candidat, Double> res_tourun )
	{
		double pourcent_vote;
		double pourcent_vote_max = 0;
		int id_candidat_max = 0;
		//int[] id_sectour = new int[nb_sectour];
		ArrayList<Integer> id_secondtour = new ArrayList<Integer>();
		for(int k=0;k<n;k++)
		{
			for(int i=0;i<candidats.length;i++)
			{
				pourcent_vote = res_tourun.get(candidats[i]);
				
				if( (pourcent_vote >= pourcent_vote_max) && !id_secondtour.contains(i))
				{
					pourcent_vote_max = pourcent_vote;
					id_candidat_max = i;
				}
			}
			if(!id_secondtour.contains(id_candidat_max))
			{
				id_secondtour.add(id_candidat_max);
				pourcent_vote_max = 0;
			}
		}
		
		return id_secondtour;
	}


}




































