package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe permet représenter dans le jeu une case vide.
  * @see ca.uqac.pat.Echec
  */
public class CaseVide extends Piece implements Cloneable{
/**
  * Constructeur par défaut.
  */
	public CaseVide(Plateau J, int PX, int PY){
		super (J, PX, PY);
	}
	
	
	public CaseVide(CaseVide original){
		super(original);
	}

/**
  * La méthode Bouger de Piece est ici redéfinie pour interdire de bouger 
  * une case vide.
  * @return Toujours faux.
  */
	public boolean Bouger (int Bid1, int Bid2, boolean PourVrai){
		return false;				//On ne peut jouer une case vide
	}
	
	
	public String toString()	{ return " ";			}
	public Object clone() 		{ return super.clone();	}
}
