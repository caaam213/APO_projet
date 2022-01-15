package votes;

import java.util.HashMap;

import parametres.*;

public abstract class Scrutin {
	
	protected Candidat[] candidats;
	protected Electeur[] electeurs;
	protected HashMap<Candidat,Double> resultatScrutin;
	protected HashMap<Candidat,Double> resultatSondage;
	
	public Scrutin(Candidat[] candidats, Electeur[] electeurs) {
		this.candidats = candidats;
		this.electeurs = electeurs;
	}

	public Scrutin(Candidat[] candidats)
	{
		this.candidats = candidats;
		this.electeurs = electeurs; //Pour les votes alternatifs, on ne va pas utiliser ce tableau
	}
	public abstract void simulation();
	
	public abstract void sondage( double pourcentElecteurs );

<<<<<<< HEAD
	/*-----------------------------------------------------------------------------*/
	/*---------M�thodes ajout�s pour le bon fonctionnement du programme------------*/
	/*-----------------------------------------------------------------------------*/
	
	public double[][] CalculDifferenceAxes(Candidat candidat, Electeur[] electeurs)
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
	
	public double[][] CalculDiffNormes(Candidat[] candidats, Electeur[] electeurs)
	{
		double[][] diffaxes = new double[electeurs.length][];
		double[][] diffnormes = new double[candidats.length][];//[Candidat][electeur]
		int i=0;
		for(Candidat candidat: candidats)
		{
			//Calcul des diff�rences
			diffaxes = CalculDifferenceAxes(candidat, electeurs);
			//Calcul de la norme
			diffnormes[i] = getNormes(diffaxes);
			i++;
		}
		
		return diffnormes;
	}
	

	public double[] getNormes(double[][] vecteurs)
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
=======
	public HashMap<Candidat, Double> getResultatScrutin() {
		return resultatScrutin;
>>>>>>> fbe9ad4e7928b13a5e807d2fe6efc40eb8922b0f
	}

	public HashMap<Candidat, Double> getResultatSondage() {
		return resultatSondage;
	}

}	
	
























