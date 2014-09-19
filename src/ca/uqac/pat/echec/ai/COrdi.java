package ca.uqac.pat.echec.ai;

import java.util.Random;
import java.util.Vector;


import ca.uqac.pat.echec.Plateau;

public class COrdi {
	private Random Des = new Random(0);
	private Plateau Jeu;
	private Vector<IIntelChess> AI;
	
	public COrdi(Plateau jeu) {
		Jeu = jeu;
		AI	= getIntelligence();
	}
	
	public void Bouger (){
		int iniX=-1, iniY=-1, finX=-1, finY=-1;
		
		int Somme  = -1;			// initialisation bidon
		do{
			Plateau Copie = (Plateau)Jeu.clone();
			do{
				iniX = Des.nextInt(8); 
				iniY = Des.nextInt(8);
				finX = Des.nextInt(8);
				finY = Des.nextInt(8);
			}
			while ( ! Copie.Bouger (iniX, iniY, finX, finY, false));
			
			System.out.print(".");
			
			Somme = 0;
			for (int y = 0; y < AI.size(); y++)
				Somme += AI.get(y).getPoids(Copie);	
		}
		while (Somme < 0);
		
		
		System.out.println	();
		System.out.print ("Mon coup: ");
		System.out.print	("" +	(char)(iniX + 'a') + (char)(iniY +'1'));
		System.out.println	("-"+	(char)(finX + 'a') + (char)(finY +'1'));
		
		Jeu.Bouger (iniX, iniY, finX, finY, true);
	}
	

	protected Vector<IIntelChess> getIntelligence (){
		Vector<IIntelChess> Ret = new Vector<IIntelChess>();
		Ret.add (new AttaqueReine ());
//		Ret.add (new AttaqueReine ());
//		Ret.add (new AttaqueReine ());
//		Ret.add (new AttaqueReine ());
		return Ret;
	}
}
