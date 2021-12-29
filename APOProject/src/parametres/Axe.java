/*
 * 
 */
package parametres;
import java.lang.Math;

// TODO: Auto-generated Javadoc
/**
 * The Class Axe.
 */
public class Axe {
	
	/** The nom. */
	private String nom;
	
	/**
	 * Instantiates a new axe.
	 *
	 * @param nom le Nom
	 */
	public Axe(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Axe [nom=" + nom + "]";
	}
	
}
