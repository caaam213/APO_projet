package votes;

import parametres.*;

public abstract class Scrutin {
	
	Candidat[] candidats;
	Electeur[] electeurs;

	public Scrutin(Candidat[] candidats, Electeur[] electeurs) {
		this.candidats = candidats;
		this.electeurs = electeurs;
	}

	public abstract void simulation();
	
	public abstract void sondage( float pourcentpop );
	
	/*---------Méthodes ajoutés pour le bon fonctionnement du programme------------*/
	public float[][] getDifferencefloat(Candidat candidat, Electeur[] electeurs)
	{
		float[] valaxes_candidats = candidat.getValAxes();
		
		float[][] valaxes_diff = new float[electeurs.length][valaxes_candidats.length];
		int i=0;
		for(Electeur electeur: electeurs)
		{
			for(int j=0;j<valaxes_candidats.length;j++)
			{
				valaxes_diff[i][j] = Math.abs(valaxes_candidats[j]  - electeur.getValAxes()[j]);
			}
		}
		
		return valaxes_diff;
	}
	
	public float[] getNormes(float[][] vecteurs)
	{
		float[] normes = new float[vecteurs.length];
		
		int i=0;
		for(float[] vecteur: vecteurs)
		{
			for(int j=0;j<vecteur.length;j++)
			{
				normes[i] += vecteur[j]*vecteur[j];
			}
			normes[i] = (float) Math.sqrt(normes[i]);
			i++;
		}
		
		return normes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public float[] getNormes(Personne[] personnes)
	{
		float[] valAxes;
		float[] normeAxes = new float[personnes.length];
		float normeAxe=0;
		
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


























