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
	
	public abstract void sondage( double pourcentElecteurs );
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
	
<<<<<<< HEAD
	public void evoluerParDiscussion()
=======
	public Electeur[] recupElecteurAlea(double pourcentElecteurs)
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
	
	
	
	
	
	
	
	
	
	
	
	
	public double[] getNormes(Personne[] personnes)
>>>>>>> 6fcbe0c8a7b6473ddb70108886348824e80c3e84
	{
		
	}
	
	public void evoluerParIdee()
	{
		
	}
	
	public void evoluerParCote()
	{
		
	}
	
	public void evoluerParMoyenne()
	{
		
	}

	
	
	
	
}


























