package model.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import allShared.ICardsCollection;


/**
 * Objet qui contient l'ensemble des cartes de la main d'un joueur
 * 
 * Il est capable d'exécuter les traitements communs à toutes 
 * les collections de cartes (ajouter, supprimer, mélanger, trier, etc.)
 * et en plus révéler, cacher, jouer une carte
 * 
 * @author francoise.perrin
 */
/* Version Atelier 3 */

public class Hand extends AbstractCardsCollection {

	public Hand() {
		super();
	}

	public Hand(Collection<Card> collection) {
		super(collection);
	}

	public Hand(ICardsCollection iCardsCollection) {
		super(iCardsCollection);
	}
	
	/**
	 * @param index
	 * @return la carte à jouer si elle existe
	 * Supprime la carte de la liste
	 */
	public final Card playCard(int index) {

		Card card = null;

			if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
				card = cards.remove(index);
				card.reveale();	
			}
		return card;
	}

	/**
	 * @param index
	 * @return true si la carte existe 
	 */
	public final boolean revealeCard(int index) {

		Card card = null;

		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
			card = cards.get(index);
			card.reveale();	
		}
		return card != null ? true : false;
	}

	/**
	 * @param index
	 * @return true si la carte existe 
	 */
	public final boolean hideCard(int index) {

		Card card = null;

		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
			card = cards.get(index);
			card.hide();	
		}
		return card != null ? true : false;
	}


}

///* Version base Elèves */
///**
// * @author francoise.perrin
// * Objet qui contient l'ensemble des cartes de la main d'un joueur
// * 
// * Il est capable d'exécuter les traitements communs à toutes 
// * les collections de cartes (ajouter, supprimer, mélanger, trier, etc.)
// * et en plus révéler, cacher, jouer une carte
// * 
// */
//public class Hand implements ICardsCollection {
//
//	private final List<Card> cards; /* ToChange Atelier2 */
//	
//	/*
//	 * ToChange Atelier2
//	 */
//	public Hand() {
//		super();
//		cards = new ArrayList<Card>();
//	}
//	
//	/*
//	 * ToChange Atelier2
//	 */
//	public Hand(Collection<Card> collection) {
//		super();
//		cards = new ArrayList<Card>(collection);
//	}
//	
//	/*
//	 * ToChange Atelier2
//	 */
//	public Hand(ICardsCollection iCardsCollection) {
//		super();
//		cards = null;
//	}
//
//	@Override
//	public final void addCard(Card pc) {
//		cards.add(pc);
//	}
//
//	@Override
//	public final Card removeTopCard() {
//		Card card = null;
//		if(!cards.isEmpty()) {
//			card = cards.remove(0);
//		}
//		return card;
//	}
//	
//	@Override
//	public final Card removeCard(int index) {
//		Card card = null;
//		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
//			card = cards.remove(index);
//		}
//		return card;
//	}
//	
//	@Override
//	public final boolean isEmpty() {
//		return cards.isEmpty();
//	}
//	
//	@Override
//	public final void clear() {
//		cards.clear();
//	}
//	
//	@Override
//	public final int size() {
//		return cards.size();
//	}
//	
//	@Override
//	public String toString() {
//		return "[" + cards + "]";
//	}
//
//	/**
//	 * @param index
//	 * @return la carte à jouer si elle existe
//	 * Supprime la carte de la liste
//	 */
//	public final Card playCard(int index) {
//		
//		Card card = null;
//		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
//			card = cards.remove(index);
//			card.reveale();	
//		}
//		return card;
//	}
//	
//	/**
//	 * @param index
//	 * @return true si la carte existe 
//	 */
//	public final boolean revealeCard(int index) {
//	 
//		Card card = null;
//	
//		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
//			card = cards.get(index);
//			card.reveale();	
//		}
//		return card != null ? true : false;
//	}
//	
//	/**
//	 * @param index
//	 * @return true si la carte existe 
//	 */
//	public final boolean hideCard(int index) {
//		 
//		Card card = null;
//	
//		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
//			card = cards.get(index);
//			card.hide();	
//		}
//		return card != null ? true : false;
//	}
//
//
//@Override
//public void sort() {
//	Collections.sort(cards);
//}
//
//@Override
//public void sort(Comparator<Card> comparator) {
//	Collections.sort(cards, comparator);
//}
//
//
///**
// * Mélange les cartes de manière aléatoire
// * Ecrivez et testez cette méthode de 2 manières :
// *  1 - en utilisant la méthode native shuffle() de la classe Collections
// *  2 - en utilisant la méthode swap() et un nombre aléatoire (Random)  
// */@Override
//public final void shuffle() {
//	/* 1ère méthode	 
//	Random random = new Random();
//	for (int i = 0; i < cards.size(); ++i) {
//		Collections.swap(cards, i, random.nextInt (cards.size()));
//	}
//	*/
//	
//	/* 2ème méthode  */
//	Collections.shuffle(cards);
//}
//
//@Override
//public final Card max() {
//	return  Collections.max(cards);
//}
//
//@Override
//public final Card max(Comparator<Card> comparator) {
//	return  Collections.max(cards, comparator);
//}
//
//	/*
//	 * Illustration du Design Pattern Iterator
//	 * et des classes anonymes
//	 * 
//	 * [ Cette partie du code sera utile à partir de l'atelier 2
//	 * Ce n'est pas grave si vous ne la comprenez pas ...]
//	 * 
//	 */
//	@Override
//	public final Iterator<Card> iterator() {
//		
//		return new Iterator<Card>() {
//			Iterator<Card> it =  cards.iterator();
//			@Override
//			public boolean hasNext() {
//				return it.hasNext();
//			}
//
//			@Override
//			public Card next() {
//				return it.next();
//			}
//			
//		};
//	}
//
//	
//}