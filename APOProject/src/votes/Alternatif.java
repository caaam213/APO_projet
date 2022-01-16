package votes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import Utilites.CalculVote;
import parametres.Candidat;
import parametres.Electeur;

/**
 * Classe pour le scrutin alternatif
 *
 */
public class Alternatif extends Scrutin {

	public Alternatif(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);

	}

	@Override
	public void simulation() {
		resultatScrutin = scrutinAlternatif(candidats, electeurs);
	}
	
	@Override
	public void sondage(double pourcentpop) {
		resultatSondage = scrutinAlternatif(candidats, CalculVote.recupElecteurAlea(pourcentpop, electeurs));

	}
	
	private LinkedHashMap<Candidat, Double> scrutinAlternatif(Candidat[] candsP, Electeur[] elects) {
		LinkedHashMap<Candidat, Double> resultatScrutinP = new LinkedHashMap<Candidat, Double>(); // Contient le candidat et le pourcentage
		HashMap<Candidat, Double> classementCandidatsParNorme;
		LinkedHashMap<Candidat, Double> nbVoteParTours;  
		int total = 0;
		int minValeur;
		int tour = 0;

		
		// Utilisation d'une arrayList pour faciliter la suppression
		ArrayList<Candidat> candidatsAL = new ArrayList<>();
		boolean avoirUnGagnant = false;
		for (int i = 0; i < candsP.length; i++) {
			candidatsAL.add(candsP[i]);
			resultatScrutinP.put(candsP[i], 0.0);
		}
		LinkedHashMap<Electeur, ArrayList<Candidat>> electeurAvecNormeParCandidats = new LinkedHashMap<Electeur, ArrayList<Candidat>>();
		// Calcul des normes avant le while pour éviter de le faire plusieurs fois
		for (Electeur electeur : elects) {
			classementCandidatsParNorme = new LinkedHashMap<Candidat, Double>();
			for (Candidat candidat : candsP) {
				double[] difference = CalculVote.calculDifference(candidat, electeur);
				double norme = CalculVote.getNorme(difference);
				classementCandidatsParNorme.put(candidat, norme);

			}
			ArrayList<Candidat> cands = CalculVote.trierNcandidatsParNorme(candidatsAL.size(),
					classementCandidatsParNorme);
			electeurAvecNormeParCandidats.put(electeur, cands);
		}

		while (!avoirUnGagnant) {
			total += elects.length;
			nbVoteParTours = new LinkedHashMap<Candidat, Double>();
			tour++;
			for (Candidat candidat : candidatsAL)
			{
				nbVoteParTours.put(candidat, 0.0);
			}
			for (Electeur electeur : elects) {
				int i = 0;
				ArrayList<Candidat> cands = electeurAvecNormeParCandidats.get(electeur);
				// On va vérifier si le premier choix n'est pas un candidat eliminé. Si le cas,
				// on prendra le prochain qui n'est pas eliminé
				while (!candidatsAL.contains(cands.get(i))) {
					i++;
				}
				nbVoteParTours.put(cands.get(i), nbVoteParTours.get(cands.get(i)) + 1);
				
			}
			nbVoteParTours = CalculVote.trier_par_votes(candidatsAL.size(), nbVoteParTours, 1);
			List<Candidat> candidats = new ArrayList<>(nbVoteParTours.keySet());
			List<Double> nbPremierChoix = new ArrayList<>(nbVoteParTours.values());
			if (nbPremierChoix.get(0) == nbPremierChoix.get(nbPremierChoix.size() - 1)) {
				avoirUnGagnant = true; // En cas d'égalité. Les égalités vont être gérées ailleurs.
			}
			else
			{
				Candidat candidatElimine = candidats.get(candidatsAL.size() - 1);

				if(tour==total)//On va garder les resultats du dernier tour uniquement
				{
					resultatScrutinP.put(candidatElimine, nbVoteParTours.get(candidatElimine));
				}
				
				candidatsAL.remove(candidatElimine);
				if (candidatsAL.size() == 1) {
					avoirUnGagnant = true;
				}
			}
		
			for(Candidat cand : candidatsAL) //Le ou les gagnants
			{
				resultatScrutinP.put(cand,  (nbVoteParTours.get(cand)/elects.length));
			}
		}
		resultatScrutinP = CalculVote.trier_par_votes(candsP.length, resultatScrutinP, 1);
		return resultatScrutinP;
	}
	


}