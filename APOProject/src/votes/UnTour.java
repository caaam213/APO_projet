package votes;

import java.util.HashMap;

import parametres.Candidat;
import parametres.Electeur;

public class UnTour extends Scrutin{

	public UnTour(Candidat[] candidats, Electeur[] electeurs) {
		super(candidats, electeurs);
	}

	@Override
	public void simulation()
	{
		scrutinUnTour(candidats,electeurs);
	}

	@Override
	public void sondage(double pourcentElecteurs) 
	{
		scrutinUnTour(candidats, recupElecteurAlea(pourcentElecteurs));
	}
	
	public HashMap<Candidat,Double> scrutinUnTour(Candidat[] candidats, Electeur[] electeurs) {
		//R�cup�ration des normes repr�sentant le rapprochement avec un candidat
		double[][] diffnormes = CalculDiffNormes(candidats,electeurs);
		//Choix des �lecteurs
		int[] choix_electeurs = choixElecteurs(electeurs, diffnormes);
		//R�sultat par candidat
		HashMap<Candidat,Double> resultat = new HashMap<Candidat,Double>();
		
		int nombre_votecandidat;
		for(int i=0;i<candidats.length;i++)
		{
			nombre_votecandidat = 0;
			for(int choix:choix_electeurs)
			{
				if(choix == i)
				{
					nombre_votecandidat++;
				}
			}
			resultat.put(candidats[i],  ((double)nombre_votecandidat/electeurs.length));
		}
		
	
		//Test
		for(Candidat candidat: candidats)
		{
			System.out.println( candidat.getNomPrenom() + " :"+ resultat.get(candidat)*100+"%");
		}
		
		return resultat;
		
	}
	
	public int[] choixElecteurs( Electeur[] electeurs, double[][] diffnormes )
	{
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
		
		return choix_electeurs;
	}


	
}

//-----ARCHIVES CODE

//-------------R�cup�ration des normes repr�sentant le rapprochement avec un candidat----------
/*double[][] diffaxes = new double[electeurs.length][];
double[][] diffnormes = new double[candidats.length][];//[Candidat][electeur]
int i=0;
for(Candidat candidat: candidats)
{
	//Calcul des diff�rences
	diffaxes = CalculDifferenceAxes(candidat, electeurs);
	//Calcul de la norme
	diffnormes[i] = getNormes(diffaxes);
	i++;
}

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
*/





















