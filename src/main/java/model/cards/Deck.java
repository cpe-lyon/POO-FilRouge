package model.cards;

import java.util.Collection;

import allShared.ICardsCollection;

/**
 * Objet qui fabrique et contient l'ensemble des cartes du Game
 * en fonction de la taille du Deck (32 ou 52 cartes)
 *
 * Il est capable d'exécuter les traitements communs à toutes 
 * les collections de cartes (ajouter, supprimer, mélanger, trier, etc.)
 * et en plus fabriquer un jeu de carte
 * 
 * @author francoise.perrin
 */
public class Deck extends AbstractCardsCollection {
	
	/**
	 * le constructeur fabrique un jeu de 32 ou de 52 cartes
	 * selon la taille du Deck passée en paramètre
	 * Tips : la méthode values prédéfinie sur les enum peut être utile...
	 * 
	 * Ex si deckSize = 32, le contenu du Deck est le suivant :
	 * 	[[7-Carreau, 7-Coeur, 7-Pique, 7-Trefle, 
	 * 	8-Carreau, 8-Coeur, 8-Pique, 8-Trefle, 
	 * 	9-Carreau, 9-Coeur, 9-Pique, 9-Trefle, 
	 * 	10-Carreau, 10-Coeur, 10-Pique, 10-Trefle, 
	 * 	Valet-Carreau, Valet-Coeur, Valet-Pique, Valet-Trefle, 
	 * 	Dame-Carreau, Dame-Coeur, Dame-Pique, Dame-Trefle, 
	 * 	Roi-Carreau, Roi-Coeur, Roi-Pique, Roi-Trefle, 
	 * 	As-Carreau, As-Coeur, As-Pique, As-Trefle]]
	 */
	public Deck(int deckSize) {
		super();
		int firstValue = deckSize == 52 ? 2 : 7;

		for(Rank rank : Rank.values()) {
			for(Suit suit: Suit.values()) {
				if (rank.getRank() >= firstValue) {
					cards.add(new Card (rank, suit));
				}
			}
		}

	}

	public Deck(Collection<Card> collection) {
		super(collection);
	}

	public Deck(ICardsCollection iCardsCollection) {
		super(iCardsCollection);
	}
}
