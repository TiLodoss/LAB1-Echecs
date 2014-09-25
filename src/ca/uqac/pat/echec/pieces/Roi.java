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
		if (Math.abs(incX) == 2 && Math.abs(incY) == 0) {
			// On vérifie que le roi n'a pas déjà bougé
			if (!dejaBouge) return petitRoqueEnTrichant(Joueur, incX, incY, PourVrai);
			else return false;
		}
				
		else if (Math.abs(incX) + Math.abs (incY) <= 2 ) {
			dejaBouge = true;
			return super.Bouger(Joueur, incX, incY, PourVrai);
		}
		
		
		
		else return false;
	}
	
	// AJOUT : méthode pour le petit roque en trichant
	private boolean petitRoqueEnTrichant(int Joueur, int incX, int incY, boolean PourVrai) {
		// Vérification des deux cases à droite du roi : si elles sont vides...
		if ((Jeu.Case(this.PosX+1, this.PosY).isCaseVide()) && 
			(Jeu.Case(this.PosX+2, this.PosY).isCaseVide())) {
			//... on autorise le petit roque : déplacement du roi
			if (super.Bouger(Joueur, incX, incY, PourVrai)) {
				System.out.println("Le roi s'est déplacé");
				// déplacement de la tour
				if (Joueur == 1) {
					Jeu.setPos(7, 0, new CaseVide(Jeu, 7, 0));
					Jeu.setPos(5, 0, new Tour(Jeu, Joueur, 5, 0));
					
					System.out.println("Tour blanche déplacée");
				}
				
				else {
					Jeu.setPos(7, 7, new CaseVide(Jeu, 7, 7));
					Jeu.setPos(5,7, new Tour(Jeu, Joueur, 5, 7));
					
					System.out.println("Tour noire déplacée");
				}
			}
			
			return true;
		}
		return false;
	}

	
	public String toString()	{ return (retCoulBlanc() == 1 ? "R" : "r");	}
	public Object clone() 		{ return super.clone();	}
}