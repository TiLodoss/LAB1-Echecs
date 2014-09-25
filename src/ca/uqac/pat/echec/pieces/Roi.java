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
		if (Math.abs(incX) == 2 && Math.abs(incY) == 0) {
			// On v�rifie que le roi n'a pas d�j� boug�
			if (!dejaBouge) return petitRoqueEnTrichant(Joueur, incX, incY, PourVrai);
			else return false;
		}
				
		else if (Math.abs(incX) + Math.abs (incY) <= 2 ) {
			dejaBouge = true;
			return super.Bouger(Joueur, incX, incY, PourVrai);
		}
		
		
		
		else return false;
	}
	
	// AJOUT : m�thode pour le petit roque en trichant
	private boolean petitRoqueEnTrichant(int Joueur, int incX, int incY, boolean PourVrai) {
		// V�rification des deux cases � droite du roi : si elles sont vides...
		if ((Jeu.Case(this.PosX+1, this.PosY).isCaseVide()) && 
			(Jeu.Case(this.PosX+2, this.PosY).isCaseVide())) {
			//... on autorise le petit roque : d�placement du roi
			if (super.Bouger(Joueur, incX, incY, PourVrai)) {
				System.out.println("Le roi s'est d�plac�");
				// d�placement de la tour
				if (Joueur == 1) {
					Jeu.setPos(7, 0, new CaseVide(Jeu, 7, 0));
					Jeu.setPos(5, 0, new Tour(Jeu, Joueur, 5, 0));
					
					System.out.println("Tour blanche d�plac�e");
				}
				
				else {
					Jeu.setPos(7, 7, new CaseVide(Jeu, 7, 7));
					Jeu.setPos(5,7, new Tour(Jeu, Joueur, 5, 7));
					
					System.out.println("Tour noire d�plac�e");
				}
			}
			
			return true;
		}
		return false;
	}

	
	public String toString()	{ return (retCoulBlanc() == 1 ? "R" : "r");	}
	public Object clone() 		{ return super.clone();	}
}