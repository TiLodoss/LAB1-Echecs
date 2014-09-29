package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe d�finit les rois, mais seulement les d�placements de base.
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
  * Il y a ici une validation sp�cifique pour ne permettre que les mouvements 
  * de 1 case..
  * @param incX D�placement demand� en X.
  * @param incY D�placement demand� en Y.
  * @return Vrai si le d�placement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger (int Joueur, int incX, int incY, boolean PourVrai){
		// DEBUT MODIF : tentative de roque - Petit roque
		if (incX == 2 && Math.abs(incY) == 0) {
			// On v�rifie que le roi n'a pas d�j� boug� et on fait le petit roque en trichant
			//if (!dejaBouge) return petitRoqueEnTrichant(Joueur, incX, incY, PourVrai);
			
			// On v�rifie que le roi n'a pas d�j� boug� et on fait le petit roque l�gal
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
				// Si le d�placement a eu lieu, on met dejaBouge � true si PourVrai = true
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
	
	// AJOUT : m�thode pour le petit roque en trichant
	private boolean petitRoqueEnTrichant(int Joueur, int incX, int incY, boolean PourVrai) {
		// V�rification des deux cases � droite du roi : si elles sont vides...
		if ((Jeu.Case(this.PosX+1, this.PosY).isCaseVide()) && 
			(Jeu.Case(this.PosX+2, this.PosY).isCaseVide())) {
			//... on autorise le petit roque : d�placement du roi
			if (super.Bouger(Joueur, incX, incY, PourVrai)) {
				dejaBouge = true; // si le d�placement est effectu�, on met dejaBouge � true
				deplacerTourPourRoque(0, Joueur);
			}			
			return true;
		}
		return false;
	}
	
	
	// AJOUT : m�thode pour roquer - Petit roque l�gal
	private boolean petitRoqueLegal(int Joueur, int incX, int incY, boolean PourVrai) {
		// On v�rifie que la tour de droite est � sa place et qu'elle n'a pas d�j� boug�
		if (Joueur == 1) // tour blanche	
		{ 		
			if ((Jeu.Case(7, 0).getClass() == Tour.class) && (!Jeu.Case(7, 0).dejaBouge)) 
			{
				// V�rification des deux cases � droite du roi : si elles sont vides...
				if (verifierCasesVides(0))
				{					
					//On v�rifie que le roi n'est pas en �chec au d�part et que les cases du d�placement
					//ne sont pas en �chec
					if ((!roiEnEchec(Joueur, this.PosX, this.PosY)) 
						&& (!roiEnEchec(Joueur, this.PosX+1, this.PosY))
						&& (!roiEnEchec(Joueur, this.PosX+2, this.PosY))) 
					{						
						if (super.Bouger(Joueur, incX, incY, true)) 
						{
							dejaBouge = true; // si le d�placement est effectu�, on met dejaBouge � true
							
							// d�placement de la tour 
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
				// V�rification des deux cases � droite du roi : si elles sont vides...
				if (verifierCasesVides(0))
				{					
					//On v�rifie que le roi n'est pas en �chec au d�part et que les cases du d�placement
					//ne sont pas en �chec
					if (!roiEnEchec(Joueur, this.PosX, this.PosY) 
						&& (!roiEnEchec(Joueur, this.PosX+1, this.PosY))
						&& (!roiEnEchec(Joueur, this.PosX+2, this.PosY))) 
					{						
						if (super.Bouger(Joueur, incX, incY, true)) 
						{
							dejaBouge = true; // si le d�placement est effectu�, on met dejaBouge � true
							
							// d�placement de la tour 
							deplacerTourPourRoque(0, Joueur);
						}
					}
				}
			}
		}
		return false;
	}
	
	// AJOUT : m�thode pour roquer - Grand roque l�gal
	private boolean grandRoqueLegal(int Joueur, int incX, int incY, boolean PourVrai) {
		// On v�rifie que la tour de gauche est � sa place et qu'elle n'a pas d�j� boug�
				if (Joueur == 1) // tour blanche	
				{ 		
					if ((Jeu.Case(0, 0).getClass() == Tour.class) && (!Jeu.Case(0, 0).dejaBouge)) 
					{
						// V�rification des deux cases � droite du roi : si elles sont vides...
						if (verifierCasesVides(1))
						{					
							//On v�rifie que le roi n'est pas en �chec au d�part et que les cases du d�placement
							//ne sont pas en �chec
							if (!roiEnEchec(Joueur, this.PosX, this.PosY) 
								&& (!roiEnEchec(Joueur, this.PosX-1, this.PosY))
								&& (!roiEnEchec(Joueur, this.PosX-2, this.PosY))) 
							{						
								if (super.Bouger(Joueur, incX, incY, PourVrai)) 
								{
									dejaBouge = true; // si le d�placement est effectu�, on met dejaBouge � true
									
									// d�placement de la tour 
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
						// V�rification des deux cases � droite du roi : si elles sont vides...
						if (verifierCasesVides(1))
						{					
							//On v�rifie que le roi n'est pas en �chec au d�part et que les cases du d�placement
							//ne sont pas en �chec
							if (!roiEnEchec(Joueur, this.PosX, this.PosY) 
								&& (!roiEnEchec(Joueur, this.PosX-1, this.PosY)) 
								&& (!roiEnEchec(Joueur, this.PosX-2, this.PosY))) 
							{						
								if (super.Bouger(Joueur, incX, incY, true)) 
								{
									dejaBouge = true; // si le d�placement est effectu�, on met dejaBouge � true
									
									// d�placement de la tour 
									deplacerTourPourRoque(1, Joueur);
								}
							}
						}
					}
				}
				return false;
	}

	// AJOUT : m�thode pour v�rifier les cases vides - Roques
	/**
	  * M�thode de v�rification des cases � c�t� du roi
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
	
	
	// AJOUT : d�placement de la tour - Roques
	/**
	  * M�thode de d�placement des tours
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
	
	
	// AJOUT : m�thode pour v�rifier des �checs possibles
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