package jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Utilites.CalculVote;
import parametres.Axe;
import parametres.Candidat;
import parametres.Electeur;


public class CalculVoteTest {
	
	@Test
	public void chercher_noabstentionnisteTest()
	{
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	    
	    
	    double[] dc1 = {0.5,0.5,0.5};
	    Candidat c1 = new Candidat(axes, dc1,"");
	    double[] dc2 = {0.1,0.1,0.1};
	    Candidat c2 = new Candidat(axes, dc2,"");
	    Candidat[] cs = {c1,c2};
	    
	    
		double[] de1 = {0.5,0.5,0.5};
		Electeur e1 = new Electeur(axes, de1, de1);
		double[] de2 = {0.1,0.1,0.1};
		Electeur e2 = new Electeur(axes, de2, de2);
		double[] de3 = {1,1,1};
		Electeur e3 = new Electeur(axes, de3, de3);
		
		Electeur[] es = {e1,e2,e3};
		
		Electeur[] res_attendu = {e3};
		
		double norme_choisi = Math.sqrt(axes.length)/2;
		
		//CalculVote.chercher_noabstentionniste(es,cs, norme_choisi);
		assertEquals(CalculVote.chercher_noabstentionniste(es,cs, norme_choisi),res_attendu);
	}
}















