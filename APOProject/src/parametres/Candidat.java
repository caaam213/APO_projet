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
		idCandidat = nbCandidat;
		nbCandidat++;
	}
	
	public String getNomPrenom() {
		return nomPrenom;
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

	public int compareTo(Candidat key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		String str = super.toString();
		return str + "Candidat [idCandidat=" + idCandidat + ", nomPrenom=" + nomPrenom + "]";
	}
	
	

}
