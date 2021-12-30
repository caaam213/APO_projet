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
		//------------Choix des électeurs en fonction du pourcentage inséré-----------
		
		//Nombre de personnes à qui on fera le Sondage
		int nb_sondage = (int) (pourcentElecteurs*electeurs.length);
		Electeur[] leselecteurs = new Electeur[nb_sondage];
		//Variable pour la sélection aléatoire
		int id_electeur = 0;
		//Boucle pour remplir le tableau electeurs
		int i=0;
		boolean b;
		while(leselecteurs[nb_sondage-1] == null)
		{
			b = true;
			double alea = Math.random();
			if(alea != 1)//Empecher que id_electeur depassa la limite du tableau
			{
				id_electeur = (int) ((electeurs.length)*alea);
			}
			//Empecher les doublons
			for(Electeur electeur: leselecteurs)
			{
				if(electeur != null)
				{
					if(electeur.getIdElecteur() == id_electeur)
					{
						b = false;
					}
				}
			}
			if(b == true)
			{
				leselecteurs[i] = electeurs[id_electeur];
				i++;
			}
		}
		
		scrutinUnTour(candidats, leselecteurs);
	}
	
	public HashMap<Candidat,Double> scrutinUnTour(Candidat[] candidats, Electeur[] electeurs) {
		//Récupération des normes représentant le rapprochement avec un candidat
		double[][] diffnormes = CalculDiffNormes(candidats,electeurs);
		//Choix des électeurs
		int[] choix_electeurs = choixElecteurs(electeurs, diffnormes);
		//Résultat par candidat
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
			choix_electeurs[i1] = 0;// 0 par défaut
			for( int j=0;j<candidats.length;j++ )
			{
				if(diffnormes[j][i1] < norme_electeur_min)
				{
					norme_electeur_min = diffnormes[j][i1];
					choix_electeurs[i1] = j;//Mise à jour du choix de l'électeur
				}
			}
		}
		
		return choix_electeurs;
	}


	
}

//-----ARCHIVES CODE

//-------------Récupération des normes représentant le rapprochement avec un candidat----------
/*double[][] diffaxes = new double[electeurs.length][];
double[][] diffnormes = new double[candidats.length][];//[Candidat][electeur]
int i=0;
for(Candidat candidat: candidats)
{
	//Calcul des différences
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
	choix_electeurs[i1] = 0;// 0 par défaut
	for( int j=0;j<candidats.length;j++ )
	{
		if(diffnormes[j][i1] < norme_electeur_min)
		{
			norme_electeur_min = diffnormes[j][i1];
			choix_electeurs[i1] = j;//Mise à jour du choix de l'électeur
		}
	}
}
*/





















