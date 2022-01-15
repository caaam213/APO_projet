/*
 * 
 */
package parametres;
import Utilites.*;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.lang.*;
// TODO: Auto-generated Javadoc
/**
 * The Class Electeur.
 */
public class Electeur extends Personne{
	
	private static int nbElecteurs = 0;
	private int idElecteur;
	/** The position geographique. */
	private double[] positionGeographique;

	/**
	 * Instantiates a new electeur.
	 *
	 * @param axes Tableau des différents axes
	 * @param valAxes Valeur des différents axes
	 * @param positionGeographique Latitudes et longitudes fictives
	 */
	public Electeur(Axe[] axes, double[] valAxes, double[] positionGeographique) {
		super(axes, valAxes);
		this.positionGeographique = positionGeographique;
		this.idElecteur = nbElecteurs;
		this.nbElecteurs++;
	}



	public int getIdElecteur() {
		return idElecteur;
	}
	/**
	 * Instantie un nouveau electeur.
	 *
	 * @param axes Tableau des différents axes
	 * @param positionGeographique position geographique de l'individu
	 */
	public Electeur(Axe[] axes,double[] positionGeographique) {
		super(axes);
		this.positionGeographique = positionGeographique;
	}

	/**
	 * Eloigne ou rapproche un electeur des opinions d'une personne
	 * @param p
	 * @param action
	 */
	public void evoluer(Personne p,String action)
	{
		for(int i=0;i<axes.length;i++)
		{
			if(action=="eloigner")
				valAxes[i] = valAxes[i] - ((p.getValAxes()[i] - valAxes[i])/2);
			else
				valAxes[i] = valAxes[i] + ((p.getValAxes()[i] - valAxes[i])/2);
			if(valAxes[i]<0 || valAxes[i]>1)
			{
				if(valAxes[i]>1)
					valAxes[i] = 1;
				else if(valAxes[i]<0)
					valAxes[i] = 0;
			}		
		}
	}
	/**
	 * Rapprochement proportionnelement à l'utilité
	 * @param p
	 * @param utilite
	 */
	public void RapprocherParUtilite(Personne p,Double utilite)
	{
		for(int i=0;i<axes.length;i++)
		{
			
			valAxes[i] = valAxes[i] + (utilite/10)*((p.getValAxes()[i] - valAxes[i])/2);
			if(valAxes[i]<0 || valAxes[i]>p.getValAxes()[i])
			{
				if(valAxes[i]>1)
					valAxes[i] = 1;
				else if(valAxes[i]<0)
					valAxes[i] = 0;
			}		
		}
	}
	

	//Diagramme de séquence à faire
	/**
	 * On se rapproche ou on s'éloigne par discussion
	 * @param p
	 */
	public void modifierOpinionParDiscussion(Personne p)
	{
		double[] difference = CalculVote.calculDifference(p,this);
		double norme = CalculVote.getNorme(difference);
		
		//On s'éloigne
		if(norme>1)
		{
			evoluer(p,"eloigner");

		}
		//On se rapproche
		else if(norme<0.5)
		{
			evoluer(p,"rapprocher");
		}
	}
	
	
	
	/**
	 * En fonction des préferences parmi N personnes
	 * @param sondage
	 * @param N
	 */
	public void evoluerOpinionsParIdee(HashMap<Candidat,Double> sondage, int N)
	{
		
		ArrayList<Integer> nPremierCandidats = CalculVote.trierNcandidats(N,sondage);
		double normeMin = 999999999;
		Candidat candidatMax = null;
		for(Map.Entry<Candidat,Double> unRes : sondage.entrySet()) {
			Candidat cand = unRes.getKey();
			Double valeur = unRes.getValue();
			double[] difference = CalculVote.calculDifference(cand,this);
			double norme = CalculVote.getNorme(difference);
			if(normeMin>norme)
			{
				normeMin = norme;
				candidatMax = cand;
			}
		}
		this.evoluer(candidatMax, "rapprocher");
	}


	/**
	 * On s'éloigne ou on se rappoche en fonction du candidat ayant l utilité la plus élevée
	 * @param sondage
	 */
	public void evoluerOpinionsParCote(HashMap<Candidat,Double> sondage)
	{
		double utiliteMax = -9999;
		double utilite = 1; //Valeur par defaut si norme = 0
		Candidat candidatUtilitePlusElevee = null;
		for(Map.Entry<Candidat,Double> unResultat : sondage.entrySet()) {
			Candidat cle = unResultat.getKey();
			Double valeur = unResultat.getValue();
			double[] difference = CalculVote.calculDifference(cle,this);
			double norme = CalculVote.getNorme(difference);
			
			if(norme != 0)
			{
				 utilite = (1/norme)*valeur;
			}
			
		    if(utiliteMax<utilite)
		    {
		    	utiliteMax = utilite;
		    	candidatUtilitePlusElevee = cle;
		    }
		}
		this.evoluer(candidatUtilitePlusElevee, "rapprocher");
		
	}


	
	
	
	public double[] getPositionGeographique() {
		return positionGeographique;
	}



	/**
	 * En fonction de l'utilité de chaque candidats
	 * @param sondage
	 */
	public void evoluerOpinionsParMoyenne(HashMap<Candidat,Double> sondage)
	{
		double utilite;
		for(Map.Entry<Candidat,Double> unResultat : sondage.entrySet()) {
			Candidat cle = unResultat.getKey();
			Double valeur = unResultat.getValue();
			double[] difference = CalculVote.calculDifference(cle,this);
			double norme = CalculVote.getNorme(difference);
			
		    utilite = (1/norme)*valeur;
		    RapprocherParUtilite(cle,utilite);
		}		
	}
	
		
	
	
	
	
}
