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

	public abstract void simulation();
	
	public abstract void sondage( double pourcentElecteurs );

	public HashMap<Candidat, Double> getResultatScrutin() {
		return resultatScrutin;
	}

	public HashMap<Candidat, Double> getResultatSondage() {
		return resultatSondage;
	}

}	
	
























