package votes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
		resultatScrutin= new LinkedHashMap<Candidat, Double>();
		resultatSondage = new LinkedHashMap<Candidat, Double>();
	}

	public Scrutin(Candidat[] candidats)
	{
		this.candidats = candidats;
		this.electeurs = null; //Pour les votes alternatifs, on ne va pas utiliser ce tableau
		resultatScrutin= new LinkedHashMap<Candidat, Double>();
		resultatSondage = new LinkedHashMap<Candidat, Double>();
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
				System.out.print("Axes de cet electeur avant : ");
				System.out.print(electeur.toString());
				electeur.modifierOpinionParDiscussion(electeurs[electeurChoisi]);
				System.out.print(" ==> Axes de cet electeur après : ");
				System.out.print(electeur.toString());
				System.out.println("");
			}
			
			if(personneChoisie == 1)
			{
				System.out.print("Axes de cet electeur avant : ");
				System.out.print(electeur.toString());
				int candidatChoisi = rand.nextInt(candidats.length);
				electeur.modifierOpinionParDiscussion(electeurs[candidatChoisi]);
				System.out.print(" ==> Axes de cet electeur après : ");
				System.out.print(electeur.toString());
				System.out.println("");
			}
			nbElecteur++;
		}
	}
	
	public void evoluerToutesLesOpinionsParIdees(int N)
	{
		for(Electeur electeur : electeurs)
		{
			System.out.print("Axes de cet electeur avant : ");
			System.out.print(electeur.toString());
			electeur.evoluerOpinionsParIdee(resultatSondage, N);
			System.out.print(" ==> Axes de cet electeur après : ");
			System.out.print(electeur.toString());
			System.out.println("");
		}
	}
	
	public void evoluerToutesLesOpinionsParCote()
	{
		for(Electeur electeur : electeurs)
		{
			System.out.print("Axes de cet electeur avant : ");
			System.out.print(electeur.toString());
			electeur.evoluerOpinionsParCote(resultatSondage);
			System.out.print(" ==> Axes de cet electeur après : ");
			System.out.print(electeur.toString());
			System.out.println("");
		}
	}
	
	public void evoluerToutesLesOpinionsParMoyenne()
	{
		for(Electeur electeur : electeurs)
		{
			System.out.print("Axes de cet electeur avant : ");
			System.out.print(electeur.toString());
			electeur.evoluerOpinionsParMoyenne(resultatSondage);
			System.out.print(" ==> Axes de cet electeur après : ");
			System.out.print(electeur.toString());
			System.out.println("");
		}
	}
	
	public LinkedHashMap<Candidat, Double> getResultatScrutin() {
		return resultatScrutin;
	}

	public LinkedHashMap<Candidat, Double> getResultatSondage() {
		return resultatSondage;
	}

	public Candidat[] getCandidats() {
		return candidats;
	}

	public Electeur[] getElecteurs() {
		return electeurs;
	}

}	
	
























