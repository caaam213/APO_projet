package votes;

import java.lang.Math;

import parametres.Candidat;
import parametres.Electeur;

public class UnTour extends Scrutin{

	public UnTour(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
	}

	@Override
	public void simulation() {
		//-------------R�cup�ration des normes repr�sentant le rapprochement avec un candidat----------
		double[][] diffaxes = new double[electeurs.length][];
		double[][] diffnormes = new double[candidats.length][];//[Candidat][electeur]
		int i=0;
		for(Candidat candidat: candidats)
		{
			//Calcul des diff�rences
			diffaxes = getDifferencefloat(candidat, electeurs);
			/*for(int k=0;k<diffaxes.length;k++){
				for(int j=0;j<diffaxes[k].length;j++)
				{
					System.out.println("Difference Electeur/Axe " + k +"/"+j+" :"+ diffaxes[k][j]);
				}
			}*/
			//Calcul de la norme
			diffnormes[i] = getNormes(diffaxes);
			i++;
		}
		/*for(int k=0;k<diffaxes.length;k++){
			for(int j=0;j<diffaxes[k].length;j++)
			{
				System.out.println("Difference Electeur/Axe " + k +"/"+j+" :"+ diffaxes[k][j]);
			}
		}*/
		System.out.println("-----------------------------------------------");
		for(int k=0;k<diffnormes.length;k++){
			for(int j=0;j<diffnormes[k].length;j++)
			{
				System.out.println("Norme Candidat/electeur " + k +"/"+j+" :"+ diffnormes[k][j]);
			}
		}
		//----------------------------Choix des �lecteurs-----------------------
		int[] choix_electeurs = new int[electeurs.length];
		double norme_electeur_min = 0;
		for( int i1=0;i1<electeurs.length;i1++ )
		{
			//Choix du meilleur candidat
			norme_electeur_min = diffnormes[0][i1];
			choix_electeurs[i1] = 0;// 0 par d�faut
			for( int j=0;j<candidats.length;j++ )
			{
				if(diffnormes[j][i1] < norme_electeur_min)
				{
					norme_electeur_min = diffnormes[j][i1];
					choix_electeurs[i1] = j;//Mise � jour du choix de l'�lecteur
				}
			}
		}
		
		//Test
		for(int k=0;k<choix_electeurs.length;k++)
		{
			System.out.println("Resultat electeur " + k +" :"+ choix_electeurs[k]);
		}
		
		//Calcul de la normes des candidats
		//float[] normescandidats = getNormes(candidats);
		//Calcul de la normes des �lecteurs
		//float[] normeselecteurs = getNormes(electeurs);
		//Calcul de la diff�rence
		//float[] normesdifference = getDifferenceNorme(normeselecteurs, normescandidats);
		
		
	}

	@Override
	public void sondage(float pourcentpop) {
		// TODO Auto-generated method stub
		
	}

	
}
