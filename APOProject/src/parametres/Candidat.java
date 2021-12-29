package parametres;

public class Candidat extends Personne{
	private static int nbCandidat = 0;
	private int idCandidat;
	private String nomPrenom;
	
	public Candidat(Axe[] axes, float[] valAxes, String nomPrenom) {
		super(axes,valAxes);
		this.nomPrenom = nomPrenom;
		nbCandidat++;
		idCandidat = nbCandidat;
	}
	
	

}
