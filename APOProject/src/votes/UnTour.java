package votes;

import Utilites.*;

import java.text.DecimalFormat;
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
	 * 
	 * @param candidats tableau de tout les candidats
	 */
	@Override
	public void simulation(Candidat[] candidats)
	{
		resultatScrutin = scrutinUnTour(candidats,CalculVote.chercher_noabstentionniste(electeurs,candidats, (double)Math.sqrt((double)electeurs[0].getAxes().length)/electeurs[0].getAxes().length));
		System.out.println("ICI:" + electeurs[0].getAxes().length);
	}

	/**
	 * Permet de lancer un sondage
	 * 
	 * @param pourcentElecteurs pourcentage d'électeurs testé dans la population
	 */
	@Override
	public void sondage(double pourcentElecteurs) 
	{
		resultatSondage = scrutinUnTour(candidats, CalculVote.chercher_noabstentionniste(CalculVote.recupElecteurAlea(pourcentElecteurs, electeurs),candidats, (double)Math.sqrt((double)electeurs[0].getAxes().length)/electeurs[0].getAxes().length));
	}
	
	/** 
	 * Fonction de traitement du Scrutin
	 * 
	 * @param candidats tableau des candidats dans le scrutin
	 * @param electeurs tableau des electeurs dans le scrutin
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
		
	
		DecimalFormat df = new DecimalFormat("0.00");
		for(Candidat candidat: candidats)
		{
			System.out.println( candidat.getNomPrenom() + " :"+ df.format(resultat.get(candidat)*100) +"%");
		}
		
		return resultat;
		
	}
	/** 
	 * Fonction de traitement du Scrutin
	 * 
	 * @param electeurs tableau des electeurs dans le scrutin
	 * @param diffnormes 
	 * @return
	 */
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
	
<<<<<<< HEAD
	/** 
	 * Fonction permettant de reconnaitre quelle est le scrutin avec un chaine de caractère
	 * 
	 * @return
	 */
=======

>>>>>>> fa04a3c90bf438b9be5095d2b311c3b410f73aec
	public String getTypeScrutin()
	{
		return "UnTour";
	}
<<<<<<< HEAD

=======
>>>>>>> fa04a3c90bf438b9be5095d2b311c3b410f73aec

}




















