package model.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import allShared.ICardsCollection;

/**
 * Classe qui décrit les attributs et méthodes commun(e)s à toutes les Collections de cartes
 * elle implémente l'interface Iterable pour être parcourue par un Iterator [Design pattern]
 * 
 * Elle s'appuie sur les méthodes de sa Collection enveloppée (ici List)  
 * enrichies des méthodes shuffle(), max() et iterator()
 * [On peut la voir comme un adapter de List - Design Pattern]
 * 
 * [Question du Carnet de Bord : qu'est-ce qui justifie l'existence de cette classe ?
 * La réponse n'est pas "... factoriser le code commun à ses classes dérivées" 
 * (même si cette phrase est néanmoins vraie ;-)) ]
 * 
 * @author francoise.perrin
 */
public abstract class AbstractCardsCollection implements ICardsCollection, Iterable<Card> {
	
	protected final List<Card> cards;

	public AbstractCardsCollection() {
		super();
		cards = new ArrayList<Card>();
	}
	
	public AbstractCardsCollection(Collection<Card> collection) {
		super();
		cards = new ArrayList<Card>(collection);
	}

	public AbstractCardsCollection(ICardsCollection iCardsCollection) {
		this( ( (AbstractCardsCollection) iCardsCollection).cards);
	}

	
	@Override
	public final Card removeTopCard() {
		Card card = null;
		if(!cards.isEmpty()) {
			card = cards.remove(0);
		}
		return card;
	}
	
	@Override
	public final Card removeCard(int index) {
		Card card = null;
		if(!cards.isEmpty() && index >= 0 && index < cards.size()) {
			card = cards.remove(index);
		}
		return card;
	}

	@Override
	public final void addCard(Card pc) {
		cards.add(pc);
	}

	@Override
	public final boolean isEmpty() {
		return cards.isEmpty();
	}
	
	@Override
	public final void clear() {
		cards.clear();
	}
	
	@Override
	public final int size() {
		return cards.size();
	}
	
	/**
	 * tri les cartes selon l'ordre naturel  
	 * (induit par méthode compareTo() de Card)
	 */
	@Override
	public void sort() {
		Collections.sort(cards);
	}
	
	/**
	 * tri les cartes selon l'ordre géré par le Comparator 
	 * passé en argument  
	 * (induit par méthode compare() de )
	 */
	@Override
	public void sort(Comparator<Card> comparator) {
		Collections.sort(cards, comparator);
	}
	
	/**
	 * Mélange les cartes de manière aléatoire
	 * Ecrivez et testez cette méthode de 2 manières :
	 *  1 - en utilisant la méthode native shuffle() de la classe Collections
	 *  2 - en utilisant la méthode swap() et un nombre aléatoire (Random)  
	 */
	@Override
	public final void shuffle() {
		
		/* 1ère méthode	 
		Random random = new Random();
		for (int i = 0; i < cards.size(); ++i) {
			Collections.swap(cards, i, random.nextInt (cards.size()));
		}
		*/
		
		/* 2ème méthode  */
		Collections.shuffle(cards);
	}

	@Override
	public final Card max() {
		return  Collections.max(cards);
	}
	
	@Override
	public final Card max(Comparator<Card> comparator) {
		return  Collections.max(cards, comparator);
	}
	
	@Override
	public String toString() {
		return "[" + cards + "]";
	}

	/*
	 * Illustration du Design Pattern Iterator
	 * et des classes anonymes
	 * 
	 * [ Cette partie du code sera utile à partir de l'atelier 2
	 * Ce n'est pas grave si vous ne la comprenez pas ...]
	 * 
	 */
	@Override
	public final Iterator<Card> iterator() {
		
		return new Iterator<Card>() {
			Iterator<Card> it =  cards.iterator();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Card next() {
				return it.next();
			}
			
		};
	}

}
