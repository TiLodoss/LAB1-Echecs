package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe définit les cavaliers, qui ne font que des déplacement en L.
  * @see ca.uqac.pat.Echec
  */
public class Cavalier extends Piece implements Cloneable{
/**
  * Constructeur standard.
  */
	public Cavalier (Plateau J, int Coul, int PX, int PY){
		super (J, 2, Coul, PX, PY);
	}

/**
  * Il y a ici une validation spécifique pour ne permettre que les mouvements en L.
  * @param incX Déplacment demandé en X.
  * @param incY Déplacement demandé en Y.
  * @param pourVrai si vrai le coup est joué, si faux le coup n'est pas joué.
  * Utilisé par les intelligences artificielles.
  * @return Vrai si le déplacement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger (int Joueur, int incX, int incY, boolean pourVrai)
	{
		if (Joueur != super.retCoulBlanc()) return false;
			
		if (Math.abs(incX) + Math.abs(incY) == 3 && 
			incX != 0 && 
			incY != 0 &&
			Jeu.Case(PosX + incX, PosY+incY).retCoulBlanc() != CoulBlanc && 
			PosX + incX >= 0 && 
			PosX + incX < 8 && 
			PosY + incY >= 0 && 
			PosY + incY < 8)
		{
			if (pourVrai)
			{
				Jeu.setPos(PosX, PosY, new CaseVide (Jeu, PosX, PosY));

				PosX = PosX + incX;
				PosY = PosY + incY;
	
				Jeu.setPos(PosX, PosY, this);
			}
			return true;
		}
		return false;
	}
	
	
	public String toString()	{ return (retCoulBlanc() == 1 ? "C" : "c");	}
	public Object clone() 		{ return super.clone();	}
}