package ca.uqac.pat.echec.pieces;


import ca.uqac.pat.echec.Plateau;

/**
 * Cette classe est parente de toutes les pièces.  Elle gère les déplacements
 * de même que l'affichage de la grille
 * @see ca.uqac.pat.Echec
 */
abstract public class Piece implements Cloneable
{
	private int Valeur;	
	private boolean dejaBouge;

	protected 	Plateau 	Jeu;
	protected 	int 		CoulBlanc;
	protected 	int 		PosX, PosY;

	/**
	 * Constructeur minimal d'une Pièce, utilisé par CaseVide.
	 * @param J C'est le plateau de jeu sur lequel la Piece est posée.
	 * @param PX C'est la position en abscisse dans le tableau.
	 * @param PY C'est la position en ordonnée dans le tableau.
	 */
	public Piece(Plateau J, int PX, int PY){		//C'est une case vide !!!
		this(J, 0, 0, PX, PY);
	}

	/**
	 * Constructeur par copie.
	 * @param original l'objet à copier.
	 */
	public Piece(Piece original){
		this(	original.Jeu, 
				original.Valeur, 
				original.CoulBlanc, 
				original.PosX, 
				original.PosY);
	}
	
	
	/**
	 * Constructeur complet d'une Pièce.
	 * @param J C'est le plateau de jeu sur lequel la Piece est posé.
	 * @param V C'est la valeur de la pièce ( 0 : case vide )
	 * @param CB C'est la couleur de la pièce ( 1: Blanc, -1:Noir )
	 * @param PX C'est la position en abscisse dans le tableau.
	 * @param PY C'est le position en ordonnée dans le tableau.
	 */
	public Piece (Plateau J, int V, int CB, int PX, int PY){
		Jeu 		= J;
		Valeur 		= V;
		CoulBlanc 	= CB;				// 1 = Blanc, -1 = noir, 0 = vide
		PosX 		= PX;
		PosY 		= PY;
	}

	/**
	 * C'est la méthode qui permet de bouger une piece de (incX, incY), si 
	 * le chemin est libre.
	 * @param incX Valeur du déplacement horizontal.
	 * @param incY Valeur du déplacement vertical.
	 * @return vrai si la voie est libre et l'arrivée vide ou ennemie, faux sinon
	 */
	public boolean Bouger(int Joueur, int incX, int incY, boolean PourVrai)
	{
		if (CoulBlanc != Joueur) return false;		// si pas ta pièces
		if(incX != 0 &&  incY != 0 && Math.abs(incX) != Math.abs(incY))
			return false;							// pas droit ni diag

		if (PosX + incX >= 0 && PosX + incX < 8 && 	// si dans le jeu
			PosY + incY >= 0 && PosY + incY < 8)
		{
			if (CheminVide(incX, incY))
			{
				if (PourVrai)
				{
					Jeu.setPos(PosX, PosY, new CaseVide (Jeu, PosX, PosY));

					PosX = PosX + incX;
					PosY = PosY + incY;

					Jeu.setPos(PosX, PosY, this);
				}
				return true;
			}
		}
		else
			System.out.println ("Hors Jeu");
		return false;
	}

	/**	
	 *	Fonction de vérification qui vérifie si une case est vide, sans pièce.
	 * @return Vrai si la case est vide, faux sinon. 
	 */
	public boolean isCaseVide()	{	return (Valeur == 0);	}

	/**
	 * Encapsulateur de l'attribut CoulBlanc.
	 * @return La coule de la pièce (1 si blanc, -1 si noir, 0 si vide)
	 */
	public int retCoulBlanc()	{	return CoulBlanc;		}

	/**
	 * Affichage de base.
	 */
	public void Afficher() {	System.out.print(this);	}
	
	
	private boolean CheminVide (int incX, int incY){
		int DeltaY = (incY != 0 ? incY / Math.abs (incY) : 0);
		int DeltaX = (incX != 0?  incX / Math.abs (incX) : 0);

		int y = PosY + DeltaY;
		int x = PosX + DeltaX;
		while (!(y == PosY+incY) || !(x == PosX + incX))
		{
			if (Jeu.Case(x,y).retCoulBlanc() != 0) return false;
			y = y + DeltaY;
			x = x + DeltaX;
		}

		if (CoulBlanc == Jeu.Case(PosX+incX,PosY+incY).retCoulBlanc()) 
			return false;						// peut pas manger ma couleur
			
		return true;			
	}
	
	
	public Object clone() { 
		try{
			return super.clone();
		}
		catch (CloneNotSupportedException e){
			System.out.println(e);
		}
		return null;
	}
} 