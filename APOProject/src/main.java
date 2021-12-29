import parametres.*;
import votes.*;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		System.out.println("Paramétrage des axes");
		int nbAxes;
		Scanner scAxes = new Scanner( System.in );
		do
		{
			
			System.out.println("Veuillez saisir le nombre d'axes");
			nbAxes = scAxes.nextInt();
		}
		while(nbAxes<=0);
		
		Axe[] axes = new Axe[nbAxes];
		
		int i = 0;
		while(i!=nbAxes)
		{
			String nomAxe;
			System.out.println("Veuillez saisir le nom de l'axe");
			do
			{
				nomAxe = scAxes.nextLine();
			}
			while(nomAxe=="");
			Axe axe = new Axe(nomAxe);
			axes[i] = axe;
			i++;
		}
		
		
		for(int i1 = 0;i1<axes.length;i1++)
		{
			System.out.println(axes[i1].toString());
		}
		
		System.out.println("Paramétrage des électeurs");
		int nbElecteurs;
		Scanner scElecteurs = new Scanner( System.in );
		do
		{
			
			System.out.println("Veuillez saisir le nombre d'électeurs");
			nbElecteurs = scElecteurs.nextInt();
		}
		while(nbElecteurs<=0);
		i = 0;
		Electeur[] electeurs = new Electeur[nbElecteurs];
		
		while(i!=nbElecteurs)
		{
			Electeur electeur = new Electeur(axes,"");
			electeurs[i] = electeur;
			i++;
		}
		for(int i1 = 0;i1<electeurs.length;i1++)
		{
			System.out.println(electeurs[i1].toString());
		}
	}

}
