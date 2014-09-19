package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe définit les fous, qui ne font que des déplacements en diagonale.
  * @see ca.uqac.pat.Echec
  */
public class Fou extends Piece implements Cloneable{
/**
 * Constructeur standard
 * @param J le plateau de jeu
 * @param Coul 1: blanc, -1: noir
 * @param PX : no colonne
 * @param PY : no ligne
 */
	public Fou (Plateau J, int Coul, int PX, int PY){
		super (J, 3, Coul, PX, PY);
	}

/**
  * Il y a ici une validation spécifique pour ne permettre que les 
  * mouvements en diagonale.
  * @param incX Déplacment demandé en X.
  * @param incY Déplacement demandé en Y.
  * @return Vrai si le déplacement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger (int Joueur, int incX, int incY, boolean PourVrai){
		if (Math.abs(incX) == Math.abs (incY))
			return super.Bouger(Joueur, incX, incY, PourVrai);
		else
			return false;
	}
	
	
	public String toString()	{ return (retCoulBlanc() == 1 ? "F" : "f");	}
	public Object clone() 		{ return super.clone();	}
}