package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe définit les tours, qui ne font que des déplacement en ligne droite.
  * @see ca.uqac.pat.Echec
  */
public class Tour extends Piece{
/**
  * Constructeur standard.
  */
	public Tour (Plateau J, int Coul, int PX, int PY){
		super (J, 5, Coul, PX, PY);
	}

/**
  * Il y a ici une validation spécifique pour ne permettre que les mouvements droits.
  * @param incX Déplacment demandé en X.
  * @param incY Déplacement demandé en Y.
  * @return Vrai si le déplacement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger (int Joueur, int incX, int incY, boolean PourVrai){
		if ((incX == 0 && incY != 0) || (incX != 0 && incY == 0))
			return super.Bouger(Joueur, incX, incY, PourVrai);
		else
			return false;
	}
	
	public String toString()	{ return (retCoulBlanc() == 1 ? "T" : "t");	}
	public Object clone() 		{ return super.clone();	}
}