package jUnit;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import parametres.Axe;
import parametres.Candidat;
import parametres.Electeur;
import votes.Approbation;
import votes.DeuxTours;

public class ApprobationTest {
	@Test
	public void ApprobationSimulationTest()
	{
		Axe a1 = new Axe("Immigration");
		Axe a2 = new Axe("Environnement");
		Axe[] axes = {a1,a2};
		
		double[] d1 =  {(double)0.3,(double)0.2};
		Electeur e1 = new Electeur(axes,d1);
		double[] d2 =  {(double)0.4,(double)0.1};
		Electeur e2 = new Electeur(axes,d2);
		double[] d3 =  {(double)0.8,(double)0.2};
		Electeur e3 = new Electeur(axes,d3);
		double[] d4 =  {(double)0.1,(double)0.1};
		Electeur e4 = new Electeur(axes,d4);
; 
		
		Electeur[] es = {e1,e2,e3,e4};
		
		HashMap<Candidat,Double> resultat_deuxtours = new HashMap<Candidat,Double>();
		HashMap<Electeur,Integer> electeursAvecNChoisi = new HashMap<Electeur,Integer>();
		electeursAvecNChoisi.put(e1, 2);
		electeursAvecNChoisi.put(e2, 1);
		electeursAvecNChoisi.put(e3, 3);
		electeursAvecNChoisi.put(e4, 2);
		
		double[] dc1 =  {(double)0.85,(double)0.21};
		Candidat c1 = new Candidat(axes,dc1,"Zemmour");
		double[] dc2 =  {(double)0.012,(double)0.32};
		Candidat c2 = new Candidat(axes,dc2,"Macron");
		double[] dc3 =  {(double)0.4,(double)0.4};
		Candidat c3 = new Candidat(axes,dc3,"Jadot");
		double[] dc4 =  {(double)0.16,(double)0.16};
		Candidat c4 = new Candidat(axes,dc4,"Melenchon");
		
		Candidat[] cs =  {c1,c2,c3,c4};
		Approbation app = new Approbation(cs, electeursAvecNChoisi); 
		app.simulation();
		
		HashMap<Candidat,Double> resultats = new HashMap<Candidat,Double>();
		resultats.put(c1, (double)1/8);
		resultats.put(c2, (double)1/8);
		resultats.put(c3, (double)2/8);
		resultats.put(c4, (double)4/8);
		//assertTrue(deuxtours.scrutinDeuxTours(cs, es, 2),resultat_deuxtours);
		assertEquals(resultats,app.getResultatScrutin());
	
	}
}
