package parametres;
import java.lang.Math;

public class Axe {
	private String nom;
	
	public Axe(String nom) {
		this.nom = nom;
	}
	
	/*public Axe(String nom)
	{
		this.nom = nom;
		valeur = (float) Math.random();
		
	}*/

	public String getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return "Axe [nom=" + nom + "]";
	}


	
	
	
	
	
	
	
}
