package votes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import Utilites.CalculVote;
import parametres.*;

// TODO: Auto-generated Javadoc
/**
 * Classe Scrutin.
 */
public abstract class Scrutin {

	
	protected Candidat[] candidats;
	
	
	protected Electeur[] electeurs;
	
	
	protected LinkedHashMap<Candidat,Double> resultatScrutin;
	
	
	protected LinkedHashMap<Candidat,Double> resultatSondage;
	
	
	protected Candidat abstention;
	
	/**
	 * Constructeur.
	 *
	 * @param candidats Tableau de tous les candidats
	 * @param electeurs Tableau de tout les electeurs
	 */
	public Scrutin(Candidat[] candidats, Electeur[] electeurs) {
		this.candidats = candidats;
		this.electeurs = electeurs;
		resultatScrutin= new LinkedHashMap<Candidat, Double>();
		resultatSondage = new LinkedHashMap<Candidat, Double>();
		int tailleValAxes = candidats[0].getValAxes().length;
		double[] valAxesPourAbstention = new double[tailleValAxes];
		abstention = new Candidat(candidats[0].getAxes(),valAxesPourAbstention,"");
	}
	
	/**
	 * Constructeur.
	 *
	 * @param candidats Tableau de tout les electeurs
	 */
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
	
	
	/**
	 * Permet de lancer une simulation.
	 *
	 * @param cands Tableau de tout les candidats
	 */
	public abstract void simulation(Candidat[] cands);
	
	/**
	 * Permet de lancer un sondage.
	 *
	 * @param pourcentElecteurs pourcentage d'electeurs qui participeront au sondage
	 */
	public abstract void sondage( double pourcentElecteurs );
	
	
	/**
	 * Permet d'afficher le gagnant et traitement de l'égalité
	 */
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
				System.out.println("Cas d'égalité, nouvelles elections !");
				if(resultatSondage.size()==0)
				{
					sondage(50); //Si un sondage n'a pas été réalisé, un sondage va être fait avec 50% de la population
				}
				this.evoluerToutesLesOpinionsParDiscussion(false);
				Candidat[] cRestants = (Candidat[]) candidatsRestants.toArray();
				this.simulation(cRestants);
				candidats = new ArrayList<>(resultatScrutin.keySet());
				pourcentages = new ArrayList<>(resultatScrutin.values());
			}
			//System.out.println("Le gagnant est "+candidats.get(0).getNomPrenom()+" avec "+pourcentages.get(0)*100+" %");
			
			double search_max = 0;
			for(Candidat cand: candidats)
			{
				if(resultatScrutin.get(cand) != null)
				{
					if(resultatScrutin.get(cand) > search_max)
					{
						search_max = resultatScrutin.get(cand);
					}
				}
			}
			DecimalFormat df = new DecimalFormat("0.00");
			for(Candidat cand: candidats)
			{
				if(resultatScrutin.get(cand) != null)
				{
					if( resultatScrutin.get(cand) == search_max )
					{
						System.out.println("Le gagnant est "+cand.getNomPrenom()+" avec " +df.format(search_max*100)  + " %");
					}
				}
			}
		}
		
	}
	
	/**
	 * Permet de faire évoluer les opinions par discussion et restreindre en cas de spacialisation
	 *
	 * @param spacialisation Permet de décider si la spacialisation sera utilisé
	 */
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
				System.out.print(" ==> Axes de cet electeur après : ");
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
				System.out.print(" ==> Axes de cet electeur après : ");
				System.out.print(electeur.toString());
				System.out.println("");
			}
			
			
			nbElecteur++;
		}
	}
	
	/**
	 * Evoluer toutes les opinions par idees.
	 *
	 * @param N the n
	 */
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
	
	/**
	 * Evoluer toutes les opinions par cote.
	 */
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
	
	/**
	 * Permet de faire évoluer toutes les opinions par la moyenne
	 */
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
	
	/**
	 * Permet d'obtenir les résultats d'un scrutin.
	 *
	 * @return the resultat scrutin
	 */
	public LinkedHashMap<Candidat, Double> getResultatScrutin() {
		return resultatScrutin;
	}

	/**
	 * Permet d'obtenir les résultats d'un sondage.
	 *
	 * @return the resultat sondage
	 */
	public LinkedHashMap<Candidat, Double> getResultatSondage() {
		return resultatSondage;
	}

	/**
	 * Permet d'obtenir les candidats d'un scrutin.
	 *
	 * @return the candidats
	 */
	public Candidat[] getCandidats() {
		return candidats;
	}
	
	/**
	 * Permet d'obtenir les electeurs d'un scrutin.
	 *
	 * @return the electeurs
	 */
	public Electeur[] getElecteurs() {
		return electeurs;
	}
	
	
	
	/**
	 * Permet de connaitre quelle sera le type de scrutin avec une chaine de caractère.
	 *
	 * @return the type scrutin
	 */
	public abstract String getTypeScrutin();
}	
	
























