package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe définit les rois, mais seulement les déplacements de base.
  * @see ca.uqac.pat.Echec
  */
public class Roi extends Piece implements Cloneable{
	/**
	 * Constructeur standard
	 * @param J le plateau de jeu
	 * @param Coul 1: blanc, -1: noir
	 * @param PX : no colonne
	 * @param PY : no ligne
	 */
	public Roi (Plateau J, int Coul, int PX, int PY){
		super (J, 20, Coul, PX, PY);
	}

/**
  * Il y a ici une validation spécifique pour ne permettre que les mouvements 
  * de 1 case..
  * @param incX Déplacement demandé en X.
  * @param incY Déplacement demandé en Y.
  * @return Vrai si le déplacement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger (int Joueur, int incX, int incY, boolean PourVrai){
		// DEBUT MODIF : tentative de roque - Petit roque
		if (incX == 2 && Math.abs(incY) == 0) {
			// On vérifie que le roi n'a pas déjà bougé et on fait le petit roque en trichant
			//if (!dejaBouge) return petitRoqueEnTrichant(Joueur, incX, incY, PourVrai);
			
			// On vérifie que le roi n'a pas déjà bougé et on fait le petit roque légal
			if (!dejaBouge) return petitRoqueLegal(Joueur, incX, incY, PourVrai);
			else return false;
		}
		
		// Tentative de grand roque
		else if (incX == -2 && Math.abs(incY) == 0) {
			if (!dejaBouge) return grandRoqueLegal(Joueur, incX, incY, PourVrai);
			else return false;
		}
		
		else if (Math.abs(incX) + Math.abs (incY) <= 2 ) {
			if (super.Bouger(Joueur, incX, incY, PourVrai)) {
				// Si le déplacement a eu lieu, on met dejaBouge à true si PourVrai = true
				if (PourVrai) {
					dejaBouge = true;
					return true;	
				}
				
				// On retourne true pour indiquer que le coup est faisable (si PourVrai = false)
				return true;
			}
			else return false;
		}
		
		return false;
		
		// FIN MODIF
	}
	
	// AJOUT : méthode pour le petit roque en trichant
	private boolean petitRoqueEnTrichant(int Joueur, int incX, int incY, boolean PourVrai) {
		// Vérification des deux cases à droite du roi : si elles sont vides...
		if ((Jeu.Case(this.PosX+1, this.PosY).isCaseVide()) && 
			(Jeu.Case(this.PosX+2, this.PosY).isCaseVide())) {
			//... on autorise le petit roque : déplacement du roi
			if (super.Bouger(Joueur, incX, incY, PourVrai)) {
				dejaBouge = true; // si le déplacement est effectué, on met dejaBouge à true
				deplacerTourPourRoque(0, Joueur);
			}			
			return true;
		}
		return false;
	}
	
	
	// AJOUT : méthode pour roquer - Petit roque légal
	private boolean petitRoqueLegal(int Joueur, int incX, int incY, boolean PourVrai) {
		// On vérifie que la tour de droite est à sa place et qu'elle n'a pas déjà bougé
		if (Joueur == 1) // tour blanche	
		{ 		
			if ((Jeu.Case(7, 0).getClass() == Tour.class) && (!Jeu.Case(7, 0).dejaBouge)) 
			{
				// Vérification des deux cases à droite du roi : si elles sont vides...
				if (verifierCasesVides(0))
				{					
					//On vérifie que le roi n'est pas en échec au départ et que les cases du déplacement
					//ne sont pas en échec
					if ((!roiEnEchec(Joueur, this.PosX, this.PosY)) 
						&& (!roiEnEchec(Joueur, this.PosX+1, this.PosY))
						&& (!roiEnEchec(Joueur, this.PosX+2, this.PosY))) 
					{						
						if (super.Bouger(Joueur, incX, incY, true)) 
						{
							dejaBouge = true; // si le déplacement est effectué, on met dejaBouge à true
							
							// déplacement de la tour 
							deplacerTourPourRoque(0, Joueur);
						}
					}
				}
			}
		}
		
		else
		{
			if ((Jeu.Case(7, 7).getClass() == Tour.class) && (!Jeu.Case(7, 7).dejaBouge)) 
			{
				// Vérification des deux cases à droite du roi : si elles sont vides...
				if (verifierCasesVides(0))
				{					
					//On vérifie que le roi n'est pas en échec au départ et que les cases du déplacement
					//ne sont pas en échec
					if (!roiEnEchec(Joueur, this.PosX, this.PosY) 
						&& (!roiEnEchec(Joueur, this.PosX+1, this.PosY))
						&& (!roiEnEchec(Joueur, this.PosX+2, this.PosY))) 
					{						
						if (super.Bouger(Joueur, incX, incY, true)) 
						{
							dejaBouge = true; // si le déplacement est effectué, on met dejaBouge à true
							
							// déplacement de la tour 
							deplacerTourPourRoque(0, Joueur);
						}
					}
				}
			}
		}
		return false;
	}
	
	// AJOUT : méthode pour roquer - Grand roque légal
	private boolean grandRoqueLegal(int Joueur, int incX, int incY, boolean PourVrai) {
		// On vérifie que la tour de gauche est à sa place et qu'elle n'a pas déjà bougé
				if (Joueur == 1) // tour blanche	
				{ 		
					if ((Jeu.Case(0, 0).getClass() == Tour.class) && (!Jeu.Case(0, 0).dejaBouge)) 
					{
						// Vérification des deux cases à droite du roi : si elles sont vides...
						if (verifierCasesVides(1))
						{					
							//On vérifie que le roi n'est pas en échec au départ et que les cases du déplacement
							//ne sont pas en échec
							if (!roiEnEchec(Joueur, this.PosX, this.PosY) 
								&& (!roiEnEchec(Joueur, this.PosX-1, this.PosY))
								&& (!roiEnEchec(Joueur, this.PosX-2, this.PosY))) 
							{						
								if (super.Bouger(Joueur, incX, incY, PourVrai)) 
								{
									dejaBouge = true; // si le déplacement est effectué, on met dejaBouge à true
									
									// déplacement de la tour 
									deplacerTourPourRoque(1, Joueur);
								}
							}
						}
					}
				}
				
				else
				{
					if ((Jeu.Case(0, 0).getClass() == Tour.class) && (!Jeu.Case(0, 0).dejaBouge)) 
					{
						// Vérification des deux cases à droite du roi : si elles sont vides...
						if (verifierCasesVides(1))
						{					
							//On vérifie que le roi n'est pas en échec au départ et que les cases du déplacement
							//ne sont pas en échec
							if (!roiEnEchec(Joueur, this.PosX, this.PosY) 
								&& (!roiEnEchec(Joueur, this.PosX-1, this.PosY)) 
								&& (!roiEnEchec(Joueur, this.PosX-2, this.PosY))) 
							{						
								if (super.Bouger(Joueur, incX, incY, true)) 
								{
									dejaBouge = true; // si le déplacement est effectué, on met dejaBouge à true
									
									// déplacement de la tour 
									deplacerTourPourRoque(1, Joueur);
								}
							}
						}
					}
				}
				return false;
	}

	// AJOUT : méthode pour vérifier les cases vides - Roques
	/**
	  * Méthode de vérification des cases à côté du roi
	  * @param roque Type de roque : 0 pour petit roque / 1 pour grand roque
	  * @return Vrai si les cases sont vides, faux sinon.
	  */
	private boolean verifierCasesVides(int roque) {
		switch(roque) {
			case 0: 
				if ((Jeu.Case(this.PosX+1, this.PosY).isCaseVide()) && 
						(Jeu.Case(this.PosX+2, this.PosY).isCaseVide())) return true;
				else return false;
			
			case 1: 
				if ((Jeu.Case(this.PosX-1, this.PosY).isCaseVide()) && 
						(Jeu.Case(this.PosX-2, this.PosY).isCaseVide()) &&
						(Jeu.Case(this.PosX-3, this.PosY).isCaseVide())) return true;
				else return false;
				
			default : return false;
		}
	}
	
	
	// AJOUT : déplacement de la tour - Roques
	/**
	  * Méthode de déplacement des tours
	  * @param roque Type de roque : 0 pour petit roque / 1 pour grand roque
	  * @param Joueur 1 pour les blancs, 0 pour les noirs
	  * @return Vrai si les cases sont vides, faux sinon.
	  */
	public void deplacerTourPourRoque(int roque, int Joueur) {
		switch(roque) {
			case 0:
				if (Joueur == 1) { //tour blanche
					Jeu.setPos(5, 0, Jeu.Case(7, 0));
					Jeu.setPos(7, 0, new CaseVide(Jeu, 7, 0));				
				}
				
				else { // tour noire
					Jeu.setPos(5, 7, Jeu.Case(7,7));
					Jeu.setPos(7, 7, new CaseVide(Jeu, 7, 7));				
				}
			break;
			
			case 1:
				if (Joueur == 1) { //tour blanche
					Jeu.setPos(3, 0, Jeu.Case(0, 0));
					Jeu.setPos(0, 0, new CaseVide(Jeu, 0, 0));
				}
				
				else { // tour noire
					Jeu.setPos(3, 7, Jeu.Case(0, 7));
					Jeu.setPos(0, 7, new CaseVide(Jeu, 0, 7));
				}
			break;
		}
	}
	
	
	// AJOUT : méthode pour vérifier des échecs possibles
	public boolean roiEnEchec(int joueur, int posRoiX, int posRoiY) {
		// On clone le plateau		
		Plateau clonePlateau = (Plateau)Jeu.clone();
		// On change le joueur pour simuler les actions adverses
		clonePlateau.inverserJoueur();
		
		for (int i=0; i < 8; i++) {
			for (int j=0; j < 8 ; j++) {
				if (clonePlateau.Case(i, j).retCoulBlanc() != joueur) { 
					if (clonePlateau.Bouger(clonePlateau.Case(i, j).PosX, clonePlateau.Case(i, j).PosY, 
							posRoiX, posRoiY, false)) return true;
				}
			}
		}		
		return false;
	}
	
	
	public String toString()	{ return (retCoulBlanc() == 1 ? "R" : "r");	}
	public Object clone() 		{ return super.clone();	}
}