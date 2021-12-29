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
}
