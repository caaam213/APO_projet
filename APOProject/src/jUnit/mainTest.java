package jUnit;

import Utilites.AnalyseScrutin;
import Utilites.CalculVote;
import parametres.Axe;
import parametres.Candidat;
import parametres.Electeur;

public class mainTest {

	public static void main(String[] args) {
		
		/*Axe a1 = new Axe("Environnement");
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
		As.AnalyseModeScrutin(c, a, axe_moy);*/
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
		double[] de2 = {0.6,0.6,0.6};
		Electeur e2 = new Electeur(axes, de2, de2);
		double[] de3 = {0.7,0.7,0.7};
		Electeur e3 = new Electeur(axes, de3, de3);
		double[] de4 = {0.8,0.8,0.8};
		Electeur e4 = new Electeur(axes, de4, de4);
		Electeur[] es = {e1,e2,e3, e4};
		
		double norme_choisi = Math.sqrt(axes.length)/2;
		
		Electeur[] elecs = CalculVote.chercher_noabstentionniste(es,cs, 0.518);
		
		for(Electeur electeur: elecs)
		{
			System.out.println(electeur.getValAxes()[0] + "," + electeur.getValAxes()[1] + "," + electeur.getValAxes()[2]);
		}
		
	}
}
























