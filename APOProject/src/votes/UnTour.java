package votes;

import Utilites.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

import parametres.Candidat;
import parametres.Electeur;

/**
 * @author nabil
 *
 */
public class UnTour extends Scrutin{

	/**
	 * Constructeur
	 * 
	 * @param candidats
	 * @param electeurs
	 */
	public UnTour(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
	}

	/**
	 * Permet de lancer la simulation
	 */
	@Override
	public void simulation()
	{
		resultatScrutin = scrutinUnTour(candidats,electeurs);
	}

	/**
	 * Permet de lancer la sondage
	 * 
	 * @param pourcentElecteurs pourcentage d'électeurs testé dans la population
	 */
	@Override
	public void sondage(double pourcentElecteurs) 
	{
		resultatSondage = scrutinUnTour(candidats, CalculVote.recupElecteurAlea(pourcentElecteurs, electeurs));
	}
	
	/**
	 * Fonction de traitement
	 * 
	 * @param candidats
	 * @param electeurs
	 * @return
	 */
	public LinkedHashMap<Candidat,Double> scrutinUnTour(Candidat[] candidats, Electeur[] electeurs) {
		//Récupération des normes représentant le rapprochement avec un candidat
		double[][] diffnormes = CalculVote.CalculDiffNormes(candidats,electeurs);
		//Choix des électeurs
		int[] choix_electeurs = choixElecteurs(electeurs, diffnormes);
		//Résultat par candidat
		LinkedHashMap<Candidat,Double> resultat = new LinkedHashMap<Candidat,Double>();
		
		int nombre_votecandidat;
		for(int i=0;i<candidats.length;i++)
		{
			nombre_votecandidat = 0;
			for(int choix:choix_electeurs)
			{
				if(choix == i)
				{
					nombre_votecandidat++;
				}
			}
			resultat.put(candidats[i],  ((double)nombre_votecandidat/electeurs.length));
		}
		
	
		//Test
		for(Candidat candidat: candidats)
		{
			System.out.println( candidat.getNomPrenom() + " :"+ resultat.get(candidat)*100+"%");
		}
		
		return resultat;
		
	}
	
	public int[] choixElecteurs( Electeur[] electeurs, double[][] diffnormes )
	{
		int[] choix_electeurs = new int[electeurs.length];
		double norme_electeur_min = 0;
		for( int i1=0;i1<electeurs.length;i1++ )
		{
			//Choix du meilleur candidat
			norme_electeur_min = diffnormes[0][i1];
			choix_electeurs[i1] = 0;// 0 par défaut
			for( int j=0;j<candidats.length;j++ )
			{
				if(diffnormes[j][i1] < norme_electeur_min)
				{
					norme_electeur_min = diffnormes[j][i1];
					choix_electeurs[i1] = j;//Mise à jour du choix de l'électeur
				}
			}
		}
		
		return choix_electeurs;
	}

}

//-----ARCHIVES CODE

//-------------Récupération des normes représentant le rapprochement avec un candidat----------
/*double[][] diffaxes = new double[electeurs.length][];
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

int[] choix_electeurs = new int[electeurs.length];
double norme_electeur_min = 0;
for( int i1=0;i1<electeurs.length;i1++ )
{
	//Choix du meilleur candidat
	norme_electeur_min = diffnormes[0][i1];
	choix_electeurs[i1] = 0;// 0 par défaut
	for( int j=0;j<candidats.length;j++ )
	{
		if(diffnormes[j][i1] < norme_electeur_min)
		{
			norme_electeur_min = diffnormes[j][i1];
			choix_electeurs[i1] = j;//Mise à jour du choix de l'électeur
		}
	}
}
*/





















