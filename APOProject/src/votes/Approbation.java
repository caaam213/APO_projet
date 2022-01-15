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

public class Approbation extends Scrutin{

	HashMap<Electeur, Integer> electeursAvecNChoisi; //Un electeur peut voter pour N candidats 
	LinkedHashMap<Candidat, Double> votes;
	public Approbation(Candidat[] candidats,HashMap<Electeur, Integer> electeursAvecNChoisi) {
		super(candidats);
		this.electeursAvecNChoisi = electeursAvecNChoisi;
	}

	@Override
	public void simulation() {
		// TODO Auto-generated method stub
		votes = new LinkedHashMap<Candidat, Double>(); //Contient le candidat et le pourcentage
		
		for(Candidat candidat : candidats)
	    {
	    	votes.put(candidat, 0.0);
	    }
		
		HashMap<Candidat, Double> classementCandidatsParNorme;
		int total = 0;
		for(Entry<Electeur, Integer> electeurEtN : electeursAvecNChoisi.entrySet()) {
			Electeur electeur = electeurEtN.getKey();
		    Integer n = electeurEtN.getValue();
		    
		    classementCandidatsParNorme = new HashMap<Candidat, Double>(); 
		    total += n; //On va ajouter le nombre de candidats choisi pour chaque electeur
		    for(Candidat candidat : candidats)
		    {
		    	double[] difference = CalculVote.calculDifference(candidat,electeur);
				double norme = CalculVote.getNorme(difference);
				classementCandidatsParNorme.put(candidat,norme);
		    }
		    
		    //Tri des candidats
		    ArrayList<Candidat> cands =  CalculVote.trierNcandidatsParNorme(n, classementCandidatsParNorme);
		    
		    //Comptabilisation des votes
		    for(Candidat candidat : cands)
		    {
		    	votes.put(candidat, votes.get(candidat) + 1.0);
		    }
		}
		
		
		//Trier les votes en commencant par le gagnant
		votes = CalculVote.trier_par_votes(candidats.length,votes,total);
		for(Entry<Candidat, Double> vote : votes.entrySet()) {
			Candidat candidat = vote.getKey();
		    Double pourcent_vote = vote.getValue();
		    
		    System.out.println(candidat.toString() + "Pourcentage : "+pourcent_vote);
		} 
		// --- Cas d'égalité : Evoluer les opinions puis refaire une simulation
	}
	
	

	public LinkedHashMap<Candidat, Double> getVotes() {
		return votes;
	}

	public HashMap<Electeur, Integer> getElecteursAvecNChoisi() {
		return electeursAvecNChoisi;
	}

	@Override
	public void sondage(double pourcentpop) {
		// TODO Auto-generated method stub
		
	}


}
