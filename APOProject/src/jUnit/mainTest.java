package jUnit;

import Utilites.AnalyseScrutin;
import parametres.Axe;
import parametres.Candidat;

public class mainTest {

	public static void main(String[] args) {
		
		Axe a1 = new Axe("Environnement");
		Axe a2 = new Axe("Social");
		Axe a3 = new Axe("Immigration");
		Axe[] a = {a1,a2,a3};
		
		double[] cd1 = {0.6,0.6,0.6};
		Candidat c1 = new Candidat(a, cd1,"Macron");
		double[] cd2 = {0.4,0.4,0.4};
		Candidat c2 = new Candidat(a, cd2,"Pecresse");
		double[] cd3 = {0.5,0.5,0.5};
		Candidat c3 = new Candidat(a, cd3,"Melenchon");
		double[] cd4 = {0.55,0.55,0.55};
		Candidat c4 = new Candidat(a, cd4,"Zemmour");
		double[] cd5 = {0.45,0.45,0.45};
		Candidat c5 = new Candidat(a, cd5,"Marine");
		
		Candidat[] c = {c1,c2,c3,c4,c5};
		double[] axe_moy = {0.5,0.5,0.5};
		
		AnalyseScrutin As = new AnalyseScrutin();
		As.AnalyseModeScrutin(c, a, axe_moy);
		
	}
}
