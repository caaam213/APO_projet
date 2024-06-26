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
	protected Axe[] axes;
	
	/** The val axes. */
	protected double[] valAxes;
	
	/**
	 * Instancie une nouvelle personne si on ne g�n�re pas des valeurs de fa�on al�atoire
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
	 * Instancie une nouvelle personne si g�n�re des valeurs de fa�on al�atoire
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

	/**
	 * Fonction utile pour le fichier de configuration
	 *
	 */
	public Axe[] getAxes() {
		return axes;
	}


	public double[] getValAxes() {
		return valAxes;
		
	}


	
	/*public String toString() {
		return "Personne [axes=" + Arrays.toString(axes) + ", valAxes=" + Arrays.toString(valAxes) + "]";
	}*/
	
	
	
	

	
	
	
}
