package Utilites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import parametres.Candidat;
import parametres.Electeur;
import parametres.Personne;



// TODO: Auto-generated Javadoc
/**
 * Classe qui contient toutes les opérations mathématiques liées à notre application.
 *
 */
public class CalculVote {
	
	/**
	 * Permet de calculer la différence entre chaque axe et retourne un tableau 2D.
	 *
	 * @param candidat the candidat
	 * @param electeurs the electeurs
	 * @return the double[][]
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
	 * Surcharge de la méthode CalculDifferenceAxes en retournant un tableau 1D.
	 *
	 * @param candidat the candidat
	 * @param electeur the electeur
	 * @return the double[]
	 */
	public static double[] CalculDifferenceAxes(Candidat candidat, Electeur electeur)
	{
		//double[] res = new double[];
		Electeur[] unelecteur = {electeur};
		return CalculDifferenceAxes(candidat, unelecteur)[0];
	}
	

	/**
	 * Calculer la difference des axes et la retourne sous la forme d'un tableau 1D 
	 *
	 * @param p
	 * @param electeur
	 * @return double[]
	 */
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
	 * Calculer la difference des axes et la retourne sous la forme d'un tableau 1D. Utile pour des
	 * valeurs qui ne sont pas contenues dans la classe personne
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @return the double[]
	 */
	public static double[] calculDifference(double[] d1, double[] d2)
	{
		double[] diffs = new double[d1.length];
		for(int j=0;j<d1.length;j++)
		{
			diffs[j] = Math.abs(d1[j]  - d2[j]);
		}
		
		return diffs;
	}
	
	/**
	 * Calculer la différence des normes.
	 *
	 * @param candidats the candidats
	 * @param electeurs the electeurs
	 * @return the double[][]
	 */
	public static double[][] CalculDiffNormes(Candidat[] candidats, Electeur[] electeurs)
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

	
	
	/**
	 * Retourne la norme d'un vecteur .
	 *
	 * @param vecteurs the vecteurs
	 * @return the norme
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
	 * Recupérer la norme d'un vecteur sous la forme d'un tableau 2D
	 * Première dimension : Distances
	 * Deuxième personne : Norme.
	 *
	 * @param vecteurs the vecteurs
	 * @return the normes
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
	
	/**
	 * Obtenir la norme.
	 *
	 * @param vecteur
	 * @return normes
	 */
	public static double getNormes(double[] vecteur)
	{
		double[][] levecteur = {vecteur};
		
		return getNormes(levecteur)[0];
	}

	/**
	 * Cette fonction permet de récupérer un pourcentage d'électeurs au hasard.
	 *
	 * @param pourcentElecteurs the pourcent electeurs
	 * @param electeurs the electeurs
	 * @return the electeur[]
	 */
	public static Electeur[] recupElecteurAlea(double pourcentElecteurs, Electeur[] electeurs)
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
	
	
	
	/**
	 * Trier ncandidats.
	 *
	 * @param n the n
	 * @param sondage the sondage
	 * @return Cette méthode permet de trier les candidats du plus favorable au moins faborable au sondage
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
	 * Trier ncandidats par norme.
	 *
	 * @param n the n
	 * @param votes the votes
	 * @return Cette méthode permet de trier les candidats par normes et de retourner une liste de candidats triées
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
	 * Trier par votes.
	 *
	 * @param n the n
	 * @param votes the votes
	 * @param nbTotalVoix the nb total voix
	 * @return Retourne un LinkedHashMap trié selon le nombre de votes
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
	
	/**
	 * Chercher noabstentionniste.
	 *
	 * @param electeurs the electeurs
	 * @param candidats the candidats
	 * @param norme_ecart the norme ecart
	 * @return the electeur[]
	 */
	public static Electeur[] chercher_noabstentionniste(Electeur[] electeurs, Candidat[] candidats, double norme_ecart)
	{
		Electeur[] electeurs_ret = new Electeur[electeurs.length];
		double[] diffaxes;
		double norme;
		int count = 0;//nb d'électeur qui participeront
		
		boolean b;
		for(Electeur electeur: electeurs)
		{
			b = false;
			//On regarde si le candidat va voter
			for(Candidat candidat: candidats)
			{
				diffaxes = CalculDifferenceAxes(candidat, electeur);
				norme = getNorme(diffaxes);
				
				if( norme < norme_ecart)
				{
					b = true;
				}
			}
			
			//Ajout du candidat sur la liste des participants
			if(b)
			{
				electeurs_ret[count] = electeur;
				count++;
			}
		}
		
		//pour que le tableau qu'on retourne fasse la bonne taille
		Electeur[] electeurs_return = new Electeur[count];
		for(int i=0;i<count;i++)
		{
			electeurs_return[i] = electeurs_ret[i];
		}
				
				
		return electeurs_return; 
	}

}









