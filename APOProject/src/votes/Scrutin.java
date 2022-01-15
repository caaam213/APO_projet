package votes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

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
	
	public void evoluerToutesLesOpinionsParDiscussion()
	{
		Random rand = new Random();
		
		for(Electeur electeur : electeurs)
		{
			int personneChoisie = rand.nextInt(2); //0:electeur 1:candidat
			if(personneChoisie == 0)
			{
				//Gérer la spacialisation ici
				int electeurChoisi = rand.nextInt(electeurs.length);
				electeur.modifierOpinionParDiscussion(electeurs[electeurChoisi]);
			}
			else
			{
				int candidatChoisi = rand.nextInt(candidats.length);
				electeur.modifierOpinionParDiscussion(electeurs[candidatChoisi]);
			}
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
	
























