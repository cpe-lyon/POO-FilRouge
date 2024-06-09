package model.games;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import allShared.ICardsCollection;
import allShared.IGame;
import allShared.IGameEvaluator;
import model.cards.Card;
import model.cards.Rank;
import model.cards.Suit;
import model.player.Player;


/**
 * Classe qui décrit les attributs et méthodes commun(e)s à les types de Bataille
 * <ul>
 *   <li> les cartes sont distribuées 1 par 1 alternativement à chaque joueur
 *   <li> un joueur joue toujours la carte au sommet de sa main (il la retourne et la joue) 	
 * </ul>
 	* 
 * Il existe 2 classes dérivées qui utilisent des évaluateurs de plis différents 
 * 
 * @author francoise.perrin
 */
public abstract class AbstractWarGame extends AbstractGame implements IGame {

	public AbstractWarGame(List<String> playersNames,ICardsCollection deck) {
		super(playersNames, deck);
	}

	/*
	 * La méthode d'évaluation d'un pli est différente selon le type de jeu de Bataille
	 */
	protected abstract IGameEvaluator getGameEvaluator();

	/*
	 * La méthode de distribution des cartes est différente selon le type de jeu
	 * 1 par 1 pour la Bataille
	 */
	protected final void dealCardsFromDeck(int nbCards) {
	
			this.deck.shuffle();
			
			for (int i=0; i < nbCards ; i++) {
				for (Player player : this.players) {
					player.addCardToHand(this.deck.removeTopCard());
				}	
			}
		}

	/*
	 * Spécifique à chaque jeu
	 * A la bataille, le joueur prend toujours sa 1ère carte   
	 */
	protected final Map<String, Integer> ChooseCardsToPlay() {
		Map<String, Integer> whichCardArePlayed = new TreeMap<String, Integer>();

		for(Player player : this.players) {
			whichCardArePlayed.put(player.getName(), 0);
		}
		return whichCardArePlayed;
	}

	/*
	 * La méthode d'organisation de la main du joueur est différente selon le type de jeu
	 * Ici, Une fois que la main du joueur est vide, il reprend les cartes qu'il a gagnées
	 */
	protected final void organizePlayerHand(Player player) {
		if (player.isHandEmpty() && !player.isTrickPileEmpty()) {
			player.addWonCardsBackToHand();
		}
	}
	
	/*
	 * La méthode de test de la fin du jeu est différente selon le type de jeu
	 * A la bataille, le jeu est fini quand 1 joueur a gagné toutes les cartes   
	 * Au delà de retourner si le Game a été gagné, cette méthode
	 * "taggue" le vainqueur du Game
	 */
	@Override
	public final boolean isGameEnd() {
		boolean isGameEnd = false;

		for (Player player : this.players) {
			if (player.hasWonAllCards(this.initDeckSize)) {
				player.setGameWinner(true);
				isGameEnd = true;
				break;
			}
		}
		return isGameEnd;
	}

}
