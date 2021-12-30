package votes;

import parametres.*;

public abstract class Scrutin {
	
	protected Candidat[] candidats;
	protected Electeur[] electeurs;

	public Scrutin(Candidat[] candidats, Electeur[] electeurs) {
		this.candidats = candidats;
		this.electeurs = electeurs;
	}

	public abstract void simulation();
	
	public abstract void sondage( double pourcentpop );
	/*-----------------------------------------------------------------------------*/
	/*---------Méthodes ajoutés pour le bon fonctionnement du programme------------*/
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
			//Calcul des différences
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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public double[] getNormes(Personne[] personnes)
	{
		double[] valAxes;
		double[] normeAxes = new double[personnes.length];
		double normeAxe=0;
		
		int k=0;
		for(Personne personne: personnes)
		{
			valAxes = personne.getValAxes();
			for(int i=0;i<personne.getValAxes().length;i++)
			{
				normeAxe += valAxes[i]*valAxes[i];
			}
			normeAxes[k] = (float) Math.sqrt(normeAxe);
			normeAxe=0;
			k++;
		}
		
		return normeAxes;
	}
	
	public float[] getDifferenceNorme(float[] normes1, float normes2)
	{
		float[] normesdiff = new float[normes1.length];
		
		for(int i=0;i<normes1.length;i++)
		{
			normesdiff[i] = Math.abs(normes1[i] - normes2);
		}
		
		return normesdiff;
	}
	
	
	
	
}


























