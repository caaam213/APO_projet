/*
 * 
 */
package parametres;
import java.lang.Math;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * La classe Personne.
 */
public abstract class Personne {
	
	/** The axes. */
	private Axe[] axes;
	
	/** The val axes. */
	private double[] valAxes;
	
	/**
	 * Instancie une nouvelle personne si on ne génère pas des valeurs de façon aléatoire
	 *
	 * @param axes the axes
	 * @param valAxes the val axes
	 */
	public Personne(Axe[] axes, double[] valAxes) {
		super();
		this.axes = axes;
		this.valAxes = valAxes;
	}

	
	/**
	 * Instancie une nouvelle personne si génère des valeurs de façon aléatoire
	 *
	 * @param axes the axes
	 */
	public Personne(Axe[] axes) {
		super();
		this.axes = axes;
		valAxes = new double[axes.length];
		for(int i = 0;i<axes.length;i++)
		{
			valAxes[i] = (float) Math.random();
		}
	}


	public Axe[] getAxes() {
		return axes;
	}


	public double[] getValAxes() {
		return valAxes;
	}


	@Override
	public String toString() {
		return "Personne [axes=" + Arrays.toString(axes) + ", valAxes=" + Arrays.toString(valAxes) + "]";
	}
	
	
	
	

	
	
	
}
