package jUnit;
import parametres.*;
import Utilites.*;
import static org.junit.Assert.*;
import votes.*;
import org.junit.Test;

public class UnTourTest{

	@Test
	public void testCalculDifferenceAxes ()
	{
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	    
	    double[] f1 = {1,0,0.5};
	    double[] f2 = {0,1,0};
	    double[] f3 = {1,0,0};
	    
	    Candidat c1 = new Candidat(axes,f1, "fer");
	    Electeur e1 = new Electeur(axes,f2, "fer");
	    Electeur e2 = new Electeur(axes,f3, "fer");
	    
	    Candidat[] tabc = new Candidat[1];
	    tabc[0] = c1;
	    
	    Electeur[] tabe = new Electeur[2];
	    tabe[0] = e1;
	   
	    tabe[1] = e2;
	    UnTour unTour = new UnTour(tabc, tabe);
	    double[][] tabTest = CalculVote.CalculDifferenceAxes(c1, tabe);
	    double[][] resultatAttendu = {{1,1,0.5},{0,0,0.5}};
	    assertEquals(resultatAttendu,tabTest);
	    
	}
	
	


}
