package votes;

import java.util.ArrayList;
import java.util.HashMap;

import parametres.Candidat;
import parametres.Electeur;

public class DeuxTours extends Scrutin{

	public DeuxTours(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
	}

	@Override
	public void simulation() {
		
	}
	public HashMap<Candidat,Double> scrutinDeuxTours(Candidat[] candidats, Electeur[] electeurs, int nb_sectour)
	{
		//-------------1er Tour------------
		UnTour tour_un = new UnTour(candidats, electeurs);
		HashMap<Candidat, Double> res_tourun = tour_un.scrutinUnTour(candidats, electeurs);
		
		//Selection des n premiers candidats
		double pourcent_vote;
		double pourcent_vote_max = 0;
		int id_candidat_max = 0;
		//int[] id_sectour = new int[nb_sectour];
		ArrayList<Integer> id_secondtour = new ArrayList<Integer>();
		for(int k=0;k<nb_sectour;k++)
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
		
		System.out.println("Les n premiers:");
		for(int k=0;k<nb_sectour;k++)
		{
			System.out.println(id_secondtour.get(k));
		}
		
		//-------------2e Tour-------------
		Candidat[] candidats_2 = new Candidat[nb_sectour];
		for(int i=0;i<id_secondtour.size();i++)
		{
			candidats_2[i] = candidats[id_secondtour.get(i)];
		}
		UnTour tour_deux = new UnTour(candidats_2, electeurs);
		System.out.println("Deuxieme tour:");
		tour_deux.scrutinUnTour(candidats_2, electeurs);
		
		
		
		
		
		
		
		
		return null;
	}
	@Override
	public void sondage(double pourcentpop) {
		// TODO Auto-generated method stub
		
	}


}




































