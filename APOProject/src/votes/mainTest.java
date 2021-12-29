package votes;

import parametres.*;

public class mainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Axe a1 = new Axe("Environnement");
		Axe a2 = new Axe("Ecologie");
		Axe a3 = new Axe("Ecologie");
		Axe[] axes = {a1,a2};
		
		float[] f1 = {(float) 0,1};
		float[] f2 = {(float) 0.25,(float) 0.75};
		float[] f3 = {(float) 0.5,(float) 0.5};
		Electeur e1 = new Electeur(axes, f1,"s");
		Electeur e2 = new Electeur(axes, f2,"s");
		Electeur e3 = new Electeur(axes, f3,"s");
		Electeur[] elec = {e1,e2,e3};
		
		float[] f4 = {0,1};
		float[] f5 = {(float) 0.25,(float) 0.75};
		float[] f6 = {(float) 0.5,(float) 0.5};
		Candidat c1 = new Candidat(axes, f4,"s");
		Candidat c2 = new Candidat(axes, f5,"s");
		Candidat c3 = new Candidat(axes, f6,"s");
		Candidat[] cand = {c1,c2,c3};
		
		UnTour untour = new UnTour(cand,elec);
		
		float[] normes = untour.getNormes(elec);
		
		for(int i=0;i<normes.length;i++)
		{
			System.out.println(normes[i]);
		}
		
	}

}
