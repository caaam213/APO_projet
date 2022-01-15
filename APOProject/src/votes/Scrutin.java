package votes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import Utilites.CalculVote;
import parametres.*;

public abstract class Scrutin {
	
	protected Candidat[] candidats;
	protected Electeur[] electeurs;
	protected LinkedHashMap<Candidat,Double> resultatScrutin;
	protected LinkedHashMap<Candidat,Double> resultatSondage;
	
	public Scrutin(Candidat[] candidats, Electeur[] electeurs) {
		this.candidats = candidats;
		this.electeurs = electeurs;
	}

	public Scrutin(Candidat[] candidats)
	{
		this.candidats = candidats;
		this.electeurs = null; //Pour les votes alternatifs, on ne va pas utiliser ce tableau
	}
	public abstract void simulation();
	
	public abstract void sondage( double pourcentElecteurs );
	
	public void evoluerToutesLesOpinionsParDiscussion(boolean spacialisation)
	{
		Random rand = new Random();
		LinkedHashMap<Electeur,Double> distances;
		
		int nbElecteur = 0;
		boolean tropLoin = true; //Detection s'il y a une personne proche sinon on interagit avec un candidat
		for(Electeur electeur : electeurs)
		{
			tropLoin = true;
			distances = new LinkedHashMap<Electeur,Double>();
			int personneChoisie = rand.nextInt(2); //0:electeur 1:candidat
			if(personneChoisie == 0)
			{
				int electeurChoisi = nbElecteur;
				
				if(spacialisation)
				{
					for(Electeur autreElecteur : electeurs)
					{
						double[] diffs = CalculVote.calculDifference(electeur.getPositionGeographique(), electeurs[electeurChoisi].getPositionGeographique());  
					    double distance = CalculVote.getNorme(diffs);
					    if(distance < 10)
					    {
					    	tropLoin = false;
					    }
					    distances.put(autreElecteur, distance);
					}	
					if(tropLoin)
					{
						personneChoisie = 1;
					}
					else
					{
						while((electeurChoisi == nbElecteur) || (distances.get(electeurs[electeurChoisi]))>=10)
						{
							 electeurChoisi = rand.nextInt(electeurs.length);
						}
					}
					
				}
				else
				{
					while(electeurChoisi == nbElecteur)
					{
						 electeurChoisi = rand.nextInt(electeurs.length);
					}
				}
				
				electeur.modifierOpinionParDiscussion(electeurs[electeurChoisi]);
			}
			
			if(personneChoisie == 1)
			{
				int candidatChoisi = rand.nextInt(candidats.length);
				electeur.modifierOpinionParDiscussion(electeurs[candidatChoisi]);
			}
			nbElecteur++;
		}
	}
	
	public void evoluerToutesLesOpinionsParIdees(int N)
	{
		for(Electeur electeur : electeurs)
		{
			electeur.evoluerOpinionsParIdee(resultatSondage, N);
		}
	}
	
	public void evoluerToutesLesOpinionsParCote()
	{
		for(Electeur electeur : electeurs)
		{
			electeur.evoluerOpinionsParCote(resultatSondage);
		}
	}
	
	public void evoluerToutesLesOpinionsParMoyenne()
	{
		for(Electeur electeur : electeurs)
		{
			electeur.evoluerOpinionsParMoyenne(resultatSondage);
		}
	}
	
	public HashMap<Candidat, Double> getResultatScrutin() {
		return resultatScrutin;
	}

	public HashMap<Candidat, Double> getResultatSondage() {
		return resultatSondage;
	}

}	
	
























