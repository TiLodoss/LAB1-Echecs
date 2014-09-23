package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe permet d'implanter des pions au échec.
  * @see ca.uqac.pat.Echec
  */
public class Pion extends Piece implements Cloneable{
	/**
	 * Constructeur standard
	 * @param J le plateau de jeu
	 * @param Coul 1: blanc, -1: noir
	 * @param PX : no colonne
	 * @param PY : no ligne
	 */
	public Pion(Plateau J, int Coul, int PX, int PY){
		super (J, 1, Coul, PX, PY);
	}

/**
  * Il y a ici une validation spécifique pour ne permettre que les 
  * mouvement d'avant ou en diagonal pour une prise, et cela d'une seule case.
  * @param incX Déplacment demandé en X.
  * @param incY Déplacement demandé en Y.
  * @return Vrai si le déplacement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger(int Joueur, int incX, int incY, boolean PourVrai){
		// DEBUT de modif : prise en passant et pion avance 2 cases
		// Si avance de 2 et dans la bonne direction
		if(Math.abs(incY) == 2 && incX == 0 && Math.abs(incY+CoulBlanc) == 3) {
			if (Jeu.Case(PosX, PosY+incY).isCaseVide() &&	// vide a l'arrivee
				Jeu.Case(PosX, PosY+incY).isCaseVide() &&	// vide juste devant
				!dejaBouge){
					// Si le pion bouge on met un fantome (PRISE EN PASSANT)
					if(deplacePion(Joueur, incX, incY, PourVrai)) {
						if(PourVrai) setPionFantome(Joueur);
						return true;
					} else {
						return false;
					}
			}
		}
		// FIN de modif
		
		if (incY == CoulBlanc)			//Si c'est la bonne direction
			if (incX == 0)				// s'il avance
				{
					if (Jeu.Case(PosX, PosY+incY).isCaseVide())	//vide devant
						return deplacePion(Joueur, incX, incY, PourVrai);
				}
			else						// s'il mange
				if (Math.abs(incX) == 1)//S'il va en Dia (manger une piece)
					if (!Jeu.Case(PosX + incX, PosY + incY).isCaseVide())
						return deplacePion(Joueur, incX, incY, PourVrai);
					else
					{	// AJOUT pour savoir si on mange un pion fantome
						CaseVide fantome = (CaseVide) Jeu.Case(PosX + incX, PosY + incY);
						if(fantome.isPionFantome() == -Joueur)	// si pion fantome adverse
						{
							if(deplacePion(Joueur, incX, incY, PourVrai)) {
								// Case vide sur le pion adverse
								Jeu.setPos(PosX, PosY-CoulBlanc, 
										new CaseVide(Jeu, PosX, PosY-CoulBlanc));
								return true;
							}
							return false;
						}
					}
		return false;
	}
	

	// AJOUT d'une méthode : premier déplacement (garder à jour la variable dejaBouge)
	private boolean deplacePion(int joueur, int incX, int incY, boolean pourVrai) {
		boolean bougeMtn;
		
		bougeMtn = super.Bouger(joueur, incX, incY, pourVrai);
		if(pourVrai && bougeMtn)
			dejaBouge = true;
		return bougeMtn;
	}

	// AJOUT d'une méthode : prise en passant (crée un pion fantome dans la case vide)
	private void setPionFantome(int joueur) {
		Jeu.setPos(PosX,
				   PosY-CoulBlanc,
				   new CaseVide(Jeu, PosX, PosY-CoulBlanc, new Pion(Jeu, joueur, 0, 0))
		);
	}

	public String toString()	{ return (retCoulBlanc() == 1 ? "P" : "p");	}
	public Object clone() 		{ return super.clone();	}
}
