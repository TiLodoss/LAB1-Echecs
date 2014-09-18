package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe permet d'implanter une Reine dans un jeu d'échec.
  * @see ca.uqac.pat.Echec
  */
public class Reine extends Piece implements Cloneable{
	/**
	 * Constructeur standard
	 * @param J le plateau de jeu
	 * @param Coul 1: blanc, -1: noir
	 * @param PX : no colonne
	 * @param PY : no ligne
	 */
	public Reine (Plateau J, int Coul, int PX, int PY){
		super (J, 8, Coul, PX, PY);
	}

	public String toString()	{ return (retCoulBlanc() == 1 ? "D" : "d");	}
	public Object clone() 		{ return super.clone();	}
}



