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
	
	
}
