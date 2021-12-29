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
		float[] valAxes;
		float[] normeAxes = new float[electeurs.length];
		float normeAxe=0;
		
		int k=0;
		for(Electeur electeur: electeurs)
		{
			valAxes = electeur.getValAxes();
			for(int i=0;i<electeur.getValAxes().length;i++)
			{
				normeAxe += valAxes[i]*valAxes[i];
			}
			normeAxes[k] = (float) Math.sqrt(normeAxe);
			k++;
		}
	}

	@Override
	public void sondage(float pourcentpop) {
		// TODO Auto-generated method stub
		
	}

	
}
