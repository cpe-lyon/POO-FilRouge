package model.cards;

import allShared.ICard;

/**
 * Cette classe peut être vue comme un Proxy ou un Adapter [Design Pattern] de la classe Card
 * Ses instances sont utilisées par la View qui
 * n'a accès qu'aux "Getters" de l'objet encapsulé
 * 
 * Cette classe restreint donc le nombre de fonctionnalités existantes de l'objet enveloppé
 * et lui délègue le soin de réaliser les traitements
 * 
 * @author francoise.perrin
 */
public class CardRender implements ICard, Comparable<CardRender> {
	private final Card card;
	
	public CardRender(Card card) {
		super();
		this.card = card;
	}

	@Override
	public final Rank getRank() {
		return card.getRank();
	}

	@Override
	public final Suit getSuit() {
		return card.getSuit();
	}
	
	@Override
	public final boolean isRevealed() {
		return card.isRevealed();
	}
	
	@Override
	public int compareTo(CardRender o) {
		return card.compareTo(o.card);
	}
// regénérer equals et hashcode avec IDE
	@Override
	public int hashCode() {
		return card.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return card.equals(obj);
	}

	@Override
	public String toString() {
		return card.toString();
	}
}

