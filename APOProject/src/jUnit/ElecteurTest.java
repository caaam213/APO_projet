package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import parametres.Axe;
import parametres.Candidat;
import parametres.Electeur;
import votes.UnTour;

public class ElecteurTest {

	@Test
	public void testMethodegetDifference()
	{
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	    
	    double[] f1 = {0.5,0.5,0.5};
	    double[] f2 = {0.4,0.6,0.5};
	    
	    Candidat c1 = new Candidat(axes,f1, "fer");
	    Electeur e1 = new Electeur(axes,f2, "fer");
	    
	    double[] tabTest = e1.calculDifference(c1); 
	    double[] resultatAttendu = {0.1,0.1,0};
	    assertArrayEquals(resultatAttendu,tabTest,1e-15);
	    
	}
	
	@Test
	public void testMethodeGetNorme()
	{
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	   
	    double[] f1 = {0.5,0.5,0.5};
	    double[] f2 = {0.4,0.6,0.5};
	    
	    Candidat c1 = new Candidat(axes,f1, "fer");
	    Electeur e1 = new Electeur(axes,f2, "fer");
	    
	    double[] tabTest = e1.calculDifference(c1); 
	    double norme = e1.getNorme(tabTest);
	    double normeAttendue = 0.141421;
	    assertEquals(norme,normeAttendue,0.0001);
	}
	
	@Test
	public void modifierOpinionParDiscussionTest1()
	{
		//Cas d'éloignement
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	    
	    double[] f1 = {0.2,0.8,0.4};
	    double[] f2 = {0.8,0.1,0.9};
	    
	    Candidat c1 = new Candidat(axes,f1, "fer");
	    Electeur e1 = new Electeur(axes,f2, "fer");

	    double[] axesAttendus = {1,0,1};
	    e1.modifierOpinionParDiscussion(c1);
	    
	    assertArrayEquals(axesAttendus,e1.getValAxes(),0.0001);
	}
	
	@Test
	public void modifierOpinionParDiscussionTest2()
	{
		//Cas neutre
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	    
	    double[] f1 = {0.4,0.4,0.4};
	    double[] f2 = {0.8,0.8,0.8};
	    
	    Candidat c1 = new Candidat(axes,f1, "fer");
	    Electeur e1 = new Electeur(axes,f2, "fer");

	    double[] axesAttendus = {0.8,0.8,0.8};
	    e1.modifierOpinionParDiscussion(c1);
	    
	    assertArrayEquals(axesAttendus,e1.getValAxes(),0.0001);
	}
	
	@Test
	public void modifierOpinionParDiscussionTest3()
	{
		//Cas rapprochement
		Axe[] axes = new Axe[3];
	    axes[0] = new Axe("e");
	    axes[1] = new Axe("a");
	    axes[2] = new Axe("m");
	    
	    double[] f1 = {0.5,0.5,0.5};
	    double[] f2 = {0.4,0.6,0.5};
	    
	    Candidat c1 = new Candidat(axes,f1, "fer");
	    Electeur e1 = new Electeur(axes,f2, "fer");

	    double[] axesAttendus = {0.45,0.55,0.5};
	    e1.modifierOpinionParDiscussion(c1);
	    
	    assertArrayEquals(axesAttendus,e1.getValAxes(),0.0001);
	}
	

}
