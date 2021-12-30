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
	 * @param axes Tableau des diff�rents axes
	 * @param valAxes Valeur des diff�rents axes
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
	 * @param axes Tableau des diff�rents axes
	 * @param positionGeographique position geographique de l'individu
	 */
	public Electeur(Axe[] axes,String positionGeographique) {
		super(axes);
		this.positionGeographique = positionGeographique;
	}

	
	
	
	
}
