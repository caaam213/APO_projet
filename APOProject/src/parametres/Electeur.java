/*
 * 
 */
package parametres;

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
	private String positionGeographique;

	/**
	 * Instantiates a new electeur.
	 *
	 * @param axes Tableau des différents axes
	 * @param valAxes Valeur des différents axes
	 * @param positionGeographique position geographique de l'individu
	 */
	public Electeur(Axe[] axes, double[] valAxes, String positionGeographique) {
		super(axes, valAxes);
		this.positionGeographique = positionGeographique;
		this.idElecteur = nbElecteurs;
		this.nbElecteurs++;
	}
	
	public int getIdElecteur() {
		return idElecteur;
	}

	/**
	 * Instantiates a new electeur.
	 *
	 * @param axes Tableau des différents axes
	 * @param positionGeographique position geographique de l'individu
	 */
	public Electeur(Axe[] axes,String positionGeographique) {
		super(axes);
		this.positionGeographique = positionGeographique;
	}
	 
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
	
	public void RapprocherParUtilite(Personne p,Double utilite)
	{
		for(int i=0;i<axes.length;i++)
		{
			
			valAxes[i] = valAxes[i] + utilite*((p.getValAxes()[i] - valAxes[i])/2);
			if(valAxes[i]<0 || valAxes[i]>1)
			{
				if(valAxes[i]>1)
					valAxes[i] = 1;
				else if(valAxes[i]<0)
					valAxes[i] = 0;
			}		
		}
	}
	
	private ArrayList<Integer> trierNcandidats( int n , HashMap<Candidat, Double> sondage )
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
	
	//Diagramme de séquence à faire
	public void modifierOpinionParDiscussion(Personne p)
	{
		double[] difference = this.calculDifference(p);
		double norme = this.getNorme(difference);
		
		//On s'éloigne d
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
	
	
	
	public void evoluerOpinionsParIdee(HashMap<Candidat,Double> sondage, Candidat[] candidats,int N)
	{
		
		ArrayList<Integer> nPremierCandidats = trierNcandidats(N,sondage);
		double normeMin = 999999999;
		Candidat candidat = null;
		for(int idCandidat : nPremierCandidats)
		{
			double[] difference = this.calculDifference(candidats[idCandidat]);
			double norme = this.getNorme(difference);
			if(normeMin>norme)
			{
				normeMin = norme;
				candidat = candidats[idCandidat];
			}
		}
		this.evoluer(candidat, "rapprocher");
	}
	
	public void evoluerOpinionsParCote(HashMap<Candidat,Double> sondage)
	{
		double utiliteMax = -9999;
		double utilite;
		Candidat candidatUtilitePlusElevee = null;
		for(Map.Entry<Candidat,Double> unResultat : sondage.entrySet()) {
			Candidat cle = unResultat.getKey();
			Double valeur = unResultat.getValue();
			double[] difference = this.calculDifference(cle);
			double norme = this.getNorme(difference);
			
		    utilite = (1/norme)*valeur;
		    if(utiliteMax<utilite)
		    {
		    	utiliteMax = utilite;
		    	candidatUtilitePlusElevee = cle;
		    }
		}
		this.evoluer(candidatUtilitePlusElevee, "rapprocher");
		
	}
	
	public void evoluerOpinionsParMoyenne(HashMap<Candidat,Double> sondage)
	{
		double utilite;
		for(Map.Entry<Candidat,Double> unResultat : sondage.entrySet()) {
			Candidat cle = unResultat.getKey();
			Double valeur = unResultat.getValue();
			double[] difference = this.calculDifference(cle);
			double norme = this.getNorme(difference);
			
		    utilite = (1/norme)*valeur;
		    RapprocherParUtilite(cle,utilite);
		}
		
		
	}
	
	public double[] calculDifference(Personne p)
	{
		double[] valaxes_candidats = p.getValAxes();
		
		double[] valaxes_diff = new double[valaxes_candidats.length];

		for(int j=0;j<valaxes_candidats.length;j++)
		{
			valaxes_diff[j] = Math.abs(valaxes_candidats[j]  - this.getValAxes()[j]);
		}
		
		return valaxes_diff;
	}
		
	public double getNorme(double[] vecteurs)
	{
		double norme = 0;
		
		for(int i=0;i<vecteurs.length;i++)
		{
			norme += vecteurs[i]*vecteurs[i];
			
			
		}
		norme =  Math.sqrt(norme);
		return norme;
	}
	
	
	
}
