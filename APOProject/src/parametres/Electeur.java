/*
 * 
 */
package parametres;

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
		this.nbElecteurs++;
		this.idElecteur = nbElecteurs;
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
	
	
	//Diagramme de séquence à faire
	public void modifierOpinionParDiscussion(Personne p)
	{
		double[] difference = this.calculDifference(p);
		double norme = this.getNorme(difference);
		
		//On s'éloigne de la valeur en lui retirant la moyenne des 2
		if(norme>1)
		{
			for(int i=0;i<axes.length;i++)
			{
				
				valAxes[i] = valAxes[i] - ((p.getValAxes()[i] - valAxes[i])/2);
				if(valAxes[i]<0 || valAxes[i]>1)
				{
					if(valAxes[i]>1)
						valAxes[i] = 1;
					else if(valAxes[i]<0)
						valAxes[i] = 0;
				}
					
					
			}
		}
		//On rapproche de la valeur en lui ajoutant la moyenne des 2
		else if(norme<0.5)
		{
			for(int i=0;i<axes.length;i++)
			{
				if(valAxes[i]>0 && valAxes[i]<1)
					valAxes[i] = valAxes[i] + ((p.getValAxes()[i] - valAxes[i])/2);
				else
					if(valAxes[i]>1)
						valAxes[i] = 1;
					else if(valAxes[i]<0)
						valAxes[i] = 0;
						
			}
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
