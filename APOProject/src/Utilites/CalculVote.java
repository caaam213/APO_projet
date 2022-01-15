package Utilites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import parametres.Candidat;
import parametres.Electeur;
import parametres.Personne;



/**
 * Classe qui contient toutes les op�rations math�matiques li�es � notre application.
 *
 */
public class CalculVote {
	
	/**
	 * Permet de calculer la diff�rence entre chaque axe et retourne un tableau 2D
	 * 
	 * @param candidat
	 * @param electeurs
	 * @return
	 */
	public static double[][] CalculDifferenceAxes(Candidat candidat, Electeur[] electeurs)
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
	/**
	 * Surcharge de la m�thode CalculDifferenceAxes en retournant un tableau 1D
	 * @param candidat
	 * @param electeur
	 * @return
	 */
	public static double[] CalculDifferenceAxes(Candidat candidat, Electeur electeur)
	{
		//double[] res = new double[];
		Electeur[] unelecteur = {electeur};
		return CalculDifferenceAxes(candidat, unelecteur)[0];
	}
	

	public static double[] calculDifference(Personne p, Electeur electeur)
	{
		double[] valaxes_candidats = p.getValAxes();
		
		double[] valaxes_diff = new double[valaxes_candidats.length];

		for(int j=0;j<valaxes_candidats.length;j++)
		{
			valaxes_diff[j] = Math.abs(valaxes_candidats[j]  - electeur.getValAxes()[j]);
		}
		
		return valaxes_diff;
	}

	
	/**
	 * Calculer la diff�rence des normes
	 * @param candidats
	 * @param electeurs
	 * @return
	 */
	public static double[][] CalculDiffNormes(Candidat[] candidats, Electeur[] electeurs)
	{
		double[][] diffaxes = new double[electeurs.length][];
		double[][] diffnormes = new double[candidats.length][];//[Candidat][electeur]
		int i=0;
		for(Candidat candidat: candidats)
		{
			//Calcul des diff�rences
			diffaxes = CalculDifferenceAxes(candidat, electeurs);
			//Calcul de la norme
			diffnormes[i] = getNormes(diffaxes);
			i++;
		}
		
		return diffnormes;
	}

	
	
	/**
	 * Retourne la norme d'un vecteur 
	 * @param vecteurs
	 * @return
	 */
	public static double getNorme(double[] vecteurs)
	{
		double norme = 0;
		
		for(int i=0;i<vecteurs.length;i++)
		{
			norme += vecteurs[i]*vecteurs[i];
			
			
		}
		norme =  Math.sqrt(norme);
		return norme;
	}
	
	/**
	 * Recup�rer la norme d'un vecteur sous la forme d'un tableau 2D
	 * Premi�re dimension : Distances
	 * Deuxi�me personne : Norme
	 * @param vecteurs
	 * @return
	 */
	public static double[] getNormes(double[][] vecteurs)
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
	public static double getNormes(double[] vecteur)
	{
		double[][] levecteur = {vecteur};
		
		return getNormes(levecteur)[0];
	}

	/**
	 * Cette fonction permet de r�cup�rer un pourcentage d'�lecteurs au hasard
	 * @param pourcentElecteurs
	 * @param electeurs
	 * @return
	 */
	public static Electeur[] recupElecteurAlea(double pourcentElecteurs, Electeur[] electeurs)
	{
		//Nombre de personnes � qui on fera le Sondage
		int nb_sondage = (int) (pourcentElecteurs*electeurs.length);
		Electeur[] leselecteurs = new Electeur[nb_sondage];
		//Variable pour la s�lection al�atoire
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
	
	
	
	/**
	 * @param n
	 * @param sondage
	 * @return
	 * 
	 * Cette m�thode permet de trier les candidats du plus favorable au moins faborable au sondage
	 */
	public static ArrayList<Integer> trierNcandidats( int n , HashMap<Candidat,Double> sondage )
    {
        double pourcent_vote;
        double pourcent_vote_max = 0;
        int id_candidat_max = 0;

        ArrayList<Integer> nCandidats = new ArrayList<Integer>();
        List<Candidat> candidats = new ArrayList<>(sondage.keySet()); 
        for(int k=0;k<n;k++)
        {
            for(int i=0;i<candidats.size();i++)
            {
                pourcent_vote = sondage.get(candidats.get(i));

                if( (pourcent_vote >= pourcent_vote_max) && !nCandidats.contains(i))
                {
                    pourcent_vote_max = pourcent_vote;
                    id_candidat_max = i;
                }
            }
            if(!nCandidats.contains(id_candidat_max))
            {
            	nCandidats.add(id_candidat_max);
                pourcent_vote_max = 0;
            }
        }

        return nCandidats;
    }
	
	/**
	 * @param n
	 * @param votes
	 * @return
	 * 
	 * Cette m�thode permet de trier les candidats par normes et de retourner une liste de candidats tri�es
	 */
	public static ArrayList<Candidat> trierNcandidatsParNorme( int n , HashMap<Candidat,Double> votes )
    {
        double norme_vote;
        double norme_vote_min = 99999;
        Candidat candidatMin = null;

        ArrayList<Candidat> nCandidats = new ArrayList<Candidat>();
        List<Candidat> candidats = new ArrayList<>(votes.keySet()); 
        for(int k=0;k<n;k++)
        {
        	for(Candidat candidat : candidats)
            {
            	norme_vote = votes.get(candidat);

                if( (norme_vote <= norme_vote_min) && !nCandidats.contains(candidat))
                {
                	norme_vote_min = norme_vote;
                	candidatMin = candidat;
                }
            }
            if(!nCandidats.contains(candidatMin))
            {
            	nCandidats.add(candidatMin);
            	norme_vote_min = 999999;
            }

        }
            

        return nCandidats;
    }
	
	/**
	 * @param n
	 * @param votes
	 * @param nbTotalVoix
	 * @return
	 * 
	 * Retourne un LinkedHashMap tri� selon le nombre de votes 
	 */
	public static LinkedHashMap<Candidat, Double> trier_par_votes( int n , LinkedHashMap<Candidat, Double> votes, int nbTotalVoix)
	{
		double pourcent_vote;
		double pourcent_vote_max = 0;
		Candidat candidatMax = null;
		//int[] id_sectour = new int[nb_sectour];
		LinkedHashMap voteTrie = new LinkedHashMap<Candidat, Double>();
		List<Candidat> candidats = new ArrayList<>(votes.keySet()); 
		for(int k=0;k<n;k++)
		{
			for(Entry<Candidat, Double> vote : votes.entrySet()) {
				Candidat candidat = vote.getKey();
			    pourcent_vote = vote.getValue()/nbTotalVoix;
				
				if( (pourcent_vote >= pourcent_vote_max) && !voteTrie.containsKey(candidat))
				{
					pourcent_vote_max = pourcent_vote;
					candidatMax = candidat;
				}
			}
			if(!voteTrie.containsKey(candidatMax))
			{
				voteTrie.put(candidatMax,pourcent_vote_max);
				pourcent_vote_max = 0;
			}
		}
		
		return voteTrie;
	}

}
