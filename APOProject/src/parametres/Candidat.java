/*
 * 
 */
package parametres;

public class Candidat extends Personne{
	private static int nbCandidat = 0;
	private int idCandidat;
	private String nomPrenom;
	
	/**
	 * Instantiates a new candidat.
	 *
	 * @param axes Tableau des différents axes
	 * @param valAxes Valeur des différents axes
	 * @param nomPrenom Nom et Prénom
	 */
	public Candidat(Axe[] axes, double[] valAxes, String nomPrenom) {
		super(axes,valAxes);
		this.nomPrenom = nomPrenom;
		nbCandidat++;
		idCandidat = nbCandidat;
	}
	
	/**
	 * Instantiates a new candidat.
	 *
	 * @param axes Tableau des différents axes
	 * @param nomPrenom Nom et Prénom
	 */
	public Candidat(Axe[] axes, String nomPrenom) {
		super(axes);
		this.nomPrenom = nomPrenom;
		nbCandidat++;
		idCandidat = nbCandidat;
	}

}
