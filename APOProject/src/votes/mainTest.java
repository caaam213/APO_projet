package votes;

import parametres.*;

public class mainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Axe a1 = new Axe("Environnement");
		Axe a2 = new Axe("Ecologie");
		Axe a3 = new Axe("Ecologie");
		Axe[] axes = {a1,a2};
		
		double[] fe0 = {(double) 0,1};
		double[] fe1 = {(double) 0.25,(double) 0.75};
		double[] fe2 = {(double) 0.5,(double) 0.5};
		double[] fe3 = {(double) 0.5,(double) 0.5};
		double[] fe4 = {(double) 0.4,(double) 0.5};
		double[] fe5 = {(double) 0.5,(double) 0.5};
		Electeur e0 = new Electeur(axes, fe0,"s");
		Electeur e1 = new Electeur(axes, fe1,"s");
		Electeur e2 = new Electeur(axes, fe2,"s");
		Electeur e3 = new Electeur(axes, fe3,"s");
		Electeur e4 = new Electeur(axes, fe4,"s");
		Electeur e5 = new Electeur(axes, fe5,"s");
		Electeur[] elec = {e0,e1,e2,e3,e4,e5};
		
		double[] f4 = {0,1};
		double[] f5 = {(double) 0.25,(double) 0.74};
		double[] f6 = {(double) 0.5,(double) 0.5};
		double[] f7 = {(double) 0.25,(double) 0.75};
		Candidat c0 = new Candidat(axes, f4,"s");
		Candidat c1 = new Candidat(axes, f5,"s");
		Candidat c2 = new Candidat(axes, f6,"s");
		Candidat c3 = new Candidat(axes, f7,"s");
		Candidat[] cand = {c0,c1,c2,c3};
		
		UnTour untour = new UnTour(cand,elec);
		
		double[] normes = untour.getNormes(elec);
		
		
		/*for(int i=0;i<normes.length;i++)
		{
			System.out.println(normes[i]);
		}*/
		/*untour.simulation();*/
	}

}
		





















