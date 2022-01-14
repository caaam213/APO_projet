package jUnit;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import Utilites.CalculVote;
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
	    
	    double[] tabTest = CalculVote.calculDifference(c1,e1); 
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
	    
	    double[] tabTest = CalculVote.calculDifference(c1,e1);  
	    double norme = CalculVote.getNorme(tabTest);
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

	@Test 
	public void evoluerOpinionsParIdeeTest()
	{
		Axe a1 = new Axe("Environnement");
		Axe a2 = new Axe("Ecologie");
		//Axe a3 = new Axe("Ecologie");
		Axe[] axes = {a1,a2};
		
		double[] fe1 = {(double) 0.23,(double) 0.68};
		Electeur e1 = new Electeur(axes, fe1,"s");
		
		double[] f4 = {0,1};
		double[] f5 = {(double) 0.25,(double) 0.74};
		double[] f6 = {(double) 0.5,(double) 0.5};
		double[] f7 = {(double) 0.75,(double) 0.2};
		Candidat c0 = new Candidat(axes, f4,"Marine le pen");
		Candidat c1 = new Candidat(axes, f5,"Zemmour");
		Candidat c2 = new Candidat(axes, f6,"Macron");
		Candidat c3 = new Candidat(axes, f7,"Melenchon");
		Candidat[] cand = {c0,c1,c2,c3};
		
		double[] axesAttendus = {0.24,0.71};
		
		HashMap<Candidat,Double> sondage = new HashMap<Candidat,Double>();
		sondage.put(c0, 0.2);
		sondage.put(c1, 0.5);
		sondage.put(c2, 0.1);
		sondage.put(c3, 0.2);
		
		e1.evoluerOpinionsParIdee(sondage,cand,3);
		assertArrayEquals(axesAttendus,e1.getValAxes(),0.01);

	}
	
	@Test 
	public void evoluerOpinionsParCoteTest()
	{
		Axe a1 = new Axe("Environnement");
		Axe a2 = new Axe("Ecologie");
		//Axe a3 = new Axe("Ecologie");
		Axe[] axes = {a1,a2};


		double[] fe1 = {(double) 0.20,(double) 0.7};
		Electeur e1 = new Electeur(axes, fe1,"s");

		double[] f4 = {0,1};
		double[] f5 = {(double) 0.25,(double) 0.74};
		double[] f6 = {(double) 0.5,(double) 0.5};
		double[] f7 = {(double) 0.75,(double) 0.2};
		Candidat c0 = new Candidat(axes, f4,"Marine le pen");
		Candidat c1 = new Candidat(axes, f5,"Zemmour");
		Candidat c2 = new Candidat(axes, f6,"Macron");
		Candidat c3 = new Candidat(axes, f7,"Melenchon");
		Candidat[] cand = {c0,c1,c2,c3};
		
		double[] axesAttendus = {0.225,0.72};
		
		HashMap<Candidat,Double> sondage = new HashMap<Candidat,Double>();
		sondage.put(c0, 0.2);
		sondage.put(c1, 0.5);
		sondage.put(c2, 0.1);
		sondage.put(c3, 0.2);
		
		e1.evoluerOpinionsParCote(sondage);
		
		assertArrayEquals(axesAttendus,e1.getValAxes(),0.0001);
		
	}
	
	@Test 
	public void evoluerOpinionsParMoyenne()
	{
		Axe a1 = new Axe("Environnement");
		Axe a2 = new Axe("Ecologie");
		//Axe a3 = new Axe("Ecologie");
		Axe[] axes = {a1,a2};

		double[] fe1 = {(double) 0.20,(double) 0.7};
		Electeur e1 = new Electeur(axes, fe1,"s");


		double[] f4 = {0,1};
		double[] f5 = {(double) 0.25,(double) 0.74};
		double[] f6 = {(double) 0.5,(double) 0.5};
		double[] f7 = {(double) 0.75,(double) 0.2};
		Candidat c0 = new Candidat(axes, f4,"Marine le pen");
		Candidat c1 = new Candidat(axes, f5,"Zemmour");
		Candidat c2 = new Candidat(axes, f6,"Macron");
		Candidat c3 = new Candidat(axes, f7,"Melenchon");
		Candidat[] cand = {c0,c1,c2,c3};
		
		double[] axesAttendus = {0.224,0.715};
		
		HashMap<Candidat,Double> sondage = new HashMap<Candidat,Double>();
		sondage.put(c0, 0.2);
		sondage.put(c1, 0.5);
		sondage.put(c2, 0.1);
		sondage.put(c3, 0.2);
		
		e1.evoluerOpinionsParMoyenne(sondage);
		
		assertArrayEquals(axesAttendus,e1.getValAxes(),0.001);
	
	}
	
}
