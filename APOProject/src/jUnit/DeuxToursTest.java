package jUnit;


import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import parametres.*;
import votes.*;

public class DeuxToursTest {
	
	@Test
	public void scrutinDeuxToursTest()
	{
		Axe a1 = new Axe("Immigration");
		Axe a2 = new Axe("Environnement");
		Axe a3 = new Axe("Pouvoir d'achat");
		Axe[] axes = {a1,a2,a3};
		
		double[] d1 =  {(double)0.75,(double)0,(double)0};
		Electeur e1 = new Electeur(axes,d1,"");
		double[] d2 =  {(double)0,(double)0.75,(double)0};
		Electeur e2 = new Electeur(axes,d2,"");
		double[] d3 =  {(double)0,(double)0,(double)0.75};
		Electeur e3 = new Electeur(axes,d3,"");
		double[] d4 =  {(double)0.5,(double)0.5,(double)0.75};
		Electeur e4 = new Electeur(axes,d4,"");
		double[] d5 =  {(double)0.5,(double)0.5,(double)0.5};
		Electeur e5 = new Electeur(axes,d5,"");
		double[] d6 =  {(double)0.2,(double)1,(double)0.7};
		Electeur e6 = new Electeur(axes,d6,"");
		double[] d7 =  {(double)0.2,(double)1,(double)0.6};
		Electeur e7 = new Electeur(axes,d7,"");
		double[] d8 =  {(double)1,(double)0,(double)0};
		Electeur e8 = new Electeur(axes,d8,"");
		double[] d9 =  {(double)0.5,(double)0.5,(double)0.5};
		Electeur e9 = new Electeur(axes,d9,""); 
		
		Electeur[] es = {e1,e2,e3,e4,e5,e6,e7,e8,e9};
		
		double[] dc1 =  {(double)1,(double)0,(double)0};
		Candidat c1 = new Candidat(axes,dc1,"Zemmour");
		double[] dc2 =  {(double)0.5,(double)0.5,(double)0.5};
		Candidat c2 = new Candidat(axes,dc2,"Macron");
		double[] dc3 =  {(double)0.2,(double)1,(double)0.7};
		Candidat c3 = new Candidat(axes,dc3,"Jadot");
		
		Candidat[] cs =  {c1,c2,c3};
		DeuxTours deuxtours = new DeuxTours(cs, es); 
		
		HashMap<Candidat,Double> resultat_deuxtours = new HashMap<Candidat,Double>();
		resultat_deuxtours.put(c2, (double)7/9);
		resultat_deuxtours.put(c3, (double)2/9);
		//assertTrue(deuxtours.scrutinDeuxTours(cs, es, 2),resultat_deuxtours);
		assertEquals(resultat_deuxtours,deuxtours.scrutinDeuxTours(cs, es, 2));
		
	}
}




































