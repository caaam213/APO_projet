package Utilites;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import parametres.Axe;
import parametres.Candidat;
import parametres.Electeur;
import votes.Alternatif;
import votes.Approbation;
import votes.Borda;
import votes.DeuxTours;
import votes.Scrutin;
import votes.UnTour;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalyseScrutin.
 */
public class AnalyseScrutin {

	/**
	 * Permet de faire une analyse des cinq modes de scrutins
	 *
	 * @param candidats 
	 * @param axes 
	 * @param valAxesCentre 
	 * @return the linked hash map
	 */
	public LinkedHashMap<Candidat, Double> AnalyseModeScrutin(Candidat[] candidats,Axe[] axes, double[] valAxesCentre)
	{
		Electeur[] electeurs;
		LinkedHashMap<Candidat, Double> res_moyenne = new LinkedHashMap<Candidat, Double>();
		
		//-----------Génération des électeurs en fct du centre de gravité valAxesCentre-----------
		electeurs = generationElecteurs(axes, valAxesCentre);
		
		//-----------Calcul de toute les elections-----------
		UnTour untour = new UnTour(candidats, electeurs);
		DeuxTours deuxtours = new DeuxTours(candidats, electeurs);
		Alternatif alternatif = new Alternatif(candidats, electeurs);
		Approbation approbation = new Approbation(candidats, electeursNchoisisPourApprobation(candidats,electeurs));
		Borda borda = new Borda(candidats, electeurs);
		
		//-----------On fait les simulations-----------
		untour.simulation(candidats);
		deuxtours.simulation(candidats);
		alternatif.simulation(candidats);
		approbation.simulation(candidats);
		borda.simulation(candidats);
		
		//-----------
		//untour.getResultatScrutin()
		double res_untour,res_deuxtours,res_alt,res_appro,res_borda;
		int count;
		DecimalFormat df = new DecimalFormat("0.00"); // import java.text.DecimalFormat;
		System.out.println("---Résultat moyen:---");
		for(Candidat candidat: candidats)
		{
			count = 0;
			res_untour = 0;
			res_deuxtours = 0;
			res_alt = 0;
			res_appro = 0;
			res_borda = 0;
			
			if( untour.getResultatScrutin().get(candidat) != null )
			{
				res_untour = untour.getResultatScrutin().get(candidat);
				count++;
			}
			if( deuxtours.getResultatScrutin().get(candidat) != null )
			{
				res_deuxtours = untour.getResultatScrutin().get(candidat);
				count++;
			}
			
			if( alternatif.getResultatScrutin().get(candidat) != null )
			{
				res_alt = alternatif.getResultatScrutin().get(candidat);
				count++;
			}
			
			if( approbation.getResultatScrutin().get(candidat) != null )
			{
				res_appro = approbation.getResultatScrutin().get(candidat);
				count++;
			}
			
			if( borda.getResultatScrutin().get(candidat) != null )
			{
				res_borda = borda.getResultatScrutin().get(candidat);
				count++;
			}
			
			if(count != 0) 
			{
				res_moyenne.put(candidat, (res_untour + res_deuxtours + res_alt + res_appro + res_borda)/count);
				System.out.println(candidat.getNomPrenom() + " : " + df.format(res_moyenne.get(candidat)*100) + "%");
			}
			
		}
		
		return res_moyenne;
	}
	
	/**
	 * Générer des électeurs de façon aléatoire
	 *
	 * @param axes
	 * @param valAxesCentre
	 * @return electeur[]
	 */
	private Electeur[] generationElecteurs(Axe[] axes, double[] valAxesCentre)
	{
		Electeur[] electeurs = new Electeur[1000];
		double[] valAxesCentreAlea = null;
		double amplitude = 0.15;//amplitude de base choisi arbitrairement
		
		for(int i=0;i<valAxesCentre.length;i++)
		{
			if(1 - valAxesCentre[i] < amplitude)//si c'est trop proche de 1 
			{
				amplitude = 1 - valAxesCentre[i]; 
			}
			if( valAxesCentre[i] < amplitude)// si c'est trop proche de 0
			{
				amplitude = valAxesCentre[i];
			}
		}
		
		//génération des electeurs aléatoire avec une amplitude donné et le centre donné
		Random random1;
		Random random2;
		for(int i=0;i<electeurs.length;i++)
		{
			valAxesCentreAlea = new double[valAxesCentre.length];
			for(int j=0;j<valAxesCentre.length;j++)
			{
				//random1 = new Random();
				//random2 = new Random();
				//double d = random.nextDouble();
				valAxesCentreAlea[j] = valAxesCentre[j] - amplitude*(new Random()).nextDouble() + amplitude*(new Random()).nextDouble();
			}
			electeurs[i] = new Electeur(axes, valAxesCentreAlea);
		}
		
		
		return electeurs;
	}
	
	/**
	 * Electeurs nchoisis pour approbation.
	 *
	 * @param candidats the candidats
	 * @param electeurs the electeurs
	 * @return the hash map
	 */
	private HashMap<Electeur, Integer> electeursNchoisisPourApprobation(Candidat[] candidats, Electeur[] electeurs) {
		HashMap<Electeur, Integer> nChoisis = new HashMap<Electeur, Integer>();
		int N = candidats.length;
		int n = 0;
		Random rand = new Random();
		for (Electeur electeur : electeurs) {
			n = rand.nextInt(N) + 1;
			nChoisis.put(electeur, n);
		}
		return nChoisis;
	}
	
}
