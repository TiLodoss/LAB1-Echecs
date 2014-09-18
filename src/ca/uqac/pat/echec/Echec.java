package ca.uqac.pat.echec;

import ca.uqac.pat.echec.Plateau;
import ca.uqac.pat.echec.ai.COrdi;

/**
  * Cette classe n'a qu'un main qui permet de tester la classe Plateau.
  */
public class Echec{
	public static void main (String Arg[]){
		Plateau P = new Plateau();		
		COrdi Ordi = new COrdi (P);

		while (true){
			char initialX = '\0';
			char initialY = '\0';

			char finalX = '\0';
			char finalY = '\0';
			do{
				P.Afficher();
				
				System.out.print ("Votre coup? ");				
				initialX = Lire();
				initialY = Lire();

				finalX = Lire();
				finalY = Lire();
				
				ViderBuffer();
			}

			while(!P.Bouger(	initialX-'a', 	initialY-'1', 
								finalX - 'a', 	finalY-'1', true));
			Ordi.Bouger();				
		}
	}

	private static char Lire(){		//Static parqu'utilisé par le main
		char C = 'A';				//Initialisation est obligatoire
		boolean OK;
		do{
			OK = true;				//On commence à OK à chaque boucle
			try{					//Préviens qu'il peut y avoir une exception
				C = (char)System.in.read();
			}

			catch (java.io.IOException e){		//Attrape l'exception
			
				OK = false;	//S'il y a une erreur, possible boucle sans fin
			}
		}
		while (!OK);			//S'il y a eu une erreur,on recommence
		return C;				//On retourne la lettre 
	}
	
	
	private static void ViderBuffer(){
		try{
			while (System.in.read() != '\n');
		}
		catch (java.io.IOException e){		//Attrape l'exception
			//S'exécute s'il y a une erreur Rien
		}
	}
}