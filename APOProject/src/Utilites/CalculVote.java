package Utilites;

import parametres.Candidat;
import parametres.Electeur;
import parametres.Personne;

public class CalculVote {
	
	/**
	 * 
	 * 
	 * @param candidat
	 * @param electeurs
	 * @return
	 */
	public static double[][] CalculDifferenceAxes(Candidat candidat, Electeur[] electeurs)
	{
		double[] valaxes_candidats = candidat.getValAxes();
		
		double[][] valaxes_diff = new double[electeurs.length][valaxes_candidats.length];
		int i=0;
		for(Electeur electeur: electeurs)
		{
			for(int j=0;j<valaxes_candidats.length;j++)
			{
				valaxes_diff[i][j] = Math.abs(valaxes_candidats[j]  - electeur.getValAxes()[j]);
			}
			i++;
		}
		
		return valaxes_diff;
	}
	public static double[] CalculDifferenceAxes(Candidat candidat, Electeur electeur)
	{
		//double[] res = new double[];
		Electeur[] unelecteur = {electeur};
		return CalculDifferenceAxes(candidat, unelecteur)[0];
		//return null;
	}
	
	public static double[][] CalculDiffNormes(Candidat[] candidats, Electeur[] electeurs)
	{
		double[][] diffaxes = new double[electeurs.length][];
		double[][] diffnormes = new double[candidats.length][];//[Candidat][electeur]
		int i=0;
		for(Candidat candidat: candidats)
		{
			//Calcul des différences
			diffaxes = CalculDifferenceAxes(candidat, electeurs);
			//Calcul de la norme
			diffnormes[i] = getNormes(diffaxes);
			i++;
		}
		
		return diffnormes;
	}
	
	
	public static double[] getNormes(double[][] vecteurs)
	{
		double[] normes = new double[vecteurs.length];
		
		int i=0;
		for(double[] vecteur: vecteurs)
		{
			for(int j=0;j<vecteur.length;j++)
			{
				normes[i] += vecteur[j]*vecteur[j];
			}
			normes[i] =  Math.sqrt(normes[i]);
			i++;
		}
		
		return normes;
	}
	public static double getNormes(double[] vecteur)
	{
		double[][] levecteur = {vecteur};
		
		return getNormes(levecteur)[0];
	}

	public static void evoluerParDiscussion()
	{
		
	}
	public static Electeur[] recupElecteurAlea(double pourcentElecteurs, Electeur[] electeurs)
	{
		//Nombre de personnes à qui on fera le Sondage
		int nb_sondage = (int) (pourcentElecteurs*electeurs.length);
		Electeur[] leselecteurs = new Electeur[nb_sondage];
		//Variable pour la sélection aléatoire
		int id_electeur = 0;
		//Boucle pour remplir le tableau electeurs
		int i=0;
		boolean b;
		while(leselecteurs[nb_sondage-1] == null)
		{
			b = true;
			double alea = Math.random();
			if(alea != 1)//Empecher que id_electeur depassa la limite du tableau
			{
				id_electeur = (int) ((electeurs.length)*alea);
			}
			//Empecher les doublons
			for(Electeur electeur: leselecteurs)
			{
				if(electeur != null)
				{
					if(electeur.getIdElecteur() == id_electeur)
					{
						b = false;
					}
				}
			}
			if(b == true)
			{
				leselecteurs[i] = electeurs[id_electeur];
				i++;
			}
		}
		
		return leselecteurs;
	}
	
	
	
	
	
	
	
	
	
	public static double[] getNormes(Personne[] personnes)
	{
		return null;
	}
	
	public static void evoluerParIdee()
	{
		
	}
	
	public static void evoluerParCote()
	{
		
	}
	
	public static void evoluerParMoyenne()
	{
		
	}

}
