import parametres.*;
import votes.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import Utilites.AnalyseScrutin;
import Utilites.SaveExcel;

//TODO : Ajouter la gestion des égalité et la verification de l'existence d'un sondage pour les évolutions
//TODO : Afficher le ou les gagnants
//TODO : Gérer les abstentions
//TODO : Gérer la spacialisation
public class main {

	public static int saisirNbAxes() {
		int nbAxes;
		Scanner scAxes = new Scanner(System.in);
		try {
			System.out.println("Veuillez saisir le nombre d'axes.");
			nbAxes = scAxes.nextInt();
			if (nbAxes < 0) {
				System.out.println("Veuillez saisir un nombre positif");
				nbAxes = saisirNbAxes();
			}
		} catch (Exception e) {
			System.out.println("Veuillez saisir un entier");
			nbAxes = saisirNbAxes();
		}
		return nbAxes;
	}

	public static Axe[] axesParametrages() {

		Axe[] axes = new Axe[saisirNbAxes()];
		Scanner scAxes = new Scanner(System.in);
		int i = 0;
		String nomAxe;
		while (i != axes.length) {
			nomAxe = "";
			while (nomAxe.equals("") == true) {
				System.out.println("Veuillez saisir le nom de l'axe n°" + (i + 1));

				nomAxe = scAxes.nextLine();
			}
			Axe axe = new Axe(nomAxe);
			axes[i] = axe;
			i++;
		}

		return axes;
	}

	public static double[] genererAxesValeursAlea(Axe[] axes) { 
		double[] valAxes = new double[axes.length];
		Random rand = new Random();
		for (int i = 0; i < valAxes.length; i++) {
			valAxes[i] = rand.nextDouble();
		}
		return valAxes;
	}

	public static double[] saisirValeursAxes(Axe[] axes) {
		double[] valAxes = new double[axes.length];
		Random rand = new Random();
		Scanner valScanner = new Scanner(System.in);
		valScanner.useLocale(Locale.US); // Pour pouvoir écrire des doubles avec des points au lieu de virgules
		for (int i = 0; i < valAxes.length; i++) {
			valAxes[i] = -1;

			while (valAxes[i] < 0 || valAxes[i] > 1) {
				System.out.println("Saisir une valeur comprise entre 0 et 1 pour l'axe suivant : " + axes[i].getNom());

				try {
					if (valScanner.hasNextLine()) {
						valAxes[i] = valScanner.nextDouble();
					}
				} catch (Exception e) {
					System.out.println("Valeurs incohérentes. Vous allez devoir tout ressaisir...");
					valAxes = saisirValeursAxes(axes);
				}
			}

		}

		return valAxes;
	}

	public static Candidat[] candidatsParametrage(Axe[] axes) {
		int nbCandidats;
		Scanner scCandidats = new Scanner(System.in);
		do {
			System.out.println("Veuillez saisir le nombre de Candidats");
			nbCandidats = scCandidats.nextInt();
		} while (nbCandidats <= 0);

		Candidat[] candidats = new Candidat[nbCandidats];
		String nomprenom;
		System.out.println("REMPLISSAGE DES AXES");
		scCandidats.nextLine();
		int choix = faireChoix("Tapez 0 pour saisir la valeur des axes ou 1 pour la générer aléatoirement");
		if (choix == 0) {
			for (int j = 0; j < nbCandidats; j++) {
				do {
					System.out.print("Veuillez saisir le nom et prénom pour le candidat n° " + (j + 1) + " : ");
					nomprenom = scCandidats.nextLine();
					System.out.println();

				} while (nomprenom == "");
				double[] valAxes = saisirValeursAxes(axes);
				candidats[j] = new Candidat(axes, valAxes, nomprenom);
			}
		} else {
			for (int j = 0; j < nbCandidats; j++) {
				scCandidats = new Scanner(System.in);

				do {
					System.out.print("Veuillez saisir le nom et prénom pour le candidat n°: " + (j + 1));
					nomprenom = scCandidats.nextLine();
					System.out.println();

				} while (nomprenom == "");
				double[] valAxes = genererAxesValeursAlea(axes);
				candidats[j] = new Candidat(axes, valAxes, nomprenom);
			}
		}

		return candidats;
	}

	public static Electeur[] parametrageElecteurs(Axe[] axes) {
		int nbElecteurs;
		Scanner scElecteurs = new Scanner(System.in);
		do {
			System.out.println("Veuillez saisir le nombre d'électeurs");
			nbElecteurs = scElecteurs.nextInt();
		} while (nbElecteurs <= 0);
		Electeur[] electeurs = new Electeur[nbElecteurs];

		for (int j = 0; j < nbElecteurs; j++) {
			scElecteurs = new Scanner(System.in);
			double[] valAxes = genererAxesValeursAlea(axes);
			electeurs[j] = new Electeur(axes, valAxes);
		}
		return electeurs;

	}

	public static int faireChoix(String instruction) {

		Scanner choixScanner = new Scanner(System.in);
		int choix;
		try {
			do {
				System.out.println(instruction);
				choix = choixScanner.nextInt();
			} while (choix != 0 && choix != 1);
		} catch (Exception e) {
			choix = 0;
			System.out.println("Veuillez saisir une valeur correcte");
			faireChoix(instruction);
		}
		return choix;
	}

	public static HashMap<Electeur, Integer> electeursNchoisisPourApprobation(Candidat[] candidats,
			Electeur[] electeurs) {
		HashMap<Electeur, Integer> nChoisis = new HashMap<Electeur, Integer>();
		int N = candidats.length;
		int n = 0;
		Random rand = new Random();
		for (Electeur electeur : electeurs) {
			n = rand.nextInt(N) + 1;
			nChoisis.put(electeur, n);
		}
		return nChoisis;
	}

	public static Scrutin choixScrutin(Candidat[] candidats, Electeur[] electeurs) {
		System.out.println("Etape 4 : Choix du mode de scrutin");
		System.out.println("1 - Un tour");
		System.out.println("2 - Deux tours");
		System.out.println("3 - Approbation");
		System.out.println("4 - Alternatif ");
		System.out.println("5 - Borda ");
		System.out.print("Votre choix : ");
		Scanner choixScanner = new Scanner(System.in);
		Scrutin scrutin;
		HashMap<Electeur, Integer> nChoisis;
		int choix = 0;
		try {
			do {
				choix = choixScanner.nextInt();
			} while (choix <= 0 || choix > 5);

		} catch (Exception e) {
			System.out.println("Valeur saisie incorrecte...");
			scrutin = choixScrutin(candidats, electeurs);
		}

		switch (choix) {

		case 1:
			return new UnTour(candidats, electeurs);

		case 2:
			return new DeuxTours(candidats, electeurs);

		case 3:
			nChoisis = electeursNchoisisPourApprobation(candidats, electeurs);
			return new Approbation(candidats, nChoisis);
		case 4:
			return new Alternatif(candidats, electeurs); 
		case 5:
			return new Borda(candidats, electeurs);
		default:
			System.out.println("Choix incorrect");
			break;
		}
		return null;

	}

	public static int menu() {
		int m = 0;
		Scanner choix = new Scanner(System.in);
		System.out.println("Menu");
		System.out.println("1 - Réaliser un sondage");
		System.out.println("2 - Evoluer des opinions");
		System.out.println("3 - Simuler une élection");
		System.out.println("4 - Analyser les modes de scrutins");
		System.out.println("5 - Consulter la liste des électeurs ");
		System.out.println("6 - Consulter la liste des candidats ");
		System.out.println("7 - Consulter la liste des axes ");
		System.out.println("8 - Sauvegarder les résultats ");
		System.out.println("9 - Sauvegarder les paramètres dans le fichier de configuration ");
		System.out.println("10  - Quitter l'application");
		System.out.print("Votre choix : ");
		try {
			while (m <= 0 || m > 10) {

				System.out.println("Saisir un nombre entre 1 et 10 : ");
				m = choix.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Veuillez saisir un nombre entier");
			menu();
		}
		return m;

	}

	public static Scrutin realiserSondage(Scrutin scrutin) {
		Scanner pourcentageScanner = new Scanner(System.in);
		pourcentageScanner.useLocale(Locale.US);
		double pourcentage = -1;
		try {
			while (pourcentage < 0 || pourcentage >= 100) {
				System.out.println("Quel est le pourcentage de la population que vous souhaitez " + "interroger :  ");
				if (pourcentageScanner.hasNextLine()) {
					pourcentage = pourcentageScanner.nextDouble();
				}
			}
			
		} catch (Exception e) {
			System.out.println("Veuillez saisir un pourcentage valide");
			realiserSondage(scrutin);
		}
		scrutin.sondage(pourcentage);
		return scrutin;
	}

	public static Scrutin evoluerOpinions(Scrutin scrutin) {
		int m = 0;
		int N = scrutin.getCandidats().length;
		
		System.out.println("Evoluer les opinions : ");
		System.out.println("1 - Evoluer par discussions : ");
		System.out.println("2 - Evoluer par idées : ");
		System.out.println("3 - Evoluer par côte :  ");
		System.out.println("4 - Evoluer par moyenne :  ");
		
		if (scrutin.getResultatSondage().size() == 0) {
			System.out.println("Aucun sondage n'a été fait, un sondage va être réalisé");
			scrutin = realiserSondage(scrutin);
		}
		
		Scanner choix = new Scanner(System.in);
		try {
			while (m <= 0 || m > 4) {
				System.out.println("Choix : ");
				m = choix.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Veuillez saisir un nombre entier");
			m = choix.nextInt();
		}
		
		switch (m) {
		case 1:
			scrutin.evoluerToutesLesOpinionsParDiscussion(false);
			return scrutin;
		case 2:
			int i = 0;
			Scanner choix2 = new Scanner(System.in);
			try {
				while (i <= 0 || i >= scrutin.getCandidats().length) {
					System.out.println(
							"Veuillez saisir le nombre des premiers candidats que vous voulez entre 1 et " + N);
					i = choix.nextInt();
				}
			} catch (Exception e) {
				System.out.println("Veuillez saisir un nombre ");
				m = choix.nextInt();
			}
			scrutin.evoluerToutesLesOpinionsParIdees(N);
			return scrutin;
		case 3:
			scrutin.evoluerToutesLesOpinionsParCote();
			return scrutin;
		case 4:
			scrutin.evoluerToutesLesOpinionsParMoyenne();
			return scrutin;
		default:
			return scrutin;
		}

	}

	public static void afficherResultat(LinkedHashMap<Candidat, Double> resultat) {
		List<Candidat> candidats = new ArrayList<>(resultat.keySet());
		List<Double> pourcentages = new ArrayList<>(resultat.values());
		
		
		
		if (pourcentages.get(0) == pourcentages.get(1)) {
			System.out.println("Egalité entre ces candidats");
			int i = 0;
			while (pourcentages.get(0) == pourcentages.get(i)) {
				System.out.println(candidats.get(i) + " avec " + (pourcentages.get(i) * 100) + " %.");
				i++;
			}
			System.out.println("Candidats perdants");
			while (i < pourcentages.size()) {
				System.out.println(candidats.get(i) + " avec " + (pourcentages.get(i) * 100) + " %.");
				i++;
			}
		} else {
			System.out.println("Candidat gagnant : ");
			System.out.println(candidats.get(0) + " avec " + (pourcentages.get(0) * 100) + " %.");
			System.out.println("Candidat défavorable : ");
			for (int i = 1; i < pourcentages.size(); i++) {
				System.out.println(i + " - " + candidats.get(i) + " avec " + (pourcentages.get(i) * 100) + " %.");
			}
		}
	}
	
	public static void saveResultatExcel(Scrutin scrutin) {
		LinkedHashMap<Candidat, Double> resultat = scrutin.getResultatScrutin();
		if(resultat == null || resultat.size() == 0)
		{
			System.out.println("Aucun simulation n'a été faîtes!");
			return;
		}
		

		//Date aujourdhui = SystemClockFactory.getDatetime();
		List<Candidat> candidats = new ArrayList<>(resultat.keySet());
		List<Double> pourcentages = new ArrayList<>(resultat.values());
		String filename = System.getProperty("user.home") + "/Desktop/Data.xls";
		SaveExcel s = new SaveExcel();
		DecimalFormat df = new DecimalFormat("0.00"); // import java.text.DecimalFormat;

		Date aujourdhui = new Date();
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yy hh:mm aaa");
		
		double search_max = 0;
		for(Candidat candidat: scrutin.getCandidats())
		{
			if( scrutin.getResultatScrutin().get(candidat) != null)
			{
				if(scrutin.getResultatScrutin().get(candidat) > search_max )
				{
					search_max = scrutin.getResultatScrutin().get(candidat);
				}
			}
		}
		
		for(Candidat candidat: scrutin.getCandidats())
		{
			if( scrutin.getResultatScrutin().get(candidat) != null)
			{
				if( scrutin.getResultatScrutin().get(candidat) == search_max  )
				{
					s.AddRowFile(filename, formater.format(aujourdhui), scrutin.getTypeScrutin(), candidat.getNomPrenom().toString() , String.valueOf((int)(scrutin.getElecteurs().length * scrutin.getResultatScrutin().get(candidat) )),(df.format(scrutin.getResultatScrutin().get(candidat) * 100)) + "%", "Vainqueur");
				}
				else
				{
					s.AddRowFile(filename, formater.format(aujourdhui), scrutin.getTypeScrutin(), candidat.getNomPrenom().toString() , String.valueOf((int)(scrutin.getElecteurs().length * scrutin.getResultatScrutin().get(candidat) )),(df.format(scrutin.getResultatScrutin().get(candidat) * 100)) + "%", "Défaite");
				}
			}
		}
		/*
		if (pourcentages.get(0) == pourcentages.get(1)) {
			//Egalité entre ces candidats
			int i = 0;
			while (pourcentages.get(0) == pourcentages.get(i)) {
				s.AddRowFile(filename, formater.format(aujourdhui), scrutin.getTypeScrutin(), candidats.get(0).getNomPrenom().toString() , String.valueOf((int)(scrutin.getElecteurs().length * pourcentages.get(0))),(df.format(pourcentages.get(0) * 100)) + "%", "Egalité");
				i++;
			}
			//Candidats perdants
			while (i < pourcentages.size()) {
				s.AddRowFile(filename, formater.format(aujourdhui), scrutin.getTypeScrutin(), candidats.get(i).getNomPrenom().toString() , String.valueOf((int)(scrutin.getElecteurs().length * pourcentages.get(i))),(df.format(pourcentages.get(i) * 100)) + "%", "Défaite");
				i++;
			}
		} else {
			//Candidat gagnant
			s.AddRowFile(filename, formater.format(aujourdhui), scrutin.getTypeScrutin(), candidats.get(0).getNomPrenom().toString() , String.valueOf((int)(scrutin.getElecteurs().length * pourcentages.get(0))),(df.format(pourcentages.get(0) * 100)) + "%", "Victoire");
			
			//Candidat défavorable
			for (int i = 1; i < pourcentages.size(); i++) {
				s.AddRowFile(filename,formater.format(aujourdhui), scrutin.getTypeScrutin(), candidats.get(i).getNomPrenom().toString() , String.valueOf( (int)(scrutin.getElecteurs().length * pourcentages.get(i))),(df.format(pourcentages.get(i) * 100)) + "%", "Défaite");
			}
		}*/

	}

	public static boolean quitterApplication() {
		Scanner sc = new Scanner(System.in);
		int c = -1;
		boolean choix;
		try {
			while (c != 1 && c != 0) {

				System.out.println("Voulez vous quittez l'application ? Oui : 1, Non : 0");
				c = sc.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Veuillez saisir un nombre entier");
			quitterApplication();
		}
		if (c == 1) {
			return true;
		}
		return false;

	}

	public static void lancerApplication(Axe[] axes, Candidat[] candidats, Electeur[] electeurs, Scrutin scrutin) {
		LinkedHashMap<Candidat, Double> resultatScrutin = new LinkedHashMap<Candidat, Double>();
		LinkedHashMap<Candidat, Double> resultatSondage = new LinkedHashMap<Candidat, Double>();
		int choixMenu;

		boolean quitterApplication = false;

		while (!quitterApplication) {
			choixMenu = menu();
			switch (choixMenu) {
			case 1:
				scrutin = realiserSondage(scrutin);
				resultatSondage = scrutin.getResultatSondage();
				System.out.println("Sondage fait !");
				quitterApplication = quitterApplication();
				break;
			case 2:
				scrutin = evoluerOpinions(scrutin);
				resultatSondage = realiserSondage(scrutin).getResultatSondage();
				electeurs = scrutin.getElecteurs();
				quitterApplication = quitterApplication();
				break;
			case 3:
				scrutin.simulation();
				resultatScrutin = scrutin.getResultatScrutin();
				quitterApplication = quitterApplication();
				break;
			case 4:
				scrutin = evoluerOpinions(scrutin);
				quitterApplication = quitterApplication();
				break;
			case 5:
				for (int i1 = 0; i1 < electeurs.length; i1++) {
					System.out.println(electeurs[i1].toString());
				}
				quitterApplication = quitterApplication();
				break;
			case 6:
				for (int i1 = 0; i1 < candidats.length; i1++) {
					System.out.println(candidats[i1].toString());
				}
				quitterApplication = quitterApplication();
				break;
			case 7:
				for (int i1 = 0; i1 < axes.length; i1++) {
					System.out.println("Axe : " + axes[i1].getNom());
				}
				quitterApplication = quitterApplication();
				break;
			case 8:
				saveResultatExcel(scrutin);
				quitterApplication = quitterApplication();
				break;
			case 9:
				quitterApplication = true;
				break;
			default:
				System.out.println("Choix incorrect");
				break;
			}

		}
	}

	public static void main(String[] args) {

		// -----------------Configuration-----------------
		Axe[] axes;
		Candidat[] candidats;
		Electeur[] electeurs;
		Scrutin scrutin;
		int choixMenu;
		
		/*System.out.println("Création file");
		SaveExcel se = new SaveExcel();
		String filename = "C:\\Users\\nabil\\Desktop\\NewExcelFile.xls";
		se.AddRowFile(filename, "UnTour", "Eric Zemmour", "75", "0.19", "");*/

		
		// ----------------Introduction--------------------
		System.out.println("BIENVENUE DANS NOTRE SIMULATION D'ELECTION");

		int choix = faireChoix("Voulez-vous récupérer les paramètres déjà stockés dans le fichier de configuration ? "
				+ "Tapez 1 pour oui et 0 pour non.");
		if (choix == 0) {
			// ----------------Partie Axes--------------------
			System.out.println("1ère étape : Paramétrage des axes");
			axes = axesParametrages();

			// ----------------Partie Candidat--------------------
			System.out.println("2ème étape : Paramétrage des candidats : ");
			candidats = candidatsParametrage(axes);

			// ---------------Partie Electeur----------------
			System.out.println("3ème étape : Paramétrage des électeurs : ");
			electeurs = parametrageElecteurs(axes);

		} else {
			axes = axesParametrages();
			candidats = candidatsParametrage(axes);
			electeurs = parametrageElecteurs(axes);
		}
		scrutin = choixScrutin(candidats, electeurs);
		lancerApplication(axes, candidats, electeurs, scrutin);

		System.out.println("Fin de l'application.");
	}

}
