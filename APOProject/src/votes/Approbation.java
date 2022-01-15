package votes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Utilites.CalculVote;
import parametres.Candidat;
import parametres.Electeur;

public class Approbation extends Scrutin {

	HashMap<Electeur, Integer> electeursAvecNChoisi; // Un electeur peut voter pour N candidats

	public Approbation(Candidat[] candidats, HashMap<Electeur, Integer> electeursAvecNChoisi) {
		super(candidats);
		this.electeursAvecNChoisi = electeursAvecNChoisi;
		List<Electeur> electeursList = new ArrayList(electeursAvecNChoisi.keySet());
		electeurs = new Electeur[electeursList.size()];
		for (int i = 0; i < electeurs.length; i++) {
			electeurs[i] = electeursList.get(i);
		}
	}

	@Override
	public void simulation() {
		resultatScrutin = scrutinApprobation(candidats, electeurs);
	}
	
	@Override
	public void sondage(double pourcentpop) {
		resultatSondage = scrutinApprobation(candidats, CalculVote.recupElecteurAlea(pourcentpop, electeurs));

	}

	private LinkedHashMap<Candidat, Double> scrutinApprobation(Candidat[] candsP, Electeur[] elecs) {
		// TODO Auto-generated method stub
		LinkedHashMap<Candidat, Double> resultats = new LinkedHashMap<Candidat, Double>(); // Contient le candidat et le pourcentage

		for (Candidat candidat : candsP) {
			resultats.put(candidat, 0.0);
		}

		HashMap<Candidat, Double> classementCandidatsParNorme;
		int total = 0;
		for (Electeur electeur : elecs) {
			Integer n = this.electeursAvecNChoisi.get(electeur);

			classementCandidatsParNorme = new HashMap<Candidat, Double>();
			total += n; // On va ajouter le nombre de candidats choisi pour chaque electeur
			for (Candidat candidat : candsP) {
				double[] difference = CalculVote.calculDifference(candidat, electeur);
				double norme = CalculVote.getNorme(difference);
				classementCandidatsParNorme.put(candidat, norme);
			}

			// Tri des candidats
			ArrayList<Candidat> cands = CalculVote.trierNcandidatsParNorme(n, classementCandidatsParNorme);

			// Comptabilisation des votes
			for (Candidat candidat : cands) {
				resultats.put(candidat, resultats.get(candidat) + 1.0);
			}
		}

		// Trier les votes en commencant par le gagnant
		resultats = CalculVote.trier_par_votes(candsP.length, resultats, total);
		for (Entry<Candidat, Double> vote : resultats.entrySet()) {
			Candidat candidat = vote.getKey();
			Double pourcent_vote = vote.getValue();

			System.out.println(candidat.toString() + "Pourcentage : " + pourcent_vote);
		}
		return resultats;
		// --- Cas d'égalité : Evoluer les opinions puis refaire une simulation
	}

	

}
