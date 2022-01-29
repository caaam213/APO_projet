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
	protected Candidat abstention;
	
	public Scrutin(Candidat[] candidats, Electeur[] electeurs) {
		this.candidats = candidats;
		this.electeurs = electeurs;
		resultatScrutin= new LinkedHashMap<Candidat, Double>();
		resultatSondage = new LinkedHashMap<Candidat, Double>();
		int tailleValAxes = candidats[0].getValAxes().length;
		double[] valAxesPourAbstention = new double[tailleValAxes];
		abstention = new Candidat(candidats[0].getAxes(),valAxesPourAbstention,"");
	}

	public Scrutin(Candidat[] candidats)
	{
		this.candidats = candidats;
		this.electeurs = null; //Pour les votes alternatifs, on ne va pas utiliser ce tableau
		resultatScrutin= new LinkedHashMap<Candidat, Double>();
		resultatSondage = new LinkedHashMap<Candidat, Double>();
		int tailleValAxes = candidats[0].getValAxes().length;
		double[] valAxesPourAbstention = new double[tailleValAxes];
		abstention = new Candidat(candidats[0].getAxes(),valAxesPourAbstention,"");
	}
	
	
	
	public abstract void simulation(Candidat[] cands);
	
	public abstract void sondage( double pourcentElecteurs );
	
	public void afficherGagnant() {
		if(resultatScrutin.size()!=0)
		{
			List<Candidat> candidats = new ArrayList<>(resultatScrutin.keySet());
			List<Double> pourcentages = new ArrayList<>(resultatScrutin.values());
			ArrayList<Candidat> candidatsRestants = new ArrayList<>();
			while(pourcentages.get(0) == pourcentages.get(1))
			{
				int i = 2;
				candidatsRestants.add(candidats.get(0));
				candidatsRestants.add(candidats.get(1));
				while(pourcentages.get(i) == pourcentages.get(1))
				{
					candidatsRestants.add(candidats.get(i));
				}
				System.out.println("Cas d'�galit�, nouvelles elections !");
				if(resultatSondage.size()==0)
				{
					sondage(50); //Si un sondage n'a pas �t� r�alis�, un sondage va �tre fait avec 50% de la population
				}
				this.evoluerToutesLesOpinionsParDiscussion(false);
				Candidat[] cRestants = (Candidat[]) candidatsRestants.toArray();
				this.simulation(cRestants);
				candidats = new ArrayList<>(resultatScrutin.keySet());
				pourcentages = new ArrayList<>(resultatScrutin.values());
			}
			System.out.println("Le gagnant est "+candidats.get(0).getNomPrenom()+" avec "+pourcentages.get(0)*100+" %");
		}
		
	}
	
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
				String personne = "L'id de l'electeur choisi est "+electeurs[electeurChoisi].getIdElecteur();
				System.out.print(electeur.toString());
				System.out.print(" ---"+personne+" --- ");
				electeur.modifierOpinionParDiscussion(electeurs[electeurChoisi]);
				System.out.print(" ==> Axes de cet electeur apr�s : ");
				System.out.print(electeur.toString());
				System.out.println("");
			}
			
			if(personneChoisie == 1)
			{
				System.out.print("Axes de cet electeur avant : ");
				System.out.print(electeur.toString());
				int candidatChoisi = rand.nextInt(candidats.length);
				String personne = "Le nom du candidat choisi est "+candidats[candidatChoisi].getNomPrenom();
				System.out.print(" ---"+personne+" --- ");
				electeur.modifierOpinionParDiscussion(candidats[candidatChoisi]);
				System.out.print(" ==> Axes de cet electeur apr�s : ");
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
			System.out.print(" ==> Axes de cet electeur apr�s : ");
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
			System.out.print(" ==> Axes de cet electeur apr�s : ");
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
			System.out.print(" ==> Axes de cet electeur apr�s : ");
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
	
	
	

	public abstract String getTypeScrutin();
}	
	
























