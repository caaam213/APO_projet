package jUnit;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import parametres.Axe;
import parametres.Candidat;
import parametres.Electeur;
import votes.Borda;

public class BordaTest {
	
	@Test
	public void scrutinBordaTest()
	{
		Axe a1 = new Axe("Immigration");
		Axe a2 = new Axe("Environnement");
		Axe a3 = new Axe("Pouvoir d'achat");
		Axe[] axes = {a1,a2,a3};
		
		double[] d1 =  {(double)0.4,(double)0.4,(double)0.4};
		Electeur e1 = new Electeur(axes,d1,"");
		double[] d2 =  {(double)0.3,(double)0.3,(double)0.3};
		Electeur e2 = new Electeur(axes,d2,"");
		double[] d3 =  {(double)0.2,(double)0.2,(double)0.2};
		Electeur e3 = new Electeur(axes,d3,"");
		double[] d4 =  {(double)1,(double)1,(double)1};
		Electeur e4 = new Electeur(axes,d4,"");
		double[] d5 =  {(double)0.8,(double)0.8,(double)0.8};
		Electeur e5 = new Electeur(axes,d5,"");
		
		Electeur[] es = {e1,e2,e3,e4,e5};
		
		double[] dc1 =  {(double)0.4,(double)0.4,(double)0.4};
		Candidat c1 = new Candidat(axes,dc1,"Zemmour");
		double[] dc2 =  {(double)0.5,(double)0.5,(double)0.5};
		Candidat c2 = new Candidat(axes,dc2,"Macron");
		double[] dc3 =  {(double)0.6,(double)0.6,(double)0.6};
		Candidat c3 = new Candidat(axes,dc3,"Jadot");
		
		Candidat[] cs =  {c1,c2,c3}; 
		
		HashMap<Candidat,Double> resAttendu = new HashMap<Candidat, Double>();
		resAttendu.put(c1, (double)11);
		resAttendu.put(c2, (double)10);
		resAttendu.put(c3, (double)9);
		
		Borda borda = new Borda(cs, es);
		//borda.scrutinBorda(cs, es);
		//HashMap<Candidat,Double> scrutinBorda(Candidat[] candidats, Electeur[] electeurs);
		HashMap<Candidat,Double> test = new HashMap<Candidat,Double>();
		test = borda.scrutinBorda(cs, es);
		System.out.println(test.get(c1));
		System.out.println(test.get(c2));
		System.out.println(test.get(c3));
		
		assertEquals(resAttendu,borda.scrutinBorda(cs, es));
	}
}




























