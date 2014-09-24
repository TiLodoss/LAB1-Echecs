package ca.uqac.pat.echec.pieces;

import ca.uqac.pat.echec.Plateau;

/**
  * Cette classe permet repr�senter dans le jeu une case vide.
  * @see ca.uqac.pat.Echec
  */
public class CaseVide extends Piece implements Cloneable{
	
	// AJOUT (prise en passant)
	private Pion fantome;
/**
  * Constructeur par d�faut.
  */
	public CaseVide(Plateau J, int PX, int PY){
		super (J, PX, PY);
	}
	
	public CaseVide(CaseVide original){
		super(original);
	}

	// AJOUT d'un constructeur avec un pion (prise en passant)
	public CaseVide(Plateau J, int PX, int PY, Pion fantome){
		super (J, PX, PY);
		this.fantome = fantome;
	}
	
/**
  * La m�thode Bouger de Piece est ici red�finie pour interdire de bouger 
  * une case vide.
  * @return Toujours faux.
  */
	public boolean Bouger (int Bid1, int Bid2, boolean PourVrai){
		return false;				//On ne peut jouer une case vide
	}
	
	// AJOUT (prise en passant)
	/**
	 * Encapsuleur
	 * @return 0 si la case n'es pas un pion fantome et la couleur du pion sinon.
	 */
	public int isPionFantome() {
		if(fantome != null)
			return fantome.CoulBlanc;
		else
			return 0;
	}

	
	public String toString()	{ return " ";			}
	public Object clone() 		{ return super.clone();	}
}
