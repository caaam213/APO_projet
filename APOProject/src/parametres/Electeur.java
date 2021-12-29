package parametres;

public class Electeur extends Personne{
	private String positionGeographique;

	public Electeur(Axe[] axes, float[] valAxes, String positionGeographique) {
		super(axes, valAxes);
		this.positionGeographique = positionGeographique;
	}
	
	public Electeur(Axe[] axes,String positionGeographique) {
		super(axes);
		this.positionGeographique = positionGeographique;
	}

	
	
	
	
}
