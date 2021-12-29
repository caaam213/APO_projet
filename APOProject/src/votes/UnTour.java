package votes;

import java.lang.Math;

import parametres.Candidat;
import parametres.Electeur;

public class UnTour extends Scrutin{

	public UnTour(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
	}

	@Override
	public void simulation() {
		//for electeur pour un candidat
		float[][] diffaxes;
		float[][] diffnormes = new float[candidats.length][];
		int i=0;
		for(Candidat candidat: candidats)
		{
			//Calcul des différences
			diffaxes = getDifferencefloat(candidat, electeurs);
			//Calcul de la norme
			diffnormes[i] = getNormes(diffaxes);
			i++;
		}
		
		
		//Calcul de la normes des candidats
		//float[] normescandidats = getNormes(candidats);
		//Calcul de la normes des électeurs
		//float[] normeselecteurs = getNormes(electeurs);
		//Calcul de la différence
		//float[] normesdifference = getDifferenceNorme(normeselecteurs, normescandidats);
		
		
	}

	@Override
	public void sondage(float pourcentpop) {
		// TODO Auto-generated method stub
		
	}

	
}
